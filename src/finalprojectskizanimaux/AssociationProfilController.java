/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Association;
import MODEL.Evenement;
import SERVICE.SAbonnement;
import SERVICE.SEvenement;
import TECHNIQUE.Session;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.naming.spi.InitialContextFactory;
import sun.util.calendar.BaseCalendar.Date;

/**
 *
 * @author Ruskov
 */
public class AssociationProfilController implements Initializable{
    @FXML
    private Button buttonRetour;
    @FXML
    private Button buttonAbonnement;
    @FXML
    private TableView<Evenement> tva;
    @FXML
    private TableColumn<Evenement, String> tblclmtitree;
    @FXML
    private TableColumn<Evenement, String> tblclmsujett;
    @FXML
    private TableColumn<Evenement, Date> tblclmdatee;
    @FXML
    private TableColumn<Evenement, String> tblclmlieuu;
    @FXML
    private TableColumn<Evenement, Integer> tblclmparticipantss;
    @FXML
    private Label lblnom;
    @FXML
    private Label lblabonnes;
    
    private Association association;
    public SAbonnement sab=new SAbonnement();
    public SEvenement se=new SEvenement();
    

    
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
        ObservableList<Evenement> obee=FXCollections.observableArrayList(se.ConsulterEvenement(association.getId_association()));
        tblclmtitree.setCellValueFactory(new PropertyValueFactory<>("titre_evenement"));
        tblclmsujett.setCellValueFactory(new PropertyValueFactory<>("sujet_evenement"));
        tblclmdatee.setCellValueFactory(new PropertyValueFactory<>("date_evenement"));
        tblclmlieuu.setCellValueFactory(new PropertyValueFactory<>("lieu_evenement"));
        tblclmparticipantss.setCellValueFactory(new PropertyValueFactory<>("nombre_interesses"));
        tva.setItems(obee);
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
    
}
