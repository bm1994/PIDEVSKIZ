/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import MODEL.Association;
import ISERVICE.IAssociation;
import TECHNIQUE.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruskov
 */
public class SAssociation implements IAssociation
{ 
    public static DataSource s1=DataSource.getInsatance();
    private PreparedStatement ps=null;
    private ResultSet rs=null;
    public SAbonnement sab=new SAbonnement();
    @Override
    public List<Association> listerAssociation() 
    {   List<Association> la=new ArrayList<>();
        try
        {
            String req="SELECT * FROM utilisateur where role=?";
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1, 2);
        }
        catch (SQLException ex)
        {
                Logger.getLogger(SAssociation.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs=ps.executeQuery();
            while(rs.next())
            {Association a=new Association(rs.getInt(7),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
             a.setNombre_abonnes(sab.NombreAbonnes(a.getId_association()));
            la.add(a);
                    
            }
        } catch (SQLException ex) {
            Logger.getLogger(SAssociation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return la;
    }

    @Override
    public Association chercherAssociation(String nom) 
    {
      try
        {
            String req="SELECT * FROM utilisateur";
            ps=s1.getConnection().prepareStatement(req);
        }
        catch (SQLException ex)
        {
                Logger.getLogger(SAssociation.class.getName()).log(Level.SEVERE, null, ex);
        }
      try
      {
          rs=ps.executeQuery();
          while(rs.next())
          {
              if (rs.getString(2).equalsIgnoreCase(nom))
              {
                  Association a=new Association(rs.getInt(7),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
             a.setNombre_abonnes(sab.NombreAbonnes(a.getId_association()));
             return a;
              }
          }
      }
      catch(SQLException ex)
      {
               Logger.getLogger(SAssociation.class.getName()).log(Level.SEVERE, null, ex);
      }
      return null;
    }

    @Override
    public Association chercherAssociation(int id_association) 
    {   Association a;
        try
        {
            String req="SELECT * FROM utilisateur WHERE id_utilisateur=?";
            ps=s1.getConnection().prepareStatement(req);
            ps.setInt(1,id_association);
        }
        catch (SQLException ex)
        {
                Logger.getLogger(SAssociation.class.getName()).log(Level.SEVERE, null, ex);
        }
      try
      {
          rs=ps.executeQuery();
          while(rs.next())
          {
            a=new Association(rs.getInt(7),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
             a.setNombre_abonnes(sab.NombreAbonnes(a.getId_association()));
             return a;
          }
      }
      catch(SQLException ex)
      {
               Logger.getLogger(SAssociation.class.getName()).log(Level.SEVERE, null, ex);
      }
      return null;
    }
    
}
