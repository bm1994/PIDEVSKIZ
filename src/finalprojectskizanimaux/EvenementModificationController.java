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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    public SEvenement sev=new SEvenement();
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
    
}
