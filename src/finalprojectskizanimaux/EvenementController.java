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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private Button evenementRetourButton;
    @FXML
    private Button detailsButton;
    @FXML
    private Button buttonAjouter;
    @FXML
    private Button buttonSupprimer;
    @FXML
    private Button buttonModifier;
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
    private final ObservableList<Evenement> obe=FXCollections.observableArrayList(se.ConsulterEvenement());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(Session.LoggedUser.getRole()!=2)
        {
            buttonAjouter.setVisible(false);
            buttonSupprimer.setVisible(false);
            buttonModifier.setVisible(false);
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
    
    @FXML
    private void evenementDetails(ActionEvent event)
    {   
    if (
       lve.getSelectionModel().getSelectedItem()!=null)
        {
        try 
        {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenementDetails.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) detailsButton.getScene().getWindow();
        stage.close();
        EvenementDetailsController edc=loader.getController();
        edc.PassByEvenement(lve.getSelectionModel().getSelectedItem());
        Stage s = new Stage ();
        s.setScene(new Scene (root));    
        s.show();
    
    
        } 
        catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
    else
    {
        Alert a=new Alert(Alert.AlertType.INFORMATION,"Aucun événement séléctionné",ButtonType.CLOSE);
        a.setTitle("Alerte !");
        a.setContentText("Vous devez selectionner un événement de la liste pour accéder à la rubrique Détails.");
        a.showAndWait();
    }
        
    }
    @FXML
    private void evenementRetour(ActionEvent event) 
    {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) evenementRetourButton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }   // TODO
      
    @FXML 
    private void actionAjouter(ActionEvent event)
    {
        try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenementAjout.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) buttonAjouter.getScene().getWindow();
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
                    sn.ajouterNotification(ari.get(i),Session.LoggedUser.getId_utilisateur(),lve.getSelectionModel().getSelectedItem().getId_evenement(),2);
                }
            se.SupprimerEvenement(lve.getSelectionModel().getSelectedItem().getId_evenement());
            Alert a=new Alert(Alert.AlertType.WARNING,"Succés",ButtonType.FINISH);
            a.setContentText("l'évenement "+lve.getSelectionModel().getSelectedItem().getTitre_evenement()+"a été supprimé avec succés !");
            a.showAndWait();
            ObservableList<Evenement>obe=FXCollections.observableArrayList(se.ConsulterEvenement());
            lve.setItems(obe);
            
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
        
        Stage stage=(Stage) buttonModifier.getScene().getWindow();
        stage.close();
        EvenementModificationController emc=loader.getController();
        emc.initController(lve.getSelectionModel().getSelectedItem());
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
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

    

