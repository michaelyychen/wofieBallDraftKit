/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.data;

import java.util.ArrayList;

/**
 *
 * @author MiChAeL
 */
public class DraftDataManager {    
    
    
    ArrayList<Player> playerList;
    ArrayList<Team> teamList;
    ArrayList<Player> eligibleList;
    
    
    
    
    
    
    

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(ArrayList<Team> teamList) {
        this.teamList = teamList;
    }

    public ArrayList<Player> getEligibleList() {
        return eligibleList;
    }

    public void setEligibleList(ArrayList<Player> eligibleList) {
        this.eligibleList = eligibleList;
    }

    
}
