/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import ISERVICE.IAnnonceService;
import MODEL.Annonce;
import SERVICE.AnnonceService;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Ajout_adoptionController implements Initializable {

    @FXML
    private Button acceuil;
    @FXML
    private TextField TitreA_txt;
    @FXML
    private TextArea DescriptionA_txt;
    @FXML
    private DatePicker dateA_txt;
    @FXML
    private TextField photo_annonce_txt;
    @FXML
    private TextField type_annonce_txt;
    @FXML
    private ImageView imageAdoption;
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
    
    ObservableList<String> comboListsexe = FXCollections.observableArrayList("Male", "Femelle");
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type_annonce_txt.setText("Adoption");
        sexe_animal_txt.setValue("Male");
        sexe_animal_txt.setItems(comboListsexe);
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
    private void submitClick(ActionEvent event) {
        
        IAnnonceService ias = new AnnonceService();
       
                                            Annonce a = new Annonce(TitreA_txt.getText(),DescriptionA_txt.getText(),dateA_txt.getEditor().getText(),photo_annonce_txt.getText(),type_annonce_txt.getText(),nomA_txt.getText(),Integer.parseInt(age_animal_txt.getText()),type_animal_txt.getText(),race_animal_txt.getText(),Integer.parseInt(poids_animal_txt.getText()),sexe_animal_txt.getValue());
                                           a.setId_user( Session.LoggedUser.getId_utilisateur());
                                            
                                            ias.create(a);
                                            
                                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Succés d'ajout ");
                                            alert.setHeaderText("Annonce adoption ajouté avec succé");
                                            Optional<ButtonType> result = alert.showAndWait();
         
    }
    
}
