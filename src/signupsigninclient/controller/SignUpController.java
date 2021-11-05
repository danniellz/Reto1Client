/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninclient.controller;

import exceptions.ConnectionException;
import exceptions.DatabaseNotFoundException;
import exceptions.IncorrectPasswordException;
import exceptions.InvalidEmailFormatException;
import exceptions.UserAlreadyExistException;
import exceptions.UserNotFoundException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.beans.EventHandler;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
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
import signable.Signable;
import signupsigninclient.logic.SignableFactory;
import user.User;

/**
 * FXML Controller class the view SingUp
 *
 * @author Jonathan Viñan , Aritz Arrieta
 */
public class SignUpController {
    private static final Logger LOG = Logger.getLogger(SignUpController.class.getName());

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

    /**
     * Defines which view is going to show up when the application executes.
     *

     * @param primaryStage the view that will show from the main application.
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

        //  stage.setOnShowing(this::handleWindowShowing);
        charlimit();
        fullNameTxt.focusedProperty().addListener(this::focusLostEspChar);
        emailTxt.focusedProperty().addListener(this::domainControl);

        stage.setOnCloseRequest(this::closeProgramSingUp);
        registerBtn.setDisable(true);

        disableButtonWhenTextFieldsEmpty();
        signInHl.addEventHandler(ActionEvent.ACTION, this::clickHyperlink);
        registerBtn.setOnAction(this::registerValidation);
        passwordTxt.focusedProperty().addListener(this::focusChanged);
        repeatPasswordTxt.focusedProperty().addListener(this::focusChangeRepeatPassword);


        stage.show();
    }

    /**
      *+*************Start ARITZ****************************
     * this method puts a limit in the textLabels (25 limit except email
     * textLabel)
     */
    private void charlimit() {
        userTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (userTxt.getText().length() > 25) {
                    userTxt.deletePreviousChar();
                    userTxt.setStyle("-fx-border-color: #DC143C	; -fx-border-width: 1.5px ;");
                    userErrorLbl.setText("25 characters limit reached");
                    userErrorLbl.setStyle("-fx-text-fill: #DC143C");
                } else {
                    userTxt.setStyle("-fx-border-color: White;");
                    userErrorLbl.setText(" ");
                    userErrorLbl.setStyle(" ");
                }
            }

        });
        fullNameTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (fullNameTxt.getText().length() > 25) {
                    fullNameTxt.deletePreviousChar();
                    fullNameTxt.setStyle("-fx-border-color: #DC143C; -fx-border-width: 1.5px ;");
                    fullNameErrorLbl.setText("25 characters limit reached");
                    fullNameErrorLbl.setStyle("-fx-text-fill: #DC143C");
                } else {
                    emailErrorLbl.setText(" ");
                    emailErrorLbl.setStyle(" ");
                }
            }
        });
        emailTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (emailTxt.getText().length() > 50) {
                    emailTxt.deletePreviousChar();
                    emailTxt.setStyle("-fx-border-color: #DC143C; -fx-border-width: 1.5px ;");
                    emailErrorLbl.setText("50 characters limit reached");
                    emailErrorLbl.setStyle("-fx-text-fill: #DC143C");
                } else {
                    emailErrorLbl.setText(" ");
                    emailErrorLbl.setStyle(" ");
                }

            }
        });

    }

    /**
     * this method is a focus lost action, that test if the full name don´t puts
     * any especial characteres
     *
     * @param observable is the field that have the focus action
     * @param oldValue is a boolean to know where was the focus
     * @param newValue is a boolean to know where is the focus
     */
    public void focusLostEspChar(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

        String comp = "[^A-Za-zÀ-ȕ\\s]";

        Pattern espChar = Pattern.compile(comp);
        Matcher matcher = espChar.matcher(fullNameTxt.getText().trim());
        //este codigo solo se ejecuta cuando se pierde el Foco
        if (oldValue) {
            LOG.info("focus lost of  fullNameTxt");
            if (matcher.find()) {
                System.out.println("INCUMPLE" + matcher.find());
                LOG.info("SI NO encuentra");
                fullNameErrorLbl.setText(" Numbers or special characters are not allowed ");
                fullNameErrorLbl.setStyle("-fx-border-color: #DC143C;");

            } else {
                LOG.info("SI encuentra");
                System.out.println("CUMLE" + matcher.find());
                fullNameErrorLbl.setText(" ");
                fullNameErrorLbl.setStyle("-fx-border-color: WHITE;");

            }
        } else if (newValue) {
            LOG.info("Focus gained on fullNameTxt");
        }
    }
    /**
     * domain control checks from "@" if domain of email is valid 
     * 
     * @param observable is the field that have the focus action
     * @param oldValue is a boolean to know where was the focus
     * @param newValue is a boolean to know where is the focus
     */
    private void domainControl(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (oldValue) {
             LOG.info("Focus Lost on fullNameTxt");
            List<String> validDomains = Arrays.asList("gmail.com", "yahoo.com", "hotmail.com","gmail.eus");
            if (!(validDomains.contains(emailTxt.getText().substring(emailTxt.getText().indexOf("@") + 1)))) {
                emailErrorLbl.setText("ERROR domain not valid");
                emailErrorLbl.setStyle("-fx-border-color: #DC143C;");
            } else {
                emailErrorLbl.setText(" ");
                emailErrorLbl.setStyle("-fx-border-color: WHITE;");
            }
        } else if (newValue) {
            LOG.info("Focus gained on fullNameTxt");
        }
    }
// final parte aritz
     /**
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void focusChanged(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

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
        LOG.info("comprobando contraseñas si son iguales");
        boolean error = false;

        if (!passwordTxt.getText().equals(repeatPasswordTxt.getText())) {
           LOG.info("error de contraseñas");
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
    private void registerValidation(ActionEvent event){
        LOG.info("Click button register");
        boolean errorPassEqual = false;
        errorPassEqual = checkPasswordsEqual();

        if (errorPassEqual) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("ERROR PASSWORS IN CORRECT");
            alert.showAndWait();
              
        }else{
             
             User user = new User();
             user.setLogin(userTxt.getText());
             user.setEmail(emailTxt.getText());
             user.setFullName(fullNameTxt.getText());
             user.setPassword(passwordTxt.getText());
            try { 
            Signable sign = new SignableFactory().getSignable();
            sign.signUp(user);
            
             openSignInWindow();
            } catch (UserAlreadyExistException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UserNotFoundException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DatabaseNotFoundException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConnectionException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IncorrectPasswordException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidEmailFormatException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             
        }

    }

    /**
     * Es el metodo que abre la ventana de SignUp
     *
     * @param event Es el evento para cuando hace click en el boton SignUp
     * @return
     */
    private void clickHyperlink(ActionEvent HyperLinkPress) {
        try {
            LOG.info("SignUp Hyper Link Pressed");
            startSignInWindow(stage);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "HyperLink Error", ex);
        }
    }

    private void startSignInWindow(Stage primaryStage) throws IOException {
        try{
            LOG.info("Starting SignIn window...");
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
            LOG.log(Level.SEVERE, "Error Starting SignIn window", ex);
        }
    }

    /**
     *
     * Mentodo para confirmar el cierre de la ventana SignIn
     *
     * @param e
     */
    @FXML
    public void closeProgramSingUp(WindowEvent e) {
        LOG.info("close program");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Program");
        alert.setHeaderText("Do you really want to Exit?");
        Optional<ButtonType> resp = alert.showAndWait();
        if (resp.get() == ButtonType.OK) {
            Platform.exit();
        }else{
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

    private void openSignInWindow() {
        try{
                    LOG.info("Starting LogIn Window...");
                    //Load the FXML file
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SignIn.fxml"));
                    Parent root = (Parent)loader.load();
                    //Get controller
                    SignInController signIn = ((SignInController)loader.getController()); 
                    //Set the stage
                    signIn.setStage(stage);
                    //initialize the window
                    signIn.initStage(root);
                  
                }catch(IOException ex){
                    LOG.log(Level.SEVERE, "Error Starting LogOut Window", ex);
                }
    }

}
