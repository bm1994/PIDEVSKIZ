/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.util.Objects;

/**
 *
 * @author asus
 */
public class User {
    private int id_utilisateur;
    private String nom;
    private String prenom;
    private String adresse;
    private int telephone;
    private String email;
    private int role;
    private String login;
    private String motDePasse;

    @Override
    public String toString() {
        return "User{" + "id_utilisateur=" + id_utilisateur + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", telephone=" + telephone + ", email=" + email + ", role=" + role + ", login=" + login + ", motDePasse=" + motDePasse + '}';
    }

    public User() {
    }

    public User(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public User(int id_utilisateur, String nom, String prenom, String adresse, int telephone, String email, int role, String login, String motDePasse) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
        this.login = login;
        this.motDePasse = motDePasse;
    }

  
    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id_utilisateur;
        hash = 67 * hash + Objects.hashCode(this.nom);
        hash = 67 * hash + Objects.hashCode(this.prenom);
        hash = 67 * hash + Objects.hashCode(this.adresse);
        hash = 67 * hash + this.telephone;
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + this.role;
        hash = 67 * hash + Objects.hashCode(this.login);
        hash = 67 * hash + Objects.hashCode(this.motDePasse);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id_utilisateur != other.id_utilisateur) {
            return false;
        }
        if (this.telephone != other.telephone) {
            return false;
        }
        if (this.role != other.role) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.motDePasse, other.motDePasse)) {
            return false;
        }
        return true;
    }

    public User(String nom, String prenom, String adresse, int telephone, String email, int role, String login, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public User(String nom, String prenom, String adresse, int telephone, String email, String login) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.login = login;
    }

    public User(int id_utilisateur, String nom, String prenom, String adresse, int telephone, String email, String login) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.login = login;
    }

    public User(int id_utilisateur, String nom, String adresse, int telephone, String email) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
    }

    public User(String nom, String adresse, int telephone, String email, int role, String login, String motDePasse) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
        this.login = login;
        this.motDePasse = motDePasse;
    }
    
    
    
    
}
