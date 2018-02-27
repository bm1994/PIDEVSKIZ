/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Animal;
import SERVICE.AnimalService;
import SERVICE.AnnonceService;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class MesAnimauxCCController implements Initializable {

    @FXML
    private Button acceuil3;
    @FXML
    private TableView<Animal> tableAnimal;
    @FXML
    private TableColumn<Animal,String> NomA;
    @FXML
    private TableColumn<Animal, String> AgeA;
    @FXML
    private TableColumn<Animal, String> TypeA;
    @FXML
    private TableColumn<Animal, String> RaceA;
    @FXML
    private TableColumn<Animal, String> poidsA;
    @FXML
    private TableColumn<Animal, String> sexeA;
    @FXML
    private Button SupprimerAnimal;
    @FXML
    private AnchorPane rootpane1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherAnimal();
    }    

    @FXML
    private void forumRetour3(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) acceuil3.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void SupprimerAnimal1(ActionEvent event) {
       AnchorPane pane=new AnchorPane();

        if (!tableAnimal.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" ");
            alert.setHeaderText("Etes-vous sur que vous voulez supprimer l'animal"
                    + tableAnimal.getSelectionModel().getSelectedItem().getId_animal()+ "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                AnnonceService as=new AnnonceService();
                AnimalService animalService=new AnimalService();
                as.deleteAllAnimalAnnonce(tableAnimal.getSelectionModel().getSelectedItem().getId_animal());
               // System.out.println(as.DisplayAllAnimalAnnonce(tableAnimal.getSelectionModel().getSelectedItem().getId_animal()));
                animalService.deleteAnimal(tableAnimal.getSelectionModel().getSelectedItem().getId_animal());
                
                try {
                     pane = FXMLLoader.load(getClass().getResource("MesAnimauxCC.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(MesAnimauxCCController.class.getName()).log(Level.SEVERE, null, ex);
                }
                rootpane1.getChildren().setAll(pane);
                 
              
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Vous etes obligé de selectioner un employe  ");

            Optional<ButtonType> result = alert.showAndWait();
        }
    }
   void afficherAnimal() {
       
        AnimalService animalService=new AnimalService();

        tableAnimal.setItems(animalService.displayallMyanimals());

        NomA.setCellValueFactory(new PropertyValueFactory<>("nom_animal"));
        AgeA.setCellValueFactory(new PropertyValueFactory<>("age_animal"));
        TypeA.setCellValueFactory(new PropertyValueFactory<>("type_animal"));
        RaceA.setCellValueFactory(new PropertyValueFactory<>("race_animal"));
        poidsA.setCellValueFactory(new PropertyValueFactory<>("poids_animal"));
        sexeA.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        
        

    }
    
}
