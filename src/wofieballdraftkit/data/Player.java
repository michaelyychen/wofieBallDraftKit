/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import static wofieballdraftkit.data.Pitcher.DEFAULT_NUM;

/**
 *
 * @author MC
 */
public class Player {
    final StringProperty fantasyTeam;
    final StringProperty proTeam;
    final StringProperty qualifyPosition;
    final StringProperty position;
    final StringProperty contract;
    final IntegerProperty salary;
    
    final StringProperty lastname;
    final StringProperty firstname;
    final StringProperty nation;
    final StringProperty notes;
    final StringProperty birth;
    
    final IntegerProperty rw;
    final IntegerProperty hrsv;
    final DoubleProperty rbik;
    final DoubleProperty sbera;
    final DoubleProperty bawhip;    
    
    
    
   
  
    
    public static final String DEFAULT_NAME = "";
    public static final int DEFAULT_NUM = 0 ;
    
    public Player(){
        firstname = new SimpleStringProperty(DEFAULT_NAME);
        lastname = new SimpleStringProperty(DEFAULT_NAME);
        proTeam = new SimpleStringProperty(DEFAULT_NAME);
        fantasyTeam = new SimpleStringProperty(DEFAULT_NAME);
        qualifyPosition = new SimpleStringProperty(DEFAULT_NAME);
        position = new SimpleStringProperty(DEFAULT_NAME);
        contract = new SimpleStringProperty(DEFAULT_NAME);
        nation = new SimpleStringProperty(DEFAULT_NAME);
        notes = new SimpleStringProperty(DEFAULT_NAME);
        birth = new SimpleStringProperty(DEFAULT_NAME);
        salary = new SimpleIntegerProperty(DEFAULT_NUM);
        rw = new SimpleIntegerProperty(DEFAULT_NUM);
        rbik = new SimpleDoubleProperty(DEFAULT_NUM);
        hrsv = new SimpleIntegerProperty(DEFAULT_NUM);
        sbera = new SimpleDoubleProperty(DEFAULT_NUM);
        bawhip = new SimpleDoubleProperty(DEFAULT_NUM);
        
    }
    
    public void reset(){
        
       setFirstName(DEFAULT_NAME); 
       setLastName(DEFAULT_NAME);
       setFantasyTeam(DEFAULT_NAME);
       setQualifyPosition(DEFAULT_NAME);
       setPosition(DEFAULT_NAME);
       setContract(DEFAULT_NAME);
       setNation(DEFAULT_NAME);
       setNotes(DEFAULT_NAME);
       setBirth(DEFAULT_NAME);
       setProTeam(DEFAULT_NAME);
       setRW(DEFAULT_NUM);
       setRBIK(DEFAULT_NUM);
       setHRSV(DEFAULT_NUM);
       setSBERA(DEFAULT_NUM);
       setBAWHIP(DEFAULT_NUM);
       setSalary(DEFAULT_NUM);
       
    
    }
    public String getProTeam() {
        return proTeam.get();
    }

    public void setProTeam(String s) {
        proTeam.set(s);
    }
    
    public StringProperty proTeamProperty(){
    return proTeam;
    }       
    public int getSalary() {
        return salary.get();
    }

    public void setSalary(int s) {
        salary.set(s);
    }
    
    public IntegerProperty salaryProperty(){
    return salary;
    }      
    
    public String getContract() {
        return contract.get();
    }

    public void setContract(String s) {
        contract.set(s);
    }
    
    public StringProperty contractProperty(){
    return contract;
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
    
    public String getQualifyPosition() {
        return qualifyPosition.get();
    }

    public void setQualifyPosition(String initPosition) {
        qualifyPosition.set(initPosition);
    }
    
    public StringProperty qualifypositionProperty(){
    return qualifyPosition;
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
    public String getFantasyTeam() {
        return fantasyTeam.get();
    }

    public void setFantasyTeam(String initTeam) {
        fantasyTeam.set(initTeam);
    }
    
    public StringProperty fantasyteamProperty(){  
        return fantasyTeam;
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
    public int getRW() {
        return rw.get();
    }

    public void setRW(int initRW) {
        rw.set(initRW);
    }
    
    public IntegerProperty rwProperty(){
    return rw;
    }   
     public int getHRSV() {
        return hrsv.get();
    }

    public void setHRSV(int initHRSV) {
        hrsv.set(initHRSV);
    }
    
    public IntegerProperty hrsvProperty(){
    return hrsv;
    } 
     public double getRBIK() {
        return rbik.get();
    }

    public void setRBIK(int initRBIK) {
        rbik.set(initRBIK);
    }
    
    public DoubleProperty rbikProperty(){
    return rbik;
    }   
    public double getSBERA() {
        return sbera.get();
    }

    public void setSBERA(int initSV) {
        sbera.set(initSV);
    }
    
    public DoubleProperty sberaProperty(){
    return sbera;
    }   
    public double getBAWHIP() {
        return bawhip.get();
    }

    public void setBAWHIP(int initBAWHIP) {
        bawhip.set(initBAWHIP);
    }
    
    public DoubleProperty bawhipProperty(){
    return bawhip;
    }       
    
    
    
    
    
}
