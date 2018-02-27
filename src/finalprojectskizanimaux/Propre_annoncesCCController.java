/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import ISERVICE.IAnnonceService;
import MODEL.Animal;
import MODEL.Annonce;
import SERVICE.AnimalService;
import SERVICE.AnnonceService;
import TECHNIQUE.Session;
import com.itextpdf.text.PageSize;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.time.LocalTime;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Propre_annoncesCCController implements Initializable {

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
    private Button acceuil22;
    @FXML
    private ListView<Annonce> ListAnimaux;
    @FXML
    private Button Modifier;
    @FXML
    private Button Supprimer;
    @FXML
    private ImageView imageAnnonce;
    @FXML
    private Button parcourir;
    @FXML
    private AnchorPane rootpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficherA();
        AnnonceService as=new AnnonceService();
       // System.out.println(as.DisplayAllMyAnimalAnnonce());
        
    ListAnimaux.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        showAccpDetails(newValue);
                    } catch (ParseException ex) {
                        Logger.getLogger(Propre_annoncesCCController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }); 
    }    

    @FXML
    private void forumRetour22(ActionEvent event) {
        try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        Parent root =loader.load();
        Stage stage=(Stage) acceuil22.getScene().getWindow();
        stage.close();
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
        
        
    }
    
    private void AfficherA(){
          AnnonceService a= new AnnonceService();
        AnimalService an=new AnimalService();
      
        ListAnimaux.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
          ObservableList<Annonce> lp = a.DisplayAllMyAnimalAnnonce();
          
          ListAnimaux.setCellFactory((ListView<Annonce> param) -> {
              ListCell<Annonce> cell = new ListCell<Annonce>() {
                  @Override
                  protected void updateItem(Annonce p , boolean bl) {
                      //System.out.println("ddddddd"+p);
                      super.updateItem(p, bl);
                      if(p!=null){
                        //  System.out.println("ppp"+p);
                         // System.out.println("bbb"+p.getId_animal());
                          Animal b=null;
                          
                          b=an.getAnimalbyId(p.getId_animal2());
                          
                          
                          Image img = new Image(p.getPhoto_annonce(), 200, 200, true, true, true) ;
                          ImageView imgV = new ImageView(img) ;
                                                
                          setGraphic(imgV);
                          
     
          setText("                             Titre de l'annonce: "+p.getTitre_annonce()+"\n \n                             Description : "
                                  +p.getDescription()+"\n \n                             Date de l'annonce : "+p.getDate_annonce()+
                                  "\n \n                             Type de l'annonce : "+p.getType_annonce()+"\n \n                             Nom de l'animal: "
          +b.getNom_animal()+"\n \n                             Race de l'animal : "+b.getRace_animal()+"\n \n                             Poids de l'animal : "+
             b.getPoids_animal()+"\n \n                             Sexe : " +b.getSexe()+"\n \n                             Type de l'animal : "  +b.getType_animal() );
                      }}};
              return cell;
              });
          
          
    
         ListAnimaux.setItems(lp); 
         
    }
    
    
    
    
   
    
    
    
    
    
  
      String idAnew;
void showAccpDetails(Annonce a) throws ParseException {
 
idAnew = String.valueOf(a.getId_annonce());
    //    SimpleDateFormat inFmt = new SimpleDateFormat("dd MMMM yyyy");
    /*    SimpleDateFormat inFmt = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat outFmt = new SimpleDateFormat("yyyy-MM-dd");

        String datea = outFmt.format(inFmt.parse(a.getDate_annonce()));
       
//        txtdate_debut.setPromptText(dated);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(datea, formatter);
            dateA_txt.setValue(date);
           // System.out.println(date);

        } catch (DateTimeParseException exc) {
            throw exc;      // throw the exception.
        }   
        photo_annonce_txt.setText(a.getPhoto_annonce());
    
    Image image1 = new Image(a.getPhoto_accouplement());
 imageAccouplement.setImage(image1);
     */
    
   Image image1 = new Image(a.getPhoto_annonce());
 imageAnnonce.setImage(image1);
 
    dateA_txt.getEditor().setText(a.getDate_annonce());
   TitreA_txt.setText(a.getTitre_annonce());
   DescriptionA_txt.setText(a.getDescription());
   type_annonce_txt.setText(a.getType_annonce());
   photo_annonce_txt.setText(a.getPhoto_annonce());
    } 

@FXML
    public void SelectionnerAnnonce(MouseEvent event) {
    }

    @FXML
    private void ModifierAnnonce(ActionEvent event) throws ParseException, IOException {
          AnchorPane pane=new AnchorPane();
        if (!ListAnimaux.getSelectionModel().isEmpty()) {
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
            a.setDescription(DescriptionA_txt.getText());
            a.setType_annonce(type_annonce_txt.getText());
          //  a.setAge_animal(Integer.parseInt(age_animal_txt.getText()));
            //a.setDescription(DescriptionA_txt.getText());
            //a.setNom_animal(nomA_txt.getText());
            //a.setRace_animal(race_animal_txt.getText());
            //a.setType_animal(type_animal_txt.getText());
            //a.setType_annonce(type_annonce_txt.getText());
            //a.setPoids_animal(Integer.parseInt(poids_animal_txt.getText()));
            //a.setSexe(sexe_animal_txt.getText());
            ias.updateAnnonce(a);
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Modifier Annonce ");
                                            alert.setHeaderText("Modification de l'Annonce"
                                                    + ListAnimaux.getSelectionModel().getSelectedItem().getId_annonce() + " est effectué avec succé");
                                            Optional<ButtonType> result = alert.showAndWait();
                                           //AfficherA();
                                         try {
                     pane = FXMLLoader.load(getClass().getResource("Propre_annoncesCC.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(Propre_annoncesCCController.class.getName()).log(Level.SEVERE, null, ex);
                }
                rootpane.getChildren().setAll(pane);
           
        } else {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Erreur de selection");
            alert1.setHeaderText("Vous etes obligé de selectioner une annonce ");

            Optional<ButtonType> result = alert1.showAndWait();
             AfficherA();
        }

    }

    @FXML
    private void SupprimerAnnonce(ActionEvent event) {
              AnchorPane pane=new AnchorPane();
         if (!ListAnimaux.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer Votre Annonce");
            alert.setHeaderText("Etes vous sur de vouloir supprimer Votre Annonce de l'animal : " + ListAnimaux.getSelectionModel().getSelectedItem().getId_animal().getNom_animal()+ " ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                AnnonceService a=new AnnonceService();
        a.deleteAnnonce(ListAnimaux.getSelectionModel().getSelectedItem().getId_annonce());
               //AfficherA();
               try {
                     pane = FXMLLoader.load(getClass().getResource("Propre_annoncesCC.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(Propre_annoncesCCController.class.getName()).log(Level.SEVERE, null, ex);
                }
                rootpane.getChildren().setAll(pane);
                 
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez selectionner une de vos annonces!");
             Optional<ButtonType> result = alert.showAndWait();
        }
        
    }

    @FXML
    private void parcouririmage(ActionEvent event) throws MalformedURLException {
        
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
            photo_annonce_txt.setText(imageFile);

            /////
        } else {
            System.out.println("file doesn't exist");
        }
        
    }
   
}
