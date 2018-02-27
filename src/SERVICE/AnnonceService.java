/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import ISERVICE.IAnnonceService;
import MODEL.Annonce;
import TECHNIQUE.DataSource;
import TECHNIQUE.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class AnnonceService implements IAnnonceService{
Connection connection;

    public AnnonceService() {
        connection = DataSource.getInsatance().getConnection();
    }
    @Override
    public void create(Annonce a) {
        String req="INSERT INTO annonce (titre_annonce,description_annonce,date_annonce,photo_annonce,type_annonce,id_animal) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement;


        try {
            preparedStatement = connection.prepareStatement(req);
           
            //preparedStatement.setDate(1,a.convert(a.getDate_accouplement()));
            preparedStatement.setString(1,a.getTitre_annonce());
            preparedStatement.setString(2,a.getDescription());
            preparedStatement.setDate(3,a.convert(a.getDate_annonce()));
            preparedStatement.setString(4,a.getPhoto_annonce());
            preparedStatement.setString(5,a.getType_annonce());
            
            preparedStatement.setInt(6,a.getId_animal().getId_animal());
            
            
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        catch (ParseException ex) {   
         Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
     }   
        
    }

    @Override
    public ObservableList<Annonce> getAllAnnonces() {
     ObservableList<Annonce> annonces=FXCollections.observableArrayList();

        String req = "select * from annonce where type_annonce=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, "Vente");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
           //     System.out.println("from annonceservice:"+new AnimalService().getAnimalbyId(resultSet.getInt(7)).getId_animal());
            Annonce annonce = new Annonce(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),Annonce.convert(resultSet.getDate(4)),resultSet.getString(5),resultSet.getString(6),new AnimalService().getAnimalbyId(resultSet.getInt(7)),new AnimalService().getAnimalbyId(resultSet.getInt(7)).getId_animal());

             annonces.add(annonce);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return annonces; 
    }
    
     
    public ObservableList<Annonce> getAllAnnoncesAccouplement() {
     ObservableList<Annonce> annonces=FXCollections.observableArrayList();

        String req = "select * from annonce where type_annonce=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, "Accouplement");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
           //     System.out.println("from annonceservice:"+new AnimalService().getAnimalbyId(resultSet.getInt(7)).getId_animal());
            Annonce annonce = new Annonce(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),Annonce.convert(resultSet.getDate(4)),resultSet.getString(5),resultSet.getString(6),new AnimalService().getAnimalbyId(resultSet.getInt(7)),new AnimalService().getAnimalbyId(resultSet.getInt(7)).getId_animal());

             annonces.add(annonce);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return annonces; 
    }
    
     public ObservableList<Annonce> getAllAnnoncesAdoption() {
     ObservableList<Annonce> annonces=FXCollections.observableArrayList();

        String req = "select * from annonce where type_annonce=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, "Adoption");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
           //     System.out.println("from annonceservice:"+new AnimalService().getAnimalbyId(resultSet.getInt(7)).getId_animal());
            Annonce annonce = new Annonce(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),Annonce.convert(resultSet.getDate(4)),resultSet.getString(5),resultSet.getString(6),new AnimalService().getAnimalbyId(resultSet.getInt(7)),new AnimalService().getAnimalbyId(resultSet.getInt(7)).getId_animal());

             annonces.add(annonce);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return annonces; 
    }
    
    
    
        public ObservableList<Annonce> getAllAnnonces2(int i) {
     ObservableList<Annonce> annonces=FXCollections.observableArrayList();

        String req = "select * from annonce where type_annonce='Vente' and id_animal="+i;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
              Annonce annonce = new Annonce(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),new AnimalService().getAnimalbyId(resultSet.getInt(7)));

           annonces.add(annonce);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return annonces; 
    }

        
    @Override
    public Annonce getAnnoncebyId(int id) {
       Annonce annonce = null;
        String req = "select * from annonce where id_annonce =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               // annonce = new Annonce(resultSet.getInt("id_annonce"),resultSet.getString("titre_annonce"),resultSet.getString("description_annonce"),resultSet.getString("date_annonce"),resultSet.getString("photo_annonce"),resultSet.getString("type_annonce"),resultSet.getString("nom_animal"),resultSet.getInt("age_animal"),resultSet.getString("type_animal"),resultSet.getString("race_animal"),resultSet.getFloat("poids_animal"),resultSet.getString("sexe_animal"),resultSet.getInt("id_utilisateur"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return annonce;  
        
    }

    @Override
    public void deleteAnnonce(int id) {
     String req = "delete from annonce where id_annonce =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        
    }
    public void deleteAllAnimalAnnonce(int id_animal)
    {
        String req = "delete from annonce where id_animal =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,id_animal);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateAnnonce(Annonce a,int b) {
    String req = "update annonce set titre_annonce=?,description_annonce=?,date_annonce=?,photo_annonce=?,type_annonce=? where id_annonce =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, a.getTitre_annonce());
            preparedStatement.setString(2, a.getDescription());
           
            preparedStatement.setDate(3, a.convert(a.getDate_annonce()));
            preparedStatement.setString(4, a.getPhoto_annonce());
            preparedStatement.setString(5, a.getType_annonce());
            preparedStatement.setInt(6, b);
            
           

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
        
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ParseException ex) {
         Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);

    }
       
    }

    @Override
    
    public ObservableList<Annonce> getAnnoncebyType(String type) {
     ObservableList<Annonce> ListeAnnonce = FXCollections.observableArrayList();
            Annonce annonce = null;
        String req = "select * from annonce where type_annonce =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, type);
          
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
           annonce = new Annonce(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),Annonce.convert(resultSet.getDate(4)),resultSet.getString(5),resultSet.getString(6),new AnimalService().getAnimalbyId(resultSet.getInt(7)),new AnimalService().getAnimalbyId(resultSet.getInt(7)).getId_animal());
           ListeAnnonce.add(annonce);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListeAnnonce;
    }

    @Override
    public ObservableList<Annonce> getAnnoncebyIdType(int id_user, String type) {
      ObservableList<Annonce> ListeAnnonce = FXCollections.observableArrayList();
            Annonce annonce = null;
        String req = "select * from annonce where id_utilisateur=? AND type_annonce=? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id_user);
            preparedStatement.setString(2,type);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
          // annonce = new Annonce(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8),resultSet.getString(9),resultSet.getString(10),resultSet.getFloat(11),resultSet.getString(12),resultSet.getInt(13));
           ListeAnnonce.add(annonce);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListeAnnonce;
    }

    @Override
    public ObservableList<Annonce> getAnnoncebyIdUser(int id_user) {
         ObservableList<Annonce> ListeAnnonce = FXCollections.observableArrayList();
            Annonce annonce = null;
        String req = "select * from annonce where id_utilisateur=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id_user);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
          annonce = new Annonce(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),Annonce.convert(resultSet.getDate(4)),resultSet.getString(5),resultSet.getString(6),new AnimalService().getAnimalbyId(resultSet.getInt(7)),new AnimalService().getAnimalbyId(resultSet.getInt(7)).getId_animal());
           ListeAnnonce.add(annonce);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListeAnnonce;
    }
    
   /* public Annonce GetAnnonceProp(int id)
    {
        
            Annonce annonce = null;
        String req = "select * from annonce where id_utilisateur=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
          // annonce = new Annonce(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8),resultSet.getString(9),resultSet.getString(10),resultSet.getFloat(11),resultSet.getString(12),resultSet.getInt(13));
           ListeAnnonce.add(annonce);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListeAnnonce;
    } 
    }*/
    
   public Annonce getAnnoncebyIdUser1(String titre) throws SQLException {
            //Annonce annonce = null;
        String req = "select * from annonce where titre_annonce=?";
        PreparedStatement preparedStatement;
        
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, titre);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
         Annonce annonce = new Annonce(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),new AnimalService().getAnimalbyId(resultSet.getInt(7)));

            
             return annonce;
}  

    @Override
    public void updateAnnonce(Annonce a) {
      String req = "update annonce set titre_annonce=?,description_annonce=?,date_annonce=?,photo_annonce=?,type_annonce=? where id_annonce = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, a.getTitre_annonce());
            preparedStatement.setString(2, a.getDescription());
            preparedStatement.setDate(3, a.convert(a.getDate_annonce()));
            preparedStatement.setString(4, a.getPhoto_annonce());
            preparedStatement.setString(5, a.getType_annonce());
            preparedStatement.setInt(6,a.getId_annonce());
          

           

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
        
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ParseException ex) {
         Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);

    }

    }
   public ObservableList<Annonce> DisplayAllAnimalAnnonce(int id_animal) {
         ObservableList<Annonce> ListeAnnonce = FXCollections.observableArrayList();
            Annonce annonce = null;
        String req = "select * from annonce where id_animal=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id_animal);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
          annonce = new Annonce(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),Annonce.convert(resultSet.getDate(4)),resultSet.getString(5),resultSet.getString(6),new AnimalService().getAnimalbyId(resultSet.getInt(7)),new AnimalService().getAnimalbyId(resultSet.getInt(7)).getId_animal());
           ListeAnnonce.add(annonce);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListeAnnonce;
    }
    public ObservableList<Annonce> DisplayAllMyAnimalAnnonce() {
         ObservableList<Annonce> ListeAnnonce = FXCollections.observableArrayList();
            Annonce annonce = null;
            UserService us=new UserService();
        String req = "select * from animal where id_utilisateur=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, Session.LoggedUser.getId_utilisateur());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ListeAnnonce.addAll(DisplayAllAnimalAnnonce(resultSet.getInt(1)));
          /*annonce = new Annonce(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),Annonce.convert(resultSet.getDate(4)),resultSet.getString(5),resultSet.getString(6),new AnimalService().getAnimalbyId(resultSet.getInt(7)),new AnimalService().getAnimalbyId(resultSet.getInt(7)).getId_animal());
           ListeAnnonce.add(annonce);*/
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListeAnnonce;
    }
    }

    

    



