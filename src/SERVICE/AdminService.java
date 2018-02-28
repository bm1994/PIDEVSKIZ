/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import ISERVICE.IAdmin;
import MODEL.Annonce;
import MODEL.User;
import TECHNIQUE.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class AdminService  implements IAdmin{
    Connection connection;

    public AdminService() {
                connection = DataSource.getInsatance().getConnection();

    }


    @Override
    public List<User> getAllUSER() {
       ObservableList<User> listuser=FXCollections.observableArrayList();

        String req = "select * from utilisateur where role=? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,3);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(7),resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getString(8));

                listuser.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listuser; 
    }

    @Override
    public List<User> getAllAssociation() {
        ObservableList<User> listuser=FXCollections.observableArrayList();

        String req = "select * from utilisateur where role=? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,2);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(7), resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5));

                listuser.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listuser; 
    }
    }
    

