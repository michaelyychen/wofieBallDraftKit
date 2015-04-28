/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.data;


import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wofieballdraftkit.gui.PositionComparator;

/**
 *
 * @author MiChAeL
 */
public class FantasyTeam{
    final StringProperty owner;
    final StringProperty teamName;
    ObservableList<Player> teamPlayer;
    ArrayList<String> posTemplate;
    
    public static final String DEFAULT_STRING = "";
    
    public FantasyTeam(){
        owner = new SimpleStringProperty(DEFAULT_STRING);
        teamName = new SimpleStringProperty(DEFAULT_STRING);
        teamPlayer = FXCollections.observableArrayList();

        
    }
    public ArrayList<String> getTemplate(){
    return posTemplate;
    
    }
    
    public boolean positionCount(String Pos){
    
    int count=0;    
    for(int i =0; i<teamPlayer.size();i++){
    
    if(teamPlayer.get(i).getPosition().equalsIgnoreCase(Pos))
        count++;
    }       
    if (Pos.equalsIgnoreCase("C")&&count<2){return true;}
    else if (Pos.equalsIgnoreCase("1B")&&count<1){return true;}
    else if (Pos.equalsIgnoreCase("CI")&&count<1){return true;}
    else if (Pos.equalsIgnoreCase("3B")&&count<1){return true;}
    else if (Pos.equalsIgnoreCase("2B")&&count<1){return true;}
    else if (Pos.equalsIgnoreCase("MI")&&count<1){return true;}
    else if (Pos.equalsIgnoreCase("SS")&&count<1){return true;}
    else if (Pos.equalsIgnoreCase("U")&&count<1){return true;}
    else if (Pos.equalsIgnoreCase("OF")&&count<5){return true;}
    else if (Pos.equalsIgnoreCase("P")&&count<9){return true;}
    
    else return false;
    }
    
    public void addByPos(Player p){
    teamPlayer.add(p);
    teamPlayer.sort(new PositionComparator());
    
    }

    
    
    public ObservableList<Player> getTeamPlayer() {
        return teamPlayer;
    }
    public void setTeamPlayer(ObservableList<Player> pool) {
        this.teamPlayer = pool;
    }        
    
    
    
    public String getOwner() {
        return owner.get();
    }
    
    public void setOwner(String initOwner) {
        owner.set(initOwner);
    }
    
    public StringProperty ownerProperty() {
        return owner;
    }
    public String getTeamName() {
        return teamName.get();
    }
    
    public void setTeamName(String initName) {
        teamName.set(initName);
    }
    
    public StringProperty teamNameProperty() {
        return teamName;
    }
    

    
}
