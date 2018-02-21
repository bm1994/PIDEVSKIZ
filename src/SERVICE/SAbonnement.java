/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import ISERVICE.IAbonnement;
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
public class SAbonnement implements IAbonnement
{   
    public static DataSource s1=DataSource.getInsatance();
    private PreparedStatement ps;
    private ResultSet rs;
    
    @Override
    public void AjouterAbonnement(int id_association, int id_utilisateur) 
    {
        String req="INSERT INTO abonnement_association VALUES(?,?)";
        try 
        {
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1, id_utilisateur);
            ps.setInt(2, id_association);
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
    public void SupprimerAbonnement(int id_association, int id_utilisateur) 
    {
        String req="DELETE FROM abonnement_association where id_utilisateur=? and id_association=?";
        try {
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1, id_utilisateur);
            ps.setInt(2, id_association);
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
    public int NombreAbonnes(int id_association) 
    {   int a=0;
        String req=" SELECT count(*) AS nombre_abonnes FROM abonnement_association where id_association=?";
        try {
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1, id_association);
        } catch (SQLException ex) {
            Logger.getLogger(SAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs=ps.executeQuery();
            while(rs.next())
            {
                a=rs.getInt("nombre_abonnes");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    @Override
    public boolean VerifierAbonnement(int id_utilisateur, int id_association) 
    {
        String req="SELECT * FROM abonnement_association where id_utilisateur=? and id_association=?";
        try {
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1, id_utilisateur);
            ps.setInt(2, id_association);
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
}
