/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class Annonce {
    
    private int id_annonce;
    private String titre_annonce;
    private String description;
    private String date_annonce;
    private String photo_annonce;
    private String type_annonce;
    private String nom_animal;
    private int age_animal;
    private String type_animal;
    private String race_animal;
    private float poids_animal;
    private String sexe;
    private int id_user;

    public Annonce() {
    }

    public Annonce(int id_annonce, String titre_annonce, String description, String date_annonce, String photo_annonce, String type_annonce, String nom_animal, int age_animal, String type_animal, String race_animal, float poids_animal, String sexe, int id_user) {
        this.id_annonce = id_annonce;
        this.titre_annonce = titre_annonce;
        this.description = description;
        this.date_annonce = date_annonce;
        this.photo_annonce = photo_annonce;
        this.type_annonce = type_annonce;
        this.nom_animal = nom_animal;
        this.age_animal = age_animal;
        this.type_animal = type_animal;
        this.race_animal = race_animal;
        this.poids_animal = poids_animal;
        this.sexe = sexe;
        this.id_user = id_user;
    }

    public Annonce(String titre_annonce, String description, String date_annonce, String photo_annonce, String type_annonce, String nom_animal, int age_animal, String type_animal, String race_animal, float poids_animal, String sexe, int id_user) {
        this.titre_annonce = titre_annonce;
        this.description = description;
        this.date_annonce = date_annonce;
        this.photo_annonce = photo_annonce;
        this.type_annonce = type_annonce;
        this.nom_animal = nom_animal;
        this.age_animal = age_animal;
        this.type_animal = type_animal;
        this.race_animal = race_animal;
        this.poids_animal = poids_animal;
        this.sexe = sexe;
        this.id_user = id_user;
    }
      public Annonce(String titre_annonce, String description, String date_annonce, String photo_annonce, String type_annonce, String nom_animal, int age_animal, String type_animal, String race_animal, float poids_animal, String sexe) {
        this.titre_annonce = titre_annonce;
        this.description = description;
        this.date_annonce = date_annonce;
        this.photo_annonce = photo_annonce;
        this.type_annonce = type_annonce;
        this.nom_animal = nom_animal;
        this.age_animal = age_animal;
        this.type_animal = type_animal;
        this.race_animal = race_animal;
        this.poids_animal = poids_animal;
        this.sexe = sexe;
      
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public String getTitre_annonce() {
        return titre_annonce;
    }

    public void setTitre_annonce(String titre_annonce) {
        this.titre_annonce = titre_annonce;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_annonce() {
        return date_annonce;
    }

    public void setDate_annonce(String date_annonce) {
        this.date_annonce = date_annonce;
    }

    public String getPhoto_annonce() {
        return photo_annonce;
    }

    public void setPhoto_annonce(String photo_annonce) {
        this.photo_annonce = photo_annonce;
    }

    public String getType_annonce() {
        return type_annonce;
    }

    public void setType_annonce(String type_annonce) {
        this.type_annonce = type_annonce;
    }

    public String getNom_animal() {
        return nom_animal;
    }

    public void setNom_animal(String nom_animal) {
        this.nom_animal = nom_animal;
    }

    public int getAge_animal() {
        return age_animal;
    }

    public void setAge_animal(int age_animal) {
        this.age_animal = age_animal;
    }

    public String getType_animal() {
        return type_animal;
    }

    public void setType_animal(String type_animal) {
        this.type_animal = type_animal;
    }

    public String getRace_animal() {
        return race_animal;
    }

    public void setRace_animal(String race_animal) {
        this.race_animal = race_animal;
    }

    public float getPoids_animal() {
        return poids_animal;
    }

    public void setPoids_animal(float poids_animal) {
        this.poids_animal = poids_animal;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id_annonce;
        hash = 59 * hash + Objects.hashCode(this.titre_annonce);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.date_annonce);
        hash = 59 * hash + Objects.hashCode(this.photo_annonce);
        hash = 59 * hash + Objects.hashCode(this.type_annonce);
        hash = 59 * hash + Objects.hashCode(this.nom_animal);
        hash = 59 * hash + this.age_animal;
        hash = 59 * hash + Objects.hashCode(this.type_animal);
        hash = 59 * hash + Objects.hashCode(this.race_animal);
        hash = 59 * hash + Float.floatToIntBits(this.poids_animal);
        hash = 59 * hash + Objects.hashCode(this.sexe);
        hash = 59 * hash + this.id_user;
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
        final Annonce other = (Annonce) obj;
        if (this.id_annonce != other.id_annonce) {
            return false;
        }
        if (this.age_animal != other.age_animal) {
            return false;
        }
        if (Float.floatToIntBits(this.poids_animal) != Float.floatToIntBits(other.poids_animal)) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.titre_annonce, other.titre_annonce)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.date_annonce, other.date_annonce)) {
            return false;
        }
        if (!Objects.equals(this.photo_annonce, other.photo_annonce)) {
            return false;
        }
        if (!Objects.equals(this.type_annonce, other.type_annonce)) {
            return false;
        }
        if (!Objects.equals(this.nom_animal, other.nom_animal)) {
            return false;
        }
        if (!Objects.equals(this.type_animal, other.type_animal)) {
            return false;
        }
        if (!Objects.equals(this.race_animal, other.race_animal)) {
            return false;
        }
        if (!Objects.equals(this.sexe, other.sexe)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Annonce{" + "id_annonce=" + id_annonce + ", titre_annonce=" + titre_annonce + ", description=" + description + ", date_annonce=" + date_annonce + ", photo_annonce=" + photo_annonce + ", type_annonce=" + type_annonce + ", nom_animal=" + nom_animal + ", age_animal=" + age_animal + ", type_animal=" + type_animal + ", race_animal=" + race_animal + ", poids_animal=" + poids_animal + ", sexe=" + sexe + ", id_user=" + id_user + '}';
    }
    
     public java.sql.Date convert(String date) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date1 = sdf1.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
        return sqlDate;
    }

    public static String convert(java.sql.Date d) {
        if (d != null) {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String text = df.format(d);
            return text;
        }
        return "//";
    }
    
    
}
