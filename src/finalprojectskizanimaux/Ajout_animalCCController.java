/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Animal;
import SERVICE.AnimalService;
import SERVICE.UserService;
import TECHNIQUE.Session;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Ajout_animalCCController implements Initializable {

    @FXML
    private TextField nomA_txt;
    @FXML
    private TextField age_animal_txt;
    @FXML
    private TextField type_animal_txt;
    @FXML
    private TextField race_animal_txt;
    @FXML
    private TextField poids_animal_txt;
    @FXML
    private ComboBox<String> sexe_animal_txt;
    @FXML
    private Button ajouter_anim;
    @FXML
    private Button Retour1;
    @FXML
    private Button acceuil;

    
     ObservableList<String> comboListsexe = FXCollections.observableArrayList("Male", "Femelle");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sexe_animal_txt.setValue("Male");
        sexe_animal_txt.setItems(comboListsexe);
    }    

    @FXML
    private void ajouterAnim(ActionEvent event) {
        if(nomA_txt.getText().equals("")||age_animal_txt.getText().equals("") || type_animal_txt.getText().equals("")|| race_animal_txt.getText().equals("") || poids_animal_txt.getText().equals("") || sexe_animal_txt.getValue().equals("") ){
       
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Ajout refusé ");
                                            alert.setHeaderText("Veuillez remplir tous les champs");
                                            Optional<ButtonType> result = alert.showAndWait();
            
        }
        else{
         AnimalService ias = new AnimalService();
        UserService userservice=new UserService();
       
                                          Animal a = new Animal(nomA_txt.getText()
                                                  ,Integer.parseInt(age_animal_txt.getText())
                                                  ,type_animal_txt.getText()
                                                  ,race_animal_txt.getText()
                                                  ,Float.valueOf(poids_animal_txt.getText())
                                                  ,sexe_animal_txt.getValue(),Session.LoggedUser);
                                           //a.setId_Utilisateur(new User(Session.LoggedUser.getId_utilisateur()));
                                            ias.create1(a);
                                           
                                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Succés d'ajout ");
                                            alert.setHeaderText("Animal ajouté avec succé");
                                            Optional<ButtonType> result = alert.showAndWait();
                                            
                                              try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajout_venteCC.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) ajouter_anim.getScene().getWindow();
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
    private void RetourVente(ActionEvent event) {
     

    }
    
}
