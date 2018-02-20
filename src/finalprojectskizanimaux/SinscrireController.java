/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.User;
import SERVICE.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class SinscrireController implements Initializable {

    @FXML
    private Button sinscrireRetourButton;
    @FXML
    private TextField utulisateurnom;
    @FXML
    private TextField utilisateurprenom;
    @FXML
    private TextField utilisateuradresse;
    @FXML
    private TextField utilisateurtelephone;
    @FXML
    private TextField utilisateurmail;
    @FXML
    private TextField utilisateurlogin;
    @FXML
    private PasswordField utlisateurmdp;
    @FXML
    private PasswordField utilisateurconfirmmdp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sinscrireRetour(ActionEvent event) {
          try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) sinscrireRetourButton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void Sinscrireuser(ActionEvent event) {
         if(utulisateurnom.getText().equals("")||utilisateurprenom.getText().equals("") || utilisateuradresse.getText().equals("")|| utilisateurtelephone.getText().equals("") || utilisateurmail.getText().equals("") || utlisateurmdp.getText().equals("") || utilisateurconfirmmdp.getText().equals("")){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("verifier les données ");
            alert.showAndWait();
        }
        else if (utilisateurtelephone.getText().length()!=8){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("le numero de telephone incorrecte ");
            alert.showAndWait();
        }
        else if (!utlisateurmdp.getText().equals(utilisateurconfirmmdp.getText())){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("mot de passe n'est pas identique");
            alert.showAndWait();
    }
        else {
            User usera =new User(utulisateurnom.getText(),utilisateurprenom.getText(),utilisateuradresse.getText(),Integer.parseInt( utilisateurtelephone.getText()),utilisateurmail.getText(), 2,utilisateurlogin.getText(),utlisateurmdp.getText());
       UserService userservice=new UserService();
       userservice.AjouterUser(usera);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Felicitation");
            alert.setHeaderText(null);
            alert.setContentText("votre compte a été cree avec succes . Redirection vers page d'acceuil ");
            alert.showAndWait();
            try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) sinscrireRetourButton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
            
        }
    
    }
    
}
