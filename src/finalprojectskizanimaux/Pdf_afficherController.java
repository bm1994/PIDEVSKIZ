/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectskizanimaux;
import javafx.stage.Stage;

import com.adobe.acrobat.Viewer;
import com.itextpdf.text.pdf.PdfReader;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author habib
 */
public class Pdf_afficherController implements Initializable {

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("C:\\Users\\habib\\Documents\\NetBeansProjects\\PIDEVSKIZ\\src\\pdf/Habib_daou.pdf");
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(Pdf_afficherController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }    
    
}
