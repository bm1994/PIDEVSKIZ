/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.User;
import SERVICE.UserService;
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
public class UpdateProfileUserController implements Initializable {

    @FXML
    private TextField updateProfilebmnom;
    @FXML
    private TextField updateProfilebmPrenom;
    @FXML
    private TextField updateProflebmAdresse;
    @FXML
    private TextField updateProfilebmTelephone;
    @FXML
    private TextField updateProfilebmMail;
    @FXML
    private TextField updateProfilebmLogin;
    @FXML
    private PasswordField updateProfilebmMotDePasse;
    @FXML
    private PasswordField UpdateprofilebmConfirmMotDePasse;
    @FXML
    private Button SauvegardeUpdateProfileUser;
    @FXML
    private Button AnnulerUpdateProfileUserButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      updateProfilebmnom.setText(Session.LoggedUser.getNom()); 
      updateProfilebmPrenom.setText(Session.LoggedUser.getPrenom());
      updateProflebmAdresse.setText(Session.LoggedUser.getAdresse());
      updateProfilebmTelephone.setText(Integer.toString(Session.LoggedUser.getTelephone()));
      updateProfilebmMail.setText(Session.LoggedUser.getEmail());
      updateProfilebmLogin.setText(Session.LoggedUser.getLogin());

    }    

    @FXML
    private void SuveguarderUpdateProfilUser(ActionEvent event) {
        
        if(!updateProfilebmMotDePasse.getText().equals(UpdateprofilebmConfirmMotDePasse.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("mot de passe n'est pas identique");
            alert.showAndWait();
        }
        else if(updateProfilebmnom.getText().equals("")||updateProfilebmPrenom.getText().equals("")||updateProflebmAdresse.getText().equals("")||updateProfilebmTelephone.getText().equals("")||updateProfilebmMail.getText().equals("")||updateProfilebmLogin.getText().equals("")||updateProfilebmMotDePasse.getText().equals("")||UpdateprofilebmConfirmMotDePasse.getText().equals("")){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("verifier les données ");
            alert.showAndWait();
        
    }
        else if(updateProfilebmTelephone.getText().length()!=8){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("le numero de telephone incorrecte ");
            alert.showAndWait();
            
        }
        else{
        Session.LoggedUser.setNom(updateProfilebmnom.getText());
        Session.LoggedUser.setPrenom(updateProfilebmPrenom.getText());
        Session.LoggedUser.setAdresse(updateProflebmAdresse.getText());
        Session.LoggedUser.setTelephone(Integer.parseInt(updateProfilebmTelephone.getText()));
        Session.LoggedUser.setEmail(updateProfilebmMail.getText());
        Session.LoggedUser.setLogin(updateProfilebmLogin.getText());
        Session.LoggedUser.setMotDePasse(updateProfilebmMotDePasse.getText());
        Session.LoggedUser.setId_utilisateur(Session.LoggedUser.getId_utilisateur());
        
        UserService userservicee=new UserService();
        userservicee.UpdateUser(Session.LoggedUser);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Felicitation");
            alert.setHeaderText(null);
            alert.setContentText("votre compte a été a jour . Redirection vers votre Profile ");
            alert.showAndWait();
            try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) SauvegardeUpdateProfileUser.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
        }  
    }

    @FXML
    private void AnnulerUpdateProfileUser(ActionEvent event) {
        try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) AnnulerUpdateProfileUserButton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
    
}
