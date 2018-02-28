/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.CommentaireEvenement;
import MODEL.Evenement;
import MODEL.Notification;
import MODEL.User;
import SERVICE.SAssociation;
import SERVICE.SCommentaireEvenement;
import SERVICE.SEvenement;
import SERVICE.SNotification;
import SERVICE.UserService;
import TECHNIQUE.Session;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Ruskov
 */
public class EvenementCommentaireController implements Initializable{
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
    private Button buttonConfimer;
    @FXML
    private ListView<CommentaireEvenement> lvc;
    private Evenement evenementt;
    private SCommentaireEvenement sce=new SCommentaireEvenement();
    private UserService us=new UserService();
    private SNotification sn=new SNotification();
    private SEvenement se=new SEvenement();
    private SAssociation sa=new SAssociation();
    private int id_commentaire=0;
    @FXML
    private AnchorPane ecAnchor;
    public ObservableList<CommentaireEvenement> oce;
    @FXML
    private Button deconnexionButton;
    @FXML
    private Button PropreAnnoncebutton;
    @FXML
    private Button PropreAnimaux1;
        
    public void initController(Evenement e)
    {
        
        evenementt =e;
        oce=FXCollections.observableArrayList(sce.ConsulterCommentaireEvenements(evenementt.getId_evenement()));
        lvc.setCellFactory((ListView<CommentaireEvenement> arg0) -> {
            ListCell<CommentaireEvenement> cell = new ListCell<CommentaireEvenement>() {
                @Override
                protected void updateItem(CommentaireEvenement ce, boolean btl) {
                    super.updateItem(ce, btl);  
                    if (ce != null) {
                       UserService us=new UserService();
                       User user= us.findById(ce.getId_utilisateur());
                       if (user.getPrenom()!=null)
                {
                    
                
                       
                            setText(user.getPrenom()+" "+user.getNom()+"                          "+ce.getDate_commentaire_evenement()+"\n"+ce.getContenu_commentaire_evenement());
                }
                       else
                {
                        setText(user.getNom()+"                          "+ce.getDate_commentaire_evenement()+"\n"+ce.getContenu_commentaire_evenement());
                    
                }
                    
                        setFont(Font.font("Berlin Sans FB Demi Bold", 12));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
       buttonConfimer.setVisible(false);
        lvc.setItems(oce);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    

    @FXML
    private void actionCommenter(ActionEvent event) 
    {
        if (!tacommentaire.getText().equalsIgnoreCase(""))
        {
            sce.AjouterCommentaireEvenement(evenementt.getId_evenement(),Session.LoggedUser.getId_utilisateur(),tacommentaire.getText());
            tacommentaire.setText("");
            initController(evenementt);
        }
        else
        {
            Alert a =new Alert(Alert.AlertType.ERROR,"erreur",ButtonType.OK);
            a.setContentText("Commentaire vide.");
            a.showAndWait();
        }
        
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
    {   if (lvc.getSelectionModel().getSelectedItem()!=null)
        {
        if (lvc.getSelectionModel().getSelectedItem().getId_utilisateur()==Session.LoggedUser.getId_utilisateur())
        {
            try
            {
                sce.SupprimerCommentaireEvenement(lvc.getSelectionModel().getSelectedItem().getId_commentaire_evenement());
                Alert a=new Alert(Alert.AlertType.CONFIRMATION,"succés",ButtonType.CANCEL);
                a.setContentText("commentaire supprimé avec succés");
                a.showAndWait();
                initController(evenementt);
            }catch(Exception e)
            {
                Alert a=new Alert(Alert.AlertType.WARNING,"erreur",ButtonType.CANCEL);
                a.setContentText("Erreur lors de la suppression du commentaire");
                a.showAndWait();
            }
        }
        else
        {
                Alert a=new Alert(Alert.AlertType.WARNING,"erreur",ButtonType.CANCEL);
                a.setContentText("Action non autorisée !");
                a.showAndWait();
        }
    
        }
    else
    {
         Alert a=new Alert(Alert.AlertType.WARNING,"erreur",ButtonType.CANCEL);
                a.setContentText("Veuillez selectionner un commentaire !");
                a.showAndWait();
    }
    }

    @FXML
    private void actionModifier(ActionEvent event) 
    {
        if (lvc.getSelectionModel().getSelectedItem()!=null)
        {
            if (lvc.getSelectionModel().getSelectedItem().getId_utilisateur()==Session.LoggedUser.getId_utilisateur())
            {
                tacommentaire.setText(lvc.getSelectionModel().getSelectedItem().getContenu_commentaire_evenement());
                buttonCommenter.setVisible(false);
                buttonConfimer.setVisible(true);
                id_commentaire=lvc.getSelectionModel().getSelectedItem().getId_commentaire_evenement();
                
             }
            else
            {
                Alert a=new Alert(AlertType.ERROR,"erreur",ButtonType.OK);
            a.setContentText("Commentaire vide");
            a.showAndWait();
            }
        }
        else
        {
            Alert a=new Alert(AlertType.ERROR,"erreur",ButtonType.OK);
            a.setContentText("Veuillez sélectionner un commentaire");
            a.showAndWait();
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
    private void actionConfirmer(ActionEvent event) 
    {
        if (!tacommentaire.getText().equals(""))
        {
            sce.ModifierCommentaireEvenement(id_commentaire , tacommentaire.getText());
            buttonConfimer.setVisible(false);
            buttonCommenter.setVisible(true);
            tacommentaire.setText("");
            Alert a=new Alert(AlertType.INFORMATION,"Information",ButtonType.OK);
            a.setContentText("Commentaire modifié avec succés");
            a.showAndWait();
            initController(evenementt);
        }
        else
        {
            Alert a=new Alert(AlertType.ERROR,"erreur",ButtonType.OK);
            a.setContentText("Commentaire vide");
            a.showAndWait();
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
