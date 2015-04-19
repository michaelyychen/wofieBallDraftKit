/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.data;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Toggle;
import wofieballdraftkit.gui.WBDK_GUI;

/**
 *
 * @author MC
 */
public class Draft {
    List<Hitter> hitterList;
    List<Pitcher> pitcherList;
    ObservableList<Player> dataPool;
    ObservableList<Player> guiPool;
    ObservableList<Player> searchPool;
    ObservableList<Player> textPool;
    int current;
    
    public Draft(List<Player> initPool){
    

    dataPool = FXCollections.observableArrayList(initPool); 

    textPool =FXCollections.observableArrayList(initPool); 
    searchPool =FXCollections.observableArrayList(initPool); 
    guiPool =FXCollections.observableArrayList(initPool); 

    
    }
    
    

    public List<Hitter> getHitterList() {
        return hitterList;
    }

    public List<Pitcher> getPitcherList() {
        return pitcherList;
    }


    public ObservableList<Player> getDataPool() {
        return dataPool;
    }
    
    public ObservableList<Player> getSearchPool() {
        return searchPool;
    }
    

    public ObservableList<Player> getGuiPool() {
        return guiPool;
    }
    public void setGuiPool(ObservableList<Player> pool) {
        this.guiPool = pool;
    }
    public ObservableList<Player> getTextPool() {
        return textPool;
    }    
    public void setTextPool(ObservableList<Player> pool) {
        this.textPool= pool;
    } 
    public void addTextPool(ObservableList<Player> t) {
        textPool.clear();
        textPool.addAll(t);
    }       
    
    
    
    
    public void addSearchPool(ObservableList<Player> t) {
        searchPool.clear();
        searchPool.addAll(t);
    }    
    

    
//    public ObservableList<Player> handleSearchTF(String s) {
//        ObservableList<Player> temp =FXCollections.observableArrayList(searchPool);
//        guiPool.clear();
//        
//        String lowFirst; 
//        String lowLast;
//        for(int i = 0;  i<temp.size(); i ++){
//          
//            lowFirst =  temp.get(i).getFirstName().toLowerCase();
//            lowLast =  temp.get(i).getLastName().toLowerCase();
//        if(lowFirst.contains(s.toLowerCase()) ||lowLast.contains(s.toLowerCase())){
//        
//            guiPool.add(temp.get(i));
//        }
//        
//    }    
//  return guiPool;
//    }
    

}
