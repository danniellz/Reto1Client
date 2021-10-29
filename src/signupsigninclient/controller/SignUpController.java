/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninclient.controller;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.beans.EventHandler;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class the view SingUp
 *
 * @author Jonathan Viñan , Aritz Arrieta
 */
public class SignUpController {

    private static final int MAX_LENGHT = 50;
    private static final int MAX_LENGHT_USER = 25;
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
    private Label paswordErrorLbl;
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
     *
     * @param singUpStage
     */
    public void setStage(Stage singUpStage) {
        stage = singUpStage;
    }

    public void initStage(Parent root) {

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SING UP");
        stage.setResizable(false);
        //  stage.setOnShowing(this::handleWindowShowing);
        charlimit();
        fullNameTxt.focusedProperty().addListener(this::focusLostEspChar);
        emailTxt.focusedProperty().addListener(this::domainControl);
        stage.show();
    }

    /**
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
            LOGGER.info("focus lost of  fullNameTxt");
            if (matcher.find()) {
                System.out.println("INCUMPLE" + matcher.find());
                LOGGER.info("SI NO encuentra");
                fullNameErrorLbl.setText(" Numbers or special characters are not allowed ");
                fullNameErrorLbl.setStyle("-fx-border-color: #DC143C;");

            } else {
                LOGGER.info("SI encuentra");
                System.out.println("CUMLE" + matcher.find());
                fullNameErrorLbl.setText(" ");
                fullNameErrorLbl.setStyle("-fx-border-color: WHITE;");

            }
        } else if (newValue) {
            LOGGER.info("Focus gained on fullNameTxt");
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
             LOGGER.info("Focus Lost on fullNameTxt");
            List<String> validDomains = Arrays.asList("gmail.com", "yahoo.com", "hotmail.com","gmail.eus");
            if (!(validDomains.contains(emailTxt.getText().substring(emailTxt.getText().indexOf("@") + 1)))) {
                emailErrorLbl.setText("ERROR domain not valid");
                emailErrorLbl.setStyle("-fx-border-color: #DC143C;");
            } else {
                emailErrorLbl.setText(" ");
                emailErrorLbl.setStyle("-fx-border-color: WHITE;");
            }
        } else if (newValue) {
            LOGGER.info("Focus gained on fullNameTxt");
        }
    }

}
