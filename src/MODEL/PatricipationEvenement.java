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
public class PatricipationEvenement {
    private int id_utilisateur;
    private int id_evenement;

    public PatricipationEvenement() {
    }

    public PatricipationEvenement(int id_utilisateur, int id_evenement) {
        this.id_utilisateur = id_utilisateur;
        this.id_evenement = id_evenement;
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
}
