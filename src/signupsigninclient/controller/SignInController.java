package signupsigninclient.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import signable.Signable;
import signupsigninclient.logic.SignableFactory;
import user.User;

/**
 * SignIn window controller class 
 * 
 * @author Daniel Brizuela
 * @version 1.0
 */
public class SignInController {
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
    private String username, password;
    
    //LOGGER
    private static final Logger LOG = Logger.getLogger(SignInController.class.getName());
    
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
            userTxt.textProperty().addListener(this::handleUserControl);
            passwordTxt.textProperty().addListener(this::handlePasswordControl);
            errorLbl.setVisible(false);
            errorLbl.setText("Incorrect User or Password");
            errorLbl.setStyle("-fx-text-fill: red");
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
        username = userTxt.getText();
        password = passwordTxt.getText();
        try{
            LOG.info("Login Button Pressed");
            //if the user or password fields are empty, throw a message
            if((username.equals("") || password.equals("")) || 
               (username.equals("") && password.equals(""))){
                LOG.info("Null value in User or password field");
                errorLbl.setVisible(true);
            }else{
                LOG.info("Proccesing user info...");
                User user = new User();
                user.setLogin(username);
                user.setPassword(password);
                Signable sign = new SignableFactory().getSignable();
                
                sign.signIn(user);
                
                /*try{
                    LOG.info("Starting LogOut Window...");
                    //Load the FXML file
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("view/LogOut.fxml"));
                    Parent root = (Parent)loader.load();
                    //Get controller
                    LogOutController logOutController = ((LogOutController)loader.getController()); 
                    //Set the stage
                    logOutController.setStage(logOutStage);
                    //initialize the window
                    logOutController.initStage(root, user);
                }catch(IOException ex){
                    LOG.log(Level.SEVERE, "Error Starting LogOut Window", ex);
                }*/
            }
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
            LOG.info("Confirm Closing");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("¿Are you sure you want to exit?");
            alert.setTitle("Exit");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                LOG.info("Closing...");
                Platform.exit();
            }else{
                LOG.info("Closing Canceled");
                //Cancel the event process
                closeEvent.consume();
            }
            
        
            
        }catch(Exception ex){
            LOG.log(Level.SEVERE, "Close request error", ex);
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
            startSignUpWindow();
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
    private void startSignUpWindow() throws IOException{
        /*try{
            LOG.info("Starting SignUp Window...");
            //Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SignUp.fxml"));
            Parent root = (Parent)loader.load();
            //Get controller
            SignUpController signUpController = ((SignUpController)loader.getController()); 
            //Set the stage
            signUpController.setStage(stage);
            //initialize the window
            signUpController.initStage(root);
        }catch(IOException ex){
            LOG.log(Level.SEVERE, "Error Starting SignUp Window", ex);
        }*/
    }
    
    /**
     * Calling this method sets the user field controls (textProperty())
     * 
     * @param observable targeted field whose value changed
     * @param oldValue previous value before change
     * @param newValue last value typed
     */
    private void handleUserControl(ObservableValue<? extends String> observable, String oldValue, String newValue){
        try{
            //if a 26 character its typed, take first character to 25 and set it to the field
            if(userTxt.getText().length() > 25){
                userTxt.setText(userTxt.getText().substring(0, 25));
                LOG.info("25 character limit reached in user");
            }
            //Control empty spaces
            if(userTxt.getText().contains(" ")){
                 userTxt.setText(userTxt.getText().replaceAll(" ", ""));
            }
            //Show error label
             errorLbl.setVisible(false);
        }catch(Exception ex){
            LOG.log(Level.SEVERE, "Error Setting User field control", ex);
        }  
    }
    
    /**
     * Calling this method sets the password field controls (textProperty())
     * 
     * @param observable targeted field whose value changed
     * @param oldValue previous value before change
     * @param newValue last value type
     */
    private void handlePasswordControl(ObservableValue<? extends String> observable, String oldValue, String newValue){
        try{
            //if a 26 character its typed, take first character to 25 and set it to the field
            if(passwordTxt.getText().length() > 25){
                passwordTxt.setText(passwordTxt.getText().substring(0, 25));
                LOG.info("25 character limit reached in password");
            }
            //Control empty spaces
            if(passwordTxt.getText().contains(" ")){
                passwordTxt.setText(passwordTxt.getText().replaceAll(" ", ""));
            }
            //Show error label
            errorLbl.setVisible(false);
        }catch(Exception ex){
            LOG.log(Level.SEVERE, "Error Setting Password field control", ex);
        }  
    }
}
