package bo.edu.ucb.chatbot.bot;

import bo.edu.ucb.chatbot.bl.BotFilmSearchBl;
import bo.edu.ucb.chatbot.bl.BotActorSearchBl;
import bo.edu.ucb.chatbot.bl.BotActorFilmSearchBl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class FilmBotHandler extends TelegramLongPollingBot {

    private BotFilmSearchBl botFilmSearchBl;
    private BotActorSearchBl botActorSearchBl;
    private BotActorFilmSearchBl botActorFilmSearchBl;

    @Autowired
    public FilmBotHandler(BotFilmSearchBl botFilmSearchBl, 
                            BotActorSearchBl botActorSearchBl, 
                            BotActorFilmSearchBl botActorFilmSearchBl) {
        this.botFilmSearchBl = botFilmSearchBl;
        this.botActorSearchBl = botActorSearchBl;
        this.botActorFilmSearchBl = botActorFilmSearchBl;
    }

    @Override
    public String getBotUsername() {
        return  "ingSoft_tefi_bot";
    }

    @Override
    public String getBotToken() {
        return "2090600975:AAHz9ntBDhZd2W-7_rpstRp6-dsqb-IdiDA";
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if(update.hasMessage() && update.getMessage().hasText()){
            List<String> result = null;

            String chatId = update.getMessage().getChatId().toString();

            String msg = update.getMessage().getText();

            Boolean caso = msg.contains("@");
            System.out.println("CASO: "+caso);

            if(caso==false){
                String [] atributo = msg.split("=");
            
                if(atributo[0].equals("Title")){
                    result = botFilmSearchBl.processMessage((atributo[1])); 
                }

                if(atributo[0].equals("Actor")){
                    String value = atributo[1];
                    String[] fullname = value.split(" ");
                    result = botActorSearchBl.processMessage(fullname[0], fullname[1]);
                }
            }
            if(caso==true){
                String [] division = msg.split("@");

                String [] parte1 = division[0].split("=");
                String [] parte2 = division[1].split("=");

                if(parte1[0].equals("Title") && parte2[0].equals("Actor")){
                    String [] fullname = parte2[1].split(" ");
                    result = botActorFilmSearchBl.processMessage(fullname[0], fullname[1], parte1[1]);
                }

                else{
                    String [] fullname = parte1[1].split(" ");
                    result = botActorFilmSearchBl.processMessage(fullname[0], fullname[1], parte2[1]);
                } 
            }
            sendMessage(chatId, result);
        }
    }

    private void sendMessage( String chatId, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage( String chatId, List<String> messageText) {
        for ( String message: messageText) {
            sendMessage(chatId, message);
        }
    }
}
