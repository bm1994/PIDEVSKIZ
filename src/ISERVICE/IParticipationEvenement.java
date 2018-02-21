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
public interface IParticipationEvenement 
{
    public void AjouterParticipation(int id_utilisateur,int id_evenement);
    public void SupprimerParticipation(int id_utilisateur,int id_evenement);
    public boolean VerifierParticipation(int id_utilisateur,int id_evenement);
}
