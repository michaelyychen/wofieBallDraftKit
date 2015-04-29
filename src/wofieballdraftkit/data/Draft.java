/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.data;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Toggle;
import wofieballdraftkit.gui.WBDK_GUI;

/**
 *
 * @author MC
 */
public class Draft {
    
    StringProperty draftName;
    List<Hitter> hitterList;
    List<Pitcher> pitcherList;
    ArrayList<FantasyTeam> teamList;
    
    ArrayList<Player> extraPlayerList;
    ObservableList<Player> dataPool;
    ObservableList<Player> guiPool;
    ObservableList<Player> searchPool;
    
   
    
    public Draft(List<Player> initPool){
    

    dataPool = FXCollections.observableArrayList(initPool); 
    teamList = new ArrayList();
    extraPlayerList = new ArrayList();
    searchPool =FXCollections.observableArrayList(initPool); 
    guiPool =FXCollections.observableArrayList(initPool); 
    draftName = new SimpleStringProperty();
    
    }
    public String getDraftName() {
      return draftName.get();
    }

    public void setDraftName(String s) {
        draftName.set(s);
    }
    
    public StringProperty draftNameProperty(){
    return draftName;
    }     
    public ArrayList<FantasyTeam> getTeamList(){
        return teamList;
    } 
    public ArrayList<Player> getExtraPlayerList(){
        return extraPlayerList;
    } 
    
    public void setTeamList(ArrayList<FantasyTeam> list){
        this.teamList = list;
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
    
    public void addSearchPool(ObservableList<Player> t) {
        searchPool.clear();
        searchPool.addAll(t);
    }    
    
    public FantasyTeam getTeamByName(String s){
    
        for(int i = 0;i<teamList.size();i++)
        {
        if(teamList.get(i).getTeamName().equalsIgnoreCase(s) ){
            return teamList.get(i);
                    }
        }
    
    return null; 
    }
    
    public void RemoveTeamByName(String s){
    
        for(int i = 0;i<teamList.size();i++)
        {
        if(teamList.get(i).getTeamName().equalsIgnoreCase(s) ){
             
            teamList.remove(i);
            teamList.trimToSize();
            
                    }
        }
    }
    
    public void clearTeamList(){
     teamList.clear();
    }
    
    public void RemovePlayerByName(List<Player>pool, Player playerToRemove){
    
        for(int i =0; i <pool.size(); i++){
         Player temp = pool.get(i);
         
         if(temp.getFirstName().equalsIgnoreCase(playerToRemove.getFirstName())
                 &&temp.getLastName().equalsIgnoreCase(playerToRemove.getLastName()))
             pool.remove(i);
        
        }  
    
        }
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
    


