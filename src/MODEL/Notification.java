/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author Ruskov
 */
public class Notification 
{
    private int id_utilisateur;
    private int id_association;
    private int id_evenement;
    private int type;
    public Notification() {
    }

    public Notification(int id_association, int id_utilisateur,int id_evenement,int type) {
        this.id_utilisateur = id_utilisateur;
        this.id_association = id_association;
        this.id_evenement=id_evenement;
        this.type=type;
    }

    /**
     * @return the id_utilisateur
     */
    public int getId_utilisateur() {
        return id_utilisateur;
    }

    /**
     * @param id_utilisateur the id_utilisateur to set
     */
    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    /**
     * @return the id_association
     */
    public int getId_association() {
        return id_association;
    }

    /**
     * @param id_association the id_association to set
     */
    public void setId_association(int id_association) {
        this.id_association = id_association;
    }

    /**
     * @return the id_evenement
     */
    public int getId_evenement() {
        return id_evenement;
    }

    /**
     * @param id_evenement the id_evenement to set
     */
    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }
    
}
