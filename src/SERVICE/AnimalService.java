/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import MODEL.Animal;
import TECHNIQUE.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ISERVICE.IAnimalService;
import MODEL.Annonce;
import TECHNIQUE.Session;
import finalprojectskizanimaux.VenteController;

/**
 *
 * @author Admin
 */
public class AnimalService implements IAnimalService{
 Connection connection;

    public AnimalService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void create1(Animal a) {
String req="INSERT INTO animal (nom,age,type,race,poids,sexe,id_utilisateur) VALUES (?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement;


        try {
            preparedStatement = connection.prepareStatement(req);
           
            //preparedStatement.setDate(1,a.convert(a.getDate_accouplement()));
            
            preparedStatement.setString(1,a.getNom_animal());
            preparedStatement.setInt(2,a.getAge_animal());
            preparedStatement.setString(3,a.getType_animal());
            preparedStatement.setString(4,a.getRace_animal());
            preparedStatement.setFloat(5,a.getPoids_animal());
            preparedStatement.setString(6,a.getSexe());
            preparedStatement.setInt(7,a.getId_Utilisateur().getId_utilisateur());
            
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        
        
    }

    
    
    @Override
    public ObservableList<Animal> getAllAnimals() {
      ObservableList<Animal> animals=FXCollections.observableArrayList();

        String req = "select * from animal";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Animal animal = new Animal(resultSet.getInt(1), resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getFloat(6),resultSet.getString(7), new UserService().findById(resultSet.getInt(8)));

                animals.add(animal);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return animals;  
  
    }

    
 @Override
    public  Animal getAnimalbyId(int id) {
      Animal animal=null;
      
        String req = "select * from animal where id_animal =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                
                 animal = new Animal(resultSet.getInt("id_animal"),resultSet.getString("nom"),resultSet.getInt("age"),resultSet.getString("type"),resultSet.getString("race"),resultSet.getFloat("poids"),resultSet.getString("sexe"),new UserService().findById(resultSet.getInt("id_utilisateur")));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return animal;      
    }
    public Animal search(int v)
    {
         Animal animal=null;
      
        String req = "select * from animal where id_animal=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, v);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                
                 animal = new Animal(resultSet.getInt("id_animal"),resultSet.getString("nom"),resultSet.getInt("age"),resultSet.getString("type"),resultSet.getString("race"),resultSet.getFloat("poids"),resultSet.getString("sexe"),new UserService().findById(resultSet.getInt("id_utilisateur")));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return animal;      
    
        
    }
    
public  Animal getAnimalbyIdUser(int id) {
      Animal animal=null;
      
        String req = "select * from animal where id_utilisateur=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                
                 animal = new Animal(resultSet.getInt("id_animal"),resultSet.getString("nom"),resultSet.getInt("age"),resultSet.getString("type"),resultSet.getString("race"),resultSet.getFloat("poids"),resultSet.getString("sexe"),new UserService().findById(resultSet.getInt("id_utilisateur")));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return animal;      
    }
    @Override
    public void deleteAnimal(int id) {
    String req = "delete from animal where id_animal =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }     
    }

    @Override
    public void updateAnimal(Animal a) {
     String req = "update animal set nom=?,age=?,type=?,race=?,sexe=?,id_utilisateur=? where id_animal = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, a.getNom_animal());
            preparedStatement.setInt(2, a.getAge_animal());
            preparedStatement.setString(3,a.getType_animal());
             preparedStatement.setString(4, a.getRace_animal());
              preparedStatement.setString(5, a.getSexe());
            preparedStatement.setInt(6, a.getId_Utilisateur().getId_utilisateur());
            preparedStatement.setInt(7,a.getId_animal());

           

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
        
            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
  

     public ObservableList<String> displayallMyanimals(int id) {
        ObservableList<String> listanimal = FXCollections.observableArrayList();
        UserService userservice=new UserService();
        String req = "select * from animal where id_utilisateur=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               
                  Animal a=new Animal(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getFloat(6),resultSet.getString(7),userservice.findById(resultSet.getInt(8)));
                    listanimal.add(a.getNom_animal());
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listanimal;
    }
     public ObservableList<Animal> displayallMyanimals() {
        ObservableList<Animal> listanimal = FXCollections.observableArrayList();
        UserService userservice=new UserService();
        String req = "select * from animal where id_utilisateur=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, Session.LoggedUser.getId_utilisateur());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               
                  Animal a=new Animal(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getFloat(6),resultSet.getString(7),userservice.findById(resultSet.getInt(8)));
                    listanimal.add(a);
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listanimal;
    }
     public Animal findAnimalByName(String nom)
     {
         Animal a=new Animal();
         UserService userservice=new UserService();
          String req = "select * from animal where nom=? and id_utilisateur=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, nom);
            preparedStatement.setInt(2,Session.LoggedUser.getId_utilisateur());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               
                   a=new Animal(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getFloat(6),resultSet.getString(7),userservice.findById(resultSet.getInt(8)));
                   
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return a;
     }
}
