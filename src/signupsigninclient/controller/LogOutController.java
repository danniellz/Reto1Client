package signupsigninclient.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
     * Set the log out stage
     *
     * @param logOutStage Log out stage value
     */
    public void setStage(Stage logOutStage) {
        stage = logOutStage;
    }

    /**
     * Set the greeting with received user data
     *
     * @param user Signed in user
     */
    public void initData(User user) {
        String fullName;
        fullName = user.getFullName();
        messageLbl.setText("Hello " + fullName + ", you have succesfully logged in!!");
    }

    /**
     * Initialize window
     *
     * @param root Contains the FXML
     */
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
     * Calling this method will close the application
     *
     * @param closeEvent A window event
     */
    public void handleCloseRequest(WindowEvent closeEvent) {
        try {
            LOG.info("Confirm Closing");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("¿Are you sure you want to exit?");
            alert.setTitle("Exit");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                LOG.info("Closing...");
                Platform.exit();
            } else {
                LOG.info("Closing Canceled");
                //Cancel the event process
                closeEvent.consume();
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Close request error", e);
        }
    }

    /**
     * Calling this method will close the application
     *
     * @param closeEvent Close action event
     */
    public void handleExit(ActionEvent closeEvent) {
        try {
            LOG.info("Closing...");
            Platform.exit();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Close request error", e);
        }
    }

    /**
     * Calling this method will log out
     *
     * @param logOutEvent Log Out action event
     */
    public void handleLogOut(ActionEvent logOutEvent) {
        try {
            LOG.info("Logging out...");

            startSignInWindow(stage);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Log Out error", ex);
        }
    }

    /**
     * Calling this method will open the sign in window
     *
     * @param primaryStage stage object (window)
     * @throws IOException Throws an error if the SignUp window fails to open
     */
    private void startSignInWindow(Stage primaryStage) throws IOException {
        try {
            LOG.info("Starting SignIn Window...");
            //Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SignIn.fxml"));
            Parent root = (Parent) loader.load();
            //Get controller
            SignInController signinController = ((SignInController) loader.getController());
            //Set the stage
            signinController.setStage(primaryStage);
            //initialize the window
            signinController.initStage(root);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error Starting SignUp Window", ex);
        }
    }
}
