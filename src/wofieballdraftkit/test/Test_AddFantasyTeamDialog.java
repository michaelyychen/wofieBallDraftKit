/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.test;

import javafx.application.Application;
import javafx.stage.Stage;
import wofieballdraftkit.data.FantasyTeam;
import wofieballdraftkit.gui.FantasyTeamDialog;
import wofieballdraftkit.gui.MessageDialog;

/**
 *
 * @author MiChAeL
 */
public class Test_AddFantasyTeamDialog extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        FantasyTeam course = new FantasyTeam();
        MessageDialog messageDialog = new MessageDialog(primaryStage, "CLOSE");
        FantasyTeamDialog testDialog = new FantasyTeamDialog(primaryStage);
        testDialog.show();
    }
    public static void main(String[] args) {
            launch(args);
        }    
    
    
    
}
