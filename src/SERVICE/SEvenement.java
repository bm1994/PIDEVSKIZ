/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;


import MODEL.Evenement;
import TECHNIQUE.DataSource;
import ISERVICE.IEvenement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ruskov
 */
public class SEvenement implements IEvenement
{
    public static DataSource s1=DataSource.getInsatance();
    private PreparedStatement ps=null;
    private ResultSet rs=null;
    @Override
    public void AjouterEvenement(int idUtilisateur, String SujetEvenement,Date DateEvenement,String TitreEvenement,String LieuEvenement) 
    {
        try {
            ps=s1.getConnection().prepareStatement("INSERT INTO evenement (sujet_evenement,date_evenement,lieu_evenement,titre_evenement,id_utilisateur) VALUES (?,?,?,?,?)");       
            ps.setString(1, SujetEvenement);
            ps.setDate(2, (Date)DateEvenement);
            ps.setString(3, LieuEvenement);
            ps.setString(4, TitreEvenement);
            ps.setInt(5, idUtilisateur);        
        } catch (SQLException ex)
        {
             System.out.println(Arrays.toString(ex.getStackTrace()));
        }
        try {
            ps.executeUpdate();
        } catch (SQLException ex) 
        {
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @Override
    public void SupprimerEvenement(int idEvenement) 
    { 
        try {
            ps=s1.getConnection().prepareStatement("DELETE  FROM evenement WHERE id_evenement=?");
            ps.setInt(1, idEvenement);
        } catch (SQLException ex)
        {
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
        try {
            ps.executeUpdate();
        } catch (SQLException ex) 
        {
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @Override
    public void ModifierEvenement(int idEvenement, String TitreEvenement, String SujetEvenement, Date DateEvenement, String LieuEvenement) {
        String req="UPDATE evenement SET sujet_evenement=?,date_evenement=?,lieu_evenement=?,titre_evenement=? where id_evenement=?";
        try {
            ps=s1.getConnection().prepareStatement(req);
            ps.setString(1, SujetEvenement);
            ps.setDate(2, (Date)DateEvenement);
            ps.setString(3, LieuEvenement);
            ps.setString(4, TitreEvenement);
            ps.setInt(5, idEvenement);
        } catch (SQLException ex)
        {
             System.out.println(Arrays.toString(ex.getStackTrace()));   
        }
        try {
            ps.executeUpdate();
        } catch (SQLException ex) 
        {
           System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @Override
    public List<Evenement> ConsulterEvenement(int idUtilisateur) 
    {
            String req="SELECT * FROM evenement WHERE id_utilisateur=?";
            List<Evenement> le=new ArrayList<>();
        try {
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1, idUtilisateur);
            
        } catch (SQLException ex)
        {
            System.out.println(Arrays.toString(ex.getStackTrace()));   
        }
        try {
            rs=ps.executeQuery();
            while(rs.next())
            {
                le.add(new Evenement(rs.getInt(1),rs.getInt(6),rs.getInt(7),rs.getString(5),rs.getString(2),rs.getDate(3),rs.getString(4)));
            }
            
        } catch (SQLException ex) 
        {
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    return le; 
    }

    @Override
    public List<Evenement> ChercherEvenement(String TitreEvenement) {
       String req="SELECT * FROM evenement where titre_evenement=?";
            List<Evenement> le=new ArrayList<>();
        try {
            ps=s1.getConnection().prepareStatement(req);
            ps.setString(1, TitreEvenement);
        } catch (SQLException ex)
        {
        }
        try {
            rs=ps.executeQuery();
            while(rs.next())
            {
                  le.add(new Evenement(rs.getInt(1),rs.getInt(6),rs.getInt(7),rs.getString(5),rs.getString(2),rs.getDate(3),rs.getString(4)));
           }
            
        } catch (SQLException ex) 
        {
        }
    return le; 
    }
    
    @Override
    public void IncrementNombreInteresses(int idEvenement) 
    {   
        
        String req="SELECT interesses FROM evenement WHERE id_evenement=?";
        String reqq="UPDATE evenement SET interesses=? where id_evenement=?";
        PreparedStatement ps1=null;
        try {
            
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1,idEvenement);
        } catch (SQLException ex)
        {
             System.out.println(Arrays.toString(ex.getStackTrace()));   
        }
        try {
            rs=ps.executeQuery();
            ps1=s1.getConnection().prepareStatement(reqq);
            while(rs.next())
            {
            ps1.setInt(1,(rs.getInt(1))+1 );
            ps1.setInt(2, idEvenement);   
            }
            ps1.executeUpdate();
        } catch (SQLException ex) 
        {
           ex.printStackTrace();
        }
        
    }

    @Override
    public void DecrementNombreInteresses(int idEvenement) 
    {
        String req="SELECT interesses FROM evenement WHERE id_evenement=?";
        String reqq="UPDATE evenement SET interesses=? where id_evenement=?";
        PreparedStatement ps1;
        try {
            
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1,idEvenement);
        } catch (SQLException ex)
        {
             System.out.println(Arrays.toString(ex.getStackTrace()));   
        }
        try {
            rs=ps.executeQuery();
            ps1=s1.getConnection().prepareStatement(reqq);
            while(rs.next())
            {
            ps1.setInt(1,(rs.getInt(1))-1 );
            ps1.setInt(2, idEvenement);   
            }
            ps1.executeUpdate();
        } catch (SQLException ex) 
        {
           ex.printStackTrace();
        }
    }

    @Override
    public List<Evenement> ConsulterEvenement() 
    {
       String req="SELECT * FROM evenement";
       List<Evenement> le=new ArrayList<>();
        try 
        {
            ps=s1.getConnection().prepareStatement(req);

        } catch (SQLException ex)
        {
            System.out.println(Arrays.toString(ex.getStackTrace()));   
        }
        try {
            rs=ps.executeQuery();
            while(rs.next())
            {
                le.add(new Evenement(rs.getInt(1),rs.getInt(6),rs.getInt(7),rs.getString(5),rs.getString(2),rs.getDate(3),rs.getString(4)));
            }
            
        } catch (SQLException ex) 
        {
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    return le;
    }

    @Override
    public Evenement ChercherEvenement(int id_evenement) 
    {   
        String req="SELECT * FROM evenement where id_evenement=?";
        try
        {
        ps=s1.getConnection().prepareStatement(req);
        ps.setInt(1, id_evenement);
        }
        catch(SQLException ex)
        {
            
        }
        try
        {
        rs=ps.executeQuery();
        if (rs.next())
        {
            return new Evenement(rs.getInt(1),rs.getInt(7),rs.getString(5),rs.getString(2),rs.getDate(3),rs.getString(4));
        }
        }
        catch(SQLException ex)
        {
            
        }
        return null;
    }

    @Override
    public int idDernierEvenementAjoute(int id_association) 
    {   String req="SELECT * FROM evenement WHERE id_utilisateur=?";
        try {
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1, id_association);
        } catch (SQLException ex) {
            Logger.getLogger(SEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            rs=ps.executeQuery();
            rs.last();
            return rs.getInt(1);
        }
        catch(SQLException ex)
        {
            
        }
        return 0;
    }
}