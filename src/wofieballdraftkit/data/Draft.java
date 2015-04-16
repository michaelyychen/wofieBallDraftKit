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
    List<Player> playerPool;
    List<Team> teamList;
    List<MLBTeams> MLBList;
    
    ObservableList<Player> guiPool;
    
    
    public Draft(List<Player> initPool){
    

  //  playerPool = initPool;
    teamList = new ArrayList();
    MLBList= new ArrayList();
    
    
    guiPool =FXCollections.observableArrayList(); 
    guiPool.addAll(initPool);
    
    }
    
    

    public List<Hitter> getHitterList() {
        return hitterList;
    }

 

    public List<Pitcher> getPitcherList() {
        return pitcherList;
    }


    public List<Player> getPlayerPool() {
        return playerPool;
    }

    public void setPlayerPool(List<Player> playerPool) {
        this.playerPool = playerPool;
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


    
    
}
