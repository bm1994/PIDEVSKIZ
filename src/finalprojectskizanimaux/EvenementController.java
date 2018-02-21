/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Evenement;
import SERVICE.SEvenement;
import TECHNIQUE.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ruskov
 */
public class EvenementController implements Initializable {
    @FXML
    private Button evenementRetourButton;
    @FXML
    private TableView<Evenement> tve;
    @FXML
    private TableColumn<Evenement,String> tblclmtitre;
    @FXML
    private TableColumn<Evenement,String> tblclmsujet;
    @FXML
    private TableColumn<Evenement,Date> tblclmdate;
    @FXML
    private TableColumn<Evenement,String> tblclmlieu;
    @FXML
    private TableColumn<Evenement,Integer> tblclmparticipants;
    @FXML
    private Button detailsButton;
    @FXML
    private Button buttonAjouter;
    @FXML
    private Button buttonSupprimer;
    @FXML
    private Button buttonModifier;
    
    /**
     * Initializes the controller class.
     */
    
    public SEvenement se =new SEvenement();
    ObservableList<Evenement> obe=FXCollections.observableArrayList(se.ConsulterEvenement());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(Session.LoggedUser.getRole()!=2)
        {
            buttonAjouter.setVisible(false);
            buttonSupprimer.setVisible(false);
            buttonModifier.setVisible(false);
        }
        tblclmtitre.setCellValueFactory(new PropertyValueFactory<>("titre_evenement"));
        tblclmsujet.setCellValueFactory(new PropertyValueFactory<>("sujet_evenement"));
        tblclmdate.setCellValueFactory(new PropertyValueFactory<>("date_evenement"));
        tblclmlieu.setCellValueFactory(new PropertyValueFactory<>("lieu_evenement"));
        tblclmparticipants.setCellValueFactory(new PropertyValueFactory<>("nombre_interesses"));
        tve.setItems(obe);
    }    
    
    @FXML
    private void evenementDetails(ActionEvent event)
    {   
    if (
       tve.getSelectionModel().getSelectedItem()!=null)
        {
        try 
        {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenementDetails.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) detailsButton.getScene().getWindow();
        stage.close();
        EvenementDetailsController edc=loader.getController();
        edc.PassByEvenement(tve.getSelectionModel().getSelectedItem());
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
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void actionSupprimer(ActionEvent event) 
    {
        if (tve.getSelectionModel().getSelectedItem().getId_association()==Session.LoggedUser.getId_utilisateur())
        {   try
            {
            se.SupprimerEvenement(tve.getSelectionModel().getSelectedItem().getId_evenement());    
            Alert a=new Alert(AlertType.WARNING,"Succés",ButtonType.FINISH);
            ObservableList<Evenement>obe=FXCollections.observableArrayList(se.ConsulterEvenement());
            tve.setItems(obe);
            a.setContentText("l'évenement "+tve.getSelectionModel().getSelectedItem().getTitre_evenement()+"a été supprimé avec succés !");
            a.showAndWait();
            }catch(Exception e)
            {
                Alert a=new Alert(AlertType.WARNING,"Erreur",ButtonType.FINISH);
                a.setContentText("une erreur est survenue lors de la suppression de l'événement"+tve.getSelectionModel().getSelectedItem().getTitre_evenement());
                a.showAndWait();
            }
            
        }
        else
        {
             Alert b=new Alert(AlertType.WARNING,"Erreur",ButtonType.CANCEL);
             b.setContentText("Action non autorisée !");
             b.showAndWait();
        }
    }

    @FXML
    private void actionModifier(ActionEvent event) 
    {
        if (tve.getSelectionModel().getSelectedItem()!=null)
        {
            if (tve.getSelectionModel().getSelectedItem().getId_association()==Session.LoggedUser.getId_utilisateur())
            {
                
            
        try {
  
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenementModification.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) buttonModifier.getScene().getWindow();
        stage.close();
        EvenementModificationController emc=loader.getController();
        emc.initController(tve.getSelectionModel().getSelectedItem());
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
            }
            else
            {
             Alert b=new Alert(AlertType.WARNING,"Erreur",ButtonType.CANCEL);
             b.setContentText("Action non autorisée !");
             b.showAndWait();
            }
    }
}
}
