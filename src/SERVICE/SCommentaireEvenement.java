/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import MODEL.CommentaireEvenement;
import ISERVICE.ICommentaireEvenement;
import TECHNIQUE.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruskov
 */
public class SCommentaireEvenement implements ICommentaireEvenement
{   
    public static DataSource s1=DataSource.getInsatance();
    private PreparedStatement ps=null;
    private ResultSet rs=null;

    @Override
    public void AjouterCommentaireEvenement(int id_evenement, int id_utilisateur, String contenu) 
    {
        try 
        {
            String req="INSERT INTO commentaireevenement (id_evenement,id_utilisateur,contenu,DateCommentaireEvenement) VALUES(?,?,?,?)";
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(currentDate.getTime());
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1, id_evenement);
            ps.setInt(2, id_utilisateur);
            ps.setString(3, contenu);
            ps.setDate(4, date);
            ps.executeUpdate();    
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        
        
        
    }

    @Override
    public void SupprimerCommentaireEvenement(int id_commentaire_evenement) 
    {
         String req="DELETE FROM commentaireevenement where id_commentaire_evenement=?";
        
        try 
        {
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1, id_commentaire_evenement);
            ps.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
        
      
    }

    @Override
    public void ModifierCommentaireEvenement(int id_commentaire_evenement, String contenu) 
    {
        String req="UPDATE commentaireevenement SET contenu=? where id_commentaire_evenement=?";
        try 
        {
            ps=s1.getConnection().prepareStatement(req);
            ps.setString(1, contenu);
            ps.setInt(2, id_commentaire_evenement);
            
        } 
        catch (SQLException ex)
        {
             System.out.println(Arrays.toString(ex.getStackTrace()));   
        }
        try 
        {
            ps.executeUpdate();
        } 
        catch (SQLException ex) 
        {
           System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @Override
    public List<CommentaireEvenement> ConsulterCommentaireEvenements(int id_evenement) 
    {
            List<CommentaireEvenement> ce=new ArrayList<>();
    String req="select * from commentaireevenement where id_evenement=?";
        try {
           
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1, id_evenement);
            
        } catch (SQLException ex)
        {
             System.out.println(Arrays.toString(ex.getStackTrace()));   
        }
        try {
            rs=ps.executeQuery();
        } catch (SQLException ex) 
        {
           System.out.println(Arrays.toString(ex.getStackTrace()));
        }
        try {
            while(rs.next())
            {
                try {
                    ce.add(new CommentaireEvenement(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getDate(5)));
                } catch (SQLException ex) {
                    Logger.getLogger(SCommentaireEvenement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SCommentaireEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
     return ce;
    }
    
}
