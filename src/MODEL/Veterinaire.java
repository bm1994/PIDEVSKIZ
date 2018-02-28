/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class Veterinaire extends User{
    private String nom_cabinet;
    private String adresse_cabinet;
    private int numero_cabinet;
private List<User>list_user = new ArrayList();
private List<Sujet>list_sujet = new ArrayList();

    public List<Sujet> getList_sujet() {
        return list_sujet;
    }

    public void setList_sujet(List<Sujet> list_sujet) {
        this.list_sujet = list_sujet;
    }

    public List<User> getList_user() {
        return list_user;
    }

    public void setList_user(List<User> list_user) {
        this.list_user = list_user;
    }











    public Veterinaire(String nom_cabinet, String adresse_cabinet, int numero_cabinet) {
        this.nom_cabinet = nom_cabinet;
        this.adresse_cabinet = adresse_cabinet;
        this.numero_cabinet = numero_cabinet;
    }

    public Veterinaire(String nom_cabinet, String adresse_cabinet, int numero_cabinet, int id_utilisateur, String nom, String prenom, String adresse, int telephone, String email, int role, String login, String motDePasse) {
        super(id_utilisateur, nom, prenom, adresse, telephone, email, role, login, motDePasse);
        this.nom_cabinet = nom_cabinet;
        this.adresse_cabinet = adresse_cabinet;
        this.numero_cabinet = numero_cabinet;
    }

    public Veterinaire(String nom_cabinet, String adresse_cabinet, int numero_cabinet, String nom, String prenom, String adresse, int telephone, String email, int role, String login, String motDePasse) {
        super(nom, prenom, adresse, telephone, email, role, login, motDePasse);
        this.nom_cabinet = nom_cabinet;
        this.adresse_cabinet = adresse_cabinet;
        this.numero_cabinet = numero_cabinet;
    }

    public Veterinaire() {
        
    }

    public String getNom_cabinet() {
        return nom_cabinet;
    }

    public void setNom_cabinet(String nom_cabinet) {
        this.nom_cabinet = nom_cabinet;
    }

    public String getAdresse_cabinet() {
        return adresse_cabinet;
    }

    public void setAdresse_cabinet(String adresse_cabinet) {
        this.adresse_cabinet = adresse_cabinet;
    }

    public int getNumero_cabinet() {
        return numero_cabinet;
    }

    public void setNumero_cabinet(int numero_cabinet) {
        this.numero_cabinet = numero_cabinet;
    }

    @Override
    public String toString() {
        return "Veterinaire{" + "nom_cabinet=" + nom_cabinet + ", adresse_cabinet=" + adresse_cabinet + ", numero_cabinet=" + numero_cabinet + '}';
    }
    
    
}
