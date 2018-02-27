/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISERVICE;


import MODEL.Association;
import java.util.List;

/**
 *
 * @author Ruskov
 */
public interface IAssociation 
{
    public List<Association> listerAssociation();
    public Association chercherAssociation (int id_association);
    public Association chercherAssociation(String nom);
    
}
