/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.data;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static wofieballdraftkit.data.Hitter.DEFAULT_NUM;
import wofieballdraftkit.gui.PositionComparator;

/**
 *
 * @author MiChAeL
 */
public class FantasyTeam {

    final StringProperty owner;
    final StringProperty teamName;
    ObservableList<Player> teamPlayer;
    ObservableList<Player> taxiSquad;
    final IntegerProperty moneyLeft;
    final IntegerProperty playerCount;
    final IntegerProperty PP;

    final IntegerProperty R;
    final IntegerProperty HR;
    final IntegerProperty RBI;
    final IntegerProperty SB;
    final IntegerProperty SV;
    final IntegerProperty K;
    final IntegerProperty PTS;

    final DoubleProperty BA;
    final DoubleProperty W;
    final DoubleProperty ERA;
    final DoubleProperty WHIP;

    public static final String DEFAULT_STRING = "";

    public FantasyTeam() {
        owner = new SimpleStringProperty(DEFAULT_STRING);
        teamName = new SimpleStringProperty(DEFAULT_STRING);
        teamPlayer = FXCollections.observableArrayList();
        taxiSquad = FXCollections.observableArrayList();
        playerCount = new SimpleIntegerProperty(23);
        moneyLeft = new SimpleIntegerProperty(260);
        PP = new SimpleIntegerProperty(0);

        R = new SimpleIntegerProperty(0);
        HR = new SimpleIntegerProperty(0);
        RBI = new SimpleIntegerProperty(0);
        SB = new SimpleIntegerProperty(0);
        SV = new SimpleIntegerProperty(0);
        K = new SimpleIntegerProperty(0);
        PTS = new SimpleIntegerProperty(0);

        BA = new SimpleDoubleProperty(DEFAULT_NUM);
        W = new SimpleDoubleProperty(DEFAULT_NUM);
        ERA = new SimpleDoubleProperty(DEFAULT_NUM);
        WHIP = new SimpleDoubleProperty(DEFAULT_NUM);

    }

    public ArrayList<String> positionEmpty() {

        int countC = 0;
        int countOF = 0;
        int countP = 0;
        int countX = 0;
        ArrayList<String> pos = new ArrayList();
        ArrayList<String> result = new ArrayList();

        if (teamPlayer.size() == 23) {
            if (taxiSquad.size() < 8) {
                int loop = 8 - taxiSquad.size();
                for (int i = 0; i < loop; i++) {
                    result.add("X");
                }
            }

        } else {
            for (int i = 0; i < teamPlayer.size(); i++) {
                pos.add(teamPlayer.get(i).getPosition());
            }

            //if standingLine up is filled, check if taxi squad is as well
            for (int i = 0; i < pos.size(); i++) {
                if (pos.get(i).equalsIgnoreCase("OF")) {
                    countOF++;
                }
                if (pos.get(i).equalsIgnoreCase("P")) {
                    countP++;
                }
                
                if (pos.get(i).equalsIgnoreCase("C")) {
                    countC++;
                }
            }

            if (countC < 2) {
                int loop = 2 - countC;
                for (int i = 0; i < loop; i++) {
                    result.add("C");
                }
            }
            if (countP < 9) {
                int loop = 9 - countP;
                for (int i = 0; i < loop; i++) {
                    result.add("P");
                }
            }
            if (countOF < 5) {
                int loop = 5 - countOF;
                for (int i = 0; i < loop; i++) {
                    result.add("OF");
                }
            }

            if (!pos.contains("1B")) {
                result.add("1B");
            }
            if (!pos.contains("CI")) {
                result.add("CI");
            }
            if (!pos.contains("3B")) {
                result.add("3B");
            }
            if (!pos.contains("2B")) {
                result.add("2B");
            }
            if (!pos.contains("MI")) {
                result.add("MI");
            }
            if (!pos.contains("SS")) {
                result.add("SS");
            }
            if (!pos.contains("U")) {
                result.add("U");
            }

            if (taxiSquad.size() < 8) {
                int loop = 8 - taxiSquad.size();
                for (int i = 0; i < loop; i++) {
                    result.add("X");
                }
            }
        }
        
        return result;
    }

    public boolean positionCount(String Pos) {

        int count = 0;
        for (int i = 0; i < teamPlayer.size(); i++) {

            if (teamPlayer.get(i).getPosition().equalsIgnoreCase(Pos)) {
                count++;
            }
        }
        if (Pos.equalsIgnoreCase("C") && count < 2) {
            return true;
        } else if (Pos.equalsIgnoreCase("1B") && count < 1) {
            return true;
        } else if (Pos.equalsIgnoreCase("CI") && count < 1) {
            return true;
        } else if (Pos.equalsIgnoreCase("3B") && count < 1) {
            return true;
        } else if (Pos.equalsIgnoreCase("2B") && count < 1) {
            return true;
        } else if (Pos.equalsIgnoreCase("MI") && count < 1) {
            return true;
        } else if (Pos.equalsIgnoreCase("SS") && count < 1) {
            return true;
        } else if (Pos.equalsIgnoreCase("U") && count < 1) {
            return true;
        } else if (Pos.equalsIgnoreCase("OF") && count < 5) {
            return true;
        } else if (Pos.equalsIgnoreCase("P") && count < 9) {
            return true;
        } else {
            return false;
        }

    }

    public void addByPos(Player p) {
        teamPlayer.add(p);
        teamPlayer.sort(new PositionComparator());

    }

    public double getBA() {
        return BA.get();
    }

    public void setBA(double initBA) {

        BA.set(initBA);
    }

    public DoubleProperty baProperty() {
        return BA;
    }

    public double getW() {
        return W.get();
    }

    public void setW(double initBA) {

        W.set(initBA);
    }

    public DoubleProperty wProperty() {
        return W;
    }

    public double getERA() {
        return ERA.get();
    }

    public void setERA(double initBA) {

        ERA.set(initBA);
    }

    public DoubleProperty eraProperty() {
        return ERA;
    }

    public double getWHIP() {
        return WHIP.get();
    }

    public void setWHIP(double initBA) {

        WHIP.set(initBA);
    }

    public DoubleProperty whipProperty() {
        return WHIP;
    }

    public int getPTS() {
        return PTS.get();
    }

    public void setPTS(int n) {
        PTS.set(n);
    }

    public IntegerProperty ptsProperty() {
        return PTS;
    }

    public int getR() {
        return R.get();
    }

    public void setR(int n) {
        R.set(n);
    }

    public IntegerProperty rProperty() {
        return R;
    }

    public int getHR() {
        return HR.get();
    }

    public void setHR(int n) {
        HR.set(n);
    }

    public IntegerProperty hrProperty() {
        return HR;
    }

    public int getRBI() {
        return RBI.get();
    }

    public void setRBI(int n) {
        RBI.set(n);
    }

    public IntegerProperty rbiProperty() {
        return RBI;
    }

    public int getSB() {
        return SB.get();
    }

    public void setSB(int n) {
        SB.set(n);
    }

    public IntegerProperty sbProperty() {
        return SB;
    }

    public int getSV() {
        return SV.get();
    }

    public void setSV(int n) {
        SV.set(n);
    }

    public IntegerProperty svProperty() {
        return SV;
    }

    public int getK() {
        return K.get();
    }

    public void setK(int n) {
        K.set(n);
    }

    public IntegerProperty kProperty() {
        return K;
    }

    public int getPP() {
        return PP.get();
    }

    public void setPP(int n) {
        PP.set(n);
    }

    public IntegerProperty ppProperty() {
        return PP;
    }

    public int getPlayerCount() {
        return playerCount.get();
    }

    public void setPlayerCount(int n) {
        playerCount.set(n);
    }

    public IntegerProperty playercountProperty() {
        return playerCount;
    }

    public int getMoneyLeft() {
        return moneyLeft.get();
    }

    public void setMoneyLeft(int n) {
        moneyLeft.set(n);
    }

    public IntegerProperty moneyleftProperty() {
        return moneyLeft;
    }

    public ObservableList<Player> getTaxiSquad() {
        return taxiSquad;
    }

    public void setTaxiSquad(ObservableList<Player> pool) {
        this.taxiSquad = pool;
    }

    public ObservableList<Player> getTeamPlayer() {
        return teamPlayer;
    }

    public void setTeamPlayer(ObservableList<Player> pool) {
        this.teamPlayer = pool;
    }

    public String getOwner() {
        return owner.get();
    }

    public void setOwner(String initOwner) {
        owner.set(initOwner);
    }

    public StringProperty ownerProperty() {
        return owner;
    }

    public String getTeamName() {
        return teamName.get();
    }

    public void setTeamName(String initName) {
        teamName.set(initName);
    }

    public StringProperty teamNameProperty() {
        return teamName;
    }

    public void updatePP() {

        int sum = 0;
        int count = 0;

        for (Player p : teamPlayer) {
            sum = sum + p.getSalary();
            count++;
        }

        if (count == 0) {
            setPP(-1);
        } else {
            setPP(sum / count);
        }

        setPlayerCount(23 - teamPlayer.size());
        count = 0;
        sum = 0;
    }

    public void updateMoney() {
        int i = 260;

        for (Player p : teamPlayer) {
            i = i - p.getSalary();
        }

        setMoneyLeft(i);
    }

    public void updateStats() {

        int playerNum = teamPlayer.size();

        int r = 0;
        int hr = 0;
        int rbi = 0;
        int sb = 0;
        double ba = 0;
        double w = 0;
        int sv = 0;
        int k = 0;
        double era = 0;
        double whip = 0;

        for (Player p : teamPlayer) {
            if (p.getPosition().equalsIgnoreCase("P")) {

                w = w + p.getRW();
                sv = sv + p.getHRSV();
                k = k + p.getRBIK();
                era = era + p.getSBERA();
                whip = whip + p.getBAWHIP();

            } else {
                r = r + p.getRW();
                hr = hr + p.getHRSV();
                rbi = rbi + p.getRBIK();
                sb = (int) (sb + p.getSBERA());
                ba = ba + p.getBAWHIP();

            }

            setW(((double) ((int) (w * 100))) / 100);

            setSV(sv / playerNum);
            setK(k / playerNum);

            setERA(((double) ((int) (era / playerNum * 100))) / 100);
            setWHIP(((double) ((int) (whip / playerNum * 100))) / 100);

            setR(r);
            setHR(hr);
            setRBI(rbi);
            setSB(sb);
            setBA(((double) ((int) (ba * 1000))) / 1000);
        }

    }

    public void changeMoneyLeft(int i) {
        moneyLeft.add(i);
    }

    public void changePlayerCount(int i) {
        playerCount.add(i);
    }
}
