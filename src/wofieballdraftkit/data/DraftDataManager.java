/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.data;

import java.util.ArrayList;
import wofieballdraftkit.file.DraftFileManager;


/**
 *
 * @author MiChAeL
 */
public class DraftDataManager {    
    
    
    ArrayList<Player> playerList;
    ArrayList<Team> teamList;
    ArrayList<Player> eligibleList;
    
    Draft draft;
    
    DraftDataView view;
    
    DraftFileManager fileManager;
    
    public DraftDataManager(DraftDataView initView, Team Team){
         
        view = initView;
        draft = new Draft();
        
        
    }
    
    public Draft getDraft(){
        return draft;
    }
    
    public DraftFileManager getFileManager() {
       return fileManager;
    }
    
    
      public void reset() {
        // CLEAR ALL THE COURSE VALUES
//        course.setSubject(DEFAULT_COURSE_SUBJECT);
//        course.setNumber(DEFAULT_NUM);
//        course.setTitle(DEFAULT_TEXT);
//        course.setSemester(DEFAULT_SEMESTER);
//        course.setYear(LocalDate.now().getYear());
//        LocalDate nextMonday = getNextMonday();
//        course.setStartingMonday(nextMonday);
//        course.setEndingFriday(getNextFriday(nextMonday));
//        course.clearLectureDays();
//        course.clearPages();
        
        // AND THEN FORCE THE UI TO RELOAD THE UPDATED COURSE
        view.reloadDraft(draft);
    }
    

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
