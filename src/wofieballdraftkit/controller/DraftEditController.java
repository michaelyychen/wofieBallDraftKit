/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.controller;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import wofieballdraftkit.WBDK_PropertyType;
import static wofieballdraftkit.WBDK_PropertyType.REMOVE_ITEM_MESSAGE;
import wofieballdraftkit.data.Draft;
import wofieballdraftkit.data.DraftDataManager;
import wofieballdraftkit.data.FantasyTeam;
import wofieballdraftkit.data.Player;
import wofieballdraftkit.error.ErrorHandler;
import wofieballdraftkit.gui.AddNewPlayerDialog;
import wofieballdraftkit.gui.EditPlayerDialog;
import wofieballdraftkit.gui.FantasyTeamDialog;
import wofieballdraftkit.gui.MessageDialog;
import wofieballdraftkit.gui.WBDK_GUI;
import wofieballdraftkit.gui.YesNoCancelDialog;

/**
 *
 * @author MiChAeL
 */
public class DraftEditController {

    // WE USE THIS TO MAKE SURE OUR PROGRAMMED UPDATES OF UI
    // VALUES DON'T THEMSELVES TRIGGER EVENTS
    boolean pause;
    private boolean enabled;
    AddNewPlayerDialog anpd;
    EditPlayerDialog epd;
    FantasyTeamDialog ftd;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    Thread thread;

    /**
     * Constructor that gets this controller ready, not much to initialize as
     * the methods for this function are sent all the objects they need as
     * arguments.
     */
    public DraftEditController(Stage initPrimaryStage, Draft draft, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {
        anpd = new AddNewPlayerDialog(initPrimaryStage);
        epd = new EditPlayerDialog(initPrimaryStage);
        ftd = new FantasyTeamDialog(initPrimaryStage);
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
        enabled = true;
    }

    /**
     * This mutator method lets us enable or disable this controller.
     *
     * @param enableSetting If false, this controller will not respond to Course
     * editing. If true, it will.
     */
    public void enable(boolean enableSetting) {
        enabled = enableSetting;
    }

    /**
     * This controller function is called in response to the user changing
     * course details in the UI. It responds by updating the bound Course object
     * using all the UI values, including the verification of that data.
     *
     * @param gui The user interface that requested the change.
     */
    public void handleDraftChangeRequest(WBDK_GUI gui) {
        if (enabled) {
            try {
                // UPDATE THE COURSE, VERIFYING INPUT VALUES
//                gui.updateDraftInfo(gui.getDataManager().getDraft());

                // THE COURSE IS NOW DIRTY, MEANING IT'S BEEN 
                // CHANGED SINCE IT WAS LAST SAVED, SO MAKE SURE
                // THE SAVE BUTTON IS ENABLED
                gui.getFileController().markAsEdited(gui);
            } catch (Exception e) {
                // SOMETHING WENT WRONG
                ErrorHandler eH = ErrorHandler.getErrorHandler();
                eH.handleUpdateCourseError();
            }
        }
    }

    public void handleEditPlayerRequest(WBDK_GUI gui, Player player, boolean s) {
        DraftDataManager cdm = gui.getDataManager();
        Draft draft = cdm.getDraft();

        //epd.clearData(); 
        epd.showEditPlayerDialog(player, draft.getTeamList(), s);

        // DID THE USER CONFIRM?
        if (epd.wasCompleteSelected()) {

            Player si = epd.getPlayer();

            //put the player back in the pool
            if ("Free Agent".equals(si.getFantasyTeam())) {
                if (player.getContract().equalsIgnoreCase("X") || player.getContract().equalsIgnoreCase("S1")) {
                    draft.getTeamByName(player.getFantasyTeam()).getTaxiSquad().remove(player);
                } else {
                    draft.getTeamByName(player.getFantasyTeam()).getTeamPlayer().remove(player);
                }
                draft.getDataPool().add(player);
                draft.getGuiPool().add(player);
                draft.getSearchPool().add(player);
            } //Moving a player from starting lineup to taxi squad
            else if ((si.getContract().equalsIgnoreCase("X") || si.getContract().equalsIgnoreCase("S1")) && s == true) {

                player.setSalary(1);
                player.setContract(si.getContract());

                draft.getTeamByName(player.getFantasyTeam()).getTaxiSquad().add(player);
                draft.getTeamByName(player.getFantasyTeam()).getTeamPlayer().remove(player);
            } else {
                player.setPosition(si.getPosition());

                //delete player from previous team
                if (!player.getFantasyTeam().isEmpty()) {
                    draft.getTeamByName(player.getFantasyTeam()).getTeamPlayer().remove(player);
                }

                //if player is switching from taxi squad
                if ((player.getContract().equalsIgnoreCase("X") || player.getContract().equalsIgnoreCase("S1")) && s == true) {
                    draft.getTeamByName(player.getFantasyTeam()).getTaxiSquad().remove(player);
                }

                player.setFantasyTeam(si.getFantasyTeam());
                player.setContract(si.getContract());
                player.setSalary(si.getSalary());

                draft.getTeamByName(player.getFantasyTeam()).updatePP();
                draft.getTeamByName(player.getFantasyTeam()).updateMoney();

                //first check if user is adding a player into taxi squad
                if ((si.getContract().equalsIgnoreCase("X") || si.getContract().equalsIgnoreCase("S1")) && s == false) {

                    if (draft.getTeamByName(player.getFantasyTeam()).getPlayerCount() != 0) {
                        PropertiesManager props = PropertiesManager.getPropertiesManager();
                        messageDialog.show(props.getProperty(WBDK_PropertyType.NOT_FILLED_ERROR));
                    } else {

                        player.setSalary(1);
                        draft.getTeamByName(player.getFantasyTeam()).getTaxiSquad().add(player);
                        draft.getDataPool().remove(player);
                        draft.getGuiPool().remove(player);
                        draft.getSearchPool().remove(player);

                    }
                } //
                else {
                    //check money before adding
                    if (player.getSalary() > draft.getTeamByName(player.getFantasyTeam()).getMoneyLeft()
                            - draft.getTeamByName(player.getFantasyTeam()).getPlayerCount() + 1) {
                        PropertiesManager props = PropertiesManager.getPropertiesManager();
                        messageDialog.show(props.getProperty(WBDK_PropertyType.NOT_EVENMONEY_ERROR));
                    } else if ((player.getPosition().isEmpty() || player.getContract().isEmpty())) {
                        PropertiesManager props = PropertiesManager.getPropertiesManager();
                        messageDialog.show(props.getProperty(WBDK_PropertyType.ILLEGAL_SELECTION));

                    } else {
                        draft.getTrascation().add(player);
                        draft.getTeamByName(player.getFantasyTeam()).addByPos(player);
                        draft.getDataPool().remove(player);
                        draft.getGuiPool().remove(player);
                        draft.getSearchPool().remove(player);
                    }

                }
            }

            // THE COURSE IS NOW DIRTY, MEANING IT'S BEEN 
            // CHANGED SINCE IT WAS LAST SAVED, SO MAKE SURE
            // THE SAVE BUTTON IS ENABLED
            gui.getFileController().markAsEdited(gui);

        } else {

            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }

    }

    public void handleNewFantasyTeamRequest(WBDK_GUI gui) {

        DraftDataManager cdm = gui.getDataManager();
        Draft draft = cdm.getDraft();
        ftd.showAddFantasyTeamDialog();

        // DID THE USER CONFIRM?
        if (ftd.wasCompleteSelected()) {
            // GET THE SCHEDULE ITEM
            FantasyTeam si = ftd.getFantasyTeam();

            // AND ADD IT AS A ROW TO THE TABLE
            draft.getTeamList().add(si);

            // THE COURSE IS NOW DIRTY, MEANING IT'S BEEN 
            // CHANGED SINCE IT WAS LAST SAVED, SO MAKE SURE
            // THE SAVE BUTTON IS ENABLED
            gui.getFileController().markAsEdited(gui);
        } else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }
    }

    public void handleEditFantasyTeamRequest(WBDK_GUI gui, String s) {

        if (s == null) {

            PropertiesManager props = PropertiesManager.getPropertiesManager();
            messageDialog.show(props.getProperty(WBDK_PropertyType.NOTHING_TO_EDIT));

        } else {

            DraftDataManager cdm = gui.getDataManager();

            FantasyTeam teamToEdit = cdm.getDraft().getTeamByName(s);

            Draft draft = cdm.getDraft();
            ftd.showEditFantasyTeamDialog(teamToEdit);

            // DID THE USER CONFIRM?
            if (ftd.wasCompleteSelected()) {
                // GET THE SCHEDULE ITEM
                FantasyTeam si = ftd.getFantasyTeam();

                // AND ADD IT AS A ROW TO THE TABLE
                teamToEdit.setTeamName(si.getTeamName());
                teamToEdit.setOwner(si.getOwner());

                // THE COURSE IS NOW DIRTY, MEANING IT'S BEEN 
                // CHANGED SINCE IT WAS LAST SAVED, SO MAKE SURE
                // THE SAVE BUTTON IS ENABLED
                gui.getFileController().markAsEdited(gui);
            } else {
                // THE USER MUST HAVE PRESSED CANCEL, SO
                // WE DO NOTHING
            }
        }
    }

    public void handlerDeleteFantasyTeamRequest(WBDK_GUI gui, String s) {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        if (s == null) {

            PropertiesManager props = PropertiesManager.getPropertiesManager();
            messageDialog.show(props.getProperty(WBDK_PropertyType.NOTHING_TO_DELETE));
        } else {
            yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_ITEM_MESSAGE));

            // AND NOW GET THE USER'S SELECTION
            String selection = yesNoCancelDialog.getSelection();

            // IF THE USER SAID YES, THEN REMOVE IT
            if (selection.equals(YesNoCancelDialog.YES)) {
                FantasyTeam temp = gui.getDataManager().getDraft().getTeamByName(s);

                for (Player p : temp.getTeamPlayer()) {

                    gui.getDataManager().getDraft().getDataPool().add(p);
                    gui.getDataManager().getDraft().getSearchPool().add(p);
                    gui.getDataManager().getDraft().getGuiPool().add(p);
                }

                temp.getTeamPlayer().clear();

                gui.getDataManager().getDraft().RemoveTeamByName(temp.getTeamName());

                // THE COURSE IS NOW DIRTY, MEANING IT'S BEEN 
                // CHANGED SINCE IT WAS LAST SAVED, SO MAKE SURE
                // THE SAVE BUTTON IS ENABLED
                gui.getFileController().markAsEdited(gui);
            }
        }
    }

    public Player handleNewPlayerRequest(WBDK_GUI gui) {
        DraftDataManager cdm = gui.getDataManager();
        Draft draft = cdm.getDraft();
        anpd.showAddPlayerDialog();

        // DID THE USER CONFIRM?
        if (anpd.wasCompleteSelected()) {
            // GET THE SCHEDULE ITEM
            Player si = anpd.getPlayer();

            // AND ADD IT AS A ROW TO THE TABLE
            draft.getExtraPlayerList().add(si);

            draft.getGuiPool().add(si);
            draft.getDataPool().add(si);
            draft.getSearchPool().add(si);

            // THE COURSE IS NOW DIRTY, MEANING IT'S BEEN 
            // CHANGED SINCE IT WAS LAST SAVED, SO MAKE SURE
            // THE SAVE BUTTON IS ENABLED
            gui.getFileController().markAsEdited(gui);
            return si;
        } else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }
        return null;
    }

    public void handleDeletePlayerRequest(WBDK_GUI gui, Player p) {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_ITEM_MESSAGE));

        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();

        // IF THE USER SAID YES, THEN REMOVE IT
        if (selection.equals(YesNoCancelDialog.YES)) {
            if (p.getBirth().equalsIgnoreCase("N/A")) {
                gui.getDataManager().getDraft().getExtraPlayerList().remove(p);
            }
            gui.getDataManager().getDraft().getGuiPool().remove(p);
            gui.getDataManager().getDraft().getDataPool().remove(p);
            gui.getDataManager().getDraft().getSearchPool().remove(p);
            // THE COURSE IS NOW DIRTY, MEANING IT'S BEEN 
            // CHANGED SINCE IT WAS LAST SAVED, SO MAKE SURE
            // THE SAVE BUTTON IS ENABLED
            gui.getFileController().markAsEdited(gui);
        }
    }

    public void handleAutoDraft(WBDK_GUI gui, String option) {

        ObservableList<FantasyTeam> team = gui.getDataManager().getDraft().getTeamList();
        ObservableList<Player> data = gui.getDataManager().getDraft().getDataPool();
        ObservableList<Player> guiPool = gui.getDataManager().getDraft().getGuiPool();
        ObservableList<Player> searchPool = gui.getDataManager().getDraft().getSearchPool();

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                
                for (FantasyTeam t : team) {
                    //If team player has less than 23 player
                    //this means there's an empty spot
                    if (t.getTeamPlayer().size() <= 23) {
                        ArrayList<String> posOpen = t.positionEmpty();
                        
                        for (String s : posOpen) {
                            Player p = gui.getDataManager().getDraft().pickPlayer(s);
                            if (s.equalsIgnoreCase("X")) {
                                p.setSalary(1);
                                p.setContract("X");
                                p.setFantasyTeam(t.getTeamName());
                                
                                t.getTaxiSquad().add(p);
                            } else {
                                p.setPosition(s);
                                p.setSalary(1);
                                p.setContract("S2");
                                p.setFantasyTeam(t.getTeamName());

                                t.addByPos(p);
                            }

                            data.remove(p);
                            guiPool.remove(p);
                            searchPool.remove(p);

                            Thread.sleep(300);
                            // UPDATE ANY PROGRESS DISPLAY
                            Platform.runLater(new Runnable() {
                                public void run() {
                                    gui.getDataManager().getDraft().getTrascation().add(p);
                                }
                            });
                            
                            checkFlag();
                            if (option.equalsIgnoreCase("star")) {
                                break;
                            }
                        }
                    }
                    if (option.equalsIgnoreCase("star")) {
                        break;
                    }
                }
                return null;
            }
        };
        
        thread = new Thread(task);
        thread.start();
    }

    public void checkFlag() {

        synchronized (thread) {
            while (pause) {
                try {
                    thread.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void pauseThread() throws InterruptedException {
        pause = true;
    }

    public void resumeThread() {

        synchronized (thread) {
            pause = false;
            thread.notifyAll();
        }
    }

}
