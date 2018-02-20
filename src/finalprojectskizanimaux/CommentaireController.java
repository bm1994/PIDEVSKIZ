/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Commentaire_sujet;
import MODEL.Sujet;
import SERVICE.Service_Commentaire_sujet;
import TECHNIQUE.Session;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author habib
 */
public class CommentaireController implements Initializable {
	Sujet sujet ;
	   public Sujet getSujet() {
			return sujet;
		}
		public void setSujet(Sujet sujet) {
			this.sujet = sujet;
		}
    @FXML
    private Button afficher_sujet;
    @FXML
    private Button acceuil;
    @FXML
    private ListView<Commentaire_sujet> list_commentaire;
    @FXML
    private TextField titre;
    @FXML
    private TextArea contenu;
    @FXML
    private TextArea contenu_commentaire;
    @FXML
    private Button Commenter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void affiche (Sujet s) {
		
		titre.setText(s.getTitre());
		contenu.setText(s.getContenu());
		   Service_Commentaire_sujet sv= new  Service_Commentaire_sujet();
		   ObservableList<Commentaire_sujet> list = FXCollections.observableArrayList(	
		 	    		sv.Afficher_commentaire(s)
		 	    		);
		   list_commentaire.setItems(list);
	}

    @FXML
    private void forumRetour(ActionEvent event) {
    }
public void ajouter(Sujet s) {
    	 
    sujet = s ;
    	
    }
   
    @FXML
    private void commenter(ActionEvent event) {
        Service_Commentaire_sujet sv = new Service_Commentaire_sujet();
   	Commentaire_sujet c = new Commentaire_sujet (Session.LoggedUser.getId_utilisateur(),sujet.getId_sujet(),contenu_commentaire.getText(),"12/1/7");
   	
   	sv.Ajouter_commentaire(c);
    }
    
}
