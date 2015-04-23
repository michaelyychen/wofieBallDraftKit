/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.test;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import wofieballdraftkit.data.Player;
import wofieballdraftkit.gui.AddNewPlayerDialog;
import wofieballdraftkit.gui.EditPlayerDialog;
import wofieballdraftkit.gui.MessageDialog;

/**
 *
 * @author MiChAeL
 */
public class Test_EditPlayerDialog extends Application{
 
        @Override
        public void start(Stage primaryStage) throws Exception {
        Player course = new Player();
        course.setFirstName("Francisco");
        course.setLastName("Cervelli");
        course.setNation("Colombia");
        course.setPosition("C_U");
        MessageDialog messageDialog = new MessageDialog(primaryStage, "CLOSE");
        EditPlayerDialog testDialog = new EditPlayerDialog(primaryStage);
        testDialog.show();
    }
    public static void main(String[] args) {
            launch(args);
        }    
}
