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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Propre_annonce_venteController implements Initializable {

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
    @FXML
    private TableView<Annonce> tblVente;
    @FXML
    private TableColumn<?, ?> tblclmTitre2;
    @FXML
    private TableColumn<?, ?> tblclmDesc2;
    @FXML
    private TableColumn<?, ?> tblclmDate2;
    @FXML
    private TableColumn<?, ?> tblclmPhoto2;
    @FXML
    private TableColumn<?, ?> tblclmTypead2;
    @FXML
    private TableColumn<?, ?> tblclmNomA2;
    @FXML
    private TableColumn<?, ?> tblclmAge2;
    @FXML
    private TableColumn<?, ?> tblclmTypeAnimal2;
    @FXML
    private TableColumn<?, ?> tblclmRace2;
    @FXML
    private TableColumn<?, ?> tblclmPoids2;
    @FXML
    private TableColumn<?, ?> tblclmSexe2;
    @FXML
    private Button Modifier_vente;
    @FXML
    private Button deletevente;
    @FXML
    private AnchorPane rootpane2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         AfficherA();
         tblVente.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        showAccpDetails(newValue);
                    } catch (ParseException ex) {
                        Logger.getLogger(Propre_annonce_AdoptionController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
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

    
     private void AfficherA() {
        AnnonceService a= new AnnonceService();
        tblclmTitre2.setCellValueFactory(new PropertyValueFactory<>("titre_annonce"));
        tblclmDesc2.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblclmDate2.setCellValueFactory(new PropertyValueFactory<>("date_annonce"));
        tblclmPhoto2.setCellValueFactory(new PropertyValueFactory<>("photo_annonce"));
        tblclmTypead2.setCellValueFactory(new PropertyValueFactory<>("type_annonce"));
        tblclmNomA2.setCellValueFactory(new PropertyValueFactory<>("nom_animal"));
        tblclmAge2.setCellValueFactory(new PropertyValueFactory<>("age_animal"));
        tblclmTypeAnimal2.setCellValueFactory(new PropertyValueFactory<>("type_animal"));
        tblclmRace2.setCellValueFactory(new PropertyValueFactory<>("race_animal"));
        tblclmPoids2.setCellValueFactory(new PropertyValueFactory<>("poids_animal"));
        tblclmSexe2.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        //flag.setCellValueFactory(new PropertyValueFactory<>("TEAM_FLAG"));
        //logo.setCellValueFactory(new PropertyValueFactory<>("TEAM_LOGO"));
        
        //table.setItems(null);
        tblVente.setItems(a.getAnnoncebyIdType(Session.LoggedUser.getId_utilisateur(),"Vente"));
        //String imageFile = (s.findById(table.getSelectionModel().getSelectedItem().getTEAM_ID()).getPath());
           // System.out.println(imageFile);
    }

       String idAnew;
    void showAccpDetails(Annonce a) throws ParseException {
 
idAnew = String.valueOf(a.getId_annonce());
    //    SimpleDateFormat inFmt = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat inFmt = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat outFmt = new SimpleDateFormat("yyyy-MM-dd");

        String datea = outFmt.format(inFmt.parse(a.getDate_annonce()));
       
//        txtdate_debut.setPromptText(dated);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(datea, formatter);
            dateA_txt.setValue(date);
            System.out.println(date);

        } catch (DateTimeParseException exc) {
            throw exc;      // throw the exception.
        }
        
        TitreA_txt.setText(a.getTitre_annonce());
        DescriptionA_txt.setText(a.getDescription());
        type_annonce_txt.setText(a.getType_annonce());
        nomA_txt.setText(a.getNom_animal());
        poids_animal_txt.setText(String.valueOf(a.getPoids_animal()));
        photo_annonce_txt.setText(a.getPhoto_annonce());
        type_animal_txt.setText(a.getType_animal());
        race_animal_txt.setText(a.getRace_animal());
         age_animal_txt.setText(String.valueOf(a.getAge_animal()));
        sexe_animal_txt.setText(a.getSexe());
        
  //Image image1 = new Image(a.getPhoto_annonce());

           // imageAdoption.setImage(image1);
       

    }

    @FXML
    private void Modifier_annonce_vente(ActionEvent event) throws ParseException {
          AnchorPane pane=new AnchorPane();
        if (!tblVente.getSelectionModel().isEmpty()) {
           IAnnonceService ias=new AnnonceService();
            String date_annonce = dateA_txt.getEditor().getText();
            SimpleDateFormat inFmt = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outFmt = new SimpleDateFormat("dd/MM/yyyy");

            String dateN = outFmt.format(inFmt.parse(date_annonce));

            LocalDate db = dateA_txt.getValue();

            Annonce a=new Annonce();
            a.setId_annonce(Integer.valueOf(idAnew));
            a.setDate_annonce(date_annonce);
            a.setPhoto_annonce(photo_annonce_txt.getText());
            a.setTitre_annonce(TitreA_txt.getText());
            a.setAge_animal(Integer.parseInt(age_animal_txt.getText()));
            a.setDescription(DescriptionA_txt.getText());
            a.setNom_animal(nomA_txt.getText());
            a.setRace_animal(race_animal_txt.getText());
            a.setType_animal(type_animal_txt.getText());
            a.setType_annonce(type_annonce_txt.getText());
            a.setPoids_animal(Float.valueOf(poids_animal_txt.getText()));
            a.setSexe(sexe_animal_txt.getText());
            ias.updateAnnonce(a);
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Modifier Annonce ");
                                            alert.setHeaderText("Modification de vente"
                                                    + tblVente.getSelectionModel().getSelectedItem().getId_annonce() + " est effectué avec succé");
                                            Optional<ButtonType> result = alert.showAndWait();
                                           //AfficherA();
                                             try {
                     pane = FXMLLoader.load(getClass().getResource("Propre_annonce_vente.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(Propre_annonce_AdoptionController.class.getName()).log(Level.SEVERE, null, ex);
                }
                rootpane2.getChildren().setAll(pane);
            
        } else {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Erreur de selection");
            alert1.setHeaderText("Vous etes obligé de selectioner un vente  ");

            Optional<ButtonType> result = alert1.showAndWait();
             AfficherA();
        }

    }

    @FXML
    private void Supprimer_vente(ActionEvent event) {
         AnchorPane pane=new AnchorPane();
         if (!tblVente.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer Votre Annonce");
            alert.setHeaderText("Etes vous sur de vouloir supprimer Votre Annonce : " + tblVente.getSelectionModel().getSelectedItem().getId_annonce()+ " ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                AnnonceService a=new AnnonceService();
        a.deleteAnnonce(tblVente.getSelectionModel().getSelectedItem().getId_annonce());
               //AfficherA();
               try {
                     pane = FXMLLoader.load(getClass().getResource("Propre_annonce_vente.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(Propre_annonce_AdoptionController.class.getName()).log(Level.SEVERE, null, ex);
                }
                rootpane2.getChildren().setAll(pane);
                 
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez selectionner une de vos annonces!");
             Optional<ButtonType> result = alert.showAndWait();
        }
    }
    
}