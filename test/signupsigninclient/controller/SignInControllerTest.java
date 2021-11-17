package signupsigninclient.controller;

import java.util.concurrent.TimeoutException;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import org.testfx.matcher.control.LabeledMatchers;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import signupsigninclient.SignUpSignInClient;

/**
 * Test for the SignIn Window IT
 * 
 * @author Jonathan Viñan
 * @version 1.0
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
    public void testC_UserAndPassword25Characters() {
        clickOn("#signInHl");
        verifyThat("#signInPanel", isVisible());
        clickOn("#userTxt");
        for (int i = 0; i < 26; i++) {
            write("a");
        }
        verifyThat("#errorLbl", isEnabled());
        clickOn("#passwordTxt");
        for (int i = 0; i < 26; i++) {
            write("a");
        }
        doubleClickOn("#userTxt");
        eraseText(1);
        doubleClickOn("#passwordTxt");
        eraseText(1);
    }

    @Test
    public void testD_EmptyFields() {
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
        
        clickOn("#loginBtn");
        
    }

    @Test
    public void testE_PasswordError() {
        clickOn("#userTxt");
        write("pepeUser");
        clickOn("#passwordTxt");
        write("Aloy12");
        clickOn("#loginBtn");
        verifyThat("#errorLbl", LabeledMatchers.hasText("Incorrect Password"));
        doubleClickOn("#userTxt");
        eraseText(1);
        doubleClickOn("#passwordTxt");
        eraseText(1);
    }

    @Test
    public void testF_UserNotExist() {
        clickOn("#userTxt");
        write("Aloy");
        clickOn("#passwordTxt");
        write("aloy10");
        clickOn("#loginBtn");
        verifyThat("#errorLbl", LabeledMatchers.hasText("Incorrect Username"));
        doubleClickOn("#userTxt");
        eraseText(1);
        doubleClickOn("#passwordTxt");
        eraseText(1);
    }

    @Test
    public void testG_LoginUserConnection() {
        clickOn("#userTxt");
        write("pepeUser");
        clickOn("#passwordTxt");
        write("123456");
        clickOn("#loginBtn");
        verifyThat("#logOutPanel", isVisible());
    }

    /**
     * Test if the is server off
     */
    @Ignore
    @Test
    public void testH_ErrorConection() {
        clickOn("#userTxt");
        write("pepeUser");
        clickOn("#passwordTxt");
        write("1234");
        clickOn("#loginBtn");
        verifyThat(".alert", NodeMatchers.isVisible());
    }

}
