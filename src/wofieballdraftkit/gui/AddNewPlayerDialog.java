/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.gui;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import wofieballdraftkit.data.Player;
import static wofieballdraftkit.gui.WBDK_GUI.CLASS_HEADING_LABEL;
import static wofieballdraftkit.gui.WBDK_GUI.CLASS_PROMPT_LABEL;
import static wofieballdraftkit.gui.WBDK_GUI.PRIMARY_STYLE_SHEET;

/**
 *
 * @author MiChAeL
 */
public class AddNewPlayerDialog extends Stage{
    Player player;
    
    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridPane;
    Scene dialogScene;
    Label firstNameLabel;
    Label lastNameLabel;
    Label proTeamLabel;
    Label headingLabel;
    Label positionLabel;
    TextField firstNameTextField;
    TextField lastNameTextField;
    ComboBox proTeamComboBox;
    CheckBox C;
    CheckBox first;
    CheckBox second;
    CheckBox third;
    CheckBox SS;
    CheckBox OF;
    CheckBox P;
    
    String s = "";
    String pos ="";
    FlowPane flow;
    ToggleGroup group;
    
    Button completeButton;
    Button cancelButton;
    
    // THIS IS FOR KEEPING TRACK OF WHICH BUTTON THE USER PRESSED
    String selection;
    
    ArrayList<String> teamArray = new ArrayList(
                    Arrays.asList("ATL", "AZ", "CHC", "CIN", "COL", "LAD", "MIA", "MIL",
                                "NYM", "PHI", "PIT", "SD", "SF", "STL", "WAS"));
   
      ArrayList<String> posArray = new ArrayList();        
    // CONSTANTS FOR OUR UI
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    public static final String FIRSTNAME_PROMPT = "First Name: ";
    public static final String LASTNAME_PROMPT = "Last Name:";
    public static final String PROTEAM_PROMPT = "Pro Team:";
    public static final String POSITION_PROMPT = "Positions:";
    public static final String PLAYER_HEADING = "Player Details";
    public static final String ADD_PLAYER_TITLE = "Add New Player";
   
    /**
     * Initializes this dialog so that it can be used for either adding
     * new schedule items or editing existing ones.
     * 
     * @param primaryStage The owner of this modal dialog.
     */
    public AddNewPlayerDialog(Stage primaryStage) {       
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        
        // FIRST OUR CONTAINER
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
       
        // PUT THE HEADING IN THE GRID, NOTE THAT THE TEXT WILL DEPEND
        // ON WHETHER WE'RE ADDING OR EDITING
        headingLabel = new Label(PLAYER_HEADING);
        headingLabel.getStyleClass().add(CLASS_HEADING_LABEL);
    
        // NOW THE DESCRIPTION 
        firstNameLabel = new Label(FIRSTNAME_PROMPT);
        firstNameLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        firstNameTextField = new TextField();
        firstNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            player.setFirstName(newValue);
        });
        
        lastNameLabel = new Label(LASTNAME_PROMPT);
        lastNameLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        lastNameTextField = new TextField();
        lastNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            player.setLastName(newValue);
        });    
        
        
        
        // AND THE URL
        proTeamLabel = new Label(PROTEAM_PROMPT);
        proTeamLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        proTeamComboBox = new ComboBox();
        loadProTeamComboBox(teamArray);
        
         proTeamComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
             //   System.out.println(newValue.toString());
                String s = newValue.toString();
                player.setProTeam(s);
            }
            
        });
        

        
        positionLabel = new Label(POSITION_PROMPT);
        positionLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        
        flow = new FlowPane();
        C = new CheckBox("C");
        first = new CheckBox("1B");
        third = new CheckBox("3B");
        second = new CheckBox("2B");
        SS = new CheckBox("SS");
        OF = new CheckBox("OF");
        P = new CheckBox("P");

        
        EventHandler<ActionEvent> checkBoxHandler = e -> {{
        if (P.isSelected()&& !pos.contains("P")) {
           pos = pos.concat("P");
           C.setSelected(false);
           first.setSelected(false);
           third.setSelected(false);
           second.setSelected(false);
           SS.setSelected(false);
           OF.setSelected(false);
           
           C.setDisable(true);
           first.setDisable(true);
           third.setDisable(true);
           second.setDisable(true);
           SS.setDisable(true);
           OF.setDisable(true);
           
           
        }
           else if (!P.isSelected()&& pos.contains("P")) {
           pos = pos.replace("P", "");
            C.setDisable(false);
           first.setDisable(false);
           third.setDisable(false);
           second.setDisable(false);
           SS.setDisable(false);
           OF.setDisable(false);
           }
         else
            
            
            if(C.isSelected()&& !pos.contains("C")){
                pos = pos.concat("C_");    
             }else if (!C.isSelected()&& pos.contains("C")) {
                pos = pos.replace("C_", "");
            }
            if(first.isSelected()&& !pos.contains("1B")){
                pos = pos.concat("1B_");
            }else if (!first.isSelected()&& pos.contains("1B")) {
                pos = pos.replace("1B_", "");
            }
            if(third.isSelected()&& !pos.contains("3B")){
                pos = pos.concat("3B_"); 
           }else if (!third.isSelected()&& pos.contains("3B")) {
                pos = pos.replace("3B_", "");
            }
            if(second.isSelected()&& !pos.contains("2B")){
               pos = pos.concat("2B_"); 
            }else if (!second.isSelected()&& pos.contains("2B")) {
                pos = pos.replace("2B_", "");
            }
            if(SS.isSelected()&& !pos.contains("SS")){
                 pos = pos.concat("SS_");
            }else if (!SS.isSelected()&& pos.contains("SS")) {
                pos = pos.replace("SS_", "");
            }   
            if(OF.isSelected()&& !pos.contains("OF")){
               pos = pos.concat("OF_");  
            }else if (!OF.isSelected()&& pos.contains("OF")) {
                pos = pos.replace("OF_", "");
            }
        }
        } ;
        
        //System.out.println(pos);
        
        C.setOnAction(checkBoxHandler);
        first.setOnAction(checkBoxHandler);
        third.setOnAction(checkBoxHandler);
        second.setOnAction(checkBoxHandler);
        SS.setOnAction(checkBoxHandler);
        OF.setOnAction(checkBoxHandler);
        P.setOnAction(checkBoxHandler);
        
//        player.setQualifyPosition(pos);
        
        flow.getChildren().addAll(C,first,third,second,SS,OF,P);
        flow.setHgap(5);
        flow.setPrefWrapLength(400);
       
        // AND FINALLY, THE BUTTONS
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        
        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            AddNewPlayerDialog.this.selection = sourceButton.getText();
            AddNewPlayerDialog.this.hide();
        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);
       
        
//        player.setQualifyPosition(temp);
        
        // NOW LET'S ARRANGE THEM ALL AT ONCE
        gridPane.add(headingLabel, 0, 0, 2, 1);
        gridPane.add(firstNameLabel, 0, 1, 1, 1);
        gridPane.add(firstNameTextField, 1, 1, 1, 1);
        gridPane.add(lastNameLabel, 0, 2, 1, 1);
        gridPane.add(lastNameTextField, 1, 2, 1, 1);
        gridPane.add(proTeamLabel, 0, 3, 1, 1);
        gridPane.add(proTeamComboBox, 1, 3, 1, 1);
        gridPane.add(positionLabel, 0, 4, 1, 1);
        gridPane.add(flow, 1, 4, 1, 1);    
        gridPane.add(completeButton, 0, 6, 1, 1);
        gridPane.add(cancelButton, 1, 6, 1, 1);
       
    
        // AND PUT THE GRID PANE IN THE WINDOW
        
        dialogScene = new Scene(gridPane);
        dialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        
        
        
        
        
        
        
        this.setScene(dialogScene);
    }
    
    /**
     * Accessor method for getting the selection the user made.
     * 
     * @return Either YES, NO, or CANCEL, depending on which
     * button the user selected when this dialog was presented.
     */
    public String getSelection() {
        return selection;
    }
    
    public Player getPlayer() { 
        return player;
    }
    
    /**
     * This method loads a custom message into the label and
     * then pops open the dialog.
     * 
     * @param message Message to appear inside the dialog.
     */
    public Player showAddPlayerDialog() {
        // SET THE DIALOG TITLE
     
        setTitle(ADD_PLAYER_TITLE);
        
        // RESET THE SCHEDULE ITEM OBJECT WITH DEFAULT VALUES
        player = new Player();
        player.setBirth("N/A");
        player.setProTeam("ATL");
        // LOAD THE UI STUFF
       firstNameTextField.setText(player.getFirstName());
       lastNameTextField.setText(player.getLastName()); 
       proTeamComboBox.getSelectionModel().select(0);
       C.setSelected(false);
       first.setSelected(false);
       third.setSelected(false);
       second.setSelected(false);
       SS.setSelected(false);
       OF.setSelected(false);       
       P.setSelected(false);  
       
       
       
       
        // AND OPEN IT UP
        this.showAndWait();
        
        
        if(pos.endsWith("_")){
        player.setQualifyPosition(pos.substring(0, pos.length()-1));
        }
        else{
            player.setQualifyPosition(pos);
        }
        
        
        return player;
    }
    
    
    public boolean wasCompleteSelected() {
        return selection.equals(COMPLETE);
    }
    
    private void loadProTeamComboBox(ArrayList<String> proTeams) {
            for (String s : proTeams) {
                proTeamComboBox.getItems().add(s);
            }
         
        }    

     
}
