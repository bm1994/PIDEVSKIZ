/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Sujet;
import SERVICE.SujetService;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author habib
 */
public class AjoutSujetController implements Initializable {

    @FXML
    private Button afficher_sujet;
    @FXML
    private Button acceuil;
    @FXML
    private TextField titre;
    @FXML
    private TextField objet;
    @FXML
    private TextField date;
    @FXML
    private TextArea contenu;
    @FXML
    private Button ajouter;
    @FXML
    private Button profile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void forumRetour(ActionEvent event) {
    }

    @FXML
    private void submitClick(ActionEvent event) {
        int su = Session.LoggedUser.getId_utilisateur();
Sujet s = new Sujet(titre.getText(),objet.getText(),contenu.getText(),date.getText(),su);
SujetService sv = new SujetService();
sv.Ajouter_Sujet(s);
System.out.println("Ajouter");
    }

    @FXML
    private void afficher_sujet(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Forum.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) afficher_sujet.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void profil(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) profile.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
    
}
