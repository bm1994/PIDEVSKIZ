/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import SERVICE.SEvenement;
import TECHNIQUE.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    
    public SEvenement sev=new SEvenement();
  
    @FXML
    private Button buttonModifier;
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
        if (tftitre.getText().length()<5 || tflieu.getText().length()<5 || tfsujet.getText().length()<5  )
        {
            Alert a=new Alert(Alert.AlertType.WARNING,"Erreur",ButtonType.OK);
            a.setContentText("Veuillez bien saisir les champs.");
            a.showAndWait();
        }
        else
        {   
        
        sev.AjouterEvenement(Session.LoggedUser.getId_utilisateur(),tfsujet.getText(),Date.valueOf(tfdate.getValue()), tftitre.getText(), tflieu.getText()); 
        
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
    
}
