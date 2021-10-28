package signupsigninclient.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Log Out Controller
 * 
 * @author Mikel Matilla
 */
public class LogOutController {
    
    private Stage stage;
    
    @FXML
    private Pane logOutPanel;
    @FXML
    private Label logOutLbl;
    @FXML
    private Label messageLbl;
    @FXML
    private MenuItem logOutItem;
    @FXML
    private MenuItem exitItem;
    
    String name;

    //Atributes
    private static final Logger LOG = Logger.getLogger(LogOutController.class.getName());

    /**
     *
     * @param logOutStage
     */
    public void setStage(Stage logOutStage) {
        stage = logOutStage;
    }

    public void initStage(Parent root) {
        try {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("LOG OUT");
            stage.setResizable(false);
            stage.setOnCloseRequest(this::handleCloseRequest);
            messageLbl.setText("Hello " + name + ", you have succesfully logged in!!");
            logOutItem.setOnAction(this::handleLogOut);
            exitItem.setOnAction(this::handleExit);
            stage.show();
        } catch (Exception e) {
             LOG.log(Level.SEVERE, "Stage init error", e);
        }
        
    }
    
    /**
     * Calling this method will close the app (EJ: Pressing the window exit button)
     * 
     * @param closeEvent A window event
     */
    public void handleCloseRequest(WindowEvent closeEvent){
        try{
            LOG.info("Closing...");
            Platform.exit();
        }catch(Exception e){
            LOG.log(Level.SEVERE, "Close request error", e);
        }  
    }
    
    public void handleLogOut(ActionEvent logOutEvent) {
        try {
            LOG.info("Logging out...");
            
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Log Out error", e);
        }
    }
    
    public void handleExit(ActionEvent closeEvent){
        try{
            LOG.info("Closing...");
            Platform.exit();
        }catch(Exception e){
            LOG.log(Level.SEVERE, "Close request error", e);
        }  
    }
    
}
