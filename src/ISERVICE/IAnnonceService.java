/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISERVICE;

import MODEL.Annonce;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public interface IAnnonceService {
    
          public void create(Annonce a);
    public List<Annonce> getAllAnnonces();
    public Annonce getAnnoncebyId(int id);
    public void deleteAnnonce(int id);
    public void updateAnnonce(Annonce a);
    public ObservableList<Annonce> getAnnoncebyType(String type);
     public ObservableList<Annonce> getAnnoncebyIdType(int id_user,String type);
          public List<Annonce> getAnnoncebyIdUser(int id_user);

}
