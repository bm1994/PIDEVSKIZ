/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.CommentaireEvenement;
import MODEL.Evenement;
import SERVICE.SCommentaireEvenement;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ruskov
 */
public class EvenementCommentaireController implements Initializable {
    @FXML
    private TextArea tacommentaire;
    @FXML
    private Button buttonCommenter;
    @FXML
    private Button buttonRetour;
    @FXML
    private Button buttonEffacer;
    @FXML
    private Button buttonModifier;
    @FXML
    private TableView<CommentaireEvenement> tvc;
    @FXML
    private TableColumn<CommentaireEvenement,Date> tblclmdate;
    @FXML
    private TableColumn<CommentaireEvenement,String> tblclmcontenu;
    private Evenement evenementt;
    public SCommentaireEvenement sce=new SCommentaireEvenement();
   public void initController(Evenement e)
    {
        
        evenementt =e;
        ObservableList<CommentaireEvenement> oce=FXCollections.observableArrayList(sce.ConsulterCommentaireEvenements(evenementt.getId_evenement()));
        tblclmdate.setCellValueFactory(new PropertyValueFactory<>("date_commentaire_evenement"));
        tblclmcontenu.setCellValueFactory(new PropertyValueFactory<>("contenu_commentaire_evenement"));
        tvc.setItems(oce);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionCommenter(ActionEvent event) 
    {
        if (!tacommentaire.getText().equalsIgnoreCase(""))
        {
            sce.AjouterCommentaireEvenement(evenementt.getId_evenement(),Session.LoggedUser.getId_utilisateur(),tacommentaire.getText());
            tacommentaire.setText("");
        }
        else
        {
            Alert a =new Alert(AlertType.ERROR,"erreur",ButtonType.OK);
            a.setContentText("Commentaire vide.");
            a.showAndWait();
        }
        ObservableList<CommentaireEvenement> oce=FXCollections.observableArrayList(sce.ConsulterCommentaireEvenements(evenementt.getId_evenement()));
        tvc.setItems(oce);
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

    @FXML
    private void actionEffacer(ActionEvent event) 
    {   if (tvc.getSelectionModel().getSelectedItem()!=null)
        {
        if (tvc.getSelectionModel().getSelectedItem().getId_utilisateur()==Session.LoggedUser.getId_utilisateur())
        {
            try
            {
                sce.SupprimerCommentaireEvenement(tvc.getSelectionModel().getSelectedItem().getId_commentaire_evenement());
                Alert a=new Alert(AlertType.CONFIRMATION,"succés",ButtonType.CANCEL);
                 ObservableList<CommentaireEvenement> oce=FXCollections.observableArrayList(sce.ConsulterCommentaireEvenements(evenementt.getId_evenement()));
                 tvc.setItems(oce);
                a.setContentText("commentaire supprimé avec succés");
                a.showAndWait();
            }catch(Exception e)
            {
                Alert a=new Alert(AlertType.WARNING,"erreur",ButtonType.CANCEL);
                a.setContentText("Erreur lors de la suppression du commentaire");
                a.showAndWait();
            }
        }
        else
        {
                Alert a=new Alert(AlertType.WARNING,"erreur",ButtonType.CANCEL);
                a.setContentText("Action non autorisée !");
                a.showAndWait();
        }
    }
    else
    {
         Alert a=new Alert(AlertType.WARNING,"erreur",ButtonType.CANCEL);
                a.setContentText("Veuillez selectionner un commentaire !");
                a.showAndWait();
    }
    }

    @FXML
    private void actionModifier(ActionEvent event) 
    {
        
    }
    
}
