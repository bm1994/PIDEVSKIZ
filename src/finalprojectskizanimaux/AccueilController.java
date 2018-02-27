/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import TECHNIQUE.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AccueilController implements Initializable {

    @FXML
    private Button profilButton;
    @FXML
    private Button adoptionButton;
    @FXML
    private Button accouplementButton;
    @FXML
    private Button venteButton;
    @FXML
    private Button evenementButton;
    @FXML
    private Button associationButton;
    @FXML
    private Button ForumButton;
    @FXML
    private Button deconnexionButton;
    @FXML
    private Label ii;
    @FXML
    private Button PropreAnnoncebutton;
    @FXML
    private Button PropreAnimaux1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     ii.setText(Session.LoggedUser.getEmail());
    }    

    @FXML
    private void Adopter(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AdoptionCC.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) adoptionButton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
        
    }

    @FXML
    private void Accoupler(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AccouplementCC.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) accouplementButton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
    

    @FXML
    private void vendre(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Vente.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) venteButton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void Evenement(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Evenement.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) evenementButton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void Association(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Association.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) associationButton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void afficherProfil(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) profilButton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
  
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
         
    }

    @FXML
    private void Forums(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Forum.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) ForumButton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void deconnecter(ActionEvent event) {
       
     try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) deconnexionButton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void GestionAnnonces(ActionEvent event) {
          try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Propre_annoncesCC.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage)  PropreAnnoncebutton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }

        
    }

    @FXML
    private void GestionAnimaux(ActionEvent event) {
     try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MesAnimauxCC.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) PropreAnimaux1.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
        
    }
    
}
