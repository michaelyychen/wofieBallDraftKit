/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.gui;

import static wofieballdraftkit.WBDK_StartUpConstants.*;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
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
import properties_manager.PropertiesManager;
import wofieballdraftkit.WBDK_PropertyType;
import wofieballdraftkit.controller.DraftEditController;
import wofieballdraftkit.controller.FileController;
import wofieballdraftkit.data.Draft;
import wofieballdraftkit.data.DraftDataManager;
import wofieballdraftkit.data.DraftDataView;
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
    Pane fantasyPane;
    Pane playerPane;
    Pane standingPane;
    Pane draftPane;
    Pane MLBPane;
    

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



    
    // AND TABLE COLUMNS
    static final String COL_DESCRIPTION = "Description";
    static final String COL_DATE = "Date";
    static final String COL_LINK = "Link";
    static final String COL_TOPIC = "Topic";
    static final String COL_SESSIONS = "Number of Sessions";
    static final String COL_NAME = "Name";
    static final String COL_TOPICS = "Topics";
    
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
            
       
            wbdkPane.setCenter(workSpaceStackPane);
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
//        courseSubjectComboBox.setValue(courseToReload.getSubject());
//        courseNumberTextField.setText("" + courseToReload.getNumber());
//        courseSemesterComboBox.setValue(courseToReload.getSemester());
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
        draftButton = initChildButton(switcherPane, WBDK_PropertyType.DRAFT_ICON, WBDK_PropertyType.DRAFT_TOOLTIP, true);
        standingButton = initChildButton(switcherPane, WBDK_PropertyType.STANDING_ICON, WBDK_PropertyType.STANDING_TOOLTIP, true);
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
         fantasyPane = new BorderPane();
         fantasyPane.setPrefSize(200, 200);
         Text text = new Text();
         text.setText("abd");
         fantasyPane.getChildren().add(text);
         
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
        
        draftController = new DraftEditController();
//        fantasyButton.setOnAction(e -> {
//            this.fantasyPane.toFront();
//        });
//        playerButton.setOnAction(e -> {
//            this.playerPane.toFront();
//        });
//        standingButton.setOnAction(e -> {
//            this.standingButton.toFront();
//        });
//        draftButton.setOnAction(e -> {
//            this.draftButton.toFront();
//        });
//        MLBButton.setOnAction(e -> {
//            this.MLBButton.toFront();
//        });
        
        
        
        
        
        
        
        

        // THEN THE COURSE EDITING CONTROLS


        // TEXT FIELDS HAVE A DIFFERENT WAY OF LISTENING FOR TEXT CHANGES
//        registerTextFieldController(courseNumberTextField);


        
 

    }

    // REGISTER THE EVENT LISTENER FOR A TEXT FIELD
    private void registerTextFieldController(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            draftController.handleDraftChangeRequest(this);
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

    // LOAD THE COMBO BOX TO HOLD Course SUBJECTS
//    private void loadSubjectComboBox(ArrayList<String> subjects) {
//        for (String s : subjects) {
//            courseSubjectComboBox.getItems().add(s);
//        }
//    }

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
    
//    // LOADS CHECKBOX DATA INTO A Course OBJECT REPRESENTING A CoursePage
//    private void updatePageUsingCheckBox(CheckBox cB, Course course, CoursePage cP) {
//        if (cB.isSelected()) {
//            course.selectPage(cP);
//        } else {
//            course.unselectPage(cP);
//        }
//    }    


}
