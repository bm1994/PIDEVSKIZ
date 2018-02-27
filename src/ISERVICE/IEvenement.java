/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISERVICE;

import MODEL.Evenement;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Ruskov
 */
public interface IEvenement 
{
 public void AjouterEvenement(int idUtilisateur,String SujetEvenement,Date DateEvenement,String TitreEvenement,String LieuEvenement);
 public void SupprimerEvenement(int idEvenement);
 public void ModifierEvenement(int idEvenement,String TitreEvenement,String SujetEvenement,Date DateEvenement,String LieuEvenement);
 public List<Evenement> ConsulterEvenement();
 public List<Evenement> ConsulterEvenement(int idUtilisateur);
 public List<Evenement> ChercherEvenement(String TitreEvenement);
 public Evenement ChercherEvenement(int id_evenement);
 public void IncrementNombreInteresses(int idEvenement);
 public void DecrementNombreInteresses(int idEvenement);
 public int idDernierEvenementAjoute(int id_association);
}
