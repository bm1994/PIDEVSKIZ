/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Evenement;
import SERVICE.SEvenement;
import SERVICE.SParticipationEvenement;
import TECHNIQUE.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
    private Evenement evenement;
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
    public SEvenement se=new SEvenement();
    @FXML
    private Label lblparticipants;
    @FXML
    private Button buttonCommentaire;
    
    public void PassByEvenement(Evenement e)
    {   evenement=e;
        lbltitre.setText(evenement.getTitre_evenement());
        lblsujet.setText("Sujet: "+evenement.getSujet_evenement());
        lbldate.setText("Date: "+evenement.getDate_evenement().toString());
        lbllieu.setText("Lieu: "+evenement.getLieu_evenement());
        lblparticipants.setText("Participants: "+evenement.getNombre_interesses());
        if (spe.VerifierParticipation(Session.LoggedUser.getId_utilisateur(), evenement.getId_evenement()))
        {
            buttonParticiper.setText("Participe");
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
        stage.close();
        EvenementCommentaireController ecc=loader.getController();
        ecc.initController(evenement);
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
    
   
} 

    


    

