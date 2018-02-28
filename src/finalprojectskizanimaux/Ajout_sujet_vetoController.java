/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Sujet;
import MODEL.Veterinaire;
import SERVICE.SujetService;
import SERVICE.VeterinaireService;
import TECHNIQUE.Session;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author habib
 */
public class Ajout_sujet_vetoController implements Initializable {
Veterinaire s ;
 
 public void veteo_all(Veterinaire v){
           this.s=v;

    } 
   @FXML
    private Label l1;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 }    
 
   



    @FXML
    private void submitClick(ActionEvent event) {
               
  if (titre.getText().equals("") || date.getText().equals("") || contenu.getText().equals("") ) {
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tout les champs ");
            alert.showAndWait();
        
  }
        
        
        int su = Session.LoggedUser.getId_utilisateur();
Sujet sujet = new Sujet(titre.getText(),objet.getText(),contenu.getText(),date.getText(),su);
SujetService sv = new SujetService();
VeterinaireService vt = new VeterinaireService();

vt.ajouter_un_Sujet(s.getId_utilisateur(), sujet);
sv.Ajouter_Sujet(sujet);
int h=sv.retour_last(su);
sv.Ajouter_user_veto(su,s.getId_utilisateur(), h);

Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Ajouter !");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous ajouter ");
         
            
ButtonType buttonTypeOne = new ButtonType("Oui");
ButtonType buttonTypeTwo = new ButtonType("Non");

alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == buttonTypeOne){
     
    try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Forum.fxml"));
        Parent root =loader.load();
        Stage stage=(Stage) ajouter.getScene().getWindow();
        stage.close();
        Stage sa = new Stage ();
    sa.setScene(new Scene (root));    
    sa.show();
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
    else {
            try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Forum.fxml"));
        Parent root =loader.load();
        Stage stage=(Stage) ajouter.getScene().getWindow();
        stage.close();
        Stage sa = new Stage ();
    sa.setScene(new Scene (root));    
    sa.show();
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }}



    }

    @FXML
    private void acceuil_button(ActionEvent event) {
    }
    
}
