/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Commentaire_sujet;
import MODEL.Sujet;
import MODEL.User;
import MODEL.Veterinaire;
import SERVICE.Service_Commentaire_sujet;
import SERVICE.VeterinaireService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author habib
 */
public class Afficher_vetoController implements Initializable {

    @FXML
    private Button acceuil;
 
    @FXML
    private ListView<Veterinaire> list_utilisateur;
    @FXML
    private Button afficher_sujet;
    @FXML
    private Button profile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
affiche();
    }    


    @FXML
    private void forumRetour(ActionEvent event) {
    }
    
    
    
      public void affiche () {

        VeterinaireService sv =new VeterinaireService();
	    
                  ObservableList<Veterinaire> items = FXCollections.observableArrayList(sv.Affichervetousr_veto());

        list_utilisateur.setCellFactory((ListView<Veterinaire> arg0) -> {
            ListCell<Veterinaire> cell = new ListCell<Veterinaire>() {
                @Override
                protected void updateItem(Veterinaire e, boolean btl) {
                    super.updateItem(e, btl);

                    if (e != null) {
                        File file = new File("src\\images\\images (1).jpg");
                        file.getParentFile().mkdirs();
                        Image IMAGE_RUBY = new Image(file.toURI().toString());
                        //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());

                        ImageView imgview = new ImageView(IMAGE_RUBY);

                        setGraphic(imgview);

                        imgview.setFitHeight(50);
                        imgview.setFitWidth(50);
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
                        
     list_utilisateur.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            

                            @Override
                            public void handle(MouseEvent event) {
                           
                                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                  
                                                        try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile_veterinaire.fxml"));
                                              
        Parent root =loader.load();
        Profile_veterinaireController pc = loader.getController();

 Veterinaire v=  (list_utilisateur.getSelectionModel().getSelectedItem());
       
         pc.veteo(v.getAdresse_cabinet());
         pc.veteo_all(v);
           System.out.println(v.getAdresse_cabinet());
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
                            
                                    


                                
                                    
                                }
                            }

                        });
                                   // System.out.println(e.getId_utilisateur());
                        setText("Nom:    "+e.getNom()+ "\n" + "Prénom      :" + e.getPrenom() + "\n" + "Email    :" + e.getEmail()+ "\n" +"Click ici pour voir plus" );

                        setFont(Font.font("Berlin Sans FB Demi Bold", 12));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
        list_utilisateur.setItems(items);
        
  

       
        // TODO
    }    


    private void afficherlist_veto(ActionEvent event) {
                                    try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile_veterinaire.fxml"));
            
        Parent root =loader.load(); 
                   
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
          
    }

    @FXML
    private void afficher_sujet(ActionEvent event) {
    }

    @FXML
    private void profil(ActionEvent event) {
    }
    
  
    
}
