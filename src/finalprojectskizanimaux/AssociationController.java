/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Association;
import MODEL.Notification;
import SERVICE.SAssociation;
import SERVICE.SEvenement;
import SERVICE.SNotification;
import TECHNIQUE.Session;
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
 *
 * @author Ruskov
 */
public class AssociationController implements Initializable{
    @FXML
    private Button evenementRetourButton;
    @FXML
    private Button buttonProfil;
    @FXML
    private ListView<Association> lva;

   public SAssociation sa=new SAssociation();
   public SNotification sn=new SNotification();
   public SEvenement se=new SEvenement();
   
   ObservableList<Association> obs=FXCollections.observableArrayList(sa.listerAssociation());
    
   
    
  
   @Override
    public void initialize(URL url, ResourceBundle rb) 
    {   
        lva.setCellFactory((ListView<Association> arg0) -> {
            ListCell<Association> cell = new ListCell<Association>() {
                @Override
                protected void updateItem(Association a, boolean btl) {
                    super.updateItem(a, btl);

                    if (a != null) 
                    {

                        setText("Nom : "+a.getNom_association()+ "\n" + " E-mail : " + a.getEmail_association()+ "\n" + " Téléphone : " + a.getTelephone_association()+ "\n" + " Abonnés : " + a.getNombre_abonnes());
                        setFont(Font.font("Berlin Sans FB Demi Bold", 12));

                        // setAlignment(Pos.CENTER);
                    }
                }

            };
            return cell;
        });
       lva.setItems(obs);
        // TODO
      
    }    
   @FXML
   private void actionProfil(ActionEvent event)
   {
       if (lva.getSelectionModel().getSelectedItem()!=null)
        {
        try {
  
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AssociationProfil.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) buttonProfil.getScene().getWindow();
        stage.close();
        AssociationProfilController afc=loader.getController();
        afc.initController(lva.getSelectionModel().getSelectedItem());
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
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
    private void evenementRetour(ActionEvent event) {
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
