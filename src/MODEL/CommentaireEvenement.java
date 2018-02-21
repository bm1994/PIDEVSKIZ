/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.sql.Date;

/**
 *
 * @author Ruskov
 */
public class CommentaireEvenement 
{   private int id_commentaire_evenement;
    private int id_evenement;
    private int id_utilisateur;
    private String contenu_commentaire_evenement;
    private Date date_commentaire_evenement;

    public CommentaireEvenement() 
    {
    }
    
      public CommentaireEvenement(int id_evenement, int id_utilisateur, String contenu_commentaire_evenement, Date date_commentaire_evenement) 
    {   
        this.id_evenement = id_evenement;
        this.id_utilisateur = id_utilisateur;
        this.contenu_commentaire_evenement = contenu_commentaire_evenement;
        this.date_commentaire_evenement = date_commentaire_evenement; 
    }
      
    public CommentaireEvenement(int id_commentaire_evenement,int id_evenement, int id_utilisateur, String contenu_commentaire_evenement, Date date_commentaire_evenement) 
    {   
        this.id_commentaire_evenement=id_commentaire_evenement;
        this.id_evenement = id_evenement;
        this.id_utilisateur = id_utilisateur;
        this.contenu_commentaire_evenement = contenu_commentaire_evenement;
        this.date_commentaire_evenement = date_commentaire_evenement; 
    }

    
    public int getId_evenement() 
    {
        return id_evenement;
    }

    /**
     * @param id_evenement the id_evenement to set
     */
    public void setId_evenement(int id_evenement) 
    {
        this.id_evenement = id_evenement;
    }

    /**
     * @return the id_utilisateur
     */
    public int getId_utilisateur() 
    {
        return id_utilisateur;
    }

    /**
     * @param id_utilisateur the id_utilisateur to set
     */
    public void setId_utilisateur(int id_utilisateur) 
    {
        this.id_utilisateur = id_utilisateur;
    }

    /**
     * @return the contenu_commentaire_evenement
     */
    public String getContenu_commentaire_evenement() 
    {
        return contenu_commentaire_evenement;
    }

    /**
     * @param contenu_commentaire_evenement the contenu_commentaire_evenement to set
     */
    public void setContenu_commentaire_evenement(String contenu_commentaire_evenement) 
    {
        this.contenu_commentaire_evenement = contenu_commentaire_evenement;
    }

    /**
     * @return the date_commentaire_evenement
     */
    public Date getDate_commentaire_evenement() 
    {
        return date_commentaire_evenement;
    }

    /**
     * @param date_commentaire_evenement the date_commentaire_evenement to set
     */
    public void setDate_commentaire_evenement(Date date_commentaire_evenement) 
    {
        this.date_commentaire_evenement = date_commentaire_evenement;
    }

    /**
     * @return the id_commentaire_evenement
     */
    public int getId_commentaire_evenement() {
        return id_commentaire_evenement;
    }

    /**
     * @param id_commentaire_evenement the id_commentaire_evenement to set
     */
    public void setId_commentaire_evenement(int id_commentaire_evenement) {
        this.id_commentaire_evenement = id_commentaire_evenement;
    }
    
    
    
}
