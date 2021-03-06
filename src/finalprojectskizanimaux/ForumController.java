/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Notification;
import MODEL.Sujet;
import SERVICE.SAbonnement;
import SERVICE.SAssociation;
import SERVICE.SEvenement;
import SERVICE.SNotification;
import SERVICE.SujetService;
import TECHNIQUE.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ForumController implements Initializable {

    @FXML
    private TableView<Sujet  > list;
    @FXML
    private TableColumn<Sujet,String> col_titre;
    @FXML
    private TableColumn<Sujet,String> cal_objet;
    @FXML
    private TableColumn<Sujet,String> col_contenu;
    @FXML
    private TableColumn<Sujet,String> col_date;
    @FXML
    private TextField Search;
    @FXML
    private Button afficher_sujet;
    @FXML
    private Button add_sujet;
    @FXML
    private Button acceuil;
    @FXML
    private Button del;
    @FXML
    private Button stat_btn;
    @FXML
    private Button profile;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private ImageView del_img;
    @FXML
    private Button modif;
     private SAssociation sa=new SAssociation();
    private SEvenement se=new SEvenement();
    private SAbonnement sab=new SAbonnement();
    private SNotification sn=new SNotification();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

	col_titre.setCellValueFactory(new PropertyValueFactory<Sujet,String>("titre"));
	cal_objet.setCellValueFactory(new PropertyValueFactory<Sujet,String>("objet"));
	col_contenu.setCellValueFactory(new PropertyValueFactory<Sujet,String>("contenu"));
	col_date.setCellValueFactory(new PropertyValueFactory<Sujet,String>("date"));



list.setItems(tab);


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
    private void Search(KeyEvent event) {
        Search.textProperty().addListener((observablevalue,oldvalue,newvalue) ->  {
	    		filter.setPredicate((Predicate<? super Sujet>) (Sujet s)-> {
	    			
	    			if (newvalue == null || newvalue.isEmpty()) {
	    				return true ;
	    			}
	    		
	    			   
	    			
	    		String lowercase =newvalue.toLowerCase();
	    		String su = s.getDate();
	    				if (su.contains(newvalue)) {
	    					return true ;
	    				}
	    				else	if (s.getTitre().contains(newvalue)) {
	    					return true ;
	    				}
	    			
	    				else	if (s.getObjet().contains(newvalue)) {
	    					return true ;
	    				}
	    			return false ;
	    			
	    			
	    			
	    			
	    		}); 
	    	
	    	

	    });
		    SortedList<Sujet> sort = new SortedList<>(filter);
		    sort.comparatorProperty().bind(list.comparatorProperty());
		    list.setItems(sort);

	    }
	    
	    
	    SujetService sv= new  SujetService();
  ObservableList<Sujet> tab = FXCollections.observableArrayList(	
	    		sv.Afficher_Sujet()
	    		);

  
  FilteredList<Sujet> filter = new FilteredList<Sujet>(tab,e->true);

    @FXML
    private void Ajouter_sujet(ActionEvent event) {
       try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutSujet.fxml"));
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
    private void delete(ActionEvent event) {
           SujetService sv= new  SujetService();
		    sv.Supprimer_Sujet(list.getSelectionModel().getSelectedItem());  
        AnchorPane pane = new AnchorPane();
                
                /*c.setNbrPlaces(c.getNbrPlaces() - result.get());
                cs.update(c);*/

                try {
                    pane = FXMLLoader.load(getClass().getResource("Forum.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
                }
                rootpane.getChildren().setAll(pane);

        
        
    }
    

    @FXML
    private void Commentaire(ActionEvent event) {
        
                  	     if (list.getSelectionModel().getSelectedItem() ==null ) {
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Il Faut Sélectionner un Sujet ");
            alert.showAndWait();
        
  }
        else
                             {
        
        try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Commentaire_Sujet.fxml"));
        Parent root =loader.load();
   CommentaireController sec =  loader.getController();
      sec.get( list.getSelectionModel().getSelectedItem());
    sec.affiche(list.getSelectionModel().getSelectedItem());
    sec.ajouter(list.getSelectionModel().getSelectedItem());
            System.out.println(list.getSelectionModel().getSelectedItem());
    
        Stage stage=(Stage) acceuil.getScene().getWindow();
        stage.close();
            Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
}}

    @FXML
    private void Statistique(ActionEvent event) {
        
             try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistique.fxml"));
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
    private void profile(ActionEvent event) {
        
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
    private void Test(MouseEvent event) {
          if (list.getSelectionModel().getSelectedItem().getId_user()!=Session.LoggedUser.getId_utilisateur())
                   {del.setVisible(false);
                 del_img.setVisible(false);
                   modif.setVisible(false);
                   }  
          else {del.setVisible(true);
          del_img.setVisible(true);
            modif.setVisible(true);
}
    }

    @FXML
    private void Modifier_sujet(ActionEvent event) {
        
        
        
        
        
    }
        private void Notify()
    {
    List<Notification> ln=sn.chercherNotification(Session.LoggedUser.getId_utilisateur());
     if (!ln.isEmpty())
     {
         ln.stream().map((n) -> {
             return n;
                }).forEach((n) -> {
                    if (n.getType()==1)
                    {
                        Notifications notification=Notifications.create()
                                .title("Nouveau événement")
                                .text("L'association "+sa.chercherAssociation(n.getId_association()).getNom_association()+" a ajouté un nouvel événement"+se.ChercherEvenement(n.getId_evenement()).getTitre_evenement())
                                .graphic(null)
                                .darkStyle()
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.TOP_RIGHT);
                        notification.showInformation();
                    }
                    else if (n.getType()==2)
                    {
                        Notifications notification=Notifications.create()
                                .title("Evénement annulé")
                                .text("L'association "+sa.chercherAssociation(n.getId_association()).getNom_association()+" a annulé un événement"+se.ChercherEvenement(n.getId_evenement()).getTitre_evenement())
                                .graphic(null)
                                .darkStyle()
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.TOP_RIGHT);
                        notification.showError();
                    }
                    else
                    {
                        Notifications notification=Notifications.create()
                                .title("Evénement Modifié")
                                .text("L'association "+sa.chercherAssociation(n.getId_association()).getNom_association()+" a modifié un événement"+se.ChercherEvenement(n.getId_evenement()).getTitre_evenement())
                                .graphic(null)
                                .darkStyle()
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.TOP_RIGHT);
                        notification.showConfirm();
                    }  });
         sn.supprimerNotification(Session.LoggedUser.getId_utilisateur());
    }
}
    }

    
    
