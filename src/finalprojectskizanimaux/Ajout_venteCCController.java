/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Annonce;
import SERVICE.AnimalService;
import SERVICE.AnnonceService;
import TECHNIQUE.Session;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Ajout_venteCCController implements Initializable {

    @FXML
    private TextField TitreA1_txt;
    @FXML
    private TextArea DescriptionA1_txt;
    @FXML
    private DatePicker dateA1_txt;
    @FXML
    private TextField photo_annonce1_txt;
    private TextField type_annonce1_txt;
    @FXML
    private Button ajouter22;
    @FXML
    private ComboBox<String> monanimalCombo;
    @FXML
    private Button Retour12;
    @FXML
    private Button acceuil11;
    @FXML
    private ImageView imageAnnonce;
    @FXML
    private Button Parcourir;
    @FXML
    private ComboBox<String> monAnnonceCombo;
    ObservableList<String> comboListannonce = FXCollections.observableArrayList("Accouplement","Adoption","Vente");
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AnimalService animalService=new AnimalService();
        //type_annonce1_txt.setText("Vente");
        // type_annonce1_txt.setDisable(true);
        monAnnonceCombo.setValue("Accouplement");
        monAnnonceCombo.setItems(comboListannonce);
        
         monanimalCombo.setItems(animalService.displayallMyanimals(Session.LoggedUser.getId_utilisateur()));
    }    


    @FXML
    private void ajouterAnnonce1(ActionEvent event) {
        
        
        
        AnnonceService ias = new AnnonceService();
      Annonce a=new Annonce();
      AnimalService animalService=new AnimalService();
      a.setTitre_annonce(TitreA1_txt.getText());
      a.setDescription(DescriptionA1_txt.getText());
      a.setDate_annonce(dateA1_txt.getEditor().getText());
      a.setPhoto_annonce(photo_annonce1_txt.getText());
      a.setType_annonce(monAnnonceCombo.getValue());
      a.setId_animal(animalService.findAnimalByName(monanimalCombo.getValue()));
      
      ias.create(a);
      TrayNotification tray = new TrayNotification();
        tray.setTitle("Votre Annonce est ajoutée");
        tray.setMessage(a.getTitre_annonce());
        tray.showAndDismiss(Duration.seconds(4));
      
    }

    @FXML
    private void RetourAcc11(ActionEvent event) {
           try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Vente.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) Retour12.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void forumRetour11(ActionEvent event) {
          try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) acceuil11.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
        
    }

    @FXML
    private void ParcourirImage(ActionEvent event) throws MalformedURLException {
           String imageFile;
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            //    getImageUrl = selectedFile.getAbsolutePath();
            // System.out.println(getImageUrl);
            // Image value = new Image(getImageUrl);
            //  img.setImage(value);
            imageFile = selectedFile.toURI().toURL().toString();
            System.out.println(imageFile);

            Image image1 = new Image(imageFile);

            imageAnnonce.setImage(image1);
            //////a changer static

            //////////
            photo_annonce1_txt.setText(imageFile);

            /////
        } else {
            System.out.println("file doesn't exist");
        }
        
    }
    
}
