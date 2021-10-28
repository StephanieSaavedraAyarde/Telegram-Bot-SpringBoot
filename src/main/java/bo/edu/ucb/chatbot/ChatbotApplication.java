package bo.edu.ucb.chatbot;

import bo.edu.ucb.chatbot.api.FilmApi;
import bo.edu.ucb.chatbot.bl.FilmSearchBl;
import bo.edu.ucb.chatbot.bot.FilmBotHandler;
import bo.edu.ucb.chatbot.dao.FilmDao;
import bo.edu.ucb.chatbot.dto.Film;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.sql.DataSource;

@SpringBootApplication
public class ChatbotApplication {
/*  Se reemplaza por @Component y @Autowired
	@Bean
	public FilmDao filmDao(DataSource dataSource) {
		return new FilmDao(dataSource);
	}

	@Bean
	public FilmSearchBl filmSearchBl(FilmDao filmDao) {
		return new FilmSearchBl(filmDao);
	}

	@Bean
	public FilmApi filmApi(FilmSearchBl filmSearchBl) {
		return  new FilmApi(filmSearchBl);
	}
 */


	public static void main(String[] args) throws  Exception{
		ApplicationContext ctx = SpringApplication.run(ChatbotApplication.class, args);
		// Obtengo la clase con Spring porque use @Component @Autowired
		FilmBotHandler filmBotHandler = ctx.getBean(FilmBotHandler.class);


		try {
			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(filmBotHandler);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

//	@Value("${myapp.store}")
//	private String store;
//
//	@Bean
//	public PersonStore personStore() {
//		if("mysql".equals(store)) {
//			return new PersonMysql();
//		} else {
//			return new PersonFirebase();
//		}
//	}
//
//	@Bean
//	public PersonBL personBL(PersonStore personStore) {
//		return new PersonBL(personStore);
//	}
//
//	public static void main(String[] args) throws  Exception{
//		ApplicationContext context = SpringApplication.run(ChatbotApplication.class, args);
//		PersonBL personBL = context.getBean(PersonBL.class);
//		personBL.mainLogic();
//
////		PersonBL personBL = new PersonBL();
////		personBL.mainLogic();
//
///*
//		PersonStore personStore = (PersonStore) Class.forName(args[0]).newInstance();
//		//PersonStore personStore = new PersonMysql();
//		personStore.persistPerson(new Person("Juan", "Perez"));
// */
//	}

}
