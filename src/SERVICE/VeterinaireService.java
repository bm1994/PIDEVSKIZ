/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import MODEL.User;
import MODEL.Veterinaire;
import TECHNIQUE.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author asus
 */
public class VeterinaireService {
    
    Connection connection;

    public VeterinaireService() {
            connection = DataSource.getInsatance().getConnection();

    }
    

    public void AjouterVeterinaire(Veterinaire v) {
        String req = "insert into utilisateur (prenom,nom,adresse,telephone,email,role,login,motdepasse,nom_cabinet,adresse_cabinet,numero_cabinet) values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            
            preparedStatement.setString(1,v.getPrenom());
            preparedStatement.setString(2,v.getNom());
            preparedStatement.setString(3,v.getAdresse());
            preparedStatement.setInt(4,v.getTelephone());
            preparedStatement.setString(5,v.getEmail());
            preparedStatement.setInt(6,v.getRole());
            preparedStatement.setString(7,v.getLogin());
            preparedStatement.setString(8,v.getMotDePasse());
            preparedStatement.setString(9,v.getNom_cabinet());
            preparedStatement.setString(10,v.getAdresse_cabinet());
            preparedStatement.setInt(11,v.getNumero_cabinet());



            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
