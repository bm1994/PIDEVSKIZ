 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Annonce;
import MODEL.Commentaire_sujet;
import MODEL.Sujet;
import SERVICE.Service_Commentaire_sujet;
import TECHNIQUE.Session;
import java.io.File;
import java.io.IOException;
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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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
 * @author habib
 */
public class CommentaireController implements Initializable {
	Sujet sujet ;
    @FXML
    private Button modif;
    @FXML
    private Button modif1;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Button profile;
    @FXML
    private Button Delete;
	   public Sujet getSujet() {
			return sujet;
		}
		public void setSujet(Sujet sujet) {
			this.sujet = sujet;
		}
    @FXML
    private Button afficher_sujet;
    @FXML
    private Button acceuil;
    @FXML
    private ListView<Commentaire_sujet> list_commentaire;
    @FXML
    private TextField titre;
    @FXML
    private TextArea contenu;
    @FXML
    private TextArea contenu_commentaire;
    @FXML
    private Button Commenter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

                   }  
    public void affiche (Sujet s) {
titre.setText(sujet.getTitre());
contenu.setText(sujet.getContenu());
        Service_Commentaire_sujet sv =new Service_Commentaire_sujet();
	       System.out.println(s);
                  ObservableList<Commentaire_sujet> items = FXCollections.observableArrayList(sv.Afficher_commentaire(s));

        list_commentaire.setCellFactory((ListView<Commentaire_sujet> arg0) -> {
            ListCell<Commentaire_sujet> cell = new ListCell<Commentaire_sujet>() {
                @Override
                protected void updateItem(Commentaire_sujet e, boolean btl) {
                    super.updateItem(e, btl);

                    if (e != null) {
                        File file = new File("src\\images\\dialog.png");
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
                        

                        setText(
                                " Commentaire : "+e.getContenu_commentaire());

                        setFont(Font.font("Berlin Sans FB Demi Bold", 12));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
        list_commentaire.setItems(items);
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
    public void get (Sujet s){
          this.sujet = s ;}
public void ajouter(Sujet s) {
    	 
    sujet = s ;
    	
    }
   
    @FXML
    private void commenter(ActionEvent event) throws IOException {
       
                  	     if (contenu_commentaire.getText().equals("")  ) {
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Il Ajouter un commentaire ");
            alert.showAndWait();
        
  }
                             else{
   
        Service_Commentaire_sujet sv = new Service_Commentaire_sujet();
   	Commentaire_sujet c = new Commentaire_sujet (Session.LoggedUser.getId_utilisateur(),sujet.getId_sujet(),contenu_commentaire.getText(),"12/1/7");
 
        
   	sv.Ajouter_commentaire(c);

           affiche(sujet);
           contenu_commentaire.setText("");

                             }
 
        
    }

    @FXML
    private void Modifier_commentaire(ActionEvent event) {
    contenu_commentaire.setText(list_commentaire.getSelectionModel().getSelectedItem().getContenu_commentaire());
    Commenter.setVisible(false);
    }

    @FXML
    private void test(MouseEvent event) {
          if (list_commentaire.getSelectionModel().getSelectedItem().getId_utilisateur()!=Session.LoggedUser.getId_utilisateur())
                   {modif.setVisible(false);
                   modif1.setVisible(false);
                   Delete.setVisible(false);
                   
                   
                   }  
          else {modif.setVisible(true);
          modif1.setVisible(true);
          Delete.setVisible(true);}
    }

    @FXML
    private void Suavegarder(ActionEvent event) {
           Service_Commentaire_sujet sv = new Service_Commentaire_sujet();
   	Commentaire_sujet c = new Commentaire_sujet (list_commentaire.getSelectionModel().getSelectedItem().getId_commentaire_sujet(),contenu_commentaire.getText());
   	
   	sv.Modifier_commentaire(c);
    }

    @FXML
    private void afficher_sujet(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Forum.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) afficher_sujet.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void profil(ActionEvent event) {
         
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) profile.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        
        
                  	     if (list_commentaire.getSelectionModel().getSelectedItem() ==null ) {
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Il Faut Sélectionner un commentaire ");
            alert.showAndWait();
        
  }
                             else{
   
        Service_Commentaire_sujet sv = new Service_Commentaire_sujet();
   	Commentaire_sujet c = new Commentaire_sujet (list_commentaire.getSelectionModel().getSelectedItem().getId_commentaire_sujet(),list_commentaire.getSelectionModel().getSelectedItem().getContenu_commentaire() );
 
        
   	sv.Supprimer_commentaire(c);

           affiche(sujet);
           contenu_commentaire.setText("");

                             }
 
        
    }


    
    
}
