/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;


import MODEL.Animal;
import MODEL.User;
import SERVICE.AnimalService;
import SERVICE.UserService;
import TECHNIQUE.Session;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class VendeurCCController implements Initializable {

    @FXML
    private Button Retour;
    @FXML
    private Button acceuil;
    @FXML
    private Label prenom;
    @FXML
    private Label nom;
    @FXML
    private Label adresse;
    @FXML
    private Label email;
    @FXML
    private Label num_tel;
    @FXML
    private Button envoyer;
   
    public static final String ACCOUNT_SID = "ACd4c26df568fe991cbb0ea6fb794c70e3";
  public static final String AUTH_TOKEN = "e2b82ac333cabb97785228193693a9fc";     
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       Affichage();
    }   
    
       public void Affichage()
    {
        AnimalService an=new AnimalService();
Animal u=an.search(VenteController.LoggedUser.getId_animal());
UserService us=new UserService();
User ur=us.findById(u.getId_Utilisateur().getId_utilisateur());
        System.out.println(ur);
        //id.setText(ur.getNom());
        prenom.setText(ur.getPrenom());
        nom.setText(ur.getNom());
        adresse.setText(ur.getAdresse());
        email.setText(ur.getEmail());
        num_tel.setText(Integer.toString(ur.getTelephone()));
        
    }
    

    @FXML
    private void RetourAcc(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Vente.fxml"));
        Parent root =loader.load();
        Stage stage=(Stage) acceuil.getScene().getWindow();
        stage.close();
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
        
    }

    @FXML
    private void forumRetour(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        Parent root =loader.load();
        Stage stage=(Stage) acceuil.getScene().getWindow();
        stage.close();
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void envoyerMessage(ActionEvent event) {
        AnimalService an=new AnimalService();
Animal u=an.search(VenteController.LoggedUser.getId_animal());
UserService us=new UserService();
User ur=us.findById(u.getId_Utilisateur().getId_utilisateur());
       //System.out.println(Session.LoggedUser.getPrenom());
        //System.out.println(ur.getPrenom());
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

   Message message = Message.creator(new PhoneNumber("+21692417135"), new PhoneNumber("+18447415160"),"  Mr/Mme    "+ur.getPrenom()+"   Mr/Mme     "+Session.LoggedUser.getPrenom()+"    veut vous contacter a propos de votre annonce de vente Si vous voulez le contacter Son Numero de telephone est  "+Session.LoggedUser.getTelephone()).create();

   System.out.println(message.getSid());
        
    }
        
    
    
    
    
    
    
}
