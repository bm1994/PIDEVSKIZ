/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Evenement;
import MODEL.Notification;
import SERVICE.SAssociation;
import SERVICE.SEvenement;
import SERVICE.SNotification;
import SERVICE.SParticipationEvenement;
import TECHNIQUE.Session;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Ruskov
 */
public class EvenementDetailsController implements Initializable {
    @FXML
    private Label lbltitre;
    @FXML
    private Label lblsujet;
    @FXML
    private Label lbldate;
    @FXML
    private Label lbllieu;
    @FXML
    private Button buttonParticiper;
    @FXML
    private Button retourButton;
    
    public Evenement evenement;
    /**
     * Initializes the controller class.
     */
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    /**
     * Initializes the controller class.
     */
    
    public SParticipationEvenement spe=new SParticipationEvenement();
    public SNotification sn=new SNotification();
    public SEvenement se=new SEvenement();
    public SAssociation sa=new SAssociation();
    @FXML
    private Label lblparticipants;
    @FXML
    private Button buttonCommentaire;
    @FXML
    private Button deconnexionButton;
    @FXML
    private Button PropreAnnoncebutton;
    @FXML
    private Button PropreAnimaux1;
    
    public void PassByEvenement(Evenement e)
    {   evenement=e;
        lbltitre.setText(evenement.getTitre_evenement());
        lblsujet.setText("Sujet: "+evenement.getSujet_evenement());
        lbldate.setText("Date: "+evenement.getDate_evenement().toString());
        lbllieu.setText("Lieu: "+evenement.getLieu_evenement());
        lblparticipants.setText("Participants: "+evenement.getNombre_interesses());
        if (spe.VerifierParticipation(Session.LoggedUser.getId_utilisateur(), evenement.getId_evenement()))
        {
            buttonParticiper.setText("Annuler");
        }
        else
        {
            buttonParticiper.setText("Participer");
        }
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        
    }

    @FXML
    private void actionParticiper(ActionEvent event) {
       
       
        if (!spe.VerifierParticipation(Session.LoggedUser.getId_utilisateur(), evenement.getId_evenement()))
        {   
            spe.AjouterParticipation(Session.LoggedUser.getId_utilisateur(), evenement.getId_evenement());
            se.IncrementNombreInteresses(evenement.getId_evenement());
            evenement.setNombre_interesses(evenement.getNombre_interesses()+1);
            lblparticipants.setText("Participants: "+Integer.toString(evenement.getNombre_interesses()));
            buttonParticiper.setText("Annuler");
        }
        else
        {   
            spe.SupprimerParticipation(Session.LoggedUser.getId_utilisateur(), evenement.getId_evenement());
            se.DecrementNombreInteresses(evenement.getId_evenement());
            evenement.setNombre_interesses(evenement.getNombre_interesses()-1);
            lblparticipants.setText("Participants: "+Integer.toString(evenement.getNombre_interesses()));
            buttonParticiper.setText("Participer");
        }
    }

    @FXML
    private void evenementRetour(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Evenement.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) retourButton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void actionCommentaires(ActionEvent event) 
    {
        try {
  
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenementCommentaire.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) buttonCommentaire.getScene().getWindow();
        EvenementCommentaireController ecc=loader.getController();
        ecc.initController(evenement);

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

    


    

