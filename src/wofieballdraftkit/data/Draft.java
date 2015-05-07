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
    ObservableList<Player> transcation;
    
    ArrayList<Player> extraPlayerList;
    ObservableList<Player> dataPool;
    ObservableList<Player> guiPool;
    ObservableList<Player> searchPool;
    
   
    
    public Draft(List<Player> initPool){
    
    transcation = FXCollections.observableArrayList(); 
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
    public ObservableList<Player> getTrascation(){
        return transcation;
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
    
    public Player pickPlayer(String pos){
    
        for(int i = 0;i<dataPool.size();i++)
        {
            if("X".equals(pos)){
                
                 return dataPool.get(i);    
              
            }
            else if("MI".equals(pos)){
                
                if(dataPool.get(i).getQualifyPosition().contains("SS")
                    ||  dataPool.get(i).getQualifyPosition().contains("2B")) 
                {
                 return dataPool.get(i);    
                }
            }
            else if("CI".equals(pos)){
                
                if(dataPool.get(i).getQualifyPosition().contains("1B")
                    ||  dataPool.get(i).getQualifyPosition().contains("3B")) 
                {
                 return dataPool.get(i);    
                }
            }    
            else if("U".equals(pos)){
                
                if(!dataPool.get(i).getQualifyPosition().contains("P"))
                {
                 return dataPool.get(i);  
                }
            } 
            else{ 
                if(dataPool.get(i).getQualifyPosition().contains(pos))
                return dataPool.get(i);
            }
        }
    
    return null; 
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
        int rank =1;
        int rank1 =1;
        int rank2 =1;
        int rank3 =1;
        int rank4 =1;
        int rankAvg=0;
        int medianSal=0;
        int numP =pitcherLeft();
        int numH =hitterLeft();
        

        int remaining = calculateMoneyLeft();
        for(int i =0; i<dataPool.size();i++){
            Player p = dataPool.get(i);
            
            if(p.getQualifyPosition().contains("P")){
            
                for(int k =0; k<dataPool.size();k++){
                if(!dataPool.get(k).equals(p)
                        &&dataPool.get(k).getQualifyPosition().contains("P")){
                    if(dataPool.get(k).getRW()>=p.getRW()){
                        rank++;
                        }   
                       
                    }
                }
                for(int k =0; k<dataPool.size();k++){
                if(!dataPool.get(k).equals(p)
                       &&dataPool.get(k).getQualifyPosition().contains("P")){
                    if(dataPool.get(k).getHRSV()>=p.getHRSV()){
                        rank1++;
                        }   
                    }
                }                
                for(int k =0; k<dataPool.size();k++){
                if(!dataPool.get(k).equals(p)
                      &&dataPool.get(k).getQualifyPosition().contains("P")){
                    if(dataPool.get(k).getRBIK()>=p.getRBIK()){
                        rank2++;
                        }   
                    }
                }                
                 for(int k =0; k<dataPool.size();k++){
                if(!dataPool.get(k).equals(p)
                      &&dataPool.get(k).getQualifyPosition().contains("P")){
                    if(dataPool.get(k).getSBERA()>=p.getSBERA()){
                        rank3++;
                        }   
                    }
                }     
                for(int k =0; k<dataPool.size();k++){
                if(!dataPool.get(k).equals(p)
                      &&dataPool.get(k).getQualifyPosition().contains("P")){
                    if(dataPool.get(k).getBAWHIP()>=p.getBAWHIP()){
                        rank4++;
                        }   
                    }
                } 
                
                rankAvg = (rank+rank1+rank2+rank3+rank4)/5;
                int temp =0;
                if(numP==0||rankAvg==0){
                medianSal =0;
                temp=0 ;
                }
                else{
                medianSal = remaining/(2*numP);
                temp = medianSal*2*numP/rankAvg;
                }
                p.setEstimated(temp);                            
                
                rank =1;
                rank1 =1;
                rank2 =1;
                rank3 =1;
                rank4 =1;

            }
            else
            {
                for(int k =0; k<dataPool.size();k++){
                if(!dataPool.get(k).equals(p)){
                    if(dataPool.get(k).getRW()>=p.getRW()){
                        rank++;
                        }   
                    }
                }
                for(int k =0; k<dataPool.size();k++){
                if(!dataPool.get(k).equals(p)){
                    if(dataPool.get(k).getHRSV()>=p.getHRSV()){
                        rank1++;
                        }   
                    }
                }                
                for(int k =0; k<dataPool.size();k++){
               if(!dataPool.get(k).equals(p)){
                    if(dataPool.get(k).getRBIK()>=p.getRBIK()){
                        rank2++;
                        }   
                    }
                }                
                 for(int k =0; k<dataPool.size();k++){
                if(!dataPool.get(k).equals(p)){
                    if(dataPool.get(k).getSBERA()>=p.getSBERA()){
                        rank3++;
                        }   
                    }
                }     
                for(int k =0; k<dataPool.size();k++){
                if(!dataPool.get(k).equals(p)){
                    if(dataPool.get(k).getBAWHIP()>=p.getBAWHIP()){
                        rank4++;
                        }   
                    }
                }            
                
            rankAvg = (rank+rank1+rank2+rank3+rank4)/5;
                int temp =0;
                if(numH==0||rankAvg==0){
                medianSal =0;
                temp=0 ;
                }
                else{
                medianSal = remaining/(2*numH);
                temp = medianSal*2*numH/rankAvg;
                }
            p.setEstimated(temp);  
            
            
            rank =1;
            rank1 =1;
            rank2 =1;
            rank3 =1;
            rank4 =1;

            } 
            
        
        
        
            
//          rankAvg = (rank+rank1+rank2+rank3+rank4)/5;
//           System.out.println(rankAvg);
//          
//          if(p.getQualifyPosition().contains("P")){
//          medianSal = remaining/(2*numP);
//          p.setEstimated(medianSal*(numP*2/rankAvg));
//         System.out.println(medianSal*(2*numP/rankAvg));
//                   }
//          else{
//          medianSal = remaining/(2*numH); 
//          p.setEstimated((medianSal*(numH*2/rankAvg)));
//          }
//          
//         rank =1;
//         rank1 =1;
//         rank2 =1;
//         rank3 =1;
//         rank4 =1;
//         
        }
    }
    
    public int pitcherLeft(){
    int sum=0;
    for(FantasyTeam t: teamList){
        
       for(int i =0; i < t.getTeamPlayer().size();i++){
       
       if(t.getTeamPlayer().get(i).getPosition().equalsIgnoreCase("P")){
        
           sum++;
       
       }
       }
    
    }  
    return teamList.size()*9 - sum;
    }
    
    
    public int hitterLeft(){
    int sum=0;
        for(FantasyTeam t: teamList){
        
       for(int i =0; i < t.getTeamPlayer().size();i++){
       
       if(!t.getTeamPlayer().get(i).getPosition().equalsIgnoreCase("P")){
        
           sum++;
       
       }
       }
    
    }  
    return teamList.size()*14 - sum;
    }
    
    
    
    
    public int calculateMoneyLeft(){
    int sum= 0;
    for(FantasyTeam t:teamList){

    sum = sum + t.getMoneyLeft();

    }
    return sum;
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
        
    


    


