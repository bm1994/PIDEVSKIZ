/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISERVICE;

import MODEL.Animal;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public interface IAnimalService {
    
           public void create1(Animal a);
    public ObservableList<Animal> getAllAnimals();
    public  Animal getAnimalbyId(int id);
    public void deleteAnimal(int id);
    public void updateAnimal(Animal a);
    
}
