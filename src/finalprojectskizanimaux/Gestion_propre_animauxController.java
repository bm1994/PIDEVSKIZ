/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;



import ISERVICE.IAnnonceService;

import MODEL.Animal;
import MODEL.User;
import SERVICE.AnimalService;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ISERVICE.IAnimalService;
import SERVICE.UserService;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Gestion_propre_animauxController implements Initializable {

    @FXML
    private Button Retour1;
    @FXML
    private Button acceuil;
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
    private TableColumn<?, ?> tblclmNomA;
    private TableColumn<?, ?> tblclmAge;
    private TableColumn<?, ?> tblclmTypeAnimal;
    private TableColumn<?, ?> tblclmRace;
    private TableColumn<?, ?> tblclmPoids;
    private TableColumn<?, ?> tblclmSexe;
    private TableView<Animal> tblAnimal;

     ObservableList<String> comboListsexe = FXCollections.observableArrayList("Male", "Femelle");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
        sexe_animal_txt.setValue("Male");
        sexe_animal_txt.setItems(comboListsexe);
        AfficherA();
    }    

    @FXML
    private void RetourAcceuil(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
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
    private void forumRetour(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
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
    private void ajouterAnim(ActionEvent event) {
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
    
    private void AfficherA() {
        AnimalService a= new AnimalService();
       
        tblclmNomA.setCellValueFactory(new PropertyValueFactory<>("nom_animal"));
        tblclmAge.setCellValueFactory(new PropertyValueFactory<>("age_animal"));
        tblclmTypeAnimal.setCellValueFactory(new PropertyValueFactory<>("type_animal"));
        tblclmRace.setCellValueFactory(new PropertyValueFactory<>("race_animal"));
        tblclmPoids.setCellValueFactory(new PropertyValueFactory<>("poids_animal"));
        tblclmSexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        //flag.setCellValueFactory(new PropertyValueFactory<>("TEAM_FLAG"));
        //logo.setCellValueFactory(new PropertyValueFactory<>("TEAM_LOGO"));
        
        //table.setItems(null);
        tblAnimal.setItems(a.getAllAnimals());
        //String imageFile = (s.findById(table.getSelectionModel().getSelectedItem().getTEAM_ID()).getPath());
           // System.out.println(imageFile);
    }
    
}
