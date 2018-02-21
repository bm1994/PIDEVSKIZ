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
public class Evenement 
{
    private int id_evnement;
    private int id_association;
    private int nombre_interesses;
    private String titre_evenement;
    private String sujet_evenement;
    private Date date_evenement;
    private String lieu_evenement;


    public Evenement() {
    }
    public Evenement(int id_association, int nombre_interesses, String titre_evenement, String sujet_evenement, Date date_evenement, String lieu_evenement) 
    {
        this.id_association = id_association;
        this.nombre_interesses = nombre_interesses;
        this.titre_evenement = titre_evenement;
        this.sujet_evenement = sujet_evenement;
        this.date_evenement = date_evenement;
        this.lieu_evenement = lieu_evenement;
    }
    public Evenement(int id_evnement, int id_association, int nombre_interesses, String titre_evenement, String sujet_evenement, Date date_evenement, String lieu_evenement) 
    {
        this.id_evnement = id_evnement;
        this.id_association = id_association;
        this.nombre_interesses = nombre_interesses;
        this.titre_evenement = titre_evenement;
        this.sujet_evenement = sujet_evenement;
        this.date_evenement = date_evenement;
        this.lieu_evenement = lieu_evenement;
    }
    
    public int getId_evenement() 
    {
        return id_evnement;
    }

    /**
     * @param id_evnement the id_evnement to set
     */
    public void setId_evenement(int id_evnement) 
    {
        this.id_evnement = id_evnement;
    }

    /**
     * @return the id_association
     */
    public int getId_association() 
    {
        return id_association;
    }

    /**
     * @param id_association the id_association to set
     */
    public void setId_association(int id_association) 
    {
        this.id_association = id_association;
    }

    /**
     * @return the nombre_interesses
     */
    public int getNombre_interesses() 
    {
        return nombre_interesses;
    }

    /**
     * @param nombre_interesses the nombre_interesses to set
     */
    public void setNombre_interesses(int nombre_interesses) 
    {
        this.nombre_interesses = nombre_interesses;
    }

    /**
     * @return the titre_evenement
     */
    public String getTitre_evenement() 
    {
        return titre_evenement;
    }

    /**
     * @param titre_evenement the titre_evenement to set
     */
    public void setTitre_evenement(String titre_evenement) 
    {
        this.titre_evenement = titre_evenement;
    }

    /**
     * @return the sujet_evenement
     */
    public String getSujet_evenement() 
    {
        return sujet_evenement;
    }

    /**
     * @param sujet_evenement the sujet_evenement to set
     */
    public void setSujet_evenement(String sujet_evenement) 
    {
        this.sujet_evenement = sujet_evenement;
    }

    /**
     * @return the date_evenement
     */
    public Date getDate_evenement() 
    {
        return date_evenement;
    }

    /**
     * @param date_evenement the date_evenement to set
     */
    public void setDate_evenement(Date date_evenement) 
    {
        this.date_evenement = date_evenement;
    }

    /**
     * @return the lieu_evenement
     */
    public String getLieu_evenement() 
    {
        return lieu_evenement;
    }

    /**
     * @param lieu_evenement the lieu_evenement to set
     */
    public void setLieu_evenement(String lieu_evenement) 
    {
        this.lieu_evenement = lieu_evenement;
    }
    
}
