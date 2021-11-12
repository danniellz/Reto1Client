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
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import signupsigninclient.SignUpSignInClient;

/**
 * Test class for LogOutController class
 * 
 * @author Mikel Matilla
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogOutControllerTest extends ApplicationTest {
    
    /**
     * Method to set up the class once before teh class
     * 
     * @throws TimeoutException Exception thrown when a blocking operation times out
     */
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(SignUpSignInClient.class);
    }
    
    /**
     * Method to test that correct sign in opens the log out window
     */
    @Test
    public void test00_signIn() {
        clickOn("#userTxt");
        write("pepeUser");
        clickOn("#passwordTxt");
        write("123456");
        clickOn("#loginBtn");
        verifyThat("#logOutPanel", isVisible());
    }
    
    /**
     * Method to test that click in log out returns to sign in window
     */
    @Test
    public void test01_logOut() {
        clickOn("#file");
        clickOn("#logOutItem");
        verifyThat("#signInPanel", isVisible());
    }
    
}