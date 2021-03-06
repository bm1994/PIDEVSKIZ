/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Admin;
import MODEL.Annonce;
import MODEL.Association;
import MODEL.User;
import MODEL.Veterinaire;
import SERVICE.AdminService;
import SERVICE.AnnonceService;
import SERVICE.SAssociation;
import SERVICE.UserService;
import SERVICE.VeterinaireService;
import TECHNIQUE.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AdminController implements Initializable {
    private int idbm;
    @FXML
    private ImageView lolo;
    @FXML
    private Pane paneGAssociation;
    @FXML
    private Text confirmpasswordtextadmin1;
    @FXML
    private TextField nomassociationadmin;
    @FXML
    private TextField numassociationadmin;
    @FXML
    private TextField adresseassociationadmin;
    @FXML
    private TextField mailassociationadmin;
    @FXML
    private TextField loginassociationadmin;
    @FXML
    private PasswordField mdpassociationadmin;
    @FXML
    private PasswordField confirmmdpassotiationasmin;
    @FXML
    private Button Ajouterassociationadmin;
    @FXML
    private ImageView annulerGAssoimage1;
    @FXML
    private Button AddAssoAdmin;
    @FXML
    private ListView<Association> ListAsso;
    @FXML
    private Button affichergassociation;
    @FXML
    private ListView<Veterinaire> list_utilisateur;
    @FXML
    private ImageView annulerGuserimage1888;

    public int getIdbm() {
        return idbm;
    }

    public void setIdbm(int idbm) {
        this.idbm = idbm;
    }

    private TextArea AdminField;
    @FXML
    private Text AdminNom;
    @FXML
    private Text AdminPrenom;
    @FXML
    private Text AdminNumero;
    @FXML
    private Text adminAdresse;
    @FXML
    private Text adminMail;
    @FXML
    private Text adminLogin;
    @FXML
    private Pane PaneAdminModifier;
    @FXML
    private TextField AdminNomModifier;
    @FXML
    private Button ModifierButtonAdminn;
    @FXML
    private AnchorPane PaneIdAdminn;
    @FXML
    private TextField AdminPrenomModifier;
    @FXML
    private TextField AdminModifierNumero;
    @FXML
    private TextField AdminModifierAdresse;
    @FXML
    private TextField AdminModifierMail;
    @FXML
    private TextField AdminModifierLogin;
    @FXML
    private Pane paneAffichmdp;
    @FXML
    private Text passwordtextadmin;
    @FXML
    private TextField ModifierAdminMdp;
    @FXML
    private Text confirmpasswordtextadmin;
    @FXML
    private TextField ModifierAdminMdpConfirm;
    @FXML
    private ImageView annulerimage1;
    private ImageView annulermdpimage;
    @FXML
    private ImageView annulerimage2;
    @FXML
    private ImageView annulerimage3;
    @FXML
    private ImageView annulerimage4;
    @FXML
    private ImageView annulerimage5;
    @FXML
    private ImageView annulerimage6;

    /**
     * Initializes the controller class.
     */
     AudioClip note = new AudioClip(this.getClass().getResource("sound.mp3").toString());
    @FXML
    private Button DeconnexionAdminbm;
    @FXML
    private ListView<User> List;
    AdminService aas=new AdminService();
    List<User> listuser= aas.getAllUSER();
   // List<Association> listassociation= aas.getAllAssociation();

    @FXML
    private Pane Guseraffich;
    @FXML
    private ImageView annulerGuserimage;
    @FXML
    private Button supp112;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list_utilisateur.setVisible(false);
        annulerimage1.setVisible(false);
        PaneAdminModifier.setVisible(false);
        AdminNomModifier.setVisible(false);
        AdminPrenomModifier.setVisible(false);
        AdminModifierNumero.setVisible(false);
        AdminModifierAdresse.setVisible(false);
        AdminModifierMail.setVisible(false);
        AdminModifierLogin.setVisible(false);
        ModifierAdminMdp.setVisible(false);
        ModifierAdminMdpConfirm.setVisible(false);
        passwordtextadmin.setVisible(false);
        confirmpasswordtextadmin.setVisible(false);
        AdminNom.setText(Session.LoggedUser.getNom());
        AdminPrenom.setText(Session.LoggedUser.getPrenom());
        adminAdresse.setText(Session.LoggedUser.getAdresse());
        AdminNumero.setText(String.valueOf(Session.LoggedUser.getTelephone()));
        adminMail.setText(Session.LoggedUser.getEmail());
        adminLogin.setText(Session.LoggedUser.getLogin());
        AdminNomModifier.setText(Session.LoggedUser.getNom());
        AdminPrenomModifier.setText(Session.LoggedUser.getPrenom());
        AdminModifierNumero.setText(String.valueOf(Session.LoggedUser.getTelephone()));
        AdminModifierAdresse.setText(Session.LoggedUser.getAdresse());
        AdminModifierMail.setText(Session.LoggedUser.getEmail());
        AdminModifierLogin.setText(Session.LoggedUser.getLogin());
        annulerimage2.setVisible(false);
        annulerimage3.setVisible(false);
        annulerimage4.setVisible(false);
        annulerimage5.setVisible(false);
        annulerimage6.setVisible(false);
        ModifierButtonAdminn.setVisible(false);
        annulerGuserimage1888.setVisible(false);
       // Guseraffich.setVisible(false);
       List.setVisible(false);
       annulerGuserimage.setVisible(false);
       supp112.setVisible(false);
               lolo.setVisible(false);
               paneGAssociation.setVisible(false);
               Ajouterassociationadmin.setVisible(false);
               annulerGAssoimage1.setVisible(false);
               affichergassociation.setVisible(false);
               ListAsso.setVisible(false);

       
        ObservableList<User> items = FXCollections.observableArrayList(listuser);

        List.setCellFactory((ListView<User> arg0) -> {
            ListCell<User> cell = new ListCell<User>() {
                @Override
                protected void updateItem(User e, boolean btl) {
                    super.updateItem(e, btl);

                    if (e != null) {
                        File file = new File("src\\images\\aaz.png");
                        file.getParentFile().mkdirs();
                        Image IMAGE_RUBY = new Image(file.toURI().toString());
                        //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());

                        ImageView imgview = new ImageView(IMAGE_RUBY);

                        setGraphic(imgview);

                        imgview.setFitHeight(100);
                        imgview.setFitWidth(100);
                        Rectangle clip = new Rectangle(
                                imgview.getFitWidth(), imgview.getFitHeight()
                        );

                        clip.setArcWidth(20);
                        clip.setArcHeight(20);
                        imgview.setClip(clip);

                        // snapshot the rounded image.
                        SnapshotParameters parameters = new SnapshotParameters();
                        parameters.setFill(Color.TRANSPARENT);
                        WritableImage image = imgview.snapshot(parameters, null);

                        // remove the rounding clip so that our effect can show through.
                        imgview.setClip(null);

                        // apply a shadow effect.
                        imgview.setEffect(new DropShadow(20, Color.BLACK));

                        // store the rounded image in the imageView.
                        imgview.setImage(image);
                        
                        List.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            

                            @Override
                            public void handle(MouseEvent event) {
                                 supp112.setVisible(false);
                                 lolo.setVisible(false);

                                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                   supp112.setVisible(true);
                                   lolo.setVisible(true);

                                    User cov = List.getItems().get(List.getSelectionModel().getSelectedIndex());
                                    setIdbm(cov.getId_utilisateur());
                                    
                                
                            
                                    


                                    //System.out.println(e.getId_utilisateur());
                                    
                                }
                            }

                        });
                                   // System.out.println(e.getId_utilisateur());
                        setText("Nom:    "+e.getNom()+ "\n" + "Prenom      :" + e.getPrenom() + "\n" + "Adresse     :" + e.getAdresse() + "\n" + "Numero        :" + e.getTelephone()+ "\n" + "Mail      :"+e.getEmail()+"\n"+"Login             :"+e.getLogin());

                        setFont(Font.font("Berlin Sans FB Demi Bold", 12));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
        List.setItems(items);
        
  

       
        // TODO
    }    

    @FXML
    private void ModifierAdmin(ActionEvent event) {
        if(!ModifierAdminMdp.getText().equals(ModifierAdminMdpConfirm.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("mot de passe n'est pas identique");
            alert.showAndWait();
        }
        else if(ModifierAdminMdp.getText().equals("")){
            ModifierAdminMdp.setStyle("-fx-background-color:#F78181");
        }
        else{
        Session.LoggedUser.setNom(AdminNomModifier.getText());
        Session.LoggedUser.setPrenom(AdminPrenomModifier.getText());
        Session.LoggedUser.setAdresse(AdminModifierAdresse.getText());
        Session.LoggedUser.setTelephone(Integer.parseInt(AdminModifierNumero.getText()));
        Session.LoggedUser.setEmail(AdminModifierMail.getText());
        Session.LoggedUser.setLogin(AdminModifierLogin.getText());
        Session.LoggedUser.setMotDePasse(ModifierAdminMdp.getText());
        Session.LoggedUser.setId_utilisateur(Session.LoggedUser.getId_utilisateur());
        
        UserService userservicee=new UserService();
        userservicee.UpdateUser(Session.LoggedUser);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText(null);
            alert.setContentText("Votre Compte a ete modifier");
            alert.showAndWait();
        
               try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) ModifierButtonAdminn.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
        } 
    }

    @FXML
    private void ActiverModifierNomAdmin(MouseEvent event) {
        AdminNomModifier.setVisible(true);
        ModifierButtonAdminn.setVisible(true);
         paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);
        annulerimage1.setVisible(true);
        

        
    }

    @FXML
    private void PaneReturnFromModifierAdmin(MouseEvent event) {
    
   
    }

    @FXML
    private void ActiverModifierPrenomAdmin(MouseEvent event) {
        AdminPrenomModifier.setVisible(true);
        ModifierButtonAdminn.setVisible(true);
         paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);
        annulerimage2.setVisible(true);
    }

    @FXML
    private void ActiverModifierNumeroAdmin(MouseEvent event) {
        AdminModifierNumero.setVisible(true);
        ModifierButtonAdminn.setVisible(true);
         paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);
        annulerimage3.setVisible(true);

    }

    @FXML
    private void ActivierModifierAdresseAdmin(MouseEvent event) {
        AdminModifierAdresse.setVisible(true);
        ModifierButtonAdminn.setVisible(true);
         paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);
        annulerimage4.setVisible(true);

    }

    @FXML
    private void ActiverModifierMailAdmin(MouseEvent event) {
        AdminModifierMail.setVisible(true);
        ModifierButtonAdminn.setVisible(true);
         paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);
        annulerimage5.setVisible(true);

    }

    @FXML
    private void ActiverModifierLoginAdmin(MouseEvent event) {
        AdminModifierLogin.setVisible(true);
        ModifierButtonAdminn.setVisible(true);
        paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);
         annulerimage6.setVisible(true);

        
    }

    @FXML
    private void ActiverCinfirmerMdpAdmin(KeyEvent event) {
        ModifierAdminMdpConfirm.setVisible(true);
        confirmpasswordtextadmin.setVisible(true);
       ModifierAdminMdp.setStyle("-fx-background-color:##FFFFFF");

    }

    private void AnnulerModifnomadmin(KeyEvent event) {
        
    }

    @FXML
    private void AnnulerModifnomadmin(MouseEvent event) {
        annulerimage1.setVisible(false);
        AdminNomModifier.setVisible(false);
        ModifierAdminMdp.setVisible(false);
        passwordtextadmin.setVisible(false);
        confirmpasswordtextadmin.setVisible(false);
        ModifierAdminMdpConfirm.setVisible(false);
        paneAffichmdp.setVisible(true);
         if(AdminModifierMail.isVisible() || AdminModifierMail.isVisible()|| AdminModifierAdresse.isVisible()|| AdminModifierNumero.isVisible()||AdminPrenomModifier.isVisible()|| AdminNomModifier.isVisible()||AdminModifierLogin.isVisible()){
ModifierButtonAdminn.setVisible(true);
        paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);        }
        else{
                    ModifierButtonAdminn.setVisible(false);

        }
    }

    @FXML
    private void AnnulerModifprenomadmin(MouseEvent event) {
        annulerimage2.setVisible(false);
        AdminPrenomModifier.setVisible(false);
        ModifierAdminMdp.setVisible(false);
        passwordtextadmin.setVisible(false);
        confirmpasswordtextadmin.setVisible(false);
        ModifierAdminMdpConfirm.setVisible(false);
        paneAffichmdp.setVisible(true);
         if(AdminModifierMail.isVisible() || AdminModifierMail.isVisible()|| AdminModifierAdresse.isVisible()|| AdminModifierNumero.isVisible()||AdminPrenomModifier.isVisible()|| AdminNomModifier.isVisible()||AdminModifierLogin.isVisible()){
ModifierButtonAdminn.setVisible(true);
        paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);        }
        else{
                    ModifierButtonAdminn.setVisible(false);

        }
    }

    @FXML
    private void AnnulerModifNumadmin(MouseEvent event) {
        annulerimage3.setVisible(false);
        AdminModifierNumero.setVisible(false);
        ModifierAdminMdp.setVisible(false);
        passwordtextadmin.setVisible(false);
        confirmpasswordtextadmin.setVisible(false);
        ModifierAdminMdpConfirm.setVisible(false);
        paneAffichmdp.setVisible(true);
 if(AdminModifierMail.isVisible() || AdminModifierMail.isVisible()|| AdminModifierAdresse.isVisible()|| AdminModifierNumero.isVisible()||AdminPrenomModifier.isVisible()|| AdminNomModifier.isVisible()||AdminModifierLogin.isVisible()){
ModifierButtonAdminn.setVisible(true);
        paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);        }
        else{
                    ModifierButtonAdminn.setVisible(false);

        }        
    }

    @FXML
    private void AnnulerModifAdresseadmin(MouseEvent event) {
        
        annulerimage4.setVisible(false);
        AdminModifierAdresse.setVisible(false);
        ModifierAdminMdp.setVisible(false);
        passwordtextadmin.setVisible(false);
        confirmpasswordtextadmin.setVisible(false);
        ModifierAdminMdpConfirm.setVisible(false);
        paneAffichmdp.setVisible(true);
         if(AdminModifierMail.isVisible() || AdminModifierMail.isVisible()|| AdminModifierAdresse.isVisible()|| AdminModifierNumero.isVisible()||AdminPrenomModifier.isVisible()|| AdminNomModifier.isVisible()||AdminModifierLogin.isVisible()){
ModifierButtonAdminn.setVisible(true);
        paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);        }
        else{
                    ModifierButtonAdminn.setVisible(false);

        }
    }

    @FXML
    private void AnnulerModifMailadmin(MouseEvent event) {
         annulerimage5.setVisible(false);
       AdminModifierMail.setVisible(false);
        ModifierAdminMdp.setVisible(false);
        passwordtextadmin.setVisible(false);
        confirmpasswordtextadmin.setVisible(false);
        ModifierAdminMdpConfirm.setVisible(false);
        paneAffichmdp.setVisible(true);
 if(AdminModifierMail.isVisible() || AdminModifierMail.isVisible()|| AdminModifierAdresse.isVisible()|| AdminModifierNumero.isVisible()||AdminPrenomModifier.isVisible()|| AdminNomModifier.isVisible()||AdminModifierLogin.isVisible()){
ModifierButtonAdminn.setVisible(true);
        paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);        }
        else{
                    ModifierButtonAdminn.setVisible(false);

        }        
    }

    @FXML
    private void AnnulerModifloginadmin(MouseEvent event) {
        annulerimage6.setVisible(false);
        AdminModifierLogin.setVisible(false);
        ModifierAdminMdp.setVisible(false);
        passwordtextadmin.setVisible(false);
        confirmpasswordtextadmin.setVisible(false);
        ModifierAdminMdpConfirm.setVisible(false);
        paneAffichmdp.setVisible(true);
        if(AdminModifierMail.isVisible() || AdminModifierMail.isVisible()|| AdminModifierAdresse.isVisible()|| AdminModifierNumero.isVisible()||AdminPrenomModifier.isVisible()|| AdminNomModifier.isVisible()||AdminModifierLogin.isVisible()){
ModifierButtonAdminn.setVisible(true);
        paneAffichmdp.setVisible(false);
        ModifierAdminMdp.setVisible(true);
        passwordtextadmin.setVisible(true);        }
        else{
                    ModifierButtonAdminn.setVisible(false);

        }
    }

    @FXML
    private void MusicPlayerr(MouseEvent event) {

        if (note.isPlaying() == true) {
            note.stop();
        } else {
           note.play();
           
        }
    }

    @FXML
    private void deconnexionAdmin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) DeconnexionAdminbm.getScene().getWindow();
            stage.close();

            Stage s = new Stage();
            s.setScene(new Scene(root));
            s.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void GutilsiateurAdmin(MouseEvent event) {
        List.setVisible(true);
        annulerGuserimage.setVisible(true);
        annulerGAssoimage1.setVisible(false);
        Ajouterassociationadmin.setVisible(false);
        paneGAssociation.setVisible(false);
    }

    @FXML
    private void annulerGuseradmin(MouseEvent event) {
         List.setVisible(false);
        annulerGuserimage.setVisible(false);
        lolo.setVisible(false);
        supp112.setVisible(false);
        annulerGAssoimage1.setVisible(false);
        Ajouterassociationadmin.setVisible(false);
        paneGAssociation.setVisible(false);
                       ListAsso.setVisible(false);

    }

    @FXML
    private void suppuser12(MouseEvent event) {
        UserService usss=new UserService();
        usss.DeleteUser(idbm);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention ! ");
                alert.setHeaderText(null);
                alert.setContentText("L'utulisitaeur a ete supprime ");
                alert.showAndWait();
        AnchorPane pane = new AnchorPane();
                                    try {
                                        pane = FXMLLoader.load(getClass().getResource("Admin.fxml"));

                                    } catch (IOException ex) {
                                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    PaneIdAdminn.getChildren().setAll(pane);
    }

    @FXML
    private void Ajouterassociationaaaadmin(ActionEvent event) {
        paneGAssociation.setVisible(true);
        Ajouterassociationadmin.setVisible(false);
        affichergassociation.setVisible(true);
        ListAsso.setVisible(false);

    }

    @FXML
    private void AcitiverGAssociation(MouseEvent event) {
        Ajouterassociationadmin.setVisible(true);
        annulerGAssoimage1.setVisible(true);
        annulerGuserimage.setVisible(false);
        List.setVisible(false);
        lolo.setVisible(false);
        supp112.setVisible(false);
        affichergassociation.setVisible(true);
        annulerGuserimage1888.setVisible(false);
        list_utilisateur.setVisible(false);
        ListAsso.setVisible(false);

    }

    @FXML
    private void annulerGassociationadmin(MouseEvent event) {
        paneGAssociation.setVisible(false);
        Ajouterassociationadmin.setVisible(false);
        annulerGAssoimage1.setVisible(false);
        annulerGuserimage.setVisible(false);
        List.setVisible(false);
        lolo.setVisible(false);
        supp112.setVisible(false);
        affichergassociation.setVisible(false);
               ListAsso.setVisible(false);
               annulerGuserimage1888.setVisible(false);
        list_utilisateur.setVisible(false);

        
    }

    @FXML
    private void AddAssoAdminnnnnn(ActionEvent event) {
        if(nomassociationadmin.getText().equals("")||adresseassociationadmin.getText().equals("")||numassociationadmin.getText().equals("")||mailassociationadmin.getText().equals("")||loginassociationadmin.getText().equals("")|| mdpassociationadmin.getText().equals("")){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("verifier les données ");
            alert.showAndWait();
        }
        else if (!mdpassociationadmin.getText().equals(confirmmdpassotiationasmin.getText())){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("mot de passe n'est pas identique");
            alert.showAndWait();
        }
        else if (numassociationadmin.getText().length()!=8){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("le numero de telephone incorrecte ");
            alert.showAndWait();
            
            
        }
        else{
        User tt =new User(nomassociationadmin.getText(), adresseassociationadmin.getText(), Integer.parseInt(numassociationadmin.getText()),mailassociationadmin.getText(),2,loginassociationadmin.getText(), mdpassociationadmin.getText());
        UserService urr=new UserService();
        urr.AjouterUser(tt);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Felicitation");
            alert.setHeaderText(null);
            alert.setContentText("votre compte a été cree avec succes . Redirection vers page d'acceuil ");
            alert.showAndWait();
        paneGAssociation.setVisible(false);
        Ajouterassociationadmin.setVisible(false);
        annulerGAssoimage1.setVisible(false);
        annulerGuserimage.setVisible(false);
        List.setVisible(false);
        lolo.setVisible(false);
        supp112.setVisible(false);
        affichergassociation.setVisible(false);
               ListAsso.setVisible(false);
               annulerGuserimage1888.setVisible(false);
        list_utilisateur.setVisible(false);
        
        }
        
    }

    @FXML
    private void AfficherListAsso(MouseEvent event) {
        annulerGuserimage1888.setVisible(false);
        list_utilisateur.setVisible(false);
        affichergassociation.setVisible(false);
        Ajouterassociationadmin.setVisible(true);
        ListAsso.setVisible(true);
        annulerGuserimage1888.setVisible(false);
        list_utilisateur.setVisible(false);
           SAssociation sa=new SAssociation();

          ObservableList<Association> obs=FXCollections.observableArrayList(sa.listerAssociation());

        ListAsso.setCellFactory((ListView<Association> arg0) -> {
            ListCell<Association> cell = new ListCell<Association>() {
                @Override
                protected void updateItem(Association a, boolean btl) {
                    super.updateItem(a, btl);

                    if (a != null) 
                    {

                        setText("Nom : "+a.getNom_association()+ "\n" + " E-mail : " + a.getEmail_association()+ "\n" + " Téléphone : " + a.getTelephone_association()+ "\n" + " Abonnés : " + a.getNombre_abonnes());
                        setFont(Font.font("Berlin Sans FB Demi Bold", 12));

                        // setAlignment(Pos.CENTER);
                    }
                }

            };
            return cell;
        });
       ListAsso.setItems(obs);
        // TODO
  

    }

    @FXML
    private void GvetoAdmin(MouseEvent event) {
        annulerGuserimage1888.setVisible(true);
        list_utilisateur.setVisible(true);
                       ListAsso.setVisible(false);

         VeterinaireService sv =new VeterinaireService();
	    
                  ObservableList<Veterinaire> items = FXCollections.observableArrayList(sv.Affichervetousr_veto());

        list_utilisateur.setCellFactory((ListView<Veterinaire> arg0) -> {
            ListCell<Veterinaire> cell = new ListCell<Veterinaire>() {
                @Override
                protected void updateItem(Veterinaire e, boolean btl) {
                    super.updateItem(e, btl);

                    if (e != null) {
                        File file = new File("src\\images\\images (1).jpg");
                        file.getParentFile().mkdirs();
                        Image IMAGE_RUBY = new Image(file.toURI().toString());
                        //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());

                        ImageView imgview = new ImageView(IMAGE_RUBY);

                        setGraphic(imgview);

                        imgview.setFitHeight(50);
                        imgview.setFitWidth(50);
                        Rectangle clip = new Rectangle(
                                imgview.getFitWidth(), imgview.getFitHeight()
                        );

                        clip.setArcWidth(20);
                        clip.setArcHeight(20);
                        imgview.setClip(clip);

                        // snapshot the rounded image.
                        SnapshotParameters parameters = new SnapshotParameters();
                        parameters.setFill(Color.TRANSPARENT);
                        WritableImage image = imgview.snapshot(parameters, null);

                        // remove the rounding clip so that our effect can show through.
                        imgview.setClip(null);

                        // apply a shadow effect.
                        imgview.setEffect(new DropShadow(20, Color.BLACK));

                        // store the rounded image in the imageView.
                        imgview.setImage(image);
                        
     list_utilisateur.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            

                            @Override
                            public void handle(MouseEvent event) {
                           
                                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
         
                          

                                
                                    
                                }
                            }

                        });
                                   // System.out.println(e.getId_utilisateur());
                        setText("Nom:    "+e.getNom()+ "\n" + "Prénom      :" + e.getPrenom() + "\n" + "Email    :" + e.getEmail()+ "\n" +"Click ici pour voir plus" );

                        setFont(Font.font("Berlin Sans FB Demi Bold", 12));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
        list_utilisateur.setItems(items);
        
        
    }

    @FXML
    private void annulergvetoadmin(MouseEvent event) {
        annulerGuserimage1888.setVisible(false);
        list_utilisateur.setVisible(false);
         paneGAssociation.setVisible(false);
        Ajouterassociationadmin.setVisible(false);
        annulerGAssoimage1.setVisible(false);
        annulerGuserimage.setVisible(false);
        List.setVisible(false);
        lolo.setVisible(false);
        supp112.setVisible(false);
    }
}
