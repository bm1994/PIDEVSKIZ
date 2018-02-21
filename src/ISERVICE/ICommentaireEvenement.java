/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISERVICE;

import MODEL.CommentaireEvenement;
import java.util.List;

/**
 *
 * @author Ruskov
 */
public interface ICommentaireEvenement 
{
    public void AjouterCommentaireEvenement(int id_evenement, int id_utilisateur, String contenu);
    public void SupprimerCommentaireEvenement(int id_commentaire_evenement);
    public void ModifierCommentaireEvenement(int id_commentaire_evenement, String contenu);
    public List<CommentaireEvenement> ConsulterCommentaireEvenements(int id_evenement);
}
