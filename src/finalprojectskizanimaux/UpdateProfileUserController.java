/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Notification;
import MODEL.User;
import SERVICE.SAssociation;
import SERVICE.SEvenement;
import SERVICE.SNotification;
import SERVICE.UserService;
import TECHNIQUE.Session;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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
    public SNotification sn=new SNotification();
    public SEvenement se=new SEvenement();
    public SAssociation sa=new SAssociation();
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
      private void Notify()
    {
    List<Notification> ln=sn.chercherNotification(Session.LoggedUser.getId_utilisateur());
     if (!ln.isEmpty())
     {
         ln.stream().map((n) -> {
             return n;
                }).forEach((n) -> {
                    if (n.getType()==1)
                    {
                        Notifications notification=Notifications.create()
                                .title("Nouveau événement")
                                .text("L'association "+sa.chercherAssociation(n.getId_association()).getNom_association()+" a ajouté un nouvel événement"+se.ChercherEvenement(n.getId_evenement()).getTitre_evenement())
                                .graphic(null)
                                .darkStyle()
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.TOP_RIGHT);
                        notification.showInformation();
                    }
                    else if (n.getType()==2)
                    {
                        Notifications notification=Notifications.create()
                                .title("Evénement annulé")
                                .text("L'association "+sa.chercherAssociation(n.getId_association()).getNom_association()+" a annulé un événement"+se.ChercherEvenement(n.getId_evenement()).getTitre_evenement())
                                .graphic(null)
                                .darkStyle()
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.TOP_RIGHT);
                        notification.showError();
                    }
                    else
                    {
                        Notifications notification=Notifications.create()
                                .title("Evénement Modifié")
                                .text("L'association "+sa.chercherAssociation(n.getId_association()).getNom_association()+" a modifié un événement"+se.ChercherEvenement(n.getId_evenement()).getTitre_evenement())
                                .graphic(null)
                                .darkStyle()
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.TOP_RIGHT);
                        notification.showConfirm();
                    }  });
         sn.supprimerNotification(Session.LoggedUser.getId_utilisateur());
    }
}
}
