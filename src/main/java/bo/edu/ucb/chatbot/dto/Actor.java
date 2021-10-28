package bo.edu.ucb.chatbot.dto;

import java.util.Date;
import java.util.Objects;

public class Actor {
    private Integer actorId;
    private String first_name;
    private String last_name;
    private Date lastUpdate;
    private Integer filmId;
    private String title;
    private String description;
    private Integer length;
    private String lengthLabel;

    public Actor() {
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return actorId.equals(actor.actorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId);
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Time: " + lengthLabel + "\n" +
                "Actor: " + first_name +" "+ last_name;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        if (length != null) { // Algoritmo para convertir a horas minutos
            int hours = length / 60;
            int minutes = length % 60;
            if (hours > 0) {
                lengthLabel = hours + "h " + minutes + "m";
            } else {
                lengthLabel = minutes + "m";
            }

        }
        this.length = length;
    }

    public String getLengthLabel() {
        return lengthLabel;
    }
}
