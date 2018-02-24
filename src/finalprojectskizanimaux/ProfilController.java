
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Annonce;
import SERVICE.AnnonceService;
import SERVICE.UserService;
import TECHNIQUE.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
    AnnonceService as=new AnnonceService();
    List<Annonce> annonces= as.getAnnoncebyIdUser(Session.LoggedUser.getId_utilisateur());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
labelprofilbmnom.setText(Session.LoggedUser.getNom()); 
 labelprofilbmmail.setText(Session.LoggedUser.getEmail());  
labelprofilbmusername.setText(Session.LoggedUser.getLogin());  
 
        ObservableList<Annonce> items = FXCollections.observableArrayList(annonces);

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
                        

                        setText("Titre Annonce : "+e.getTitre_annonce()+ "\n" + " Description  :" + e.getDescription() + "\n" + " Date de dernier Mise a jour : " + e.getDate_annonce()  + "\n" + " Nom de L'animale  :" + e.getNom_animal() + "\n" + "type de l'animale  :"+e.getType_animal());

                        setFont(Font.font("Berlin Sans FB Demi Bold", 12));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
        List.setItems(items);
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

  

    
}
