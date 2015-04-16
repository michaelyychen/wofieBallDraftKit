/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author MC
 */
public class Player {
    final StringProperty team;
    final StringProperty lastname;
    final StringProperty firstname;
    final StringProperty position;
    final StringProperty nation;
    final StringProperty notes;
    final StringProperty birth;
    
    
    int salary;
  
    
    public static final String DEFAULT_NAME = "";
    
    public Player(){
        firstname = new SimpleStringProperty(DEFAULT_NAME);
        lastname = new SimpleStringProperty(DEFAULT_NAME);
        team = new SimpleStringProperty(DEFAULT_NAME);
        position = new SimpleStringProperty(DEFAULT_NAME);
        nation = new SimpleStringProperty(DEFAULT_NAME);
        notes = new SimpleStringProperty(DEFAULT_NAME);
        birth = new SimpleStringProperty(DEFAULT_NAME);
        
    }

    public String getPosition() {
        return position.get();
    }

    public void setPosition(String initPosition) {
        position.set(initPosition);
    }
    
    public StringProperty positionProperty(){
    return position;
    }

    public String getFirstName() {
        return firstname.get();
    }

    public void setFirstName(String initName) {
        firstname.set(initName);
    }
    
    public StringProperty firstnameProperty(){  
        return firstname;
    }
    public String getLastName() {
        return lastname.get();
    }

    public void setLastName(String initName) {
        lastname.set(initName);
    }
    
    public StringProperty lastnameProperty(){  
        return lastname;
    }    
    public String getTeam() {
        return team.get();
    }

    public void setTeam(String initTeam) {
        team.set(initTeam);
    }
    
    public StringProperty teamProperty(){  
        return team;
    }
    public String getNation() {
        return nation.get();
    }

    public void setNation(String initNation) {
        nation.set(initNation);
    }
    
    public StringProperty nationProperty(){  
        return nation;
    }    
    public String getNotes() {
        return notes.get();
    }

    public void setNotes(String initNotes) {
        notes.set(initNotes);
    }
    
    public StringProperty notesProperty(){  
        return notes;
    }
    public String getBirth() {
        return birth.get();
    }

    public void setBirth(String initBirth) {
        birth.set(initBirth);
    }
    
    public StringProperty birthProperty(){  
        return birth;
    }    
    
    
    
    
}
