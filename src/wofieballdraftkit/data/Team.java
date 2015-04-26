/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MC
 */
public class Team {
    ObservableList<Player> teamPlayer;
    
    String teamName;
    String teamOwner;
    int money;
    int numPlayer;
    public Team(){
        
    teamPlayer = FXCollections.observableArrayList();
    }
    
    public ObservableList<Player> getTeamPlayer() {
        return teamPlayer;
    }
    public void setTeamPlayer(ObservableList<Player> pool) {
        this.teamPlayer = pool;
    }    
    
    
    
    
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamOwner() {
        return teamOwner;
    }

    public void setTeamOwner(String teamOwner) {
        this.teamOwner = teamOwner;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getNumPlayer() {
        return numPlayer;
    }

    public void setNumPlayer(int numPlayer) {
        this.numPlayer = numPlayer;
    }

    
}
