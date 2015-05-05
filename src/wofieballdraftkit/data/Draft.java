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
    ObservableList<FantasyTeam> teamList;
    
    ArrayList<Player> extraPlayerList;
    ObservableList<Player> dataPool;
    ObservableList<Player> guiPool;
    ObservableList<Player> searchPool;
    
   
    
    public Draft(List<Player> initPool){
    

    dataPool = FXCollections.observableArrayList(initPool); 
    teamList =  FXCollections.observableArrayList(); 
    extraPlayerList = new ArrayList();
    searchPool =FXCollections.observableArrayList(initPool); 
    guiPool =FXCollections.observableArrayList(initPool); 
    draftName = new SimpleStringProperty("");
    
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
    public ObservableList<FantasyTeam> getTeamList(){
        return teamList;
    } 
    public ArrayList<Player> getExtraPlayerList(){
        return extraPlayerList;
    } 
    
    public void setTeamList(ObservableList<FantasyTeam> list){
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
            //teamList.
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
    
    
    public void calculateEstimate(){
        
        for(int i =0; i<dataPool.size();i++){
            Player p = dataPool.get(i);
            if(p.getPosition().equalsIgnoreCase("P")){
            
                
                
                
                
                
                
                
            
            
            
            
            }
            else{
            
            
            
            } 
            
        
        
        
            
            
            
        
        }
    }
    
    
    
    
    
    
    
    
    public void calculatePts(){
        int count = 1;
        int count1 = 1;
        int count2 = 1;
        int count3 = 1;
        int count4 = 1;
        int count5 = 1;
        int count6 = 1;
        int count7 = 1;
        int count8 = 1;
        int count9 = 1;
    
    for(int i =0; i<teamList.size();i++){
        
        FantasyTeam t = teamList.get(i);
        // find how t ranks among  teams
        for(int j =0; j<teamList.size();j++){  
            if(!teamList.get(j).equals(t)){
                if(teamList.get(j).getR()<t.getR()){
                count ++;
                }
               
            }
         
        }
          for(int j =0; j<teamList.size();j++){  
            if(!teamList.get(j).equals(t)){
                if(teamList.get(j).getHR()<t.getHR()){
                count1 ++;
                }
               
            }
         
        }
          for(int j =0; j<teamList.size();j++){  
            if(!teamList.get(j).equals(t)){
                if(teamList.get(j).getRBI()<t.getRBI()){
                count2 ++;
                }
               
            }
         
        }
          for(int j =0; j<teamList.size();j++){  
            if(!teamList.get(j).equals(t)){
                if(teamList.get(j).getSB()<t.getSB()){
                count3 ++;
                }
               
            }
         
        }
          for(int j =0; j<teamList.size();j++){  
            if(!teamList.get(j).equals(t)){
                if(teamList.get(j).getBA()<t.getBA()){
                count4 ++;
                }
               
            }
         
        }
          for(int j =0; j<teamList.size();j++){  
            if(!teamList.get(j).equals(t)){
                if(teamList.get(j).getW()<t.getW()){
                count5 ++;
                }
               
            }
         
        }
          for(int j =0; j<teamList.size();j++){  
            if(!teamList.get(j).equals(t)){
                if(teamList.get(j).getSV()<t.getSV()){
                count6 ++;
                }
               
            }
         
        }
          for(int j =0; j<teamList.size();j++){  
            if(!teamList.get(j).equals(t)){
                if(teamList.get(j).getK()<t.getK()){
                count7 ++;
                }
               
            }
         
        }
        
          for(int j =0; j<teamList.size();j++){  
            if(!teamList.get(j).equals(t)){
                if(teamList.get(j).getERA()<t.getERA()){
                count8 ++;
                }
               
            }
         
        }
          for(int j =0; j<teamList.size();j++){  
            if(!teamList.get(j).equals(t)){
                if(teamList.get(j).getWHIP()<t.getWHIP()){
                count9 ++;
                }
               
            }
         
        }
          
        int sum = count +count1+count2+count3+count4
                +count5+count6+count7+count8+count9;    
         t.setPTS(sum);
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
    


