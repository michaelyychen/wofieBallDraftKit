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


/**
 *
 * @author MiChAeL
 */
public class Hitter extends Player{

    final IntegerProperty r;
    final IntegerProperty hr;
    final IntegerProperty h;
    final IntegerProperty sb;

    final DoubleProperty rbi;
    final DoubleProperty ba;
    
    final StringProperty qp;
    
    public static final String DEFAULT_NAME = "";
    public static final int DEFAULT_NUM = 0 ;
    
    public Hitter(String initFirstname, String initLastname){
   super();
   
   this.firstname.set(initFirstname);
   this.lastname.set(initLastname);
   
   r = new SimpleIntegerProperty(DEFAULT_NUM);
   hr = new SimpleIntegerProperty(DEFAULT_NUM);
   h = new SimpleIntegerProperty(DEFAULT_NUM);
   sb = new SimpleIntegerProperty(DEFAULT_NUM);
   
   rbi = new SimpleDoubleProperty(DEFAULT_NUM);
   ba = new SimpleDoubleProperty(DEFAULT_NUM);   
   
   qp = new SimpleStringProperty(DEFAULT_NAME);
   
   
   }
    public String getQP() {
        return qp.get();
    }

    public void setQP(String initQP) {
        qp.set(initQP);
    }
    
    public StringProperty qpProperty(){
    return qp;
    } 
    public double getRBI() {
        return rbi.get();
    }

    public void setRBI(double initRBI) {
        this.rbik.set(initRBI);
        rbi.set(initRBI);
    }
    
    public DoubleProperty rbiProperty(){
    return rbi;
    }     
    public double getBA() {
        return ba.get();
    }

    public void setBA(double initBA) {
        this.bawhip.set(initBA);
        ba.set(initBA);
    }
    
    public DoubleProperty baProperty(){
    return ba;
    }
    public int getR() {
        return r.get();
    }

    public void setR(int initR) {
        this.rw.set(initR);
        r.set(initR);
    }
    
    public IntegerProperty rProperty(){
    return r;
    }
    public int getHR() {
        return hr.get();
    }

    public void setHR(int initHR) {
        this.hrsv.set(initHR);
        hr.set(initHR);
    }
    
    public IntegerProperty hrProperty(){
    return hr;
    }
    public int getH() {
        return h.get();
    }

    public void setH(int initH) {
        h.set(initH);
    }
    
    public IntegerProperty hProperty(){
    return h;
    }
    public int getSB() {
        return sb.get();
    }

    public void setSB(int initSB) 
    {   
        this.setSBERA(initSB);
        sb.set(initSB);
    }
    
    public IntegerProperty sbProperty(){
    return sb;
    }      


    

}
