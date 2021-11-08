/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninclient.controller;

import java.util.concurrent.TimeoutException;
import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.CONTROL;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import signupsigninclient.SignUpSignInClient;

/**
 *
 * @author JonY
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpControllerTest extends ApplicationTest {

    public SignUpControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(SignUpSignInClient.class);

    }

    @Test
    public void test0A_start() {
        
        doubleClickOn("#signUpHl");
        verifyThat("#signUpPanel", isVisible());
    }

    @Test
    public void test0B_InitalState() {
        clickOn("#userTxt");
        verifyThat("#userTxt", isFocused());
        verifyThat("#userTxt", hasText(""));
        verifyThat("#fullNameTxt", hasText(""));
        verifyThat("#emailTxt", hasText(""));
        verifyThat("#passwordTxt", hasText(""));
        verifyThat("#repeatPasswordTxt", hasText(""));
        verifyThat("#registerBtn", isDisabled());
        verifyThat("#signInHl", isEnabled());
    }

    @Test
    public void test0C_RegisterIsEnabled() {
      
        clickOn("#userTxt");
        write("123456789123456789123456789123456789");
        clickOn("#fullNameTxt");
        write("fullname23");
        clickOn("#emailTxt");
        write("email@gmail.om");
        clickOn("#passwordTxt");
        write("password");
        clickOn("#repeatPasswordTxt");
        write("password");
        verifyThat("#registerBtn", isDisabled());
        clickOn("#registerBtn");
        clearText();
    }
   
    //solo se puede una vez por login
   /* @Test
    public void test0Z_RegisterOk(){
        clickOn("#userTxt");
        write("Jamiro");
        clickOn("#fullNameTxt");
        write("Jamiro Reamie");
        clickOn("#emailTxt");
        write("jamiro@gmail.com");
        clickOn("#passwordTxt");
        write("password");
        clickOn("#repeatPasswordTxt");
        write("password");
        verifyThat("#registerBtn", isEnabled());
        clickOn("#registerBtn");
        clearText();
    
    }*/
    public void clearText() {
        doubleClickOn("#userTxt");
        this.push(CONTROL,A);
        eraseText(1);
        doubleClickOn("#fullNameTxt");
        this.push(CONTROL,A);
        eraseText(1);
        doubleClickOn("#emailTxt");
        this.push(CONTROL,A);
        eraseText(1);
        doubleClickOn("#passwordTxt");
        this.push(CONTROL,A);
        eraseText(1);
        doubleClickOn("#repeatPasswordTxt");
        this.push(CONTROL,A);
        eraseText(1);

    }
}
