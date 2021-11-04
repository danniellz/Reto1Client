package signupsigninclient.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import user.User;

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
    
    //Atributes
    private static final Logger LOG = Logger.getLogger(LogOutController.class.getName());

    /**
     *
     * @param logOutStage
     */
    public void setStage(Stage logOutStage) {
        stage = logOutStage;
    }

    public void initData(User user) {
        String fullName;
        fullName = user.getFullName();
        messageLbl.setText("Hello " + fullName + ", you have succesfully logged in!!");
    }
    
    public void initStage(Parent root) {
        try {
            LOG.info("Initializing stage...");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Log Out");
            stage.setResizable(false);
            stage.setOnCloseRequest(this::handleCloseRequest);
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
            
            startSignInWindow(stage);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Log Out error", ex);
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
    
    /**
     * Open the SignIn window
     * 
     * @param primaryStage stage object (window)
     * @throws IOException Throws an error if the SignUp window fails to open
     */
    private void startSignInWindow(Stage primaryStage) throws IOException{
        try{
            LOG.info("Starting SignIn Window...");
            //Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SignIn.fxml"));
            Parent root = (Parent)loader.load();
            //Get controller
            SignInController signinController = ((SignInController)loader.getController()); 
            //Set the stage
            signinController.setStage(primaryStage);
            //initialize the window
            signinController.initStage(root);
        }catch(IOException ex){
            LOG.log(Level.SEVERE, "Error Starting SignUp Window", ex);
        }
    } 
}
