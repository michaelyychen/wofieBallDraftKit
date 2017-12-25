/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static wofieballdraftkit.WBDK_PropertyType.*;
import static wofieballdraftkit.WBDK_StartUpConstants.*;
import wofieballdraftkit.data.DraftDataManager;
import wofieballdraftkit.data.Hitter;
import wofieballdraftkit.data.Pitcher;
import wofieballdraftkit.error.ErrorHandler;
import wofieballdraftkit.file.JsonDraftFileManager;
import wofieballdraftkit.gui.WBDK_GUI;
import xml_utilities.InvalidXMLFileFormatException;

/**
 *
 * @author MiChAeL
 */
public class WofieBallDraftKit extends Application {

    WBDK_GUI gui;

    @Override
    public void start(Stage primaryStage) {
        // LET'S START BY GIVING THE PRIMARY STAGE TO OUR ERROR HANDLER
        ErrorHandler eH = ErrorHandler.getErrorHandler();
        eH.initMessageDialog(primaryStage);

        // LOAD APP SETTINGS INTO THE GUI AND START IT UP
        boolean success = loadProperties();
        if (success) {
            try {
                PropertiesManager props = PropertiesManager.getPropertiesManager();
                String appTitle = props.getProperty(PROP_APP_TITLE);

                JsonDraftFileManager jsonFileManager = new JsonDraftFileManager();

                ArrayList<Pitcher> pitchers = jsonFileManager.loadPitcherData(JSON_FILE_PATH_PITCHERS, "Pitchers");
                ArrayList<Hitter> hitters = jsonFileManager.loadHitterData(JSON_FILE_PATH_HITTERS, "Hitters");

                gui = new WBDK_GUI(primaryStage);
                gui.setDraftFileManager(jsonFileManager);

                // CONSTRUCT THE DATA MANAGER AND GIVE IT TO THE GUI
                DraftDataManager dataManager = new DraftDataManager(gui, pitchers, hitters);
                gui.setDataManager(dataManager);
                gui.initGUI(appTitle);

            } catch (IOException ex) {
                Logger.getLogger(WofieBallDraftKit.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Loads this application's properties file, which has a number of settings
     * for initializing the user interface.
     *
     * @return true if the properties file was loaded successfully, false
     * otherwise.
     */
    public boolean loadProperties() {
        try {
            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
            props.loadProperties(PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            return true;
        } catch (InvalidXMLFileFormatException ixmlffe) {
            // SOMETHING WENT WRONG INITIALIZING THE XML FILE
            ErrorHandler eH = ErrorHandler.getErrorHandler();
            eH.handlePropertiesFileError();
            return false;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
