/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Association;
import SERVICE.SAssociation;
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
import javafx.scene.control.Alert;
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
public class AssociationController implements Initializable {
    @FXML
    private TableView<Association> tv;
    @FXML
    private TableColumn<Association,String> tblclmnom;
    @FXML
    private TableColumn<Association,String> tblclmtel;
    @FXML
    private TableColumn<Association,String> tblclmemail;
    
    @FXML
    private Button evenementRetourButton;

    /**
     * Initializes the controller class.
     */
    public SAssociation sa=new SAssociation();
    ObservableList<Association> obs=FXCollections.observableArrayList(sa.listerAssociation());
    
   
    @FXML
    private Button buttonProfil;
    @FXML
    private TableColumn<?, ?> tblclmbutt;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {   
        
        tblclmnom.setCellValueFactory(new PropertyValueFactory<>("nom_association"));
        tblclmtel.setCellValueFactory(new PropertyValueFactory<>("telephone_association"));
        tblclmemail.setCellValueFactory(new PropertyValueFactory<>("email_association"));
        tv.setItems(obs);
        // TODO
    }    
   @FXML
   private void actionProfil(ActionEvent event)
   {
       if (tv.getSelectionModel().getSelectedItem()!=null)
        {
        try {
  
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AssociationProfil.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) buttonProfil.getScene().getWindow();
        stage.close();
        AssociationProfilController afc=loader.getController();
        afc.initController(tv.getSelectionModel().getSelectedItem());
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
    
    
}
