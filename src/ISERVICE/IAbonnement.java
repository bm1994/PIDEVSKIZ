/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISERVICE;

/**
 *
 * @author Ruskov
 */
public interface IAbonnement 
{
    public void AjouterAbonnement(int id_association,int id_utilisateur);
    public void SupprimerAbonnement(int id_association,int id_utilisateur);
    public int NombreAbonnes(int id_association);
    public boolean VerifierAbonnement(int id_utilisateur,int id_association);
}
