package signupsigninclient.logic;

import java.io.IOException;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import signable.Signable;
import user.User;

/**
 * Class that implements the Signable Interface to request a SignIn or SingUp and connect with a server project
 * 
 * @author Daniel Brizuela
 * @version 1.0
 */
public class SignableImplement implements Signable{
    //LOGGER
    private static final Logger LOG = Logger.getLogger(SignableImplement.class.getName());
    /**
     * Request a Sign In
     * 
     * @param user the user object containing the user data
     */
    @Override
    public void signIn(User user) {
        try{
            
        }catch(Exception ex){
            
        }  
    }

    /**
     * Request a Sign Up
     * 
     * @param user the user object containing the user data
     */
    @Override
    public void signUp(User user) {
        try{
            
        }catch(Exception ex){
            
        }  
    }

    public void serverConnection(){
        try{
            LOG.info("Initializing Client...");
            //local host, data can be change in the configuration file (config.properties)
            final int PORT = Integer.parseInt(ResourceBundle.getBundle("signupsigninclient.file/config").getString("PORT"));
            final String SERVER = ResourceBundle.getBundle("signupsigninclient.file/config").getString("SERVER");
            try{
                Socket clientSc = new Socket(SERVER, PORT);
                LOG.info("Client > Initialized");
                clientSc.close();
            }catch(IOException ex){
                LOG.log(Level.SEVERE,"Client > An error occurred trying to connect to the server");
            }
             
        }catch(NumberFormatException ex){
           LOG.log(Level.SEVERE, "An error occurred in serverConnection", ex);
        }  
    }
    
}
