/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.gui;

import static wofieballdraftkit.WBDK_StartUpConstants.*;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.event.HyperlinkEvent;
import properties_manager.PropertiesManager;
import wofieballdraftkit.WBDK_PropertyType;
import wofieballdraftkit.controller.DraftEditController;
import wofieballdraftkit.controller.FileController;
import wofieballdraftkit.data.Draft;
import wofieballdraftkit.data.DraftDataManager;
import wofieballdraftkit.data.DraftDataView;
import wofieballdraftkit.data.Pitcher;
import wofieballdraftkit.data.Player;
import wofieballdraftkit.file.DraftFileManager;
import wofieballdraftkit.file.DraftSiteExporter;

/**
 *
 * @author MiChAeL
 */
public class WBDK_GUI implements DraftDataView{
    
    static final String PRIMARY_STYLE_SHEET = PATH_CSS + "wbdk_style.css";
    static final String CLASS_BORDERED_PANE = "bordered_pane";
    static final String CLASS_SUBJECT_PANE = "subject_pane";
    static final String CLASS_HEADING_LABEL = "heading_label";
    static final String CLASS_SUBHEADING_LABEL = "subheading_label";
    static final String CLASS_PROMPT_LABEL = "prompt_label";
    static final String EMPTY_TEXT = "";
    static final int LARGE_TEXT_FIELD_LENGTH = 20;
    static final int SMALL_TEXT_FIELD_LENGTH = 5;

    // THIS MANAGES ALL OF THE APPLICATION'S DATA
    DraftDataManager dataManager;

    // THIS MANAGES COURSE FILE I/O
    DraftFileManager draftFileManager;

    // THIS MANAGES EXPORTING OUR SITE PAGES
    DraftSiteExporter siteExporter;

    // THIS HANDLES INTERACTIONS WITH FILE-RELATED CONTROLS
    FileController fileController;

    // THIS HANDLES INTERACTIONS WITH COURSE INFO CONTROLS
    DraftEditController draftController;
    

    // THIS IS THE APPLICATION WINDOW
    Stage primaryStage;
    
    Scene primaryScene;
    // THIS IS THE STAGE'S SCENE GRAPH
    VBox fantasyPane;
    VBox playerPane;
    BorderPane standingPane;
    BorderPane draftPane;
    BorderPane MLBPane;
    

    // THIS PANE ORGANIZES THE BIG PICTURE CONTAINERS FOR THE
    // APPLICATION GUI
    BorderPane wbdkPane;
    
    // THIS IS THE TOP TOOLBAR AND ITS CONTROLS
    FlowPane fileToolbarPane;
    Button newButton;
    Button loadButton;
    Button saveButton;
    Button exportButton;
    Button exitButton;
    
    
    //BOTTOM TOOLBAR AND ITS BUTTON
    FlowPane switcherPane;
    Button fantasyButton;
    Button playerButton;
    Button standingButton;
    Button draftButton;
    Button MLBButton;
    Button addButton;
    Button minusButton;
    
    
    Button editButton;

    // WE'LL ORGANIZE OUR WORKSPACE COMPONENTS USING A BORDER PANE
    
    BorderPane workspacePane;
    boolean workspaceActivated;
    
    // WE'LL PUT THE WORKSPACE INSIDE A SCROLL PANE
    ScrollPane workspaceScrollPane;
    StackPane workSpaceStackPane;
    // WE'LL PUT THIS IN THE TOP OF THE WORKSPACE, IT WILL
    // HOLD TWO OTHER PANES FULL OF CONTROLS AS WELL AS A LABEL
    VBox topWorkspacePane;
    Label courseHeadingLabel;
    SplitPane topWorkspaceSplitPane;
    
    RadioButton all; 
    RadioButton C ;
    RadioButton first;
    RadioButton CI;
    RadioButton third; 
    RadioButton second ;
    RadioButton MI ;
    RadioButton SS ;
    RadioButton OF;
    RadioButton U ;
    RadioButton P ;
    
    
    String tf ;
    TableView taxiDraftTable;
    TableView startingLineUpTable;
    TableView playerTable;
    TableColumn firstNameColumn;
    TableColumn lastNameColumn;
    TableColumn proTeamColumn;
    TableColumn positionColumn; //this is specifically use for pos in fantasy team
    TableColumn positionsColumn;
    TableColumn yearOfBirthColumn;
    TableColumn RWColumn;
    TableColumn HRSVColumn;
    TableColumn RBIKColumn;
    TableColumn SBERAColumn;
    TableColumn BAWHIPColumn;
    TableColumn estimatedColumn;
    TableColumn notesColumn;
    TableColumn contractColumn; 
    TableColumn salaryColumn; 

    TextField searchTF;
    String tfo;
    final ToggleGroup group = new ToggleGroup();
    ComboBox fantasyTeamComboBox;
    
    // AND TABLE COLUMNS
    static final String COL_FIRST = "First Name";
    static final String COL_LAST = "Last Name";
    static final String COL_PROTEAM = "Pro Team";
    static final String COL_POSITION = "Position";
    static final String COL_POSITIONS = "Positions";
    static final String COL_YEAROFBIRTH = "Year of Birth";
    static final String COL_RW = "R/W";
    static final String COL_HRSV = "HR/SV";
    static final String COL_RBIK = "RBI/K";
    static final String COL_SBERA = "SB/ERA";
    static final String COL_BAWHIP = "BA/WHIP";
    static final String COL_ESTIMATED = "Estimated Value";
    static final String COL_NOTES = "Notes";
    static final String COL_CONTRACT = "Contract";
    static final String COL_SALARY = "Salary";
    
    
    // HERE ARE OUR DIALOGS
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    ProgressDialog progressDialog;
    
    /**
     * Constructor for making this GUI, note that it does not initialize the UI
     * controls. To do that, call initGUI.
     *
     * @param initPrimaryStage Window inside which the GUI will be displayed.
     */
    public WBDK_GUI(Stage initPrimaryStage) {
        primaryStage = initPrimaryStage;
    }

    /**
     * Accessor method for the data manager.
     *
     * @return The CourseDataManager used by this UI.
     */
    public DraftDataManager getDataManager() {
        return dataManager;
    }

    /**
     * Accessor method for the file controller.
     *
     * @return The FileController used by this UI.
     */
    public FileController getFileController() {
        return fileController;
    }

    /**
     * Accessor method for the course file manager.
     *
     * @return The CourseFileManager used by this UI.
     */
    public DraftFileManager getDraftFileManager() {
        return draftFileManager;
    }

    /**
     * Accessor method for the site exporter.
     *
     * @return The CourseSiteExporter used by this UI.
     */
    public DraftSiteExporter getSiteExporter() {
        return siteExporter;
    }

    /**
     * Accessor method for the window (i.e. stage).
     *
     * @return The window (i.e. Stage) used by this UI.
     */
    public Stage getWindow() {
        return primaryStage;
    }
    
    public MessageDialog getMessageDialog() {
        return messageDialog;
    }
    
    public YesNoCancelDialog getYesNoCancelDialog() {
        return yesNoCancelDialog;
    }

    /**
     * Mutator method for the data manager.
     *
     * @param initDataManager The CourseDataManager to be used by this UI.
     */
    public void setDataManager(DraftDataManager initDataManager) {
        dataManager = initDataManager;
    }

    /**
     * Mutator method for the course file manager.
     *
     * @param initDraftFileManager The CourseFileManager to be used by this UI.
     */
    public void setDraftFileManager(DraftFileManager initDraftFileManager) {
        draftFileManager = initDraftFileManager;
    }

    /**
     * Mutator method for the site exporter.
     *
     * @param initSiteExporter The CourseSiteExporter to be used by this UI.
     */
    public void setSiteExporter(DraftSiteExporter initSiteExporter) {
        siteExporter = initSiteExporter;
    }

    /**
     * This method fully initializes the user interface for use.
     *
     * @param windowTitle The text to appear in the UI window's title bar.
     
     * @throws IOException Thrown if any initialization files fail to load.
     */
    public void initGUI(String windowTitle) throws IOException {
        // INIT THE DIALOGS
        initDialogs();
        
        // INIT THE TOOLBAR
        initFileToolbar();
        
        initScreenToolbar();
        
        initFantasyPane();
        initPlayerPane();
        initStandingPane();
        initDraftPane();
        initMLBPane();
        // INIT THE CENTER WORKSPACE CONTROLS BUT DON'T ADD THEM
        // TO THE WINDOW YET
        initWorkspace();

        // NOW SETUP THE EVENT HANDLERS
        initEventHandlers();

        // AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
        initWindow(windowTitle);
    }

    /**
     * When called this function puts the workspace into the window,
     * revealing the controls for editing a Course.
     */
    public void activateWorkspace() {
        if (!workspaceActivated) {
            // PUT THE WORKSPACE IN THE GUI
            
            workspacePane.setCenter(fantasyPane);
            workspacePane.setBottom(switcherPane);
            wbdkPane.setCenter(workspacePane);
            
            //wbdkPane.setCenter(fantasyPane);
            workspaceActivated = true;
        }
    }
    
    @Override
    public void reloadDraft(Draft draftToReload) {
        // FIRST ACTIVATE THE WORKSPACE IF NECESSARY
        if (!workspaceActivated) {
            activateWorkspace();
        }

        // WE DON'T WANT TO RESPOND TO EVENTS FORCED BY
        // OUR INITIALIZATION SELECTIONS
        draftController.enable(false);

        // FIRST LOAD ALL THE BASIC COURSE INFO

          searchTF.setText("" );
          group.selectToggle(all);
//        courseYearComboBox.setValue(courseToReload.getYear());
//        courseTitleTextField.setText(courseToReload.getTitle());
//        instructorNameTextField.setText(courseToReload.getInstructor().getName());
//        instructorURLTextField.setText(courseToReload.getInstructor().getHomepageURL());
//        indexPageCheckBox.setSelected(courseToReload.hasCoursePage(CoursePage.INDEX));
//        syllabusPageCheckBox.setSelected(courseToReload.hasCoursePage(CoursePage.SYLLABUS));
//        schedulePageCheckBox.setSelected(courseToReload.hasCoursePage(CoursePage.SCHEDULE));
//        hwsPageCheckBox.setSelected(courseToReload.hasCoursePage(CoursePage.HWS));
//        projectsPageCheckBox.setSelected(courseToReload.hasCoursePage(CoursePage.PROJECTS));

//        
//        mondayCheckBox.setSelected(courseToReload.hasLectureDay(DayOfWeek.MONDAY));
//        tuesdayCheckBox.setSelected(courseToReload.hasLectureDay(DayOfWeek.TUESDAY));
//        wednesdayCheckBox.setSelected(courseToReload.hasLectureDay(DayOfWeek.WEDNESDAY));
//        thursdayCheckBox.setSelected(courseToReload.hasLectureDay(DayOfWeek.THURSDAY));
//        fridayCheckBox.setSelected(courseToReload.hasLectureDay(DayOfWeek.FRIDAY));
        
        // THE SCHEDULE ITEMS TABLE
       
        // THE LECTURES TABLE
        
        // THE HWS TABLE

        // NOW WE DO WANT TO RESPOND WHEN THE USER INTERACTS WITH OUR CONTROLS
        draftController.enable(true);
    }    
    
    
    
    
    
    
    /**
     * This method is used to activate/deactivate toolbar buttons when
     * they can and cannot be used so as to provide foolproof design.
     * 
     * @param saved Describes whether the loaded Course has been saved or not.
     */
    public void updateToolbarControls(boolean saved) {
        // THIS TOGGLES WITH WHETHER THE CURRENT COURSE
        // HAS BEEN SAVED OR NOT
        saveButton.setDisable(saved);

        // ALL THE OTHER BUTTONS ARE ALWAYS ENABLED
        // ONCE EDITING THAT FIRST COURSE BEGINS
        loadButton.setDisable(false);
        exportButton.setDisable(false);

        // NOTE THAT THE NEW, LOAD, AND EXIT BUTTONS
        // ARE NEVER DISABLED SO WE NEVER HAVE TO TOUCH THEM
    }

    /**
     * This function loads all the values currently in the user interface
     * into the course argument.
     * 
     * @param course The course to be updated using the data from the UI controls.
     */
    public void updateDraftInfo(Draft draftToEdit ) {

    }

    /****************************************************************************/
    /* BELOW ARE ALL THE PRIVATE HELPER METHODS WE USE FOR INITIALIZING OUR GUI */
    /****************************************************************************/
    
    private void initDialogs() {
        messageDialog = new MessageDialog(primaryStage, CLOSE_BUTTON_LABEL);
        yesNoCancelDialog = new YesNoCancelDialog(primaryStage);
        progressDialog = new ProgressDialog(primaryStage, "Preparing");
    }
    
    /**
     * This function initializes all the buttons in the toolbar at the top of
     * the application window. These are related to file management.
     */
    private void initFileToolbar() {
        fileToolbarPane = new FlowPane();

        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
        // START AS ENABLED (false), WHILE OTHERS DISABLED (true)
        newButton = initChildButton(fileToolbarPane, WBDK_PropertyType.NEW_ICON, WBDK_PropertyType.NEW_TOOLTIP, false);
        loadButton = initChildButton(fileToolbarPane, WBDK_PropertyType.LOAD_ICON, WBDK_PropertyType.LOAD_TOOLTIP, false);
        saveButton = initChildButton(fileToolbarPane, WBDK_PropertyType.SAVE_ICON, WBDK_PropertyType.SAVE_TOOLTIP, true);
        exportButton = initChildButton(fileToolbarPane, WBDK_PropertyType.EXPORT_ICON, WBDK_PropertyType.EXPORT_TOOLTIP, true);
        exitButton = initChildButton(fileToolbarPane, WBDK_PropertyType.EXIT_ICON, WBDK_PropertyType.EXIT_TOOLTIP, false);
    }
    
    private void initScreenToolbar() {
        switcherPane = new FlowPane();

        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
        // START AS ENABLED (false), WHILE OTHERS DISABLED (true)
        fantasyButton = initChildButton(switcherPane, WBDK_PropertyType.HOME_ICON, WBDK_PropertyType.HOME_TOOLTIP, false);
        playerButton = initChildButton(switcherPane, WBDK_PropertyType.PLAYER_ICON, WBDK_PropertyType.PLAYER_TOOLTIP, false);
        draftButton = initChildButton(switcherPane, WBDK_PropertyType.DRAFT_ICON, WBDK_PropertyType.DRAFT_TOOLTIP, false);
        standingButton = initChildButton(switcherPane, WBDK_PropertyType.STANDING_ICON, WBDK_PropertyType.STANDING_TOOLTIP, false);
        MLBButton = initChildButton(switcherPane, WBDK_PropertyType.MLB_ICON, WBDK_PropertyType.MLB_TOOLTIP, false);
    }
    
    
    
    
    
    

    // CREATES AND SETS UP ALL THE CONTROLS TO GO IN THE APP WORKSPACE
    private void initWorkspace() throws IOException {
        // THE WORKSPACE HAS A FEW REGIONS, THIS 
        // IS FOR BASIC COURSE EDITING CONTROLS
     //   initBasicCourseInfoControls();


        // THE TOP WORKSPACE HOLDS BOTH THE BASIC COURSE INFO
        // CONTROLS AS WELL AS THE PAGE SELECTION CONTROLS
      

      

        // THIS HOLDS ALL OUR WORKSPACE COMPONENTS, SO NOW WE MUST
        // ADD THE COMPONENTS WE'VE JUST INITIALIZED
        workspacePane = new BorderPane();
        
        workspacePane.setCenter(wbdkPane);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        
        // AND NOW PUT IT IN THE WORKSPACE
        workspaceScrollPane = new ScrollPane();
        workspaceScrollPane.setContent(workspacePane);
        workspaceScrollPane.setFitToWidth(true);
        workspaceScrollPane.setFitToHeight(true);
        
        
        workSpaceStackPane = new StackPane();
        workSpaceStackPane.getChildren().add(workspacePane);
        workSpaceStackPane.setPrefSize(600, 600);
        
        
     
        
        

        // NOTE THAT WE HAVE NOT PUT THE WORKSPACE INTO THE WINDOW,
        // THAT WILL BE DONE WHEN THE USER EITHER CREATES A NEW
        // COURSE OR LOADS AN EXISTING ONE FOR EDITING
        workspaceActivated = false;
    }
    
    // INITIALIZES THE TOP PORTION OF THE WORKWPACE UI
    private void initFantasyPane() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        fantasyPane = new VBox();
        FlowPane draftNamePane = new FlowPane();
        FlowPane iconPane = new FlowPane();
        
        
        Label nameLabel = initLabel(WBDK_PropertyType.DRAFT_NAME_LABEL, CLASS_SUBHEADING_LABEL);
        searchTF = new TextField();
        searchTF.setPrefColumnCount(20);
        searchTF.setText("");
        searchTF.setEditable(true);        
        draftNamePane.getChildren().addAll(nameLabel,searchTF);
       
        addButton = initChildButton(iconPane, WBDK_PropertyType.ADD_ICON, WBDK_PropertyType.ADD_FANTASYTEAM_TOOLTIP, false);
        minusButton = initChildButton(iconPane, WBDK_PropertyType.MINUS_ICON, WBDK_PropertyType.REMOVE_FANTASYTEAM_TOOLTIP, false); 
        editButton = initChildButton(iconPane, WBDK_PropertyType.PEN_ICON, WBDK_PropertyType.EDIT_TOOLTIP, false); 
        Label selectLabel = initLabel(WBDK_PropertyType.SELECT_DRAFT_LABEL, CLASS_SUBHEADING_LABEL);
        fantasyTeamComboBox = new ComboBox();
        iconPane.getChildren().addAll(selectLabel,fantasyTeamComboBox);
        
        VBox  temp = new VBox();
        startingLineUpTable = new TableView();
        Label lineupLabel = initLabel(WBDK_PropertyType.LINEUP_LABEL, CLASS_SUBHEADING_LABEL);
        positionColumn = new TableColumn(COL_POSITION);
        firstNameColumn = new TableColumn(COL_FIRST);
        lastNameColumn = new TableColumn(COL_LAST);
        proTeamColumn = new TableColumn(COL_PROTEAM);
        positionsColumn = new TableColumn(COL_POSITIONS);
        RWColumn = new TableColumn(COL_RW);
        HRSVColumn = new TableColumn(COL_HRSV);
        RBIKColumn = new TableColumn(COL_RBIK);
        SBERAColumn = new TableColumn(COL_SBERA);
        BAWHIPColumn = new TableColumn(COL_BAWHIP);
        estimatedColumn = new TableColumn(COL_ESTIMATED);
        contractColumn = new TableColumn(COL_CONTRACT);
        salaryColumn = new TableColumn(COL_SALARY);
        
        startingLineUpTable.getColumns().addAll(positionColumn,firstNameColumn,lastNameColumn,proTeamColumn, positionsColumn
        , RWColumn, HRSVColumn, RBIKColumn, SBERAColumn, BAWHIPColumn, estimatedColumn, contractColumn,salaryColumn);  
        startingLineUpTable.setPrefHeight(800);
        

      
        taxiDraftTable = new TableView();
        Label taxiDraftLabel = initLabel(WBDK_PropertyType.TAXISQUAD_LABEL, CLASS_SUBHEADING_LABEL);
        positionColumn = new TableColumn(COL_POSITION);
        firstNameColumn = new TableColumn(COL_FIRST);
        lastNameColumn = new TableColumn(COL_LAST);
        proTeamColumn = new TableColumn(COL_PROTEAM);
        positionsColumn = new TableColumn(COL_POSITIONS);
        RWColumn = new TableColumn(COL_RW);
        HRSVColumn = new TableColumn(COL_HRSV);
        RBIKColumn = new TableColumn(COL_RBIK);
        SBERAColumn = new TableColumn(COL_SBERA);
        BAWHIPColumn = new TableColumn(COL_BAWHIP);
        estimatedColumn = new TableColumn(COL_ESTIMATED);
        contractColumn = new TableColumn(COL_CONTRACT);
        salaryColumn = new TableColumn(COL_SALARY);
        
        taxiDraftTable.getColumns().addAll(positionColumn,firstNameColumn,lastNameColumn,proTeamColumn, positionsColumn
        , RWColumn, HRSVColumn, RBIKColumn, SBERAColumn, BAWHIPColumn, estimatedColumn, contractColumn,salaryColumn);  
        taxiDraftTable.setPrefHeight(800);
        
        temp.setSpacing(5);
        temp.setPadding(new Insets(10,20,20,20));
        temp.setStyle("-fx-background-color: #FFB6C1; -fx-border-color: #FF69B4;");
        temp.getChildren().addAll(lineupLabel,startingLineUpTable,taxiDraftLabel,taxiDraftTable);
        
        fantasyPane.getChildren().add(initLabel(WBDK_PropertyType.FANTASY_TEAMS_LABEL, CLASS_HEADING_LABEL));
        fantasyPane.getChildren().addAll(draftNamePane,iconPane,temp);
        
        
        fantasyPane.setStyle("-fx-background-color: GhostWhite");
        fantasyPane.setSpacing(10);   
        

  
         
    }
    private void initPlayerPane() {
        playerPane = new VBox();
               
        FlowPane searchHbox = new FlowPane();
        FlowPane radioHBox = new FlowPane();
        //  BorderPane wrapper = new BorderPane();
        
        addButton = initChildButton(searchHbox, WBDK_PropertyType.ADD_ICON, WBDK_PropertyType.ADD_PLAYER_TOOLTIP, false);
        minusButton = initChildButton(searchHbox, WBDK_PropertyType.MINUS_ICON, WBDK_PropertyType.REMOVE_PLAYER_TOOLTIP, false); 
        Label searchLabel = initLabel(WBDK_PropertyType.SEARCH_LABEL, CLASS_SUBHEADING_LABEL);

        searchTF = new TextField();
        searchTF.setPrefColumnCount(100);
        searchTF.setText("");
        searchTF.setEditable(true);
        
        searchHbox.getChildren().addAll(searchLabel,searchTF);
        
        
        radioHBox.setPadding(new Insets(25,30,20,30));

        radioHBox.setHgap(10);
        radioHBox.setStyle("-fx-background-color: #FFB6C1; -fx-border-color: #FF69B4;");
     
         all = new RadioButton("All");
         C = new RadioButton("C");
         first = new RadioButton("1B");
         CI = new RadioButton("CI");
         third = new RadioButton("3B");
         second = new RadioButton("2B");
         MI = new RadioButton("MI");
         SS = new RadioButton("SS");
         OF = new RadioButton("OF");
         U = new RadioButton("U");
         P = new RadioButton("P");
        
         all.setToggleGroup(group);
         all.setSelected(true);
         C.setToggleGroup(group);
         first.setToggleGroup(group);
         CI.setToggleGroup(group);
         third.setToggleGroup(group);
         second.setToggleGroup(group);
         MI.setToggleGroup(group);
         SS.setToggleGroup(group);
         OF.setToggleGroup(group);
         U.setToggleGroup(group);
         P.setToggleGroup(group);
         
        radioHBox.getChildren().addAll(all,C,first,CI,third,second,MI,SS,OF,U,P);      
        
        playerTable = new TableView();
        
        firstNameColumn = new TableColumn(COL_FIRST);
        lastNameColumn = new TableColumn(COL_LAST);
        proTeamColumn = new TableColumn(COL_PROTEAM);
        positionsColumn = new TableColumn(COL_POSITIONS);
        yearOfBirthColumn = new TableColumn(COL_YEAROFBIRTH);
        RWColumn = new TableColumn(COL_RW);
        HRSVColumn = new TableColumn(COL_HRSV);
        RBIKColumn = new TableColumn(COL_RBIK);
        SBERAColumn = new TableColumn(COL_SBERA);
        BAWHIPColumn = new TableColumn(COL_BAWHIP);
        estimatedColumn = new TableColumn(COL_ESTIMATED);
        notesColumn = new TableColumn(COL_NOTES);  
        
        
        playerTable.getColumns().addAll(firstNameColumn,lastNameColumn,proTeamColumn, positionsColumn,yearOfBirthColumn
        , RWColumn, HRSVColumn, RBIKColumn, SBERAColumn, BAWHIPColumn, estimatedColumn, notesColumn);       

        estimatedColumn.setPrefWidth(100);
           
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("firstname"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("lastname"));
        proTeamColumn.setCellValueFactory(new PropertyValueFactory<String, String>("team"));
        positionsColumn.setCellValueFactory(new PropertyValueFactory<String, String>("position"));
        yearOfBirthColumn.setCellValueFactory(new PropertyValueFactory<String, String>("birth"));
        estimatedColumn.setCellValueFactory(new PropertyValueFactory<String, String>(""));  
        notesColumn.setCellValueFactory(new PropertyValueFactory<String, String>("notes"));
        notesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        playerTable.setEditable(true);
        notesColumn.setEditable(true); 
       
        RWColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("rw"));
        HRSVColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("hrsv"));
        RBIKColumn.setCellValueFactory(new PropertyValueFactory<Double, String>("rbik"));
        SBERAColumn.setCellValueFactory(new PropertyValueFactory<Double, String>("sbera"));
        BAWHIPColumn.setCellValueFactory(new PropertyValueFactory<Double, String>("bawhip"));
       

        
        playerTable.setItems(dataManager.getDraft().getGuiPool());
        
       // firstNameColumn.setSortType(TableColumn.SortType.ASCENDING);
        FXCollections.sort(dataManager.getDraft().getGuiPool(), new NameComparator());
        
        playerTable.setPrefHeight(1000);
        
        playerPane.getChildren().add(initLabel(WBDK_PropertyType.PLAYERS_LABEL, CLASS_HEADING_LABEL));
        playerPane.getChildren().add(searchHbox);    
        playerPane.getChildren().add(radioHBox);
        playerPane.getChildren().add(playerTable);
        playerPane.setStyle("-fx-background-color: GhostWhite");
        playerPane.setSpacing(10);
        
     //   playerPane.setCenter(a);
         
    }
    
    private void initStandingPane() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        standingPane = new BorderPane();
        GridPane a = new GridPane();
             
        a.add(initLabel(WBDK_PropertyType.STANDING_LABEL, CLASS_HEADING_LABEL), 0, 0);
        a.setStyle("-fx-background-color: GhostWhite");
           
        standingPane.setCenter(a);
      //  standingPane.setBottom(switcherPane);
         
    }
    private void initDraftPane() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        draftPane = new BorderPane();
        GridPane a = new GridPane();
             
        a.add(initLabel(WBDK_PropertyType.DRAFT_LABEL, CLASS_HEADING_LABEL), 0, 0);
        a.setStyle("-fx-background-color: GhostWhite");
           
        draftPane.setCenter(a);
     //   draftPane.setBottom(switcherPane);
         
    }    
    private void initMLBPane() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        MLBPane = new BorderPane();
        GridPane a = new GridPane();
             
        a.add(initLabel(WBDK_PropertyType.MLB_LABEL, CLASS_HEADING_LABEL), 0, 0);
        a.setStyle("-fx-background-color: GhostWhite");
           
        MLBPane.setCenter(a);
      //  MLBPane.setBottom(switcherPane);
         
    }
   

    // INITIALIZE THE WINDOW (i.e. STAGE) PUTTING ALL THE CONTROLS
    // THERE EXCEPT THE WORKSPACE, WHICH WILL BE ADDED THE FIRST
    // TIME A NEW Course IS CREATED OR LOADED
    private void initWindow(String windowTitle) {
        // SET THE WINDOW TITLE
        primaryStage.setTitle(windowTitle);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        // ADD THE TOOLBAR ONLY, NOTE THAT THE WORKSPACE
        // HAS BEEN CONSTRUCTED, BUT WON'T BE ADDED UNTIL
        // THE USER STARTS EDITING A COURSE
        wbdkPane = new BorderPane();
        wbdkPane.setTop(fileToolbarPane);
       
        primaryScene = new Scene(wbdkPane);

        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
        // WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
        primaryScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    // INIT ALL THE EVENT HANDLERS
    private void initEventHandlers() throws IOException {
        // FIRST THE FILE CONTROLS
        fileController = new FileController(messageDialog, yesNoCancelDialog, progressDialog, draftFileManager, siteExporter);
        newButton.setOnAction(e -> {
            fileController.handleNewRequest(this);
        });
        loadButton.setOnAction(e -> {
            fileController.handleLoadRequest(this);
        });
        saveButton.setOnAction(e -> {
            fileController.handleSaveRequest(this, dataManager.getDraft());
        });
        exportButton.setOnAction(e -> {
            fileController.handleExportRequest(this);
        });
        exitButton.setOnAction(e -> {
            fileController.handleExitRequest(this);
        });
        
        draftController = new DraftEditController(primaryStage, dataManager.getDraft().g, messageDialog, yesNoCancelDialog);
        
        
        
        playerTable.setOnMouseClicked(e -> {
         if (e.getClickCount() == 2) {
               // OPEN UP THE LECTURE EDITOR
         Player l = (Player)playerTable.getSelectionModel().getSelectedItem();
         DraftEditController.handleEditPlayerRequest(this, l);
            }
        });
        
        
        
        
        
        //switch pane mechanism
        fantasyButton.setOnAction(e -> {
           
        workspacePane.setCenter(fantasyPane);

        });
        playerButton.setOnAction(e -> {
            workspacePane.setCenter(playerPane);
            
        });
        standingButton.setOnAction(e -> {
            workspacePane.setCenter(standingPane);
            
        });
        draftButton.setOnAction(e -> {
            workspacePane.setCenter(draftPane);
            
        });
        MLBButton.setOnAction(e -> {
            workspacePane.setCenter(MLBPane);
        });
        

        registerToggleGroupController(group);     
        registerTextFieldController(searchTF);
        
        
    }
    
    
    
    private void registerToggleGroupController(ToggleGroup group){
        
    group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
    public void changed(ObservableValue<? extends Toggle> ov,
        Toggle old_toggle, Toggle new_toggle) {
            if (group.getSelectedToggle() != null) {
              //  System.out.println(new_toggle.toString().);
               // System.out.println(new_toggle.toString().substring(46, new_toggle.toString().length()-1));
              
                dataManager.getDraft().addSearchPool(handleToggleController(new_toggle) );
                
            }                
        }
});

    
    }
    
    // REGISTER THE EVENT LISTENER FOR A TEXT FIELD
    private void registerTextFieldController(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
         //   dataManager.getDraft().addSearchPool( dataManager.getDraft().handleSearchTF(newValue) );
           // ObservableList<Player> t = 
            
            handleSearchTF(newValue);
            tf = newValue;
            tfo = oldValue;

         //   dataManager.getDraft().addTextPool(t);
        });
    }
    
    // INIT A BUTTON AND ADD IT TO A CONTAINER IN A TOOLBAR
    private Button initChildButton(Pane toolbar, WBDK_PropertyType icon, WBDK_PropertyType tooltip, boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imagePath = "file:" + PATH_IMAGES + props.getProperty(icon.toString());
        Image buttonImage = new Image(imagePath);
        Button button = new Button();
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
        button.setTooltip(buttonTooltip);
        toolbar.getChildren().add(button);
        return button;
    }
    
    // INIT A LABEL AND SET IT'S STYLESHEET CLASS
    private Label initLabel(WBDK_PropertyType labelProperty, String styleClass) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelText = props.getProperty(labelProperty);
        Label label = new Label(labelText);
        label.getStyleClass().add(styleClass);
        return label;
    }

    // INIT A LABEL AND PLACE IT IN A GridPane INIT ITS PROPER PLACE
    private Label initGridLabel(GridPane container, WBDK_PropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
        Label label = initLabel(labelProperty, styleClass);
        container.add(label, col, row, colSpan, rowSpan);
        return label;
    }

    // INIT A LABEL AND PUT IT IN A TOOLBAR
    private Label initChildLabel(Pane container, WBDK_PropertyType labelProperty, String styleClass) {
        Label label = initLabel(labelProperty, styleClass);
        container.getChildren().add(label);
        return label;
    }

    // INIT A COMBO BOX AND PUT IT IN A GridPane
    private ComboBox initGridComboBox(GridPane container, int col, int row, int colSpan, int rowSpan) throws IOException {
        ComboBox comboBox = new ComboBox();
        container.add(comboBox, col, row, colSpan, rowSpan);
        return comboBox;
    }
    private void initGridButton (GridPane container,Button button, int col, int row, int colSpan, int rowSpan) throws IOException {
       
        container.add(button, col, row, colSpan, rowSpan);
        
    }    


    // INIT A TEXT FIELD AND PUT IT IN A GridPane
    private TextField initGridTextField(GridPane container, int size, String initText, boolean editable, int col, int row, int colSpan, int rowSpan) {
        TextField tf = new TextField();
        tf.setPrefColumnCount(size);
        tf.setText(initText);
        tf.setEditable(editable);
        container.add(tf, col, row, colSpan, rowSpan);
        return tf;
    }

    // INIT A DatePicker AND PUT IT IN A GridPane
    private DatePicker initGridDatePicker(GridPane container, int col, int row, int colSpan, int rowSpan) {
        DatePicker datePicker = new DatePicker();
        container.add(datePicker, col, row, colSpan, rowSpan);
        return datePicker;
    }

    // INIT A CheckBox AND PUT IT IN A TOOLBAR
    private CheckBox initChildCheckBox(Pane container, String text) {
        CheckBox cB = new CheckBox(text);
        container.getChildren().add(cB);
        return cB;
    }

    // INIT A DatePicker AND PUT IT IN A CONTAINER
    private DatePicker initChildDatePicker(Pane container) {
        DatePicker dp = new DatePicker();
        container.getChildren().add(dp);
        return dp;
    }

    
    public ToggleGroup getToggle(){
    return group;
    }
    
    public ObservableList<Player> handleToggleController(Toggle selection){
        String s="";
        String s2 = "";
        String k;
        ObservableList<Player> temp = dataManager.getDraft().getGuiPool();
        
        
        ObservableList<Player> data = FXCollections.observableArrayList(dataManager.getDraft().getDataPool()); 
        ObservableList<Player> text = FXCollections.observableArrayList(dataManager.getDraft().getGuiPool()); 
        
        if(searchTF.textProperty().getValue().equals("")){
        text = data;
        }

      
        temp.clear();
        
        if(selection.equals(all)){
        temp.addAll(data);
        RWColumn.setText(COL_RW);
        HRSVColumn.setText(COL_HRSV);
        RBIKColumn.setText(COL_RBIK);
        SBERAColumn.setText(COL_SBERA);
        BAWHIPColumn.setText(COL_BAWHIP);
        }
        
        else if(selection.equals(P)){
   
            for(int i = 0;  i<text.size(); i ++){
            if(text.get(i).getPosition().contains("P")){
            temp.add(text.get(i));
                        }
                }
        RWColumn.setText("W");
        HRSVColumn.setText("SV");
        RBIKColumn.setText("K");
        SBERAColumn.setText("ERA");
        BAWHIPColumn.setText("WHIP");
            
            }
        
        else {
        
        RWColumn.setText("R");
        HRSVColumn.setText("HR");
        RBIKColumn.setText("RBI");
        SBERAColumn.setText("SB");
        BAWHIPColumn.setText("BA");
            
            
            
            
            if(selection.equals(C)){ s ="C"; }
            else if(selection.equals(first)){ s ="1B"; }
            else if(selection.equals(third)){ s ="3B"; }
            else if(selection.equals(second)){ s ="2B"; }
            else if(selection.equals(SS)){ s ="SS"; }
            else if(selection.equals(OF)){ s ="OF"; }
                       
            if(selection.equals(U)){
            for(int i = 0;  i<text.size(); i ++){
            if(!text.get(i).getPosition().contains("P")){
            temp.add(text.get(i));
                }   
            }
           }
            else if (selection.equals(CI)||selection.equals(MI)) {
            if(selection.equals(CI)){ s="1B"; s2="3B";  }
            else{s="2B"; s2="SS";}
            for(int i = 0;  i<text.size(); i ++){
            if(text.get(i).getPosition().contains(s) 
                    ||text.get(i).getPosition().contains(s2) ){
            temp.add(text.get(i));
                     }   
                 }
            }
            else {
             
            for(int i = 0;  i<text.size(); i ++){
            if(text.get(i).getPosition().contains(s)){
            temp.add(text.get(i));
                }   
            }
            }
            
        }
          
      
      return temp;
    }
    public ObservableList<Player> handleSearchTF(String s) {
        ObservableList<Player> guiPool = dataManager.getDraft().getGuiPool();
        ObservableList<Player> temp =FXCollections.observableArrayList(dataManager.getDraft().getSearchPool());
        guiPool.clear();
        
        String lowFirst; 
        String lowLast;
        for(int i = 0;  i<temp.size(); i ++){
            lowFirst =  temp.get(i).getFirstName().toLowerCase();
            lowLast =  temp.get(i).getLastName().toLowerCase();
            
        if(lowFirst.contains(s.toLowerCase()) ||lowLast.contains(s.toLowerCase())){
            guiPool.add(temp.get(i));
        }       
    }
        

      if(searchTF.textProperty().getValue().equalsIgnoreCase("")){
  
       guiPool = handleToggleController(group.getSelectedToggle());
       dataManager.getDraft().addSearchPool(guiPool);
       
      }  
  return guiPool;
    }    
    
    
    
//    // LOADS CHECKBOX DATA INTO A Course OBJECT REPRESENTING A CoursePage
//    private void updatePageUsingCheckBox(CheckBox cB, Course course, CoursePage cP) {
//        if (cB.isSelected()) {
//            course.selectPage(cP);
//        } else {
//            course.unselectPage(cP);
//        }
//    }    

    
}
