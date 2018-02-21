/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.User;
import SERVICE.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ProfilpropAnnonceController implements Initializable {
 int id;
    @FXML
    private Button Retour;
    @FXML
    private Button acceuil;
    @FXML
    private Label prenom;
    @FXML
    private Label nom;
    @FXML
    private Label adresse;
    @FXML
    private Label email;
    @FXML
    private Label num_tel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* UserService us = new UserService();
        User u = us.findById(id);
        prenom.setText(u.getPrenom());
        System.out.println(u.getPrenom());*/
    }    

    @FXML
    private void RetourAcc(ActionEvent event) {
    }

    @FXML
    private void forumRetour(ActionEvent event) {
    }
    public void redirection(int id)
    {
        this.id=id;
    }
    }

