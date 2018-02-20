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
public class Sujet {
    private int id_sujet;
    private String titre ;
	private String objet;
        private String contenu ;
	private String date ;
        private int id_user;

    public Sujet(String titre, String objet, String contenu, String date, int id_user) {
        this.titre = titre;
        this.objet = objet;
        this.contenu = contenu;
        this.date = date;
        this.id_user = id_user;
    }

    public Sujet(int id_sujet, String titre, String objet, String contenu, String date, int id_user) {
        this.id_sujet = id_sujet;
        this.titre = titre;
        this.objet = objet;
        this.contenu = contenu;
        this.date = date;
        this.id_user = id_user;
    }
        

    public int getId_sujet() {
        return id_sujet;
    }

    public void setId_sujet(int id_sujet) {
        this.id_sujet = id_sujet;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
	
}
