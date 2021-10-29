/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninclient.controller;

import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class the view SingUp
 *
 * @author Jonathan Viñan
 */
public class SignUpController {

    private static final int LESS_LENGHT = 6;
    private static final int MAX_LENGHT = 8;
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
        registerBtn.setDisable(true);
        //  userTxt.textProperty().addListener(this::limitUserTextField);

        passwordTxt.focusedProperty().addListener(this::focusChanged);
        // passwordTxt.textProperty().addListener(this::checkSixCharacterPasswordTextField);
        //passwordTxt.textProperty().addListener(this::limitPasswordTextField);
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
            passwordTxt.textProperty().addListener(this::limitPasswordTextField);

        } else if (oldValue) {

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

    private void checkSixCharacterPasswordTextField(ObservableValue obsevable, String oldValue, String newValue) {
        if (passwordTxt.getText().length() < LESS_LENGHT) {
            String password = passwordTxt.getText();
            passwordTxt.setText(password);
            passwordErrorLbl.setText("Minimum of 6 characters required");
            passwordErrorLbl.setTextFill(Color.web("#ff0000"));
            // passwordTxt.focusedProperty().addListener(this::focusChanged);
        } else {
            passwordErrorLbl.setText("");
        }
    }

    private void limitPasswordTextField(ObservableValue obsevable, String oldValue, String newValue) {

        if (passwordTxt.getText().length() > MAX_LENGHT) {

            String password = passwordTxt.getText().substring(0, MAX_LENGHT);
            passwordTxt.setText(password);

            passwordErrorLbl.setText("Password must be less than 50 character");
            passwordErrorLbl.setTextFill(Color.web("#ff0000"));
        }
        if (MAX_LENGHT < passwordTxt.getText().length()) {
            passwordErrorLbl.setText("");
        }

    }

}
