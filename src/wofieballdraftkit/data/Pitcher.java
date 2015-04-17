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

/**
 *
 * @author MiChAeL
 */
public class Pitcher extends Player {
  final IntegerProperty w;
  final IntegerProperty k;
  final IntegerProperty sv;
  final IntegerProperty h;

  final DoubleProperty IP;
  final DoubleProperty ERA;
  final DoubleProperty WHIP;
  
  


   
   public static final int DEFAULT_NUM = 0 ;
   
   public Pitcher(String initFirstname, String initLastname){
   super();
   
   this.firstname.set(initFirstname);
   this.lastname.set(initLastname);
   
   w = new SimpleIntegerProperty(DEFAULT_NUM);
   k = new SimpleIntegerProperty(DEFAULT_NUM);
   sv = new SimpleIntegerProperty(DEFAULT_NUM);
   h = new SimpleIntegerProperty(DEFAULT_NUM);
   
   IP = new SimpleDoubleProperty(DEFAULT_NUM);
   ERA = new SimpleDoubleProperty(DEFAULT_NUM);
   WHIP = new SimpleDoubleProperty(DEFAULT_NUM);
 
   }
    public int getW() {
        return w.get();
    }

    public void setW(int initW) {
        this.rw.setValue(initW);
        w.set(initW);
    }
    
    public IntegerProperty wProperty(){
    return w;
    }
    
    public int getK() {
        return k.get();
    }

    public void setK(int initK) {
        this.rbik.set(initK);
        k.set(initK);
    }
    
    public IntegerProperty kProperty(){
    return k;
    }      
    public int getSV() {
        return sv.get();
    }

    public void setSV(int initSV) {
        this.hrsv.set(initSV);
        sv.set(initSV);
    }
    
    public IntegerProperty svProperty(){
    return sv;
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
    public double getIP() {
        return IP.get();
    }

    public void setIP(double initIP) {
        IP.set(initIP);
    }
    
    public DoubleProperty ipProperty(){
    return IP;
    }     
    public double getERA() {
        return ERA.get();
    }

    public void setERA(double initERA) {
        this.sbera.set(initERA);
        ERA.set(initERA);
    }
    
    public DoubleProperty eraProperty(){
    return ERA;
    }   
    public double getWHIP() {
        return WHIP.get();
    }

    public void setWHIP(double initWHIP) {
        this.bawhip.set(initWHIP);
        WHIP.set(initWHIP);
    }
    
    public DoubleProperty whipProperty(){
    return WHIP;
    }      
}
