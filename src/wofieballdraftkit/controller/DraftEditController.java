/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.controller;

import javafx.stage.Stage;
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
    
    private boolean enabled;
    AddNewPlayerDialog anpd;
    EditPlayerDialog epd;
    FantasyTeamDialog ftd;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;

    /**
     * Constructor that gets this controller ready, not much to
     * initialize as the methods for this function are sent all
     * the objects they need as arguments.
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
     * @param enableSetting If false, this controller will not respond to
     * Course editing. If true, it will.
     */
    public void enable(boolean enableSetting) {
        enabled = enableSetting;
    }

    /**
     * This controller function is called in response to the user changing
     * course details in the UI. It responds by updating the bound Course
     * object using all the UI values, including the verification of that
     * data.
     * 
     * @param gui The user interface that requested the change.
     */
    public void handleDraftChangeRequest(WBDK_GUI gui) {
        if (enabled) {
            try {
                // UPDATE THE COURSE, VERIFYING INPUT VALUES
                gui.updateDraftInfo(gui.getDataManager().getDraft());
                
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
    
    
    public void handleEditPlayerRequest(WBDK_GUI gui, Player player) {
        DraftDataManager cdm = gui.getDataManager();
        Draft draft = cdm.getDraft();
        epd.showEditPlayerDialog(player);
      
        // DID THE USER CONFIRM?
        if (epd.wasCompleteSelected()) {
              
            
            Player si = epd.getPlayer();
            
            player.setFantasyTeam(si.getFantasyTeam());
            player.setQualifyPosition(si.getQualifyPosition());
            player.setContract(si.getContract());
            player.setSalary(si.getSalary());
            
            // THE COURSE IS NOW DIRTY, MEANING IT'S BEEN 
            // CHANGED SINCE IT WAS LAST SAVED, SO MAKE SURE
            // THE SAVE BUTTON IS ENABLED
            epd.clearData();
            gui.getFileController().markAsEdited(gui);
            
        }
        else {
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
        }
        else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }        
    }
    
    public void handleEditFantasyTeamRequest(WBDK_GUI gui, FantasyTeam teamToEdit) {
        
        DraftDataManager cdm = gui.getDataManager();
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
        }
        else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }        
    }    
    
    
    
    
    
    

   
    
}
