/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import ISERVICE.IParticipationEvenement;
import static SERVICE.SAbonnement.s1;
import TECHNIQUE.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruskov
 */
public class SParticipationEvenement implements IParticipationEvenement
{
    public static DataSource s1=DataSource.getInsatance();
    private PreparedStatement ps;
    private ResultSet rs;
    
    @Override
    public void AjouterParticipation(int id_utilisateur, int id_evenement) 
    {
         String req="INSERT INTO participation_evenement VALUES(?,?)";
        try 
        {
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1, id_utilisateur);
            ps.setInt(2, id_evenement);
        } catch (SQLException ex) {
            Logger.getLogger(SAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean VerifierParticipation(int id_utilisateur, int id_evenement) 
    {
      
        String req="SELECT * FROM participation_evenement where id_utilisateur=? and id_evenement=?";
        try {
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1, id_utilisateur);
            ps.setInt(2, id_evenement);
        } catch (SQLException ex) {
            Logger.getLogger(SAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs=ps.executeQuery();
            if (!rs.next())
            {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public void SupprimerParticipation(int id_utilisateur, int id_evenement) 
    {
          String req="DELETE FROM participation_evenement where id_utilisateur=? and id_evenement=?";
        try 
        {
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1, id_utilisateur);
            ps.setInt(2, id_evenement);
        } catch (SQLException ex) {
            Logger.getLogger(SAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    

