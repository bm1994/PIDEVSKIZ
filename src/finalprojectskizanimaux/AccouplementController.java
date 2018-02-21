/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import ISERVICE.IAnnonceService;
import MODEL.Annonce;
import SERVICE.AnnonceService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AccouplementController implements Initializable {

    
    @FXML
    private Button afficher_mesannonce_acc;
    @FXML
    private Button acceuil;
    @FXML
    private TableView<Annonce> tblAccouplement;
    @FXML
    private TableColumn<?, ?> tblclmTitre;
    @FXML
    private TableColumn<?, ?> tblclmDesc;
    @FXML
    private TableColumn<?, ?> tblclmDate;
    @FXML
    private TableColumn<?, ?> tblclmPhoto;
    @FXML
    private TableColumn<?, ?> tblclmTypead;
    @FXML
    private TableColumn<?, ?> tblclmNomA;
    @FXML
    private TableColumn<?, ?> tblclmAge;
    @FXML
    private TableColumn<?, ?> tblclmTypeAnimal;
    @FXML
    private TableColumn<?, ?> tblclmRace;
    @FXML
    private TableColumn<?, ?> tblclmPoids;
    @FXML
    private TableColumn<?, ?> tblclmSexe;
    @FXML
    private Button Ajouter_annonce_button1;
    @FXML
    private TextField Recherche_Acc_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        AfficherA();
        Recherche_Acc_txt.textProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                filtrerACCList((String) oldValue, (String) newValue);

            }

        });
    }    

   

    @FXML
    private void affiche_mesannonce_acc(ActionEvent event) {
        try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Propre_annonce_accouplement.fxml"));
        Parent root =loader.load();
        Stage stage=(Stage) afficher_mesannonce_acc.getScene().getWindow();
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
    private void Ajouter_annonce_acc(ActionEvent event) {
        try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajout_accouplement.fxml"));
        Parent root =loader.load();
        Stage stage=(Stage) Ajouter_annonce_button1.getScene().getWindow();
        stage.close();
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
    
     private void AfficherA() {
        AnnonceService a= new AnnonceService();
        tblclmTitre.setCellValueFactory(new PropertyValueFactory<>("titre_annonce"));
        tblclmDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblclmDate.setCellValueFactory(new PropertyValueFactory<>("date_annonce"));
        tblclmPhoto.setCellValueFactory(new PropertyValueFactory<>("photo_annonce"));
        tblclmTypead.setCellValueFactory(new PropertyValueFactory<>("type_annonce"));
        tblclmNomA.setCellValueFactory(new PropertyValueFactory<>("nom_animal"));
        tblclmAge.setCellValueFactory(new PropertyValueFactory<>("age_animal"));
        tblclmTypeAnimal.setCellValueFactory(new PropertyValueFactory<>("type_animal"));
        tblclmRace.setCellValueFactory(new PropertyValueFactory<>("race_animal"));
        tblclmPoids.setCellValueFactory(new PropertyValueFactory<>("poids_animal"));
        tblclmSexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        //flag.setCellValueFactory(new PropertyValueFactory<>("TEAM_FLAG"));
        //logo.setCellValueFactory(new PropertyValueFactory<>("TEAM_LOGO"));
        
        //table.setItems(null);
        tblAccouplement.setItems(a.getAnnoncebyType("accouplement"));
        //String imageFile = (s.findById(table.getSelectionModel().getSelectedItem().getTEAM_ID()).getPath());
           // System.out.println(imageFile);
    }
     
      void filtrerACCList(String oldValue, String newValue) {
     IAnnonceService ias=new AnnonceService();
        
        ObservableList<Annonce> filteredList = FXCollections.observableArrayList();
        if (Recherche_Acc_txt.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tblAccouplement.setItems(ias.getAnnoncebyType("Accouplement"));

        } else {

            newValue = newValue.toUpperCase();

            for (Annonce adoption : tblAccouplement.getItems()) {

                String filterRaceAd = adoption.getRace_animal();

               

                if (filterRaceAd.toUpperCase().contains(newValue) ) {
                    filteredList.add(adoption);

                }

            }
            tblAccouplement.setItems(filteredList);

        }
    }

    
}
