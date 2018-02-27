/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISERVICE;

import MODEL.Association;
import MODEL.Evenement;
import MODEL.Notification;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ruskov
 */
public interface INotification 
{
    public List<Notification> chercherNotification(int id_utilisateur);
    public void ajouterNotification(int id_utilisateur, int id_association,int evenement,int type);
    public void supprimerNotification(int id_utilisateur);
}
