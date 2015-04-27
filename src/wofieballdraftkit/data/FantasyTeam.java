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
//        for(int i = 0; i<23; i ++){
//        Player p = new Player();
//        teamPlayer.add(p);
//        }
        posTemplate = new ArrayList();
        posTemplate.add("C");
        posTemplate.add("C");
        posTemplate.add("1B");
        posTemplate.add("CI");
        posTemplate.add("3B");
        posTemplate.add("2B");
        posTemplate.add("MI");
        posTemplate.add("SS");
        posTemplate.add("U");
        posTemplate.add("OF");
        posTemplate.add("OF");
        posTemplate.add("OF");
        posTemplate.add("OF");
        posTemplate.add("OF");
        posTemplate.add("P");
        posTemplate.add("P");
        posTemplate.add("P");
        posTemplate.add("P");
        posTemplate.add("P");
        posTemplate.add("P");
        posTemplate.add("P");
        posTemplate.add("P");
        posTemplate.add("P");
        
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
