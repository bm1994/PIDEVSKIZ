/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Mail;
import MODEL.User;
import MODEL.Veterinaire;
import SERVICE.UserService;
import SERVICE.VeterinaireService;
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
import javafx.scene.control.RadioButton;
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
    @FXML
    private Text nomdecabinettext1;
    @FXML
    private Text adressecabinettext1;
    @FXML
    private Text numerocabinettext1;
    @FXML
    private TextField Nomdecabinetsinscrire1;
    @FXML
    private TextField adressecabinetsinscrire1;
    @FXML
    private TextField numerocabinetsinscrire1;
    @FXML
    private RadioButton usersinscrire11;
    @FXML
    private RadioButton usersinscrire12;
    @FXML
    private RadioButton boutique1125;

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
            Nomdecabinetsinscrire1.setVisible(false);
            nomdecabinettext1.setVisible(false);
            adressecabinettext1.setVisible(false);
            adressecabinetsinscrire1.setVisible(false);
            numerocabinettext1.setVisible(false);
            numerocabinetsinscrire1.setVisible(false);





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
        } else if(usersinscrire11.isSelected()) {
            User usera = new User(utulisateurnom.getText(), utilisateurprenom.getText(), utilisateuradresse.getText(), Integer.parseInt(utilisateurtelephone.getText()), utilisateurmail.getText(), 3, utilisateurlogin.getText(), utlisateurmdp.getText());
            UserService userservice = new UserService();
            userservice.AjouterUser(usera);
            
            Mail ma = new Mail();
        ma.send(usera.getEmail(),"Votre Compte SkiizAnimaux à été crée avec succée","Bienvenue \n  coordonnées de Votre Compte Skiizanimaux \n Nom:"+usera.getNom()+"\n Prenom:"+usera.getPrenom()+"\n Adresse :"+usera.getAdresse()+"\n Telephone :"+usera.getTelephone()+"\n Login :"+usera.getLogin(),"SkiizAnimaux@gmail.com","hammouda1994");
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Felicitation");
            alert.setHeaderText(null);
            alert.setContentText("votre compte a été cree avec succes . Redirection vers page d'acceuil ");
            alert.showAndWait();  
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
        else if(boutique1125.isSelected()) {
            User usera = new User(utulisateurnom.getText(), utilisateurprenom.getText(), utilisateuradresse.getText(), Integer.parseInt(utilisateurtelephone.getText()), utilisateurmail.getText(), 4, utilisateurlogin.getText(), utlisateurmdp.getText());
            UserService userservice = new UserService();
            userservice.AjouterUser(usera);
            
            Mail ma = new Mail();
        ma.send(usera.getEmail(),"Votre Compte SkiizAnimaux à été crée avec succée","Bienvenue \n  coordonnées de Votre Compte Skiizanimaux Boutique \n Nom:"+usera.getNom()+"\n Prenom:"+usera.getPrenom()+"\n Adresse :"+usera.getAdresse()+"\n Telephone :"+usera.getTelephone()+"\n Login :"+usera.getLogin(),"SkiizAnimaux@gmail.com","hammouda1994");
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Felicitation");
            alert.setHeaderText(null);
            alert.setContentText("votre compte a été cree avec succes . Redirection vers page d'acceuil ");
            alert.showAndWait();
        
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
        else if(usersinscrire12.isSelected()){
            if(Nomdecabinetsinscrire1.getText().equals("")||adressecabinetsinscrire1.equals("")||numerocabinetsinscrire1.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("verifier les données ");
            alert.showAndWait();
            }
            else if(numerocabinetsinscrire1.getText().length()!=8){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("le numero de telephone de Cabinet est incorrecte ");
            alert.showAndWait();
            }
            else{
            Veterinaire vetoa=new Veterinaire(Nomdecabinetsinscrire1.getText(),adressecabinetsinscrire1.getText(),Integer.parseInt(numerocabinetsinscrire1.getText()),utulisateurnom.getText(), utilisateurprenom.getText(), utilisateuradresse.getText(), Integer.parseInt(utilisateurtelephone.getText()), utilisateurmail.getText(), 5, utilisateurlogin.getText(), utlisateurmdp.getText());
            VeterinaireService vvv=new VeterinaireService();
            vvv.AjouterVeterinaire(vetoa);
           
            Mail ma = new Mail();
        ma.send(vetoa.getEmail(),"Votre compte SkiizANIMAUX à été crée avec succée ","Bienvenue \n Coordonnées de Votre Compte Skiizanimaux \n Type De Compte : Veterinaire \n Nom:"+vetoa.getNom()+"\n Prenom:"+vetoa.getPrenom()+"\n Adresse:"+vetoa.getAdresse()+"\n Login:"+vetoa.getLogin()+"\n Nom du Cabinet:"+vetoa.getNom_cabinet()+"\n Adresse de Cabinet :"+vetoa.getAdresse_cabinet()+"\n Numero de Cabinet :"+vetoa.getNumero_cabinet(),"SkiizAnimaux@gmail.com","hammouda1994");
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Felicitation");
            alert.setHeaderText(null);
            alert.setContentText("votre compte a été cree avec succes . Redirection vers page d'acceuil ");
            alert.showAndWait();
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

    }

    @FXML
    private void Boutiqueon(MouseEvent event) {
         nomdeboutiquetext.setVisible(false);
            NomdeBoutiquesinscrire.setVisible(false);
            adresseboutiquesinscrire.setVisible(false);
            adresseboutiquetext.setVisible(false);
            numeroboutiquesinscrire.setVisible(false);
            numeroboutiquetext.setVisible(false);
            paneboutiquesinscrire.setVisible(false);
             Nomdecabinetsinscrire1.setVisible(false);
            nomdecabinettext1.setVisible(false);
            adressecabinettext1.setVisible(false);
            adressecabinetsinscrire1.setVisible(false);
            numerocabinettext1.setVisible(false);
            numerocabinetsinscrire1.setVisible(false);
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
             Nomdecabinetsinscrire1.setVisible(false);
            nomdecabinettext1.setVisible(false);
            adressecabinettext1.setVisible(false);
            adressecabinetsinscrire1.setVisible(false);
            numerocabinettext1.setVisible(false);
            numerocabinetsinscrire1.setVisible(false);
    }

    @FXML
    private void Cabineton(MouseEvent event) {
        paneboutiquesinscrire.setVisible(true);
            Nomdecabinetsinscrire1.setVisible(true);
            nomdecabinettext1.setVisible(true);
            adressecabinettext1.setVisible(true);
            adressecabinetsinscrire1.setVisible(true);
            numerocabinettext1.setVisible(true);
            numerocabinetsinscrire1.setVisible(true);
            
            nomdeboutiquetext.setVisible(false);
            NomdeBoutiquesinscrire.setVisible(false);
            adresseboutiquesinscrire.setVisible(false);
            adresseboutiquetext.setVisible(false);
            numeroboutiquesinscrire.setVisible(false);
            numeroboutiquetext.setVisible(false);
    }

}
