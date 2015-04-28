package wofieballdraftkit.gui;

import java.util.Comparator;
import wofieballdraftkit.data.Player;

/**
 *
 * @author MiChAeL
 */
public class PositionComparator implements Comparator<Player> {
      
    
    
    @Override
 public int compare(Player o1, Player o2) {
     int temp1 = getValue(o1);
     int temp2 = getValue(o2);
    if (temp1>=temp2){
    return -1;
    }
    else if (temp1 <= temp2){
    return 1;
    }
    else {
    return 0;
    }
 }
 
 public int getValue(Player p){
     int i = 0;
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