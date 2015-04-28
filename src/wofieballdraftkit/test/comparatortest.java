/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.test;

import java.util.Comparator;
import wofieballdraftkit.data.Player;

/**
 *
 * @author MiChAeL
 */
public class comparatortest  {
    static int temp1;
     static int temp2;
    static int i;
    public static void main(String[]args){
    
    Player p1 = new Player();
    Player p2 = new Player();
    
    p1.setPosition("3B");
    p2.setPosition("CI");
    
    System.out.println(compare(p1,p2));
    
    
    
    } 
    
    
  
 public static int compare(Player o1, Player o2) {
    temp1 = getValue(o1);
    temp2 = getValue(o2);
    if (temp1>=temp2){
    return 1;
    }
    else if (temp1 <= temp2){
    return -1;
    }
    else {
    return 0;
    }
 }
 
 public static int getValue(Player p){
      i = 0;
     if (p.getPosition().equalsIgnoreCase("C")){i = 10;}
     if (p.getPosition().equalsIgnoreCase("1B")){i = 9;}
     if (p.getPosition().equalsIgnoreCase("CI")){i = 8;}
     if (p.getPosition().equalsIgnoreCase("3B")){i = 7;}
     if (p.getPosition().equalsIgnoreCase("2B")){i = 6;}
     if (p.getPosition().equalsIgnoreCase("MI")){i = 5;}
     if (p.getPosition().equalsIgnoreCase("SS")){i = 4;}
     if (p.getPosition().equalsIgnoreCase("OF")){i = 3;}
     if (p.getPosition().equalsIgnoreCase("U")){i = 2;}
     if (p.getPosition().equalsIgnoreCase("P")){i = 1;}
     
     
     return i;
 
 }
 
 
}
