/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Evenement;
import MODEL.Notification;
import SERVICE.SAbonnement;
import SERVICE.SAssociation;
import SERVICE.SEvenement;
import SERVICE.SNotification;
import TECHNIQUE.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.net.ssl.SNIHostName;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Ruskov
 */
public class EvenementModificationController implements Initializable {
    @FXML
    private TextField tftitree;
    @FXML
    private TextField tflieuu;
    @FXML
    private DatePicker tfdatee;
    @FXML
    private TextArea tfsujett;
    @FXML
    private Button buttonModifier;
    @FXML
    private Button buttonAnnuler;
    @FXML
    private Button buttonRetour;
    private Evenement evenement;
    private final SEvenement sev=new SEvenement();
    private final SNotification sn=new SNotification();
    private final SAssociation sa=new SAssociation();
    private final SAbonnement sab=new SAbonnement();
    private final ArrayList<Integer> ari=new ArrayList<>(sab.ListAbonnes(Session.LoggedUser.getId_utilisateur()));
    /**
     * Initializes the controller class.
     */
    public void initController(Evenement e)
    {
        evenement=e;
        tftitree.setText(evenement.getTitre_evenement());
        tflieuu.setText(evenement.getLieu_evenement());
        tfsujett.setText(evenement.getSujet_evenement());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionModifier(ActionEvent event) 
    {
        if (tftitree.getText().length()<5 || tflieuu.getText().length()<5 || tfsujett.getText().length()<5  )
        {
            Alert a=new Alert(Alert.AlertType.WARNING,"Erreur",ButtonType.OK);
            a.setContentText("Veuillez bien saisir les champs.");
            a.showAndWait();
        }
        else
        {   
        try
        {
            for (int i=0;i<ari.size();++i)
                {
                    sn.ajouterNotification(ari.get(i),Session.LoggedUser.getId_utilisateur(),evenement.getId_evenement(),3);
                }
        sev.ModifierEvenement(evenement.getId_evenement(),tftitree.getText(),tfsujett.getText(),Date.valueOf(tfdatee.getValue()),tflieuu.getText());
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Succés",ButtonType.FINISH);
        a.setContentText("l'évenement "+evenement.getTitre_evenement()+"a été modifié avec succés !");
        a.showAndWait();
        tftitree.setText("");
        tfsujett.setText("");
        tflieuu.setText("");
        tfdatee.setValue(null);   
        
        }
        catch(Exception e)
        {
            Alert a=new Alert(Alert.AlertType.WARNING,"Succés",ButtonType.OK);
        a.setContentText("Erreur lors de la modification de l'événement"+evenement.getTitre_evenement());
         a.showAndWait();
        }
        }
    }

    @FXML
    private void actionAnnuler(ActionEvent event) 
    {
        tftitree.setText("");
        tflieuu.setText("");
        tfsujett.setText("");
        tfdatee.setValue(null);
    }

    @FXML
    private void actionRetour(ActionEvent event) 
    {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Evenement.fxml"));
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
                                .text("L'association "+sa.chercherAssociation(n.getId_association()).getNom_association()+" a ajouté un nouvel événement"+sev.ChercherEvenement(n.getId_evenement()).getTitre_evenement())
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
                                .text("L'association "+sa.chercherAssociation(n.getId_association()).getNom_association()+" a annulé un événement"+sev.ChercherEvenement(n.getId_evenement()).getTitre_evenement())
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
                                .text("L'association "+sa.chercherAssociation(n.getId_association()).getNom_association()+" a modifié un événement"+sev.ChercherEvenement(n.getId_evenement()).getTitre_evenement())
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
