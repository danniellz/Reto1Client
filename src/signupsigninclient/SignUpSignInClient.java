package signupsigninclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * class responsible for starting the application
 * 
 * @author Aritz Arrieta, Mikel Matilla, Daniel Brizuela
 */
public class SignUpSignInClient extends Application{

    /**
     * Main class, start the application 
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/LogOut.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
}
