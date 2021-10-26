/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninclient.controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.beans.EventHandler;
import java.io.IOException;
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
 * @author Jonathan Viñan
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
        charlimit();
        registerValidation();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SING UP");
        stage.setResizable(false);
        //  stage.setOnShowing(this::handleWindowShowing);

        stage.show();
    }

    /* private void handleWindowShowing(WindowEvent event) {

    }*/
    /**
     * this method puts a limit in the textLabels (25 limit except email textLabel)
     */
    private void charlimit() {
        userTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (userTxt.getText().length() > 25 ) {
                    userTxt.deletePreviousChar();
                    userTxt.setStyle("-fx-border-color: #DC143C	; -fx-border-width: 1.5px ;");
                } else {
                    userTxt.setStyle("-fx-border-color: White;");
                }
            }

        });
        fullNameTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (fullNameTxt.getText().length() > 25) {
                    fullNameTxt.deletePreviousChar();
                    fullNameTxt.setStyle("-fx-border-color: #DC143C; -fx-border-width: 1.5px ;");
                }
            }
        });
        emailTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (emailTxt.getText().length() > 50) {
                    emailTxt.deletePreviousChar();
                    emailTxt.setStyle("-fx-border-color: #DC143C; -fx-border-width: 1.5px ;");
                }
            }
        });

    }

    private void registerValidation() {
        Pattern espChar = Pattern.compile("[$&+,:;=?@#|'<>.-^*()%!0-9]");
         fullNameTxt.hoverProperty().addListener(new ChangeListener<Boolean>(){
             @Override
             public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                 Matcher matcher = espChar.matcher(fullNameTxt.getText());
                 Boolean est = matcher.matches();
                 if(est){
                     fullNameErrorLbl.setText(" Numbers or special characters are not allowed ");
                     fullNameErrorLbl.setStyle("-fx-border-color: #DC143C;");
                 } else{
                 fullNameErrorLbl.setText(" ");
                  fullNameErrorLbl.setStyle("-fx-border-color: WHITE;");
                 }
             }
         
         
         });
    }
}
