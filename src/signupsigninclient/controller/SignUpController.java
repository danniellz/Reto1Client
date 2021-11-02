/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninclient.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.Hyperlink;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class the view SingUp
 *
 * @author Jonathan Viñan
 */
public class SignUpController {

    private static final int LESS_LENGHT = 6;
    private static final int MAX_LENGHT = 25;
    public static final Pattern VALIDEMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALIDPASS = Pattern.compile("^[a-zA-Z0-9]+$");

    private Stage stage;

    //PANEL
    @FXML
    private Pane signUpPanel;

    //LABEL
    @FXML
    private Label signUpLbl;
    @FXML
    private Label userLbl;
    @FXML
    private Label fullNameLbl;
    @FXML
    private Label emailLbl;
    @FXML
    private Label passwordLbl;
    @FXML
    private Label repeatPasswordLbl;

    //LABEL THE ERROR
    @FXML
    private Label userErrorLbl;
    @FXML
    private Label fullNameErrorLbl;
    @FXML
    private Label emailErrorLbl;
    @FXML
    private Label passwordErrorLbl;
    @FXML
    private Label repeatPasswordErrorLbl;

    //HIPERLINK
    @FXML
    private Hyperlink signInHl;

    //TEXTFIELDS
    @FXML
    private TextField userTxt;
    @FXML
    private TextField fullNameTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField passwordTxt;
    @FXML
    private TextField repeatPasswordTxt;

    //BUTTON
    @FXML
    private Button registerBtn;

    //LOGGER
    private static final Logger LOG = Logger.getLogger(SignUpController.class.getName());

    /**
     * Defines which view is going to show up when the application executes.
     *
     * @param stageSignUp the view that will show from the main application.
     */
    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    /**
     * Initializes the view.
     *
     * @param root loads all the nodes that descend from root.
     */
    public void initStage(Parent root) {
        LOG.info("Initializing stage...");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SING UP");
        stage.setResizable(false);
        registerBtn.setDisable(true);

        disableButtonWhenTextFieldsEmpty();
        signInHl.addEventHandler(ActionEvent.ACTION, this::clickHyperlink);
        registerBtn.setOnAction(this::registerValidation);
        passwordTxt.focusedProperty().addListener(this::focusChanged);
        repeatPasswordTxt.focusedProperty().addListener(this::focusChangeRepeatPassword);
        stage.show();
    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void focusChanged(ObservableValue observable, Boolean oldValue, Boolean newValue) {

        if (newValue) {
            LOG.info("el foco esta en campo password");
            passwordTxt.textProperty().addListener(this::limitPasswordTextField);

        } else if (oldValue) {
            LOG.info("el foco salio del campo password ");
            if (passwordTxt.getText().length() < LESS_LENGHT) {
                String password = passwordTxt.getText();
                passwordTxt.setText(password);
                passwordErrorLbl.setText("Minimum of 6 characters required");
                passwordErrorLbl.setTextFill(Color.web("#ff0000"));
            } else {
                passwordErrorLbl.setText("");
            }
        }
    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void focusChangeRepeatPassword(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        if (newValue) {
            LOG.info("el foco esta en campo repeatPassword");
            repeatPasswordTxt.textProperty().addListener(this::limitPasswordTextField);

        } else if (oldValue) {
            LOG.info("el foco salido del campo repeatPassword");
            if (!passwordTxt.getText().equals(repeatPasswordTxt.getText())) {
                repeatPasswordErrorLbl.setText("Passwords don't match");
                repeatPasswordErrorLbl.setTextFill(Color.web("#FF0000"));

            } else {
                repeatPasswordErrorLbl.setText("");
            }
        }
    }

    /**
     * Disables Sign Up button if there's at least one Text Field empty.
     *
     * @param observable observes the changes that happen in the Text Field.
     * @param oldValue the value that was in the Text Field before changes
     * happened.
     * @param newValue the value that is in the Text Field after changes
     * happenes.
     */
    private void limitPasswordTextField(ObservableValue obsevable, String oldValue, String newValue) {

        if (passwordTxt.getText().length() > MAX_LENGHT) {

            String password = passwordTxt.getText().substring(0, MAX_LENGHT);
            passwordTxt.setText(password);

            passwordErrorLbl.setText("Password must be less than 50 character");
            passwordErrorLbl.setTextFill(Color.web("#ff0000"));
        }
        if (MAX_LENGHT < passwordTxt.getText().length()) {
            LOG.info("Campo informado valido");
            passwordErrorLbl.setText("");
        }

    }

    /**
     * Limits the max number of characters of the "Repeat Password" Text Field.
     *
     * @param observable observes the changes that happen in the Text Field.
     * @param oldValue the value that was in the Text Field before changes
     * happened.
     * @param newValue the value that is in the Text Field after changes
     * happenes.
     */
    private void limitRepeatPasswordTextField(ObservableValue observable, String oldValue, String newValue) {

        if (repeatPasswordTxt.getText().length() > MAX_LENGHT) {
            String repPass = repeatPasswordTxt.getText().substring(0, MAX_LENGHT);
            repeatPasswordTxt.setText(repPass);
        }
    }

    /**
     * Checks if the two passwords are equal.
     *
     * @return "error" boolean if the two passwords don't match.
     */
    private boolean checkPasswordsEqual() {
        boolean error = false;

        if (passwordTxt.getText().equals(repeatPasswordTxt.getText())) {
            repeatPasswordErrorLbl.setText("Passwords don't match");
            repeatPasswordErrorLbl.setTextFill(Color.web("#FF0000"));

            error = true;
        }

        return error;
    }

    /**
     * Disables Register button if there's at least one Text Field empty.
     *
     * @param observable observes the changes that happen in the Text Field.
     * @param oldValue the value that was in the Text Field before changes
     * happened.
     * @param newValue the value that is in the Text Field after changes
     * happenes.
     */
    private void disableButtonWhenTextFieldsEmpty() {
        registerBtn.disableProperty().bind(
                userTxt.textProperty().isEmpty()
                        .or(fullNameTxt.textProperty().isEmpty())
                        .or(emailTxt.textProperty().isEmpty())
                        .or(passwordTxt.textProperty().isEmpty())
                        .or(repeatPasswordTxt.textProperty().isEmpty())
        );
    }

    /**
     * Executes action when Sign Up button pressed.
     *
     * @param event determines which event has happened.
     */
    private void registerValidation(ActionEvent event) {
        LOG.info("Click button register");
        boolean errorPassEqual = false;
        errorPassEqual = checkPasswordsEqual();

        if (!errorPassEqual) {
            //    openSignInWindow();
        } else if (errorPassEqual) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR CAMPOS");
            alert.setContentText("Los campos son invalidos");
            alert.showAndWait();
        }

    }

    /**
     * Es el metodo que abre la ventana de SignUp
     *
     * @param event Es el evento para cuando hace click en el boton SignUp
     * @return
     */
    private void clickHyperlink(ActionEvent HyperLinkPress) {
        LOG.info("click hyperlink");
        try {
            LOG.info("SignUp Hyper Link Pressed");
            startSignInWindow(stage);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "HyperLink Error", ex);
        }
    }

    private void startSignInWindow(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("signupsigninclient/view/SignIn.fxml"));
        Parent root = (Parent) loader.load();

       //  UISignInController controller = ((UISignInController) loader.getController());
        //controller.setStage(primaryStage);
        //7controller.initStage(root);
    }

    /**
     *
     * Mentodo para confirmar el cierre de la ventana SignIn
     *
     * @param e
     */
    @FXML
    public void cerrarProgramaSignIn(WindowEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Programa");
        alert.setHeaderText("Estas seguro de cerrar el programa");
        Optional<ButtonType> okButton = alert.showAndWait();
        if (okButton.isPresent() && okButton.get() == ButtonType.CANCEL) {
            e.consume();
        }
    }

    /**
     * Es el metodo que alerta para cerrar secion en logout
     *
     * @param event Es el evento para cuando hace click en el boton logOut
     */
    @FXML
    private void mostrarAlertConfirmation(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmacion");
        alert.setContentText("¿Deseas realmente confirmar?");
        alert.showAndWait();
    }

}
