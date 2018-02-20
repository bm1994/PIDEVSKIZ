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
public class Commentaire_sujet {

private int id_commentaire_sujet;
private int	id_utilisateur	;
private int id_sujet;
private String contenu_commentaire	;
private String DateCommentaireSujet;


    public Commentaire_sujet(int id_utilisateur, int id_sujet, String contenu_commentaire, String DateCommentaireSujet) {
        this.id_utilisateur = id_utilisateur;
        this.id_sujet = id_sujet;
        this.contenu_commentaire = contenu_commentaire;
        this.DateCommentaireSujet = DateCommentaireSujet;
    }



    public int getId_commentaire_sujet() {
        return id_commentaire_sujet;
    }

    public void setId_commentaire_sujet(int id_commentaire_sujet) {
        this.id_commentaire_sujet = id_commentaire_sujet;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_sujet() {
        return id_sujet;
    }

    public void setId_sujet(int id_sujet) {
        this.id_sujet = id_sujet;
    }

    public String getContenu_commentaire() {
        return contenu_commentaire;
    }

    public void setContenu_commentaire(String contenu_commentaire) {
        this.contenu_commentaire = contenu_commentaire;
    }

    public String getDateCommentaireSujet() {
        return DateCommentaireSujet;
    }

    public void setDateCommentaireSujet(String DateCommentaireSujet) {
        this.DateCommentaireSujet = DateCommentaireSujet;
    }


    public Commentaire_sujet(int id_commentaire_sujet, int id_utilisateur, int id_sujet, String contenu_commentaire, String DateCommentaireSujet) {
        this.id_commentaire_sujet = id_commentaire_sujet;
        this.id_utilisateur = id_utilisateur;
        this.id_sujet = id_sujet;
        this.contenu_commentaire = contenu_commentaire;
        this.DateCommentaireSujet = DateCommentaireSujet;
    }

    
}
