/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import MODEL.Annonce;
import MODEL.Evenement;
import MODEL.Notification;
import SERVICE.SAbonnement;
import SERVICE.SAssociation;
import SERVICE.SEvenement;
import SERVICE.SNotification;
import TECHNIQUE.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Ruskov
 */
public class EvenementController implements Initializable {
  
    @FXML
    private Button buttonSupprimer;
    @FXML
    private ListView<Evenement> lve;
    
    /**
     * Initializes the controller class.
     */
    private final SNotification sn=new SNotification();
    private final SAbonnement sab=new SAbonnement();
    private final SEvenement se=new SEvenement();
    private final SAssociation sa=new SAssociation();
    private final ArrayList<Integer> ari=new ArrayList<>(sab.ListAbonnes(Session.LoggedUser.getId_utilisateur()));
    private  ObservableList<Evenement> obe=FXCollections.observableArrayList(se.ConsulterEvenement());
    @FXML
    private Button deconnexionButton;
    @FXML
    private Button PropreAnnoncebutton;
    @FXML
    private Button PropreAnimaux1;
    @FXML
    private Button buttonModifier1;
    @FXML
    private Button buttonDetails;
    @FXML
    private Button buttonAjout;
    @FXML
    private Button buttonRetour;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(Session.LoggedUser.getRole()!=2)
        {
            buttonAjout.setVisible(false);
            buttonSupprimer.setVisible(false);
            buttonModifier1.setVisible(false);
        }
       lve.setCellFactory((ListView<Evenement> arg0) -> {
            ListCell<Evenement> cell = new ListCell<Evenement>() {
                @Override
                protected void updateItem(Evenement e, boolean btl) {
                    super.updateItem(e, btl);

                    if (e != null) {
                        

                        setText("Titre : "+e.getTitre_evenement()+ "\n" + " Lieu : " + e.getLieu_evenement()+ "\n" + " Date : " + e.getDate_evenement()+ "\n" + " Sujet : " + e.getSujet_evenement()+ "\n" + "Participants  : "+e.getNombre_interesses());

                        setFont(Font.font("Berlin Sans FB Demi Bold", 12));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
       lve.setItems(obe);
    }    
    
   
    private void initilaze()
    {
    lve.setCellFactory((ListView<Evenement> arg0) -> {
            ListCell<Evenement> cell = new ListCell<Evenement>() {
                @Override
                protected void updateItem(Evenement e, boolean btl) {
                    super.updateItem(e, btl);

                    if (e != null) {
                        

                        setText("Titre : "+e.getTitre_evenement()+ "\n" + " Lieu : " + e.getLieu_evenement()+ "\n" + " Date : " + e.getDate_evenement()+ "\n" + " Sujet : " + e.getSujet_evenement()+ "\n" + "Participants  : "+e.getNombre_interesses());

                        setFont(Font.font("Berlin Sans FB Demi Bold", 12));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
       lve.setItems(obe);
    }
// TODO
      
    @FXML 
    private void actionAjouter(ActionEvent event)
    {
        try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenementAjout.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) buttonAjout.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
        s.setScene(new Scene (root));    
        s.show();
    
            Notify();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void actionSupprimer(ActionEvent event) 
    {
        if (lve.getSelectionModel().getSelectedItem().getId_association()==Session.LoggedUser.getId_utilisateur())
        {   try
            {
            for (int i=0;i<ari.size();++i)
                {        
                    sn.supprimerNotification(i,lve.getSelectionModel().getSelectedItem().getId_evenement(),1);
                    sn.ajouterNotification(ari.get(i),Session.LoggedUser.getId_utilisateur(),lve.getSelectionModel().getSelectedItem().getId_evenement(),2);
                }
            se.SupprimerEvenement(lve.getSelectionModel().getSelectedItem().getId_evenement());
            Alert a=new Alert(Alert.AlertType.WARNING,"Succés",ButtonType.FINISH);
            a.setContentText("l'évenement "+lve.getSelectionModel().getSelectedItem().getTitre_evenement()+"a été supprimé avec succés !");
            a.showAndWait();
            Notify();
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Evenement.fxml"));
            Parent root =loader.load();
        
            Stage stage=(Stage) buttonSupprimer.getScene().getWindow();
            stage.close();
        
            Stage s = new Stage ();
            s.setScene(new Scene (root));    
            s.show();
            Notify();
            
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
            
            }
            catch(Exception e)
            {
                Alert a=new Alert(Alert.AlertType.WARNING,"Erreur",ButtonType.FINISH);
                a.setContentText("une erreur est survenue lors de la suppression de l'événement"+lve.getSelectionModel().getSelectedItem().getTitre_evenement());
                a.showAndWait();
            }
            
        }
        else
        {
             Alert b=new Alert(Alert.AlertType.WARNING,"Erreur",ButtonType.CANCEL);
             b.setContentText("Action non autorisée !");
             b.showAndWait();
        }
    }

    @FXML
    private void actionModifier(ActionEvent event) 
    {
        if (lve.getSelectionModel().getSelectedItem()!=null)
        {
            if (lve.getSelectionModel().getSelectedItem().getId_association()==Session.LoggedUser.getId_utilisateur())
            {
                
            
        try {
  
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenementModification.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) buttonModifier1.getScene().getWindow();
        stage.close();
        EvenementModificationController emc=loader.getController();
        emc.initController(lve.getSelectionModel().getSelectedItem());
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    Notify();
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
            }
            else
            {
             Alert b=new Alert(Alert.AlertType.WARNING,"Erreur",ButtonType.CANCEL);
             b.setContentText("Action non autorisée !");
             b.showAndWait();
            }
    }
}
   private void Notify() throws IOException
    {
    List<Notification> ln=sn.chercherNotification(Session.LoggedUser.getId_utilisateur());
     if (!ln.isEmpty())
     {  System.out.println(Session.LoggedUser.getId_utilisateur());
         for(Notification n:ln)
         {
             System.out.println(n.getId_association());
             if (n.getType()==1)
             {
                 Notifications notification=Notifications.create()
                         .title("Nouveau événement")
                         .text("L'association "+sa.chercherAssociation(n.getId_association()).getNom_association()+" a ajouté un nouvel événement")
                         .graphic(null)
                         .darkStyle()
                         .hideAfter(Duration.seconds(5))
                         .position(Pos.TOP_RIGHT).onAction(new EventHandler<ActionEvent>() {

                     @Override
                     public void handle(ActionEvent event) {
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenementDetails.fxml"));
                 Parent root;
                         try {
                             root = loader.load();
                             Stage stage = (Stage) buttonSupprimer.getScene().getWindow();
                             stage.close();
                             EvenementDetailsController edc=loader.getController();
                             edc.PassByEvenement(se.ChercherEvenement(n.getId_evenement()));
                             Stage s = new Stage();
                             s.setScene(new Scene(root));
                             s.show();
                         } catch (IOException ex) {
                             Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                         }

            
                     }
                 });
                          notification.showConfirm();
             
             }
             else if (n.getType()==2)
             {
                 Notifications notification=Notifications.create()
                         .title("Evénement annulé")
                         .text("L'association "+sa.chercherAssociation(n.getId_association()).getNom_association()+" a annulé un événement")
                         .graphic(null)
                         .darkStyle()
                         .hideAfter(Duration.seconds(5))
                         .position(Pos.TOP_RIGHT);
                          notification.showConfirm();
             }
             else
             {
                 Notifications notification=Notifications.create()
                         .title("Evénement annulé")
                         .text("L'association "+sa.chercherAssociation(n.getId_association()).getNom_association()+" a modifié un événement")
                         .graphic(null)
                         .darkStyle()
                         .hideAfter(Duration.seconds(5))
                         .position(Pos.TOP_RIGHT).onAction(new EventHandler<ActionEvent>() {

                    @Override
                     public void handle(ActionEvent event) {
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenementDetails.fxml"));
                 Parent root;
                         try {
                             root = loader.load();
                             Stage stage = (Stage) buttonSupprimer.getScene().getWindow();
                             stage.close();
                             EvenementDetailsController edc=loader.getController();
                             edc.PassByEvenement(se.ChercherEvenement(n.getId_evenement()));
                             Stage s = new Stage();
                             s.setScene(new Scene(root));
                             s.show();
                         } catch (IOException ex) {
                             Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                         }

            
                          
             }
                     
                         });
                                 
                          notification.showConfirm();
     }
sn.supprimerNotification(Session.LoggedUser.getId_utilisateur());
}
    
}
}


   @FXML
    private void deconnecter(ActionEvent event) {
       
     try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) deconnexionButton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void GestionAnnonces(ActionEvent event) {
          try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Propre_annoncesCC.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage)  PropreAnnoncebutton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    Notify();
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }

        
    }

    @FXML
    private void GestionAnimaux(ActionEvent event) {
     try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MesAnimauxCC.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) PropreAnimaux1.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    Notify();
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
        
    }
    @FXML
    private void actionDetails(ActionEvent event) {
        if (
       lve.getSelectionModel().getSelectedItem()!=null)
        {
        try 
        {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenementDetails.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) buttonDetails.getScene().getWindow();
        stage.close();
        EvenementDetailsController edc=loader.getController();
        edc.PassByEvenement(lve.getSelectionModel().getSelectedItem());
        Stage s = new Stage ();
        s.setScene(new Scene (root));    
        s.show();
        Notify();
    
        } 
        catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
    else
    {
        Alert a=new Alert(Alert.AlertType.WARNING,"Aucun événement séléctionné",ButtonType.CLOSE);
        a.setTitle("Alerte !");
        a.setContentText("Vous devez selectionner un événement de la liste pour accéder à la rubrique Détails.");
        a.showAndWait();
    }
      
    }

    @FXML
    private void actionRetour(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) buttonRetour.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
}

    

