package signupsigninclient.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * SignIn window controller class 
 * 
 * @author Daniel Brizuela
 */
public class SignInController implements Initializable {
    //Attributes
    private Stage stage;
    @FXML
    private TextField userTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private Button loginBtn;
    @FXML
    private Hyperlink signUpHL;
    @FXML
    private Label errorLbl;
    
    private static final Logger LOG = Logger.getLogger(SignInController.class.getName());
    
    /**
     * 
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    /**
     * Set the primary stage
     * 
     * @param primaryStage contains the stage value
     */
    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }
    
    /**
     * Initialize the window
     * 
     * @param root Parent value containing the FXML
     */
    public void initStage(Parent root) {
        try{
            LOG.info("Initializing stage...");
            //Creates a new Scene
            Scene scene = new Scene (root);
            //Associate the scene to window(stage)
            stage.setScene(scene);
            //Window properties
            stage.setTitle("Sign In");
            stage.setResizable(false);
            stage.setOnCloseRequest(this::handleCloseRequest);
            //Controls
            loginBtn.addEventHandler(ActionEvent.ACTION, this::handleButtonLogin);
            signUpHL.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    handleSignUpHyperLink();
                }
            });
            //ventana asincrona
            stage.show();
        }catch(Exception ex){
            LOG.log(Level.SEVERE, "Stage init error", ex);
        }
        
    }
    
    //Pressing the login button
    private void handleButtonLogin(ActionEvent actionEvent){
        try{
            LOG.info("Login Button Pressed");
        }catch(Exception ex){
            LOG.log(Level.SEVERE, "Login Button Error", ex);
        }  
    }
    //Pressing the window exit button
    private void handleCloseRequest(WindowEvent event){
        try{
            LOG.info("Closing...");
            Platform.exit();
        }catch(Exception ex){
            LOG.log(Level.SEVERE, "Close request error", ex);
        }  
    }
    
    //Pressing the HyperLink
    private void handleSignUpHyperLink(){
        try{
            LOG.info("Sign Up Hyper Link Pressed");
        }catch(Exception ex){
            LOG.log(Level.SEVERE, "HyperLink Error", ex);
        }  
    }
}
