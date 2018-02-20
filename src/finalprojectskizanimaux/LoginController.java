/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import SERVICE.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class LoginController implements Initializable {
    
    private Label label;
    @FXML
    private TextField loginid;
    @FXML
    private PasswordField passwordid;
    private Button close;
    @FXML
    private Button loginButton;
    @FXML
    private Button sinscrireButton;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Authentifier(ActionEvent event) {
        try {
            UserService userrser = new UserService();
        String b = userrser.getelemntbylogin(loginid.getText());
        int arole=userrser.getelemntbyrole(loginid.getText());
        if ((b!=null)&&b.equals(passwordid.getText())&& arole==2) { 
            
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        Parent root =loader.load();
        Stage stage=(Stage) loginButton.getScene().getWindow();
        stage.close();
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show(); 
   
        } else { 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention ! ");
            alert.setHeaderText(null);
            alert.setContentText("id et/ou mot de passe incorrecte ");
            alert.showAndWait();

        }

            
            
            
          
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void Sinscrire(ActionEvent event) {
         try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Sinscrire.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) sinscrireButton.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
        
    }
    

