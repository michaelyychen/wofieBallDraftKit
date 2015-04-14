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
public class Hitter extends Player{

    int r;
    int hr;
    int h;


    double rbit;
    int sb;
    double ba;
    String qp;

    String lastName;
    String firstName;  


    
    public Hitter(String ln, String fn){
   super();
   lastName = ln;
   firstName = fn;
   }
    


    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getHr() {
        return hr;
    }

    public void setHr(int hr) {
        this.hr = hr;
    }

    public double getRbi() {
        return rbit;
    }

    public void setRbi(double rbit) {
        this.rbit = rbit;
    }

    public int getSb() {
        return sb;
    }

    public void setSb(int sb) {
        this.sb = sb;
    }

    public double getBa() {
        return ba;
    }

    public void setBa(double ba) {
        this.ba = ba;
    }
    public String getQp() {
        return qp;
    }    
    
    public void setQp(String qp) {
        this.qp = qp;
    }   
    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }    

}
