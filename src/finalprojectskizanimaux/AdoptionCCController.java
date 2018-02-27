/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Animal;
import MODEL.Annonce;
import SERVICE.AnimalService;
import SERVICE.AnnonceService;
import static TECHNIQUE.Session.LoggedUser;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AdoptionCCController implements Initializable {
     public static Animal LoggedUser;
    @FXML
    private ListView<Annonce> liste2;
    @FXML
    private Button acceuil;
    @FXML
    private Button Ajouter_annonce_button3;
    @FXML
    private AnchorPane anochor2;
    @FXML
    private ChoiceBox<String> combochoix;
        ObservableList<String> comboList = FXCollections.observableArrayList("RaceAnimal", "SexeAnimal", "AgeAnimal");

    @FXML
    private TextField Recherche_Adoption_txt;
    @FXML
    private ComboBox<String> comboSexe;
    ObservableList<String> comboListsexe = FXCollections.observableArrayList("Male","Femelle");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficherA();
         combochoix.setItems(comboList);
      comboSexe.setItems(comboListsexe);
      Recherche_Adoption_txt.setVisible(true);
      comboSexe.setVisible(false);
      comboSexe.getSelectionModel().selectFirst();
      //**********************************
    combochoix.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //liste2.refresh();
                Recherche_Adoption_txt.setText("");
                switch (combochoix.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        Recherche_Adoption_txt.setVisible(true);
                        comboSexe.setVisible(false);
                        
                        break;
                    case 1:
                        comboSexe.setVisible(true);
                        Recherche_Adoption_txt.setVisible(false);
                      
                        break;
                    case 2:
                        Recherche_Adoption_txt.setVisible(true);
                        comboSexe.setVisible(false);
                        break;
                    default:
                        break;
                }
            }
        });
       Recherche_Adoption_txt.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filtrerAdoptionList((String) oldValue, (String) newValue);
            }

        });
        
    }    

    @FXML
    private void forumRetour(ActionEvent event) {
        try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        Parent root =loader.load();
        Stage stage=(Stage) acceuil.getScene().getWindow();
        stage.close();
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
        
    }

    @FXML
    private void PosterAnnonce1(ActionEvent event) {
          Alert alert = new Alert(AlertType.CONFIRMATION);
           alert.setTitle("Confirmation D'ajout");
           alert.setHeaderText("Avez vous déja un Animal que vous voulez le poster pour une annonce?");
alert.setContentText("Choose your option.");

ButtonType buttonTypeOne = new ButtonType("Oui");
ButtonType buttonTypeTwo = new ButtonType("Non");

ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo,buttonTypeCancel);

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == buttonTypeOne){
     
    try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajout_venteCC.fxml"));
        Parent root =loader.load();
        Stage stage=(Stage) Ajouter_annonce_button3.getScene().getWindow();
        stage.close();
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    
} else if (result.get() == buttonTypeTwo) {
     try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajout_animalCC.fxml"));
        Parent root =loader.load();
        Stage stage=(Stage) Ajouter_annonce_button3.getScene().getWindow();
        stage.close();
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
} 
 else {
    // ... user chose CANCEL or closed the dialog
    
}
        
    }
    
     private void AfficherA(){
        
        AnnonceService a= new AnnonceService();
        AnimalService an=new AnimalService();
      
        liste2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
          ObservableList<Annonce> lp = a.getAllAnnoncesAdoption();
          
          liste2.setCellFactory((ListView<Annonce> param) -> {
              ListCell<Annonce> cell = new ListCell<Annonce>() {
                  @Override
                  protected void updateItem(Annonce p , boolean bl) {
                      //System.out.println("ddddddd"+p);
                      super.updateItem(p, bl);
                      if(p!=null){
                        //  System.out.println("ppp"+p);
                         // System.out.println("bbb"+p.getId_animal());
                          Animal b=null;
                          
                          b=an.getAnimalbyId(p.getId_animal2());
                          
                          
                          Image img = new Image(p.getPhoto_annonce(), 200, 200, true, true, true) ;
                          ImageView imgV = new ImageView(img) ;
                                                
                          setGraphic(imgV);
                          
        setText("                   Titre de l'annonce: "+p.getTitre_annonce()+"\n \n                   Description : "
                                  +p.getDescription()+"\n \n                   Date de l'annonce : "+p.getDate_annonce()+
                                  "\n \n                   Type de l'annonce : "+p.getType_annonce()+"\n \n                   Nom de l'animal: "
          +b.getNom_animal()+"\n \n                   Race de l'animal : "+b.getRace_animal()+"\n \n                   Poids de l'animal : "+
             b.getPoids_animal()+"\n \n                   Sexe : " +b.getSexe()+"\n \n                   Type de l'animal : "  +b.getType_animal()+"\n \n                   Age de l'animal : "  +b.getAge_animal() );
                      }}};
              return cell;
              });
          liste2.setItems(lp);
    }

    @FXML
    private void onSelectAdoption(MouseEvent event) throws IOException{
          if(event.getClickCount() == 2){
          /*  ProfilpropAnnonceController.annonce = liste2.getSelectionModel().getSelectedItem();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("ProfilpropAnnonce.fxml"));
            */
 Annonce an =  liste2.getSelectionModel().getSelectedItem();
  //Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       LoggedUser =new Animal();
                       
                       LoggedUser.setId_animal(an.getId_animal2());
                      // System.out.println(LoggedUser.getId_animal());
//               LoggedAnimal.setId_animal(s.getId_animal2());
                        
                     //       System.out.println(s);
     
         anochor2.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("ProfilpropAnnonceAdoption.fxml"));
            anochor2.getChildren().add(newLoadedPane);

        
    }
    }

    @FXML
    private void comboRechercheSexe(ActionEvent event) {
        Recherche_Adoption_txt.setText(comboSexe.getValue());
    }
    
    //***************************************************************
       
       
       
       
  
        void filtrerAdoptionList(String oldValue, String newValue) {
            
        String choix = combochoix.getValue();
        AnimalService an = new AnimalService();
        AnnonceService a=new AnnonceService();
    
        if (choix.equals("RaceAnimal")) {
            
            ObservableList<Annonce> filteredList = FXCollections.observableArrayList();
            if (Recherche_Adoption_txt.getText() == null || newValue == null) {
                liste2.setItems(a.getAllAnnoncesAdoption());
            } else {
                liste2.setItems(a.getAllAnnoncesAdoption());
                newValue = newValue.toUpperCase();

                for (Annonce annonce : liste2.getItems()) {

                    String filterRaceAnimal = annonce.getId_animal().getRace_animal();

                    if (filterRaceAnimal.toUpperCase().contains(newValue)) {
                        filteredList.add(annonce);
                       // System.out.println(annonce);

                    }
               }
                liste2.setItems(filteredList);

            } 
        } else if (choix.equals("SexeAnimal")) {
            

            ObservableList<Annonce> filteredList = FXCollections.observableArrayList();
            if (Recherche_Adoption_txt.getText()== null || newValue == null) {
                liste2.setItems(a.getAllAnnoncesAdoption());
            } else {
                liste2.setItems(a.getAllAnnoncesAdoption());
                newValue = newValue.toUpperCase();

                for (Annonce annonce2 : liste2.getItems()) {

                    String filterSexeAnimal = annonce2.getId_animal().getSexe();

                    if (filterSexeAnimal.toUpperCase().contains(newValue)) {
                        filteredList.add(annonce2);

                    }

                }
                liste2.setItems(filteredList);

            }

        } else {
            
            ObservableList<Annonce> filteredList = FXCollections.observableArrayList();
            if ( Recherche_Adoption_txt.getText()== null || newValue == "") {
                liste2.setItems(a.getAllAnnoncesAdoption());
            } else {
                liste2.setItems(a.getAllAnnoncesAdoption());
              

                newValue = newValue.toUpperCase();

                for (Annonce annonce3 : liste2.getItems()) {
                   
                    int filterAgeAnimal = annonce3.getId_animal().getAge_animal();

                    if (filterAgeAnimal==Integer.valueOf(newValue)) {
                        filteredList.add(annonce3);

                    }

                }
                liste2.setItems(filteredList);

            }
        } }

     
       
       
       
       
       
       
//****************************************************************  
    
    
}
