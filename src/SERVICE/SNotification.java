/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import ISERVICE.INotification;
import MODEL.Association;
import MODEL.Evenement;
import MODEL.Notification;
import TECHNIQUE.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruskov
 */
public class SNotification implements INotification
{   
    private DataSource s1=DataSource.getInsatance();
    private PreparedStatement ps=null;
    private ResultSet rs=null;

    @Override
    public void supprimerNotification(int id_utilisateur) 
    {
        String req="DELETE FROM notification WHERE id_utilisateur=?";
        try
        {
            ps=s1.getConnection().prepareStatement(req);        
            ps.setInt(1, id_utilisateur);
        }
        catch(SQLException ex)
        {
                 Logger.getLogger(SNotification.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SNotification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Notification> chercherNotification(int id_utilisateur) {
        List<Notification> ln=new ArrayList<>();
        String req="SELECT * FROM notification where id_utilisateur=?";
        try
        {
            ps=s1.getConnection().prepareStatement(req);        
            ps.setInt(1, id_utilisateur);
        }
        catch(SQLException ex)
        {
            
        }
        try
        {
            rs=ps.executeQuery();
            while(rs.next())
            {
                ln.add(new Notification(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4)));
            }
        }
        catch(SQLException ex)
        {
            
        }
     return ln; }

    @Override
    public void ajouterNotification(int id_utilisateur, int id_association, int id_evenement,int type) {
   String req="INSERT INTO notification VALUES(?,?,?,?)";
        try
        {
            ps=s1.getConnection().prepareStatement(req);        
            ps.setInt(1, id_association);
            ps.setInt(2, id_utilisateur);
            ps.setInt(3, id_evenement);
            ps.setInt(4, type);
            
        }
        catch(SQLException ex)
        {
                 Logger.getLogger(SNotification.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps.executeUpdate();
        } 
        catch (SQLException ex) {
            Logger.getLogger(SNotification.class.getName()).log(Level.SEVERE, null, ex);
        } }
  
}
