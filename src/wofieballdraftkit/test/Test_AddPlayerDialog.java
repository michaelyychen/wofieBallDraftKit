/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.test;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import wofieballdraftkit.data.FantasyTeam;
import wofieballdraftkit.data.Player;
import wofieballdraftkit.gui.AddNewPlayerDialog;
import wofieballdraftkit.gui.FantasyTeamDialog;
import wofieballdraftkit.gui.MessageDialog;

/**
 *
 * @author MiChAeL
 */
public class Test_AddPlayerDialog extends Application {
        @Override
    public void start(Stage primaryStage) throws Exception {
        Player course = new Player();
        MessageDialog messageDialog = new MessageDialog(primaryStage, "CLOSE");
        AddNewPlayerDialog testDialog = new AddNewPlayerDialog(primaryStage, course, messageDialog);
        testDialog.show();
    }
    public static void main(String[] args) {
            launch(args);
        }    
    
}
