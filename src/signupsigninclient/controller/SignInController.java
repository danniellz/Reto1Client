package signupsigninclient.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
            //ventana asincrona
            stage.show();
        }catch(Exception ex){
            LOG.log(Level.SEVERE, "Stage init error", ex);
        }
        
    }
    
    private void handleButtonLogin(){
        
    }
    private void handleCloseRequest(WindowEvent event){
        Platform.exit();
    }
    
}


