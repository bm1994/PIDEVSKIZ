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
public class Abonnement 
{
    private int id_association;
    private int id_utilisateur;
    
    public Abonnement() 
    {
    }

    public Abonnement(int id_association, int id_utilisateur) 
    {
        this.id_association = id_association;
        this.id_utilisateur = id_utilisateur;
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
     * @return the id_utilisateur
     */
    public int getId_utilisateur() 
    {
        return id_utilisateur;
    }

    /**
     * @param id_utilisateur the id_utilisateur to set
     */
    public void setId_utilisateur(int id_utilisateur) 
    {
        this.id_utilisateur = id_utilisateur;
    }
    
}
