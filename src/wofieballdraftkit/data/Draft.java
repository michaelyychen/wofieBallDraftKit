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
    List<Player> dataPool;
    List<Team> teamList;
    List<MLBTeams> MLBList;
    WBDK_GUI gui;
    ObservableList<Player> guiPool;
    ObservableList<Player> searchPool;
    int current;
    
    public Draft(List<Player> initPool){
    

    dataPool = new ArrayList(initPool);

    
    searchPool =FXCollections.observableArrayList(); 
    searchPool.addAll(initPool);
    guiPool =FXCollections.observableArrayList(); 
    guiPool.addAll(initPool);
    
    }
    
    

    public List<Hitter> getHitterList() {
        return hitterList;
    }

 

    public List<Pitcher> getPitcherList() {
        return pitcherList;
    }


    public List<Player> getDataPool() {
        return dataPool;
    }

  

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public List<MLBTeams> getMLBList() {
        return MLBList;
    }
    

    public ObservableList<Player> getGuiPool() {
        return guiPool;
    }
    
    public void addSearchPool(ObservableList<Player> t) {
        searchPool.clear();
        searchPool.addAll(t);
    }    
    

    
    public ObservableList<Player> handleSearchTF(String s) {
        
        guiPool.clear();
        
        String lowFirst; 
        String lowLast;
        for(int i = 0;  i<searchPool.size(); i ++){
          
            lowFirst =  searchPool.get(i).getFirstName().toLowerCase();
            lowLast =  searchPool.get(i).getLastName().toLowerCase();
        if(lowFirst.contains(s.toLowerCase()) ||lowLast.contains(s.toLowerCase())){
        
            guiPool.add(searchPool.get(i));
        }
        
    }    
  return guiPool;
    }
    

}
