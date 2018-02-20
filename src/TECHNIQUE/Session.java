/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TECHNIQUE;

import MODEL.User;
import SERVICE.UserService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author habib
 */
public class Session {
    
    public static UserService iuserService =new UserService();
    public  static User LoggedUser ;
    
    
 
    public Session() {
    }

   

    public User getLoggedUser() {
        return LoggedUser;
    }

    

    public  void setLoggedUser(User LoggedUser) {
        this.LoggedUser = LoggedUser;
    }

  
    
    /**
     *
     * @param login
     * @return
     * @throws SQLException
     */
    public User SetLoggedUser (String login ) throws SQLException{
       Connection connection;
         connection = DataSource.getInsatance().getConnection();
               
        String sql = "SELECT * FROM users WHERE login=? " ;
        
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, login);
        
        ResultSet resultSet = pst.executeQuery();
        if(resultSet.next()){
         LoggedUser = new User(); 
        LoggedUser.setId_utilisateur(resultSet.getInt("id_utilisateur") );  
        LoggedUser = iuserService.findById(LoggedUser.getId_utilisateur());
        }
        
       
        return LoggedUser;   
        
    }

}
