package signupsigninclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import signupsigninclient.controller.SignUpController;

/**
 * class responsible for starting the application
 *
 * @author Aritz Arrieta, Mikel Matilla, Daniel Brizuela
 */
public class SignUpSignInClient extends Application {

    /**
     * Main class, start the application
     *
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/SignUp.fxml"));
        Parent root = (Parent) loader.load();

        SignUpController controllerSignUp
                = ((SignUpController) loader.getController());
        controllerSignUp.setStage(primaryStage);
        controllerSignUp.initStage(root);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
