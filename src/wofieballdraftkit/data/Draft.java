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
    
    ObservableList<Player> guiPool;
    int current;
    
    public Draft(List<Player> initPool){
    

    dataPool = new ArrayList(initPool);

    
    
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
    
    public Player getCurrentPlayer(){
    
        return guiPool.get(current);
    
    }
    
    public ObservableList<Player> handleSearchTF(String s) {
        
        guiPool.clear();
        
        String lowFirst; 
        String lowLast;
        for(int i = 0;  i<dataPool.size(); i ++){
          
            lowFirst =  dataPool.get(i).getFirstName().toLowerCase();
            lowLast =  dataPool.get(i).getLastName().toLowerCase();
        if(lowFirst.contains(s.toLowerCase()) ||lowLast.contains(s.toLowerCase())){
        
            guiPool.add(dataPool.get(i));
        }
        
    }    
  return guiPool;
    }
    
    
}
