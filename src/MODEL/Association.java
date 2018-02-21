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
public class Association 
{
    private int id_association;
    private String nom_association;
    private String adresse_association;
    private String telephone_association;
    private String email_association;
    private int nombre_abonnes;
    private short role;


    public Association() 
    {
    }
     public Association(String nom_association, String adresse_association, String telephone_association, String email_association) 
    {
        this.nom_association = nom_association;
        this.adresse_association = adresse_association;
        this.telephone_association = telephone_association;
        this.email_association = email_association;
        this.role = 2;
        this.nombre_abonnes=0;
    }
    public Association(int id_association, String nom_association, String adresse_association, String telephone_association, String email_association) 
    {
        this.id_association = id_association;
        this.nom_association = nom_association;
        this.adresse_association = adresse_association;
        this.telephone_association = telephone_association;
        this.email_association = email_association;
        this.role = 2;
    }
    
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
     * @return the nom_association
     */
    public String getNom_association() 
    {
        return nom_association;
    }

    /**
     * @param nom_association the nom_association to set
     */
    public void setNom_association(String nom_association) 
    {
        this.nom_association = nom_association;
    }

    /**
     * @return the adresse_association
     */
    public String getAdresse_association() 
    {
        return adresse_association;
    }

    /**
     * @param adresse_association the adresse_association to set
     */
    public void setAdresse_association(String adresse_association) 
    {
        this.adresse_association = adresse_association;
    }

    /**
     * @return the telephone_association
     */
    public String getTelephone_association() 
    {
        return telephone_association;
    }

    /**
     * @param telephone_association the telephone_association to set
     */
    public void setTelephone_association(String telephone_association) 
    {
        this.telephone_association = telephone_association;
    }

    /**
     * @return the email_association
     */
    public String getEmail_association() 
    {
        return email_association;
    }

    /**
     * @param email_association the email_association to set
     */
    public void setEmail_association(String email_association) 
    {
        this.email_association = email_association;
    }

    /**
     * @return the role
     */
    public short getRole() 
    {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(short role) 
    {
        this.role = role;
    }

    /**
     * @return the nombre_abonnes
     */
    public int getNombre_abonnes() {
        return nombre_abonnes;
    }

    /**
     * @param nombre_abonnes the nombre_abonnes to set
     */
    public void setNombre_abonnes(int nombre_abonnes) {
        this.nombre_abonnes = nombre_abonnes;
    }
    
}
