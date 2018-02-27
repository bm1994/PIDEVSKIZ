/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Association;
import MODEL.Evenement;
import MODEL.Notification;
import SERVICE.SAbonnement;
import SERVICE.SAssociation;
import SERVICE.SEvenement;
import SERVICE.SNotification;
import TECHNIQUE.Session;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.net.ssl.SNIHostName;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Ruskov
 */
public class AssociationProfilController implements Initializable
{
    @FXML
    private Button buttonRetour;
    @FXML
    private Button buttonAbonnement;
    @FXML
    private Label lblnom;
    @FXML
    private Label lblabonnes;
    @FXML
    private Button buttonDetails;
    @FXML
    private Button buttonAjout;
    @FXML
    private Button buttonSupprimer;
    @FXML
    private Button buttonModifier;
    @FXML
    private ListView<Evenement> lve;
    private final SNotification sn=new SNotification();
    private final SAssociation sa=new SAssociation();
    private final SAbonnement sab=new SAbonnement();
    private final SEvenement se=new SEvenement();
    private final ArrayList<Integer> ari=new ArrayList<>(sab.ListAbonnes(Session.LoggedUser.getId_utilisateur()));
    private Association association;
    public void initController(Association a)
    {
        association=a;
        lblnom.setText(association.getNom_association());
        lblabonnes.setText("Abonnés: "+Integer.toString(sab.NombreAbonnes(association.getId_association())));
        if (association.getId_association()!=Session.LoggedUser.getId_utilisateur())
        {
            buttonAbonnement.setVisible(true);
            if (sab.VerifierAbonnement(Session.LoggedUser.getId_utilisateur(),association.getId_association()))
                    {
                        buttonAbonnement.setText("Abonné(e)");
                    }
            else
                    {
                        buttonAbonnement.setText("S'abonner");
                    }
        }
        else
        {
            buttonAbonnement.setVisible(false);
        }
        ObservableList<Evenement> obe=FXCollections.observableArrayList(se.ConsulterEvenement(association.getId_association()));
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
       if (association.getId_association()!=Session.LoggedUser.getId_utilisateur())
       {
           buttonAjout.setVisible(false);
           buttonSupprimer.setVisible(false);
           buttonModifier.setVisible(false);
       }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
      
    }    
    @FXML
    private void actionRetour(ActionEvent event) 
    {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Association.fxml"));
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
    

    @FXML
    private void actionAbonnement(ActionEvent event) 
    {
        if (!sab.VerifierAbonnement(Session.LoggedUser.getId_utilisateur(),association.getId_association()))
                    {   
                        sab.AjouterAbonnement(association.getId_association(),Session.LoggedUser.getId_utilisateur());
                        buttonAbonnement.setText("Abonné(e)");
                        association.setNombre_abonnes(association.getNombre_abonnes()+1);
                        lblabonnes.setText("Abonnés: "+Integer.toString(association.getNombre_abonnes()));
                    }
            else
                    {   
                        sab.SupprimerAbonnement(association.getId_association(),Session.LoggedUser.getId_utilisateur());
                        buttonAbonnement.setText("S'abonner");
                        association.setNombre_abonnes(association.getNombre_abonnes()-1);
                        lblabonnes.setText("Abonnés: "+Integer.toString(association.getNombre_abonnes()));
                    }
    }
    @FXML
    private void actionDetails(ActionEvent event)
    {
        if (lve.getSelectionModel().getSelectedItem()!=null)
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
        
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
    @FXML
    private void actionSupprimer(ActionEvent event)
    {   if(lve.getSelectionModel().getSelectedItem()!=null)
    {
             try
            {
                
                se.SupprimerEvenement(lve.getSelectionModel().getSelectedItem().getId_evenement());
                for (int i=0;i<ari.size();++i)
                {
                    sn.ajouterNotification(ari.get(i),Session.LoggedUser.getId_utilisateur(),lve.getSelectionModel().getSelectedItem().getId_evenement(),2);
                }
             Alert a=new Alert(Alert.AlertType.WARNING,"Succés",ButtonType.FINISH);
            a.setContentText("l'évenement "+lve.getSelectionModel().getSelectedItem().getId_evenement()+"a été supprimé avec succés !");
            a.showAndWait();
             ObservableList<Evenement>obe=FXCollections.observableArrayList(se.ConsulterEvenement(Session.LoggedUser.getId_utilisateur()));
            lve.setItems(obe);
            }catch(Exception e)
            {
                Alert a=new Alert(Alert.AlertType.WARNING,"Erreur",ButtonType.FINISH);
                a.setContentText("une erreur est survenue lors de la suppression de l'événement"+lve.getSelectionModel().getSelectedItem().getTitre_evenement());
                a.showAndWait();
            }
      }
            
        
    }
    @FXML
    private void actionModifier(ActionEvent event)
    {
        if (lve.getSelectionModel().getSelectedItem()!=null)
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
                                .text("L'association "+sa.chercherAssociation(n.getId_association()).getNom_association()+" a ajouté un nouvel événement ")
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
                                .text("L'association "+sa.chercherAssociation(n.getId_association()).getNom_association()+" a annulé un événement "+se.ChercherEvenement(n.getId_evenement()).getTitre_evenement())
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
                                .text("L'association "+sa.chercherAssociation(n.getId_association()).getNom_association()+" a modifié un événement "+se.ChercherEvenement(n.getId_evenement()).getTitre_evenement())
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
