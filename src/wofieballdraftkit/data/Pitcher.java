/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.data;

/**
 *
 * @author MiChAeL
 */
public class Pitcher extends Player {
   int w;
   int k;
   int sv;
   double ERA;
   double WHIP;
   
   public Pitcher(){
   super();
   
   }
   
    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getSv() {
        return sv;
    }

    public void setSv(int sv) {
        this.sv = sv;
    }

    public double getERA() {
        return ERA;
    }

    public void setERA(double ERA) {
        this.ERA = ERA;
    }

    public double getWHIP() {
        return WHIP;
    }

    public void setWHIP(double WHIP) {
        this.WHIP = WHIP;
    }

   
   
}
