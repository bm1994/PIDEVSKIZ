/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Animal;
import MODEL.Annonce;
import SERVICE.AnimalService;
import SERVICE.AnnonceService;
import SERVICE.UserService;
import TECHNIQUE.Session;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ProfilController implements Initializable {

    private Button profilRetourButton;
    @FXML
    private Button retourprofilbutton;
    private Label labelprofilbm;
    @FXML
    private Label labelprofilbmnom;
    @FXML
    private Label labelprofilbmmail;
    @FXML
    private Label labelprofilbmusername;
    @FXML
    private Button DeleteProfilUserButton;
    @FXML
    private ListView<Annonce> List;
   // AnnonceService as=new AnnonceService();
   // List<Annonce> annonces= as.getAnnoncebyIdUser(Session.LoggedUser.getId_utilisateur());
    @FXML
    private Label labeltime;
    @FXML
    private ImageView imageprofil11;
    @FXML
    private Button Parcourirphoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Parcourirphoto.setVisible(false);
labelprofilbmnom.setText(Session.LoggedUser.getNom()); 
 labelprofilbmmail.setText(Session.LoggedUser.getEmail());  
labelprofilbmusername.setText(Session.LoggedUser.getLogin());  
  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
Calendar cal = Calendar.getInstance();
labeltime.setText(dateFormat.format(cal.getTime()));

AnnonceService a= new AnnonceService();
        AnimalService an=new AnimalService();
      
        List.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
          ObservableList<Annonce> lp = a.DisplayAllMyAnimalAnnonce();
          
          List.setCellFactory((ListView<Annonce> param) -> {
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
          
          
    
         List.setItems(lp); 
         

     /*   ObservableList<Annonce> items = FXCollections.observableArrayList(annonces);

        List.setCellFactory((ListView<Annonce> arg0) -> {
            ListCell<Annonce> cell = new ListCell<Annonce>() {
                @Override
                protected void updateItem(Annonce e, boolean btl) {
                    super.updateItem(e, btl);

                    if (e != null) {
                        File file = new File("src\\images\\images (2).jpg");
                        file.getParentFile().mkdirs();
                        Image IMAGE_RUBY = new Image(file.toURI().toString());
                        //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());

                        ImageView imgview = new ImageView(IMAGE_RUBY);

                        setGraphic(imgview);

                        imgview.setFitHeight(100);
                        imgview.setFitWidth(100);
                        Rectangle clip = new Rectangle(
                                imgview.getFitWidth(), imgview.getFitHeight()
                        );

                        clip.setArcWidth(20);
                        clip.setArcHeight(20);
                        imgview.setClip(clip);

                        // snapshot the rounded image.
                        SnapshotParameters parameters = new SnapshotParameters();
                        parameters.setFill(Color.TRANSPARENT);
                        WritableImage image = imgview.snapshot(parameters, null);

                        // remove the rounding clip so that our effect can show through.
                        imgview.setClip(null);

                        // apply a shadow effect.
                        imgview.setEffect(new DropShadow(20, Color.BLACK));

                        // store the rounded image in the imageView.
                        imgview.setImage(image);
                        

                     //   setText("Titre Annonce : "+e.getTitre_annonce()+ "\n" + " Description  :" + e.getDescription() + "\n" + " Date de dernier Mise a jour : " + e.getDate_annonce()  + "\n" + " Nom de L'animale  :" + e.getNom_animal() + "\n" + "type de l'animale  :"+e.getType_animal());

                        setFont(Font.font("Berlin Sans FB Demi Bold", 12));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
        List.setItems(items);
        */
    }    
   

    @FXML
    private void retourAcc(ActionEvent event) {
             try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) retourprofilbutton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void Updateprofileuser(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProfileUser.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) retourprofilbutton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }

        
    }

    @FXML
    private void DeleteProfileuserbm(ActionEvent event) {
        
        
        try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("VerificationSuppritionCompteUser.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) DeleteProfilUserButton.getScene().getWindow();
        stage.close();
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void actualisettime(MouseEvent event) {
        
         
    }

    @FXML
    private void changetime(MouseEvent event) {
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
Calendar cal = Calendar.getInstance();
       
      
labeltime.setText(dateFormat.format(cal.getTime()));
    }

    @FXML
    private void parcourirphotoprofile(ActionEvent event) throws MalformedURLException {
        Parcourirphoto.setVisible(false);
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

            imageprofil11.setImage(image1);
            //////a changer static

            //////////
          //  photo_annonce1_txt.setText(imageFile);

            /////
        } else {
            System.out.println("file doesn't exist");
        }
    }

    @FXML
    private void affichparcour(MouseEvent event) {
        Parcourirphoto.setVisible(true);
    }

    @FXML
    private void hideparcour(MouseEvent event) {
       // profilRetourButton.setVisible(false);
    }
    
  

    
}
