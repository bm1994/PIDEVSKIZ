/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ProfilController implements Initializable {

    private Button profilRetourButton;
    @FXML
    private Button retourprofilbutton;
    private Label labelprofilbm;
    @FXML
    private Label labelprofilbmnom;
    @FXML
    private Label labelprofilbmmail;
    @FXML
    private Label labelprofilbmusername;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
labelprofilbmnom.setText(Session.LoggedUser.getNom()); 
 labelprofilbmmail.setText(Session.LoggedUser.getEmail());  
labelprofilbmusername.setText(Session.LoggedUser.getLogin());  
 
    }    

    @FXML
    private void retourAcc(ActionEvent event) {
             try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) retourprofilbutton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void Updateprofileuser(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProfileUser.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) retourprofilbutton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }

        
    }

  

    
}
