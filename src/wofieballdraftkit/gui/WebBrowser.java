/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.gui;

import java.net.MalformedURLException;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author MiChAeL
 */
public class WebBrowser {
    Stage browserStage;
    WebView browserView;
    WebEngine browserEngine;
    BorderPane browserPane;
    Scene browserScene;
    
    /**
     * This constructor loads the pageURLPath into the initBrowserStage. Note
     * that it actually loads it into another stage.
     * 
     * @param initBrowserStage Stage to display Web page.
     * @param pageURLPath URL of Web page to load and display.
     * @throws MalformedURLException This exception will be thrown if
     * a bad URL is provided.
     */
    public WebBrowser(Stage initBrowserStage, String pageURLPath) throws MalformedURLException {
        // THIS WILL SERVE AS OUR BROWSER WINDOW
        browserStage = initBrowserStage;
        
        // MAKE THE WEB VIEW AND LOAD THE WEB PAGE INTO IT
        browserView = new WebView();
        browserEngine = browserView.getEngine();
        browserEngine.load(pageURLPath);
        
        // PUT THE WEB VIEW IN THE WINDOW
        browserPane = new BorderPane();
        browserPane.setCenter(browserView);
        browserScene = new Scene(browserPane);
        browserStage.setScene(browserScene);
    }
}
