/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Admin;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AdminController implements Initializable {

    private TextArea AdminField;
    @FXML
    private Text AdminNom;
    @FXML
    private Text AdminPrenom;
    @FXML
    private Text AdminNumero;
    @FXML
    private Text adminAdresse;
    @FXML
    private Text adminMail;
    @FXML
    private Text adminLogin;
    @FXML
    private Pane PaneAdminModifier;
    @FXML
    private TextField AdminNomModifier;
    @FXML
    private Button ModifierButtonAdminn;
    @FXML
    private AnchorPane PaneIdAdminn;
    @FXML
    private TextField AdminPrenomModifier;
    @FXML
    private TextField AdminModifierNumero;
    @FXML
    private TextField AdminModifierAdresse;
    @FXML
    private TextField AdminModifierMail;
    @FXML
    private TextField AdminModifierLogin;
    @FXML
    private Pane paneAffichmdp;
    @FXML
    private Text passwordtextadmin;
    @FXML
    private TextField ModifierAdminMdp;
    @FXML
    private Text confirmpasswordtextadmin;
    @FXML
    private TextField ModifierAdminMdpConfirm;
    @FXML
    private ImageView annulerimage1;
    private ImageView annulermdpimage;
    @FXML
    private ImageView annulerimage2;
    @FXML
    private ImageView annulerimage3;
    @FXML
    private ImageView annulerimage4;
    @FXML
    private ImageView annulerimage5;
    @FXML
    private ImageView annulerimage6;

    /**
     * Initializes the controller class.
     */
                AudioClip note = new AudioClip(this.getClass().getResource("sound.mp3").toString());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        annulerimage1.setVisible(false);
        PaneAdminModifier.setVisible(false);
        AdminNomModifier.setVisible(false);
        AdminPrenomModifier.setVisible(false);
        AdminModifierNumero.setVisible(false);
        AdminModifierAdresse.setVisible(false);
        AdminModifierMail.setVisible(false);
        AdminModifierLogin.setVisible(false);
        ModifierAdminMdp.setVisible(false);
        ModifierAdminMdpConfirm.setVisible(false);
        passwordtextadmin.setVisible(false);
        confirmpasswordtextadmin.setVisible(false);
        AdminNom.setText(Session.LoggedUser.getNom());
        AdminPrenom.setText(Session.LoggedUser.getPrenom());
        adminAdresse.setText(Session.LoggedUser.getAdresse());
        AdminNumero.setText(String.valueOf(Session.LoggedUser.getTelephone()));
        adminMail.setText(Session.LoggedUser.getEmail());
        adminLogin.setText(Session.LoggedUser.getLogin());
        AdminNomModifier.setText(Session.LoggedUser.getNom());
        AdminPrenomModifier.setText(Session.LoggedUser.getPrenom());
        AdminModifierNumero.setText(String.valueOf(Session.LoggedUser.getTelephone()));
        AdminModifierAdresse.setText(Session.LoggedUser.getAdresse());
        AdminModifierMail.setText(Session.LoggedUser.getEmail());
        AdminModifierLogin.setText(Session.LoggedUser.getLogin());
        annulerimage2.setVisible(false);
        annulerimage3.setVisible(false);
        annulerimage4.setVisible(false);
        annulerimage5.setVisible(false);
        annulerimage6.setVisible(false);








        ModifierButtonAdminn.setVisible(false);



  

       
        // TODO
    }    

    @FXML
    private void ModifierAdmin(ActionEvent event) {
        if(!ModifierAdminMdp.getText().equals(ModifierAdminMdpConfirm.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("mot de passe n'est pas identique");
            alert.showAndWait();
        }
        else if(ModifierAdminMdp.getText().equals("")){
            ModifierAdminMdp.setStyle("-fx-background-color:#F78181");
        }
        else{
        Session.LoggedUser.setNom(AdminNomModifier.getText());
        Session.LoggedUser.setPrenom(AdminPrenomModifier.getText());
        Session.LoggedUser.setAdresse(AdminModifierAdresse.getText());
        Session.LoggedUser.setTelephone(Integer.parseInt(AdminModifierNumero.getText()));
        Session.LoggedUser.setEmail(AdminModifierMail.getText());
        Session.LoggedUser.setLogin(AdminModifierLogin.getText());
        Session.LoggedUser.setMotDePasse(ModifierAdminMdp.getText());
        Session.LoggedUser.setId_utilisateur(Session.LoggedUser.getId_utilisateur());
        
        UserService userservicee=new UserService();
        userservicee.UpdateUser(Session.LoggedUser);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText(null);
            alert.setContentText("Votre Compte a ete modifier");
            alert.showAndWait();
        
               try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) ModifierButtonAdminn.getScene().getWindow();
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
    private void ActiverModifierNomAdmin(MouseEvent event) {
        AdminNomModifier.setVisible(true);
        ModifierButtonAdminn.setVisible(true);
         paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);
        annulerimage1.setVisible(true);
        

        
    }

    @FXML
    private void PaneReturnFromModifierAdmin(MouseEvent event) {
    
   
    }

    @FXML
    private void ActiverModifierPrenomAdmin(MouseEvent event) {
        AdminPrenomModifier.setVisible(true);
        ModifierButtonAdminn.setVisible(true);
         paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);
        annulerimage2.setVisible(true);
    }

    @FXML
    private void ActiverModifierNumeroAdmin(MouseEvent event) {
        AdminModifierNumero.setVisible(true);
        ModifierButtonAdminn.setVisible(true);
         paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);
        annulerimage3.setVisible(true);

    }

    @FXML
    private void ActivierModifierAdresseAdmin(MouseEvent event) {
        AdminModifierAdresse.setVisible(true);
        ModifierButtonAdminn.setVisible(true);
         paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);
        annulerimage4.setVisible(true);

    }

    @FXML
    private void ActiverModifierMailAdmin(MouseEvent event) {
        AdminModifierMail.setVisible(true);
        ModifierButtonAdminn.setVisible(true);
         paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);
        annulerimage5.setVisible(true);

    }

    @FXML
    private void ActiverModifierLoginAdmin(MouseEvent event) {
        AdminModifierLogin.setVisible(true);
        ModifierButtonAdminn.setVisible(true);
        paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);
         annulerimage6.setVisible(true);

        
    }

    @FXML
    private void ActiverCinfirmerMdpAdmin(KeyEvent event) {
        ModifierAdminMdpConfirm.setVisible(true);
        confirmpasswordtextadmin.setVisible(true);
       ModifierAdminMdp.setStyle("-fx-background-color:##FFFFFF");

    }

    private void AnnulerModifnomadmin(KeyEvent event) {
        
    }

    @FXML
    private void AnnulerModifnomadmin(MouseEvent event) {
        annulerimage1.setVisible(false);
        AdminNomModifier.setVisible(false);
        ModifierAdminMdp.setVisible(false);
        passwordtextadmin.setVisible(false);
        confirmpasswordtextadmin.setVisible(false);
        ModifierAdminMdpConfirm.setVisible(false);
        paneAffichmdp.setVisible(true);
         if(AdminModifierMail.isVisible() || AdminModifierMail.isVisible()|| AdminModifierAdresse.isVisible()|| AdminModifierNumero.isVisible()||AdminPrenomModifier.isVisible()|| AdminNomModifier.isVisible()||AdminModifierLogin.isVisible()){
ModifierButtonAdminn.setVisible(true);
        paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);        }
        else{
                    ModifierButtonAdminn.setVisible(false);

        }
    }

    @FXML
    private void AnnulerModifprenomadmin(MouseEvent event) {
        annulerimage2.setVisible(false);
        AdminPrenomModifier.setVisible(false);
        ModifierAdminMdp.setVisible(false);
        passwordtextadmin.setVisible(false);
        confirmpasswordtextadmin.setVisible(false);
        ModifierAdminMdpConfirm.setVisible(false);
        paneAffichmdp.setVisible(true);
         if(AdminModifierMail.isVisible() || AdminModifierMail.isVisible()|| AdminModifierAdresse.isVisible()|| AdminModifierNumero.isVisible()||AdminPrenomModifier.isVisible()|| AdminNomModifier.isVisible()||AdminModifierLogin.isVisible()){
ModifierButtonAdminn.setVisible(true);
        paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);        }
        else{
                    ModifierButtonAdminn.setVisible(false);

        }
    }

    @FXML
    private void AnnulerModifNumadmin(MouseEvent event) {
        annulerimage3.setVisible(false);
        AdminModifierNumero.setVisible(false);
        ModifierAdminMdp.setVisible(false);
        passwordtextadmin.setVisible(false);
        confirmpasswordtextadmin.setVisible(false);
        ModifierAdminMdpConfirm.setVisible(false);
        paneAffichmdp.setVisible(true);
 if(AdminModifierMail.isVisible() || AdminModifierMail.isVisible()|| AdminModifierAdresse.isVisible()|| AdminModifierNumero.isVisible()||AdminPrenomModifier.isVisible()|| AdminNomModifier.isVisible()||AdminModifierLogin.isVisible()){
ModifierButtonAdminn.setVisible(true);
        paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);        }
        else{
                    ModifierButtonAdminn.setVisible(false);

        }        
    }

    @FXML
    private void AnnulerModifAdresseadmin(MouseEvent event) {
        
        annulerimage4.setVisible(false);
        AdminModifierAdresse.setVisible(false);
        ModifierAdminMdp.setVisible(false);
        passwordtextadmin.setVisible(false);
        confirmpasswordtextadmin.setVisible(false);
        ModifierAdminMdpConfirm.setVisible(false);
        paneAffichmdp.setVisible(true);
         if(AdminModifierMail.isVisible() || AdminModifierMail.isVisible()|| AdminModifierAdresse.isVisible()|| AdminModifierNumero.isVisible()||AdminPrenomModifier.isVisible()|| AdminNomModifier.isVisible()||AdminModifierLogin.isVisible()){
ModifierButtonAdminn.setVisible(true);
        paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);        }
        else{
                    ModifierButtonAdminn.setVisible(false);

        }
    }

    @FXML
    private void AnnulerModifMailadmin(MouseEvent event) {
         annulerimage5.setVisible(false);
       AdminModifierMail.setVisible(false);
        ModifierAdminMdp.setVisible(false);
        passwordtextadmin.setVisible(false);
        confirmpasswordtextadmin.setVisible(false);
        ModifierAdminMdpConfirm.setVisible(false);
        paneAffichmdp.setVisible(true);
 if(AdminModifierMail.isVisible() || AdminModifierMail.isVisible()|| AdminModifierAdresse.isVisible()|| AdminModifierNumero.isVisible()||AdminPrenomModifier.isVisible()|| AdminNomModifier.isVisible()||AdminModifierLogin.isVisible()){
ModifierButtonAdminn.setVisible(true);
        paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);        }
        else{
                    ModifierButtonAdminn.setVisible(false);

        }        
    }

    @FXML
    private void AnnulerModifloginadmin(MouseEvent event) {
        annulerimage6.setVisible(false);
        AdminModifierLogin.setVisible(false);
        ModifierAdminMdp.setVisible(false);
        passwordtextadmin.setVisible(false);
        confirmpasswordtextadmin.setVisible(false);
        ModifierAdminMdpConfirm.setVisible(false);
        paneAffichmdp.setVisible(true);
        if(AdminModifierMail.isVisible() || AdminModifierMail.isVisible()|| AdminModifierAdresse.isVisible()|| AdminModifierNumero.isVisible()||AdminPrenomModifier.isVisible()|| AdminNomModifier.isVisible()||AdminModifierLogin.isVisible()){
ModifierButtonAdminn.setVisible(true);
        paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);        }
        else{
                    ModifierButtonAdminn.setVisible(false);

        }
    }

    @FXML
    private void MusicPlayerr(MouseEvent event) {

        if (note.isPlaying() == true) {
            note.stop();
        } else {
           note.play();
           
        }
    }
}
