/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Sujet;
import MODEL.User;
import MODEL.Veterinaire;
import SERVICE.VeterinaireService;
import TECHNIQUE.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
 * @author asus
 */
public class VeterinaireUserController implements Initializable {
  
    
            VeterinaireService sv =new VeterinaireService();

            Veterinaire v =sv.veto_byid(Session.LoggedUser.getId_utilisateur());
          
            

    @FXML
    private TextField veto_nom;
    @FXML
    private TextField AdminModifierNumero;
    @FXML
    private TextField AdminModifierAdresse;
    @FXML
    private Button ModifierButtonAdminn;
    @FXML
    private ListView<Sujet> List;
    @FXML
    private Button DeconnexionAdminbm;
    @FXML
    private Button Supprimer_demmande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       affiche();
       veto_nom.setText(v.getNom_cabinet());
       AdminModifierNumero.setText(Integer.toString(v.getNumero_cabinet()));
       AdminModifierAdresse.setText(v.getAdresse_cabinet());
    }    

    
    public void affiche(){    
        ObservableList<Sujet> items = FXCollections.observableArrayList(sv.list_Sujet(Session.LoggedUser.getId_utilisateur()));

        List.setCellFactory((ListView<Sujet> arg0) -> {
            ListCell<Sujet> cell = new ListCell<Sujet>() {
                @Override
                protected void updateItem(Sujet e, boolean btl) {
                    super.updateItem(e, btl);

                    if (e != null) {
                        File file = new File("src\\images\\aaz.png");
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
                        
                        List.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            

                            @Override
                            public void handle(MouseEvent event) {
                                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                  
                               try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Commentaire_Sujet.fxml"));
        Parent root =loader.load();
   CommentaireController sec =  loader.getController();
    sec.get( List.getSelectionModel().getSelectedItem());
    sec.affiche(List.getSelectionModel().getSelectedItem());
    sec.ajouter(List.getSelectionModel().getSelectedItem());
            System.out.println(List.getSelectionModel().getSelectedItem());
   
            Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
                                    


                                    //System.out.println(e.getId_utilisateur());
                                    
                                }
                            }

                        });
                                   // System.out.println(e.getId_utilisateur());
                        setText("Nom:    "+e.getContenu()+ "\n" + "Prenom      :" + e.getObjet()+ "\n" + "Adresse     :" + e.getDate() + "\n" );

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
    private void ModifierAdmin(ActionEvent event) {
         if (AdminModifierNumero.getText().length()!=8 || AdminModifierAdresse.getText().equals("") || veto_nom.getText().equals("") ) {
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Verifier les information ");
            alert.showAndWait();
              
  }
         else {
         
         VeterinaireService sv = new VeterinaireService();
         Veterinaire veto = new Veterinaire(veto_nom.getText(),AdminModifierAdresse.getText(),Integer.parseInt(AdminModifierNumero.getText()),v.getId_utilisateur());
         sv.UpdateUser(veto);
         
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modifier !");
            alert.setHeaderText(null);
            alert.setContentText("Modification avec succes ");
            alert.showAndWait();
         }
         
         
         
    }

    @FXML
    private void deconnexionAdmin(ActionEvent event) {
             try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) DeconnexionAdminbm.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void suppuser12(MouseEvent event) {
                                    List.getItems().remove(List.getSelectionModel().getSelectedItem());

    }
    
}
