/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import MODEL.Notification;
import SERVICE.SAssociation;
import SERVICE.SEvenement;
import SERVICE.SNotification;
import SERVICE.SujetService;
import TECHNIQUE.Session;
import com.adobe.acrobat.Viewer;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Image;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author habib
 */

public class StatistiqueController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private PieChart piechart;
    public SNotification sn=new SNotification();
    public SEvenement se=new SEvenement();
    public SAssociation sa=new SAssociation();
SujetService sv = new SujetService();
int t=sv.Afficher_list_allsujet();
int s =Session.LoggedUser.getId_utilisateur();
int sc = sv.Afficher_list(s);
private final ObservableList<PieChart.Data> details = FXCollections.observableArrayList(
new PieChart.Data("Les Autre Sujet"+t,t),
        new PieChart.Data("Les sujet Que tu as poster"+sc, sc)


);
       String shemain="src/pdf/"+Session.LoggedUser.getNom()+"_"+Session.LoggedUser.getPrenom()+".pdf"; 

    @FXML
    private Button afficher_sujet;
    @FXML
    private Button acceuil;
    @FXML
    private Button profile;
    @FXML
    private Button pdf;
    @FXML
    private Button afficher_suite;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      piechart.setData(details);
      piechart.setTitle("Votre Interraction Sur le Forum En %");
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
    private void afficher_sujet(ActionEvent event) {
          try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Forum.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) afficher_sujet.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
       
    }

    @FXML
    private void profile(ActionEvent event) {
          try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
        Parent root =loader.load();
        
        Stage stage=(Stage) profile.getScene().getWindow();
        stage.close();
        
        Stage s = new Stage ();
    s.setScene(new Scene (root));    
    s.show();
    
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
       
    }

    @FXML
    private void pdf(ActionEvent event) throws Exception {
        try{
        Document d= new Document();
     
            SnapshotParameters s = new  SnapshotParameters ()
                ;
        WritableImage  i =  new WritableImage(100,100);
        WritableImage img =piechart.snapshot(new SnapshotParameters(),null);
        String url="snapshot"+new Date().getTime()+".png";
       File output =new File (url);
       ImageIO.write(SwingFXUtils.fromFXImage(img, null),"png",output );
     
     
       String p = "Bienvenu "+Session.LoggedUser.getNom()+"_"+Session.LoggedUser.getPrenom()+" Merci pour votre participation a notre application Vous avez Ajouter "+sc+" Sujet ";
        PdfWriter.getInstance(d,new FileOutputStream(shemain));
     


        d.open();
     Image is = Image.getInstance(url);
        d.add(new Paragraph(p));
d.add(is);
        d.close();
                }
       catch (DocumentException | FileNotFoundException ex) {
        System.out.println(ex.getMessage());
    }
       
    }

    @FXML
    private void afficher_suite(ActionEvent event) throws Exception {
    
          File file = new File(shemain);
            Desktop.getDesktop().open(file);
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
}
