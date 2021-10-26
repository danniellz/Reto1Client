package signupsigninclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
 * @version 1.0
 */
public class SignInController implements Initializable {
    //Attributes, @FXML allows interaction with controls from the FXML file
    private Stage stage;
    @FXML
    private TextField userTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private Button loginBtn;
    @FXML
    private Hyperlink signUpHl;
    @FXML
    private Label errorLbl;
    
    //LOGGER
    private static final Logger LOG = Logger.getLogger(SignInController.class.getName());
    
    /**
     * Initialize method from implements Initializable
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
            signUpHl.addEventHandler(ActionEvent.ACTION, this::handleSignUpHyperLink);
            handleTextLimit();
            //ventana asincrona
            stage.show();
        }catch(Exception ex){
            LOG.log(Level.SEVERE, "Stage init error", ex);
        }
        
    }
    
    //Pressing the login button
    /**
     * Calling this method the SignIn action will be executed to grant access to the app (LogOut window)
     * 
     * @param buttonPress Action event at pressing the login button
     */
    private void handleButtonLogin(ActionEvent buttonPress){
        try{
            LOG.info("Login Button Pressed");
        }catch(Exception ex){
            LOG.log(Level.SEVERE, "Login Button Error", ex);
        }  
    }

    /**
     * Calling this method will close the app (EJ: Pressing the window exit button)
     * 
     * @param closeEvent A window event
     */
    private void handleCloseRequest(WindowEvent closeEvent){
        try{
            LOG.info("Closing...");
            Platform.exit();
        }catch(Exception ex){
            LOG.log(Level.SEVERE, "Close request error", ex);
        }  
    }
    
    //Calling this method sets the user and password field limit character (textProperty())
    private void handleTextLimit(){
        try{
            LOG.info("Setting User and Password field limit character...");
            //User field setting (lambda -- ChangeListener)
            userTxt.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                //if a 26 character its typed, take first character to 25 and set it to the field
                if(userTxt.getText().length() > 25){
                    userTxt.setText(userTxt.getText().substring(0, 25));
                }
                //controlar espacios
                if(userTxt.getText().matches(" "+userTxt.getText()) || userTxt.getText().matches(userTxt.getText()+" ") ){
                    LOG.info("espacio");
                }
            });
            //Password field setting (lambda -- ChangeListener)
            passwordTxt.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                //if a 26 character its typed, take first character to 25 and set it to the field
                if(passwordTxt.getText().length() > 25){
                    userTxt.setText(passwordTxt.getText().substring(0, 25));
                }
            });
        }catch(Exception ex){
            LOG.log(Level.SEVERE, "Error Setting User and Password field(handleTextChange)", ex);
        }  
    }
    
    /**
     * Calling this method will open the SignUp window
     * 
     * @param HyperLinkPress Action event at pressing the HyperLink
     */
    private void handleSignUpHyperLink(ActionEvent HyperLinkPress){
        try{
            LOG.info("SignUp Hyper Link Pressed");
            startSignUpWindow(stage);
        }catch(IOException ex){
            LOG.log(Level.SEVERE, "HyperLink Error", ex);
        }  
    }
    
    /**
     * Open the SignUp window
     * 
     * @param primaryStage stage object (window)
     * @throws IOException Throws an error if the SignUp window fails to open
     */
    private void startSignUpWindow(Stage primaryStage) throws IOException{
        /*try{
            LOG.info("Starting SignUp Window...");
            //Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/SignUp.fxml"));
            Parent root = (Parent)loader.load();
            //Get controller
            SignUpController signUpController = ((SignUpController)loader.getController()); 
            //Set the stage
            SignUpController.setStage(primaryStage);
            //initialize the window
            SignUpController.initStage(root);
        }catch(IOException ex){
            LOG.log(Level.SEVERE, "Error Starting SignUp Window", ex);
        }*/
    }
    
    /**
     * Open the LogOut window
     * 
     * @param primaryStage stage object (window)
     * @throws IOException Throws an error if the LogOut window fails to open
     */
    private void startLogOutWindow(Stage primaryStage) throws IOException{
        /*try{
            LOG.info("Starting LogOut Window...");
            //Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/LogOut.fxml"));
            Parent root = (Parent)loader.load();
            //Get controller
            LogOutController logOutController = ((LogOutController)loader.getController()); 
            //Set the stage
            LogOutController.setStage(primaryStage);
            //initialize the window
            LogOutController.initStage(root);
        }catch(IOException ex){
            LOG.log(Level.SEVERE, "Error Starting LogOut Window", ex);
        }*/
    }
}
