/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.data;


import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    
    final IntegerProperty moneyLeft;
    final IntegerProperty playerCount;
    final IntegerProperty PP;
    
    public static final String DEFAULT_STRING = "";
    
    public FantasyTeam(){
        owner = new SimpleStringProperty(DEFAULT_STRING);
        teamName = new SimpleStringProperty(DEFAULT_STRING);
        teamPlayer = FXCollections.observableArrayList();
        
        playerCount = new SimpleIntegerProperty(23);
        moneyLeft = new SimpleIntegerProperty(260);
        PP = new SimpleIntegerProperty(0);
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
    
    public int getPP() {
        return PP.get();
    }
    
    public void setPP(int n) {
        PP.set(n);
    }
    
    public IntegerProperty ppProperty() {
        return PP;
    }
        
    public int getPlayerCount() {
        return playerCount.get();
    }
    
    public void setPlayerCount(int n) {
        playerCount.set(n);
    }
    
    public IntegerProperty playercountProperty() {
        return playerCount;
    }
    
    
    public int getMoneyleft() {
        return moneyLeft.get();
    }
    
    public void setMoneyleft(int n) {
        moneyLeft.set(n);
    }
    
    public IntegerProperty moneyleftProperty() {
        return moneyLeft;
    }
    
    public void changeMoneyLeft(int i ){
    moneyLeft.add(i);
    }
    
    public void changePlayerCount(int i ){
    playerCount.add(i);
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
    
    public void updatePP(){
        
        int sum = 0;
        int count = 0;
        
        for(Player p : teamPlayer){
        
        sum = sum + p.getSalary();
        count ++;
        }       
        
        setPP(sum/count);
    }
    
}
