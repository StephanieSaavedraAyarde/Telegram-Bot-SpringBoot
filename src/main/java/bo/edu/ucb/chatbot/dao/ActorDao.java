package bo.edu.ucb.chatbot.dao;

import bo.edu.ucb.chatbot.dto.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActorDao {
    private DataSource dataSource;

    @Autowired
    public ActorDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Actor> findByActor(String firstname, String lastname) {
        List<Actor> result = new ArrayList<>();
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
                        "   UPPER( a.first_name) LIKE (?)" +
                        "   AND UPPER(a.last_name) LIKE (?)";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
            ) {
                
            pstmt.setString(1, "%"+firstname.toUpperCase()+ "%");
            pstmt.setString(2, "%"+lastname.toUpperCase()+ "%");
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                Actor actor = new Actor();
                //Actor
                actor.setActorId(rs.getInt("actor_id"));
                actor.setFirst_name(rs.getString("first_name"));
                actor.setLast_name(rs.getString("last_name"));
                //Film
                actor.setFilmId(rs.getInt("film_id"));
                actor.setTitle(rs.getString("title"));
                actor.setDescription(rs.getString("description"));
                actor.setLength(rs.getInt("length"));
                result.add(actor);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }   
}

