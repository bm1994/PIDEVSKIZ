/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import SERVICE.UserService;
import TECHNIQUE.DataSource;
import TECHNIQUE.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.stage.Stage;
import javafx.util.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
    AudioClip note = new AudioClip(this.getClass().getResource("sound.mp3").toString());
  // private Slider SliderVolumee;
    //private TextField vvvv;
    @FXML
    private Button CntFb;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //note.play();

    }

    @FXML
    private void Authentifier(ActionEvent event) {
        try {
            UserService userrser = new UserService();
            String b = userrser.getelemntbylogin(loginid.getText());
            int arole = userrser.getelemntbyrole(loginid.getText());
            
            if ((b != null) && b.equals(passwordid.getText()) && arole == 3) {
                Session.LoggedUser = userrser.findByLogin(loginid.getText());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
                Stage s = new Stage();
                s.setScene(new Scene(root));
                s.show();
                

            } 
            else if ((b != null) && b.equals(passwordid.getText()) && arole == 1){
                        Session.LoggedUser = userrser.findByLogin(loginid.getText());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
                Stage s = new Stage();
                s.setScene(new Scene(root));
                s.show();
                        }
            else if ((b != null) && b.equals(passwordid.getText()) && arole == 5){
                        Session.LoggedUser = userrser.findByLogin(loginid.getText());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VeterinaireUser.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
                Stage s = new Stage();
                s.setScene(new Scene(root));
                s.show();
                        }
            
            else {
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
            Parent root = loader.load();

            Stage stage = (Stage) sinscrireButton.getScene().getWindow();
            stage.close();

            Stage s = new Stage();
            s.setScene(new Scene(root));
            s.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void LoginFb(ActionEvent event) throws IOException {
        String domain = "https://www.google.fr/";
        String appId = "776136815927253";
////      String authUrl ="https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=user_about_me,"
////             + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_birthday,user_education_history,"
////             + "user_events,user_photos,user_friends,user_games_activity,user_hometown,user_likes,user_location,user_photos,user_relationship_details,"
////             + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
////             + "manage_pages,publish_actions,read_insights,user_friends,read_page_mailboxes,rsvp_event,pages_show_list,email";
        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain;

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String fblog = "facebook.com";
        CharSequence cs = fblog.subSequence(0, 11);
        while (true) {
            if (!driver.getCurrentUrl().contains(cs)) {
                String url = driver.getCurrentUrl();
                String accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");

                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                User user = fbClient.fetchObject("me", User.class, Parameter.with("fields", "id,last_name,name,first_name,gender,email,birthday"));

                //   System.out.println(user.getId() + user.getName() + user.getLastName() + user.getEmail() + user.getGender() + user.getLastName() + user);
//  label.setText(user.getName());
                driver.quit();

                String parts = user.getId().substring(10);
                MODEL.User usera = new MODEL.User(Integer.parseInt(parts), user.getFirstName(), user.getLastName(), "Veuiller remplir ladresse", 88888888, user.getEmail(), 2, user.getFirstName(), "Mot de passe");

                /*System.out.println(user.getId());
                System.out.println(parts);
                System.out.println(Integer.parseInt(parts));
                System.out.println(user.getLastName());
                System.out.println(user.getFirstName());
                System.out.println(user.getGender());
                System.out.println(user.getBirthday());
                System.out.println(user.getEmail());

                System.out.println(Date.valueOf("1970-01-01"));*/
                if (user.getEmail() == null) {
                    usera.setEmail(user.getFirstName() + "." + user.getLastName() + "@esprit.tn");
                } else {

                }
                Session.LoggedUser = usera;
                // System.out.println(usera);

                if (Session.iuserService.findByLogin(user.getFirstName()) != null) {
                    Session.LoggedUser = Session.iuserService.findByLogin(user.getFirstName());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    Stage s = new Stage();
                    s.setScene(new Scene(root));
                    s.show();
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Sinscrire.fxml"));
                    Parent root = loader.load();

                    Stage stage = (Stage) sinscrireButton.getScene().getWindow();
                    stage.close();

                    Stage s = new Stage();
                    s.setScene(new Scene(root));
                    s.show();
                }
                /* 
                Passager p = new Passager(Integer.parseInt(parts), user.getLastName(), user.getFirstName(), user.getGender(), Date.valueOf("1970-02-02"), 1, user.getEmail(), "none", "Avatar", circleService.findById(2));

                System.out.println(p);
                IPassagerService ips = new PassagerService();
                if (ips.findById(p.getId()) == null) {

                    ips.add(p);
                    idlogger = p.getId();
                    VariablesGlobale.ipassagerService.findById(idlogger);

                    //////
                    Parent root = FXMLLoader.load(getClass().getResource("/Views/MenuPassager.fxml"));
                    Scene scene = new Scene(root);
                    Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    s.setScene(scene);
                    s.show();

                } else {
                    idlogger = p.getId();
                    VariablesGlobale.ipassagerService.findById(idlogger);
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/Views/MenuPassager.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController_1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    s.setScene(scene);
                    s.show();
                }*/

                return;

            }
        }
    }

    private void MusicPlayerr(ActionEvent event) {

        

    }

    @FXML
    private void MusicPlayerr(MouseEvent event) {
        
       if (note.isPlaying() == true) {
            note.stop();
        } else {
           note.play();
           
        }
    }


    
}
