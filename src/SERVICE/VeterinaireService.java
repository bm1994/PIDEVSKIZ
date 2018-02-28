/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import MODEL.Sujet;
import MODEL.User;
import MODEL.Veterinaire;
import MODEL.user_veto;
import TECHNIQUE.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class VeterinaireService {
    
    Connection connection;
Connection cnx;
    Statement ste;
    ResultSet rs;
    public VeterinaireService() {
            connection = DataSource.getInsatance().getConnection();
 try {
            cnx = DataSource.getInsatance().getConnection();
            ste = cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
       public List<User> list_demmande(int id_utilisateur) {
           
           Veterinaire v = veto_byid(id_utilisateur);
           
           return v.getList_user();
       }    
    
       
       
       public List<Integer> id_return (int id_utilisateur){
               List<Integer> listP = new ArrayList<Integer>();

        
          String req1 = "select * from user_veto where id_utilisateur = "+id_utilisateur;
          try{  
          rs = ste.executeQuery(req1);
            while (rs.next()) {
            	int v = rs.getInt(1);
                listP.add(v);
            }
          }catch(SQLException ex)
                    {ex.printStackTrace();
                    
                    }
               return listP;
       }
       
       
      public List<Sujet> list_Sujet(int id_utilisateur) {
           
      
        List<Sujet> listP = new ArrayList<Sujet>();
        List<Sujet> listP2 = new ArrayList<Sujet>();

        try {
            
                String req="" ;     
              
            for (int i=0;i<id_return(id_utilisateur).size();++i)
            {
             req = "select * from sujet where id_utilisateur ="+id_return(id_utilisateur).get(i);
            
             rs = ste.executeQuery(req);
            while (rs.next()) {
            	Sujet s = new Sujet(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                listP.add(s);
                
        }}
                for (int i=0;i<id_return(id_utilisateur).size();++i){
              listP2.add(listP.get(i));
                
                }

        }
     catch (SQLException ex) {
        Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return listP2;
       }    
    
    
    
     public List<User> Affichervetousr() {
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
    
    public List<Veterinaire> Affichervetousr_veto() {
       ObservableList<Veterinaire> listuser=FXCollections.observableArrayList();

        String req = "select * from utilisateur where role=? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,5);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Veterinaire user = new Veterinaire(resultSet.getString(10),resultSet.getString(11),resultSet.getInt(12),resultSet.getInt(7),resultSet.getString(2), resultSet.getString(1),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getInt(6),resultSet.getString(8),resultSet.getString(9));

                listuser.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listuser; 
    }
    
    
    
    public void ajouter_une_demande(int id_user,int id_veto){
    UserService s =new UserService();
    User u = s.findById(id_user);
    Veterinaire v = veto_byid(id_veto);
    v.getList_user().add(u);
    }
    
       public void ajouter_un_Sujet(int id_veto,Sujet s){
   
    Veterinaire v = veto_byid(id_veto);
    v.getList_sujet().add(s);
    }
    
    
    public Veterinaire veto_byid(int id) {
 Veterinaire user =null;
        String req = "select * from utilisateur where id_utilisateur=? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 user = new Veterinaire(resultSet.getString(10),resultSet.getString(11),resultSet.getInt(12),resultSet.getInt(7),resultSet.getString(2), resultSet.getString(1),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getInt(6),resultSet.getString(8),resultSet.getString(9));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
       return user;
    }
    
    
    
    
    

    public void AjouterVeterinaire(Veterinaire v) {
        String req = "insert into utilisateur (prenom,nom,adresse,telephone,email,role,login,motdepasse,nom_cabinet,adresse_cabinet,numero_cabinet) values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            
            preparedStatement.setString(1,v.getPrenom());
            preparedStatement.setString(2,v.getNom());
            preparedStatement.setString(3,v.getAdresse());
            preparedStatement.setInt(4,v.getTelephone());
            preparedStatement.setString(5,v.getEmail());
            preparedStatement.setInt(6,v.getRole());
            preparedStatement.setString(7,v.getLogin());
            preparedStatement.setString(8,v.getMotDePasse());
            preparedStatement.setString(9,v.getNom_cabinet());
            preparedStatement.setString(10,v.getAdresse_cabinet());
            preparedStatement.setInt(11,v.getNumero_cabinet());



            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
