/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Mail;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
    @FXML
    private Button sinscrireButton12;
    @FXML
    private Pane paneboutiquesinscrire;
    @FXML
    private Text nomdeboutiquetext;
    @FXML
    private Text adresseboutiquetext;
    @FXML
    private Text numeroboutiquetext;
    @FXML
    private TextField NomdeBoutiquesinscrire;
    @FXML
    private TextField adresseboutiquesinscrire;
    @FXML
    private TextField numeroboutiquesinscrire;
    @FXML
    private ToggleGroup aaz;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Session.LoggedUser == null) {
             

        } else {
            utulisateurnom.setText(Session.LoggedUser.getNom());
            utilisateurprenom.setText(Session.LoggedUser.getPrenom());
            utilisateuradresse.setPromptText(Session.LoggedUser.getAdresse());
            utilisateurtelephone.setPromptText(String.valueOf(Session.LoggedUser.getTelephone()));
            utilisateurmail.setText(Session.LoggedUser.getEmail());
            utilisateurlogin.setText(Session.LoggedUser.getLogin());
            utlisateurmdp.setPromptText(Session.LoggedUser.getMotDePasse());
            utilisateurconfirmmdp.setPromptText(Session.LoggedUser.getMotDePasse());
           

        }
        nomdeboutiquetext.setVisible(false);
            NomdeBoutiquesinscrire.setVisible(false);
            adresseboutiquesinscrire.setVisible(false);
            adresseboutiquetext.setVisible(false);
            numeroboutiquesinscrire.setVisible(false);
            numeroboutiquetext.setVisible(false);
            paneboutiquesinscrire.setVisible(false);
        // TODO
    }

    @FXML
    private void sinscrireRetour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) sinscrireRetourButton.getScene().getWindow();
            stage.close();

            Stage s = new Stage();
            s.setScene(new Scene(root));
            s.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Sinscrireuser(ActionEvent event) {

        if (utulisateurnom.getText().equals("") || utilisateurprenom.getText().equals("") || utilisateuradresse.getText().equals("") || utilisateurtelephone.getText().equals("") || utilisateurmail.getText().equals("") || utlisateurmdp.getText().equals("") || utilisateurconfirmmdp.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("verifier les données ");
            alert.showAndWait();
        } else if (utilisateurtelephone.getText().length() != 8) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("le numero de telephone incorrecte ");
            alert.showAndWait();
        } else if (!utlisateurmdp.getText().equals(utilisateurconfirmmdp.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("mot de passe n'est pas identique");
            alert.showAndWait();
        } else {
            User usera = new User(utulisateurnom.getText(), utilisateurprenom.getText(), utilisateuradresse.getText(), Integer.parseInt(utilisateurtelephone.getText()), utilisateurmail.getText(), 3, utilisateurlogin.getText(), utlisateurmdp.getText());
            UserService userservice = new UserService();
            userservice.AjouterUser(usera);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Felicitation");
            alert.setHeaderText(null);
            alert.setContentText("votre compte a été cree avec succes . Redirection vers page d'acceuil ");
            alert.showAndWait();
            Mail ma = new Mail();
        ma.send(usera.getEmail(),"Votre à été crée avec succée","bye","SkiizAnimaux@gmail.com","hammouda1994");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) sinscrireRetourButton.getScene().getWindow();
                stage.close();

                Stage s = new Stage();
                s.setScene(new Scene(root));
                s.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    @FXML
    private void Boutiqueon(MouseEvent event) {
         nomdeboutiquetext.setVisible(true);
            NomdeBoutiquesinscrire.setVisible(true);
            adresseboutiquesinscrire.setVisible(true);
            adresseboutiquetext.setVisible(true);
            numeroboutiquesinscrire.setVisible(true);
            numeroboutiquetext.setVisible(true);
            paneboutiquesinscrire.setVisible(true);
    }

    @FXML
    private void utilisateuron(MouseEvent event) {
         nomdeboutiquetext.setVisible(false);
            NomdeBoutiquesinscrire.setVisible(false);
            adresseboutiquesinscrire.setVisible(false);
            adresseboutiquetext.setVisible(false);
            numeroboutiquesinscrire.setVisible(false);
            numeroboutiquetext.setVisible(false);
            paneboutiquesinscrire.setVisible(false);
    }

}
