package signupsigninclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import signupsigninclient.controller.SignInController;

/**
 * class responsible for starting the application
 * 
 * @author Aritz Arrieta, Mikel Matilla, Daniel Brizuela, Jonathan Viñan
 */
public class SignUpSignInClient extends Application{
    /**
     * This is the first window (SignIn)
     * 
     * @param primaryStage stage object (window)
     * @throws Exception throws an error if the start method fails
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/SignIn.fxml"));
        Parent root = (Parent)loader.load();
        //Get controller
        SignInController signinController = ((SignInController)loader.getController()); 
        //Set the stage
        signinController.setStage(primaryStage);
        //initialize the window
        signinController.initStage(root);
    }
    
    /**
    * Main class, start the application 
    * 
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        launch(args);
}
    
}
