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
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import signupsigninclient.SignUpSignInClient;

/**
 *
 * @author JonY
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInControllerTest extends ApplicationTest {

    /**
     *
     * @throws TimeoutException
     */
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(SignUpSignInClient.class);
    }
    
    @Test
    public void testA_start() {
        verifyThat("#userTxt", hasText(""));
        verifyThat("#passwordTxt", hasText(""));
        verifyThat("#loginBtn", isEnabled());
    }
    
    @Test
    public void testB_VisibleWindowSignUp() {
        clickOn("#signUpHl");
        verifyThat("#signUpPanel", isVisible());
    }

    @Test
    public void testC_UserAndPassword50Characters() {
        clickOn("#signInHl");
        verifyThat("#signInPanel", isVisible());
        clickOn("#userTxt");
        for (int i = 0; i < 26; i++) {
            write("a");
        }
        eraseText(25);
        clickOn("#passwordTxt");
        for (int i = 0; i < 26; i++) {
            write("a");
        }
        eraseText(25);
        verifyThat("#userTxt", hasText(""));
        verifyThat("#passwordTxt", hasText(""));
        doubleClickOn("#userTxt");
        eraseText(1);
        doubleClickOn("#passwordTxt");
        eraseText(1);
    }

    @Test
    public void testD_UserAndPasswordError() {
        clickOn("#userTxt");
        write("Aloy");
        clickOn("#loginBtn");
        verifyThat("#errorLbl", isVisible());
        doubleClickOn("#userTxt");
        eraseText(1);

        clickOn("#passwordTxt");
        write("Aloy");
        clickOn("#loginBtn");
        verifyThat("#errorLbl", isVisible());
        doubleClickOn("#passwordTxt");
        eraseText(1);
    }

    @Test
    public void testE_UserNotExist() {
        clickOn("#userTxt");
        write("Aloy");
        clickOn("#passwordTxt");
        write("Aloy12");
        clickOn("#loginBtn");
        verifyThat("#errorLbl", isVisible());
        doubleClickOn("#userTxt");
        eraseText(1);
        doubleClickOn("#passwordTxt");
        eraseText(1);
    }

    @Test
    public void testF_LoginUserConnection() {
        clickOn("#userTxt");
        write("pepeUser");
        clickOn("#passwordTxt");
        write("123456");
        clickOn("#loginBtn");
        verifyThat("#logOutPanel", isVisible());
    }

}
