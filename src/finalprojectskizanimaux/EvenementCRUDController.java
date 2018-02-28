/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Ruskov
 */
public class EvenementCRUDController implements Initializable {
    @FXML
    private TextField tftitre;
    @FXML
    private TextField tflieu;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextArea tfsujet;
    @FXML
    private Button buttonRetourr;
    @FXML
    private Button buttonAnnuler;
    
    private final SEvenement sev=new SEvenement();
    private final SNotification sn=new SNotification();
    private final SAbonnement sab=new SAbonnement();
    private final SAssociation  sa=new SAssociation();
    private final ArrayList<Integer> ari=new ArrayList<>(sab.ListAbonnes(Session.LoggedUser.getId_utilisateur()));
    @FXML
    private Button buttonEnvoyer;
    @FXML
    private Button deconnexionButton;
    @FXML
    private Button PropreAnnoncebutton;
    @FXML
    private Button PropreAnimaux1;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionRetourr(ActionEvent event) 
    {
   try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Evenement.fxml"));
        Parent root =loader.load();
        Stage stage=(Stage) buttonRetourr.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void actionEnvoi(ActionEvent event) 
    {
        if (tftitre.getText().length()<5 || tflieu.getText().length()<5 || tfsujet.getText().length()<5 || tfdate.getValue()==null )
        {
            Alert a=new Alert(Alert.AlertType.WARNING,"Erreur",ButtonType.OK);
            a.setContentText("Veuillez bien saisir les champs.");
            a.showAndWait();
        }
        else
        {   
        for (int i=0;i<ari.size();++i)
        {
            sn.ajouterNotification(ari.get(i),Session.LoggedUser.getId_utilisateur(),sev.idDernierEvenementAjoute(Session.LoggedUser.getId_utilisateur()),1);
        }
         sev.AjouterEvenement(Session.LoggedUser.getId_utilisateur(),tfsujet.getText(),Date.valueOf(tfdate.getValue()), tftitre.getText(), tflieu.getText());
         Alert a=new Alert(Alert.AlertType.WARNING,"Succés",ButtonType.OK);
         a.setContentText("Evénement ajouté.");
         a.showAndWait();
        tftitre.setText("");
        tfsujet.setText("");
        tflieu.setText("");
        tfdate.setValue(null);   
        }

    }

    @FXML
    private void actionAnnulation(ActionEvent event) 
       {
        tftitre.setText("");
        tfsujet.setText("");
        tflieu.setText("");
        tfdate.setValue(null);
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
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

}
