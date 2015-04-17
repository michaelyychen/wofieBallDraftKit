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
    if (o1.getFirstName().compareTo( o2.getFirstName()) >= 1){
    return 1;
    }
    else if (o1.getFirstName().compareTo( o2.getFirstName()) <= 1){
    return -1;
    }
    else {
    return 0;
    }
 }   
}
