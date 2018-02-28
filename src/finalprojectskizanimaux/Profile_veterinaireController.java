package finalprojectskizanimaux;

import MODEL.Veterinaire;
import SERVICE.VeterinaireService;
import TECHNIQUE.Session;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * FXML Controller class
 *
 * @author habib
 */
public class Profile_veterinaireController implements Initializable,MapComponentInitializedListener {
Veterinaire s ;
String h;
    @FXML
    private TextField adresse;
    @FXML
    private Button contacter;

  public void veteo(String v){
           this.h=v;

    } 
   public void veteo_all(Veterinaire v){
           this.s=v;

    } 
    
     
    @FXML
    private Button afficher_sujet;
    @FXML
    private Button profile;
    @FXML
    private Button stat_btn;
    @FXML
    private Button acceuil;
    @FXML
    private GoogleMapView mapView;
GoogleMap map;
    @FXML
    private TextField nom_caninet;
    @FXML
    private TextField tel;

    /**
     * Initializes the controller class.
     */
  
     @Override
    public void initialize(URL location, ResourceBundle resources) {

        mapView.addMapInializedListener(this);

        
    } 
    
 
 private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }
  
  
  
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {

  InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

    
  
  

@Override
public void mapInitialized() {
    nom_caninet.setText(s.getNom_cabinet());
adresse.setText(h);
tel.setText(""+s.getTelephone());
    Double l =0.0;
    Double log=0.0;
    String adresse= s.getAdresse_cabinet();
    String lat, lon;
       String locationAddres = adresse.replaceAll(" ", "%20");
        String str = "http://maps.googleapis.com/maps/api/geocode/json?address=" + locationAddres + "&sensor=true";    
          
 
           
      
     try {
         JSONObject json = readJsonFromUrl(str);
          JSONObject geoMetryObject = new JSONObject();
            JSONObject locations =json;
            JSONArray jarr = json.getJSONArray("results");
            int i;
            for (i = 0; i < jarr.length(); i++) {
                json = jarr.getJSONObject(i);
                geoMetryObject = json.getJSONObject("geometry");
                locations = geoMetryObject.getJSONObject("location");
                l = locations.getDouble("lat");
                log = locations.getDouble("lng");
     
            }
                         
 
         
     } catch (IOException ex) {
         Logger.getLogger(Profile_veterinaireController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (JSONException ex) {
         Logger.getLogger(Profile_veterinaireController.class.getName()).log(Level.SEVERE, null, ex);
     }

         
    
    MapOptions mapOptions = new MapOptions();

    mapOptions.center(new LatLong(l, log))
            .overviewMapControl(false)
            .panControl(false)
            .rotateControl(false)
            .scaleControl(false)
            .streetViewControl(false)
            .zoomControl(false)
            .zoom(12);

    map = mapView.createMap(mapOptions);

    //Add a marker to the map
    MarkerOptions markerOptions = new MarkerOptions();

    markerOptions.position( new LatLong(l, log) )
                .visible(Boolean.TRUE)
                .title("My Marker");

    Marker marker = new Marker( markerOptions );

    map.addMarker(marker);
 

}

    @FXML
    private void afficher_sujet(ActionEvent event) {
    }

    @FXML
    private void profile(ActionEvent event) {
    }

    @FXML
    private void Statistique(ActionEvent event) {
    }

    @FXML
    private void forumRetour(ActionEvent event) {
    }

    @FXML
    private void contacter(ActionEvent event) {
       VeterinaireService v =new VeterinaireService();
      int c= Session.LoggedUser.getId_utilisateur();
       v.ajouter_une_demande(c,s.getId_utilisateur());
       
       
       
       
    
    try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajout_sujet_veto.fxml"));
        Parent root =loader.load();
                Ajout_sujet_vetoController as= loader.getController();
                 Veterinaire vs=  s;

       as.veteo_all(vs);
           System.out.println(vs.getId_utilisateur());
        Stage stage=(Stage) contacter.getScene().getWindow();
        stage.close();
        Stage sa = new Stage ();
    sa.setScene(new Scene (root));    
    sa.show();
    
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

        }
  
        
    

    
    

