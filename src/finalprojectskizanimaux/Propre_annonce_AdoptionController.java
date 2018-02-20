/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Annonce;
import SERVICE.AnnonceService;
import TECHNIQUE.Session;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Propre_annonce_AdoptionController implements Initializable {

    @FXML
    private Button acceuil;
    @FXML
    private TableView<Annonce> tblAdoption;
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
    private TextField sexe_animal_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficherA();
    }    

    @FXML
    private void forumRetour(ActionEvent event) {
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
        tblAdoption.setItems(a.getAnnoncebyIdType(Session.LoggedUser.getId_utilisateur(),"Adoption"));
        //String imageFile = (s.findById(table.getSelectionModel().getSelectedItem().getTEAM_ID()).getPath());
           // System.out.println(imageFile);
    }

}
