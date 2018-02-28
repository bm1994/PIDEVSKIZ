/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author habib
 */
public class user_veto {
 private int id_utilisateur;
 private int id_veto;
 private int id_sujet;

    public user_veto(int id_utilisateur, int id_veto, int id_sujet) {
        this.id_utilisateur = id_utilisateur;
        this.id_veto = id_veto;
        this.id_sujet = id_sujet;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_veto() {
        return id_veto;
    }

    public void setId_veto(int id_veto) {
        this.id_veto = id_veto;
    }

    public int getId_sujet() {
        return id_sujet;
    }

    public void setId_sujet(int id_sujet) {
        this.id_sujet = id_sujet;
    }
 
 
 
}
