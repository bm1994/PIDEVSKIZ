/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;

import TECHNIQUE.Session;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class FinalProjectSkizanimaux extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       /*AudioClip note = new AudioClip (this.getClass().getResource("azerty.mp3").toString());

         Session.note.play();*/

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            AudioClip note = new AudioClip(this.getClass().getResource("sound.mp3").toString());
            note.play();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        System.out.println("Hello");
    }
    
}
