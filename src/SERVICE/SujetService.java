/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import MODEL.Sujet;
import TECHNIQUE.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author habib
 */
public class SujetService {
     Connection cnx;
    Statement ste;
    ResultSet rs;

    public SujetService() {
        try {
            cnx = DataSource.getInsatance().getConnection();
            ste = cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


	public void Ajouter_Sujet(Sujet s) {
		try {
            String req = "insert into sujet  values ("+null+",'" + s.getContenu() + "','" + s.getObjet() + "','" + s.getTitre() + "','"+s.getDate()+"',"+s.getId_user()+")";
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger( SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

	public void Modifier_Sujet(Sujet s) {
		try {
            String req = "update sujet  set contenu ='" + s.getContenu() + "',objet='" + s.getObjet() + "',titre='" + s.getTitre() + "',photo='"+s.getDate()+"'where id= "+s.getId_sujet();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger( SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

	public void Supprimer_Sujet(Sujet s ) {
		try {
			
			String req = "delete from sujet where id_sujet='"+s.getId_sujet()+"'";
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger( SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
				
	}

                public int  Afficher_list(int id) {
int c = 0 ;
        try {
            
            String req = "select * from sujet where id_utilisateur =" + id;
            rs = ste.executeQuery(req);
            while (rs.next()) {
                c++;
            }

        } 
     catch (SQLException ex) {
        Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return c;

}
        public int  Afficher_list_allsujet() {
int c = 0 ;
        try {
            
            String req = "select * from sujet ";
            rs = ste.executeQuery(req);
            while (rs.next()) {
                c++;
            }

        } 
     catch (SQLException ex) {
        Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return c;

}
        
        
        

	public List<Sujet> Afficher_Sujet() {

        List<Sujet> listP = new ArrayList<Sujet>();
        try {
            String req = "select * from sujet";
            rs = ste.executeQuery(req);
            while (rs.next()) {
            	Sujet s = new Sujet(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                listP.add(s);
            }

        } 
     catch (SQLException ex) {
        Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return listP;

}


	public List<Sujet> Afficher_Sujet_Titre(String titre) {
	     List<Sujet> listP = new ArrayList<Sujet>();
	        try {
	            String req = "select * from sujet where titre ="+titre;
	            rs = ste.executeQuery(req);
	            while (rs.next()) {
	            	Sujet s = new Sujet(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
	                listP.add(s);
	            }

	        } 
	     catch (SQLException ex) {
	        Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return listP;

	}
    
}
