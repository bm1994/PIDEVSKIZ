/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import ISERVICE.UserInscriptionInterface;
import MODEL.User;
import TECHNIQUE.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author asus
 */
public class UserService  implements UserInscriptionInterface{
Connection connection;

    public UserService() {
    connection = DataSource.getInsatance().getConnection();

    }

    @Override
    public void AjouterUser(User u) {
        String req = "insert into utilisateur (prenom,nom,adresse,telephone,email,role,login,motdepasse) values (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            
            preparedStatement.setString(1,u.getPrenom());
            preparedStatement.setString(2,u.getNom());
            preparedStatement.setString(3,u.getAdresse());
            preparedStatement.setInt(4,u.getTelephone());
            preparedStatement.setString(5,u.getEmail());
            preparedStatement.setInt(6,u.getRole());
            preparedStatement.setString(7,u.getLogin());
            preparedStatement.setString(8,u.getMotDePasse());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public User findById(int id_utilisateur) {
User user = null;
        String req = "select * from users where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id_utilisateur);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt("id_utilisateur"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("adresse"),
                        resultSet.getInt("telephone"),
                        resultSet.getString("adresse"),
                        resultSet.getString("email"),
                        resultSet.getInt("role"),
                        resultSet.getString("login"),
                        resultSet.getString("motdepasse")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;    }
   
    }
    
    

