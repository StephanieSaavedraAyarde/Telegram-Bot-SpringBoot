package bo.edu.ucb.chatbot.dao;

import bo.edu.ucb.chatbot.dto.ActorFilm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActorFilmDao {
    private DataSource dataSource;

    @Autowired
    public ActorFilmDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<ActorFilm> findByTitleActor(String firstname, String lastname, String title) {
        List<ActorFilm> result = new ArrayList<>();
        String query = "SELECT a.actor_id"+
                        ", a.first_name"+
                        ", a.last_name"+
                        ", f.film_id"+
                        ", f.title"+
                        ", f.description"+
                        ", f.length"+
                        " FROM  actor a" +
                        "   LEFT JOIN film_actor fa ON (a.actor_id = fa.actor_id)" +
                        "   LEFT JOIN film f ON (fa.film_id = f.film_id)" +
                        " WHERE " +
                        "   UPPER(a.first_name) LIKE (?)" +
                        "   AND UPPER(a.last_name) LIKE (?)" +
                        "   AND UPPER(f.title) LIKE (?)";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
            ) {
            
            pstmt.setString(1, "%"+firstname.toUpperCase()+ "%");
            pstmt.setString(2, "%"+lastname.toUpperCase()+ "%");
            pstmt.setString(3, "%"+title.toUpperCase()+ "%");
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                ActorFilm actorFilm = new ActorFilm();
                //Actor
                actorFilm.setActorId(rs.getInt("actor_id"));
                actorFilm.setFirst_name(rs.getString("first_name"));
                actorFilm.setLast_name(rs.getString("last_name"));
                //Film
                actorFilm.setFilmId(rs.getInt("film_id"));
                actorFilm.setTitle(rs.getString("title"));
                actorFilm.setDescription(rs.getString("description"));
                actorFilm.setLength(rs.getInt("length"));
                result.add(actorFilm);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }   
}

