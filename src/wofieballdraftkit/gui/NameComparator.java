/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.gui;

import java.util.Comparator;
import wofieballdraftkit.data.Player;

/**
 *
 * @author MiChAeL
 */
public class NameComparator implements Comparator<Player> {
    
    @Override
 public int compare(Player o1, Player o2) {
     
     String name1=o1.getLastName().concat(o1.getFirstName());
     String name2= o2.getLastName().concat(o1.getFirstName());
     
     
    if (name1.compareTo( name2) >= 1){
    return 1;
    }
    else if (name1.compareTo( name2) <= 1){
    return -1;
    }
    else {
    return 0;
    }
 }   
}
