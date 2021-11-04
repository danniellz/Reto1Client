/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninclient.controller;

import java.util.concurrent.TimeoutException;
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
    public void test00_start() {
        clickOn("#registerBtn");
        verifyThat("#signUpPanel", isVisible());
    }

    @Test
    public void test01_InitalState() {
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
    public void test02_RegisterIsEnabled() {
        clickOn("#userTxt");
        write("user");
        clickOn("#fullNameTxt");
        write("fullname");
        clickOn("#emailTxt");
        write("email");
        clickOn("#passwordTxt");
        write("password");
        clickOn("#repeatPasswordTxt");
        write("repeat password");
        verifyThat("#registerBtn", isEnabled());
        clickOn("#registerBtn");
        clearText();
    }

    public void clearText() {
        doubleClickOn("#userTxt");
        eraseText(1);
        doubleClickOn("#fullNameTxt");
        eraseText(1);
        doubleClickOn("#emailTxt");
        eraseText(1);
        doubleClickOn("#passwordTxt");
        eraseText(1);
        doubleClickOn("#repeatPasswordTxt");
        eraseText(1);

    }
}
