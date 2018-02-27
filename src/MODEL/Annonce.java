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
   
    private Animal id_animal;

    public Annonce() {
    }
     public Annonce(int id_annonce, String titre_annonce, String description, String date_annonce, String photo_annonce, String type_annonce, Animal id_animal) {
        this.id_annonce = id_annonce;
        this.titre_annonce = titre_annonce;
        this.description = description;
        this.date_annonce = date_annonce;
        this.photo_annonce = photo_annonce;
        this.type_annonce = type_annonce;
this.id_animal=id_animal;    //   this.id_animal = id_animal;
    }

    public Annonce(int id_annonce, String titre_annonce, String description, String date_annonce, String photo_annonce, String type_annonce, Animal id_animal,int id) {
        this.id_annonce = id_annonce;
        this.titre_annonce = titre_annonce;
        this.description = description;
        this.date_annonce = date_annonce;
        this.photo_annonce = photo_annonce;
        this.type_annonce = type_annonce;
         this.id_animal = id_animal;
this.id_animal.setId_animal(id);     //   this.id_animal = id_animal;
    }

    public Annonce(String titre_annonce, String description, String date_annonce, String photo_annonce, String type_annonce, Animal id_animal) {
        this.titre_annonce = titre_annonce;
        this.description = description;
        this.date_annonce = date_annonce;
        this.photo_annonce = photo_annonce;
        this.type_annonce = type_annonce;
        this.id_animal = id_animal;
    }

    public Annonce(int id_annonce, String titre_annonce, String description, String date_annonce, String type_annonce, Animal id_animal) {
        this.id_annonce = id_annonce;
        this.titre_annonce = titre_annonce;
        this.description = description;
        this.date_annonce = date_annonce;
        this.type_annonce = type_annonce;
        this.id_animal = id_animal;
    }

    public Annonce(String titre_annonce, String description, String date_annonce, String type_annonce, Animal id_animal) {
        this.titre_annonce = titre_annonce;
        this.description = description;
        this.date_annonce = date_annonce;
        this.type_annonce = type_annonce;
        this.id_animal = id_animal;
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

    public Animal getId_animal() {
        return id_animal;
    }
   public int getId_animal2()
   {
   return id_animal.getId_animal();
   }   

    public void setId_animal(Animal id_animal) {
        this.id_animal = id_animal;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id_annonce=" + id_annonce + ", titre_annonce=" + titre_annonce + ", description=" + description + ", date_annonce=" + date_annonce + ", photo_annonce=" + photo_annonce + ", type_annonce=" + type_annonce + ", id_animal=" + id_animal + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id_annonce;
        hash = 89 * hash + Objects.hashCode(this.titre_annonce);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.date_annonce);
        hash = 89 * hash + Objects.hashCode(this.photo_annonce);
        hash = 89 * hash + Objects.hashCode(this.type_annonce);
        hash = 89 * hash + Objects.hashCode(this.id_animal);
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
        return true;
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
