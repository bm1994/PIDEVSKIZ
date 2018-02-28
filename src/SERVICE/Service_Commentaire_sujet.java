/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import MODEL.Commentaire_sujet;
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
public class Service_Commentaire_sujet {
    
	 Connection cnx;
	    Statement ste;
	    ResultSet rs;

	    public Service_Commentaire_sujet () {
	        try {
	            cnx = DataSource.getInsatance().getConnection();
	            ste = cnx.createStatement();
	        } catch (SQLException ex) {
	            Logger.getLogger(Service_Commentaire_sujet.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	
	
	
	

	public void Ajouter_commentaire(Commentaire_sujet c ) {
		try {
            String req = "insert into commentairesujet  values ("+null+","+c.getId_utilisateur()+","+c.getId_sujet()+",'" + c.getContenu_commentaire()+ "','" + c.getDateCommentaireSujet() +"')";
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger( Service_Commentaire_sujet.class.getName()).log(Level.SEVERE, null, ex);
        }		
	}


	public void Modifier_commentaire(Commentaire_sujet c) {
		try {
            String req = "update commentairesujet  set contenu_commentaire='"+ c.getContenu_commentaire()+"'where id_commentaire_sujet="+c.getId_commentaire_sujet();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger( Service_Commentaire_sujet.class.getName()).log(Level.SEVERE, null, ex);
        }
				
	}


	public void Supprimer_commentaire(Commentaire_sujet c) {
		try {
                    

                    
			String req = "delete from commentairesujet where  id_commentaire_sujet="+c.getId_commentaire_sujet()+"";
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger( Service_Commentaire_sujet.class.getName()).log(Level.SEVERE, null, ex);
        }		
	}

         
	public List<Commentaire_sujet> Afficher_commentaire(Sujet s ) {
		  List<Commentaire_sujet> listP = new ArrayList<Commentaire_sujet>();
	        try {
	            String req = "select * from commentairesujet where id_sujet ="+s.getId_sujet();
	            rs = ste.executeQuery(req);
	            while (rs.next()) {
	            	Commentaire_sujet c = new Commentaire_sujet(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5));
	                listP.add(c);
	            }

	        } 
	     catch (SQLException ex) {
	        Logger.getLogger(Service_Commentaire_sujet.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return listP;
	}

        
        
        
        
	public List<String> Afficher(Sujet s ) {
		  List<String> listP = new ArrayList<String>();
	        try {
	            String req = "select * from commentairesujet where id_sujet ="+s.getId_sujet();
	            rs = ste.executeQuery(req);
	            while (rs.next()) {
	            	Commentaire_sujet c = new Commentaire_sujet(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5));
	                listP.add(c.getContenu_commentaire());
	            }

	        } 
	     catch (SQLException ex) {
	        Logger.getLogger(Service_Commentaire_sujet.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return listP;
	}

}
    

