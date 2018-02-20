/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISERVICE;

import MODEL.User;

/**
 *
 * @author asus
 */
public interface UserInscriptionInterface {
    public void AjouterUser(User u);
    public String getelemntbylogin(String m);
    public int getelemntbyrole(String m);
    public void UpdateUser(User u);
    public void DeleteUser(int id);


    
}
