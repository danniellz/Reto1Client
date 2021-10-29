package signupsigninclient.logic;

import exceptions.ConnectionException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import message.Accion;
import message.Message;
import signable.Signable;
import user.User;

/**
 * Class that implements the Signable Interface to request a SignIn or SingUp and connect with a server project
 * 
 * @author Daniel Brizuela
 * @version 1.0
 */
public class SignableImplement implements Signable{
    Alert alert;
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
           Message msg = new Message();
           msg.setUser(user);
           msg.setAccion(Accion.SIGNIN);
           
           serverConnection(msg);
           
        }catch(ConnectionException ex){
            LOG.log(Level.SEVERE, "An error occurred in SignIn process", ex);
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("SignUp Error");
            alert.setContentText("An error has occurred trying to Sign In");
            alert.showAndWait();
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
            Message msg = new Message();
            msg.setUser(user);
            msg.setAccion(Accion.SIGNUP);
            
            serverConnection(msg);
        }catch(ConnectionException ex){
            LOG.log(Level.SEVERE, "An error occurred in SignUp process", ex);
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("SignUp Error");
            alert.setContentText("An error has occurred trying to Sign Up");
            alert.showAndWait();
        }  
    }

    /**
     * Connect with the server project sending a user object and the type of request (SignIn or SignUp)
     * 
     * @param message the message class contains the user and the request type
     */
    public void serverConnection(Message message) throws ConnectionException{
        final int PORT = Integer.parseInt(ResourceBundle.getBundle("signupsigninclient.file/config").getString("PORT"));
        final String SERVER = ResourceBundle.getBundle("signupsigninclient.file/config").getString("SERVER");
        ObjectInputStream inO;
        ObjectOutputStream outO;
        try{
            LOG.info("Initializing Client...");
            Socket clientSc;
            //local host, data can be change in the configuration file (config.properties)
            try{
                clientSc = new Socket(SERVER, PORT);
                LOG.info("Client > Initialized");
                /*outO = new ObjectOutputStream(clientSc.getOutputStream()); //enviar mensaje
                inO = new ObjectInputStream(clientSc.getInputStream()); //recibir mensaje
                
                outO.writeObject(message);
                
                outO.close();*/
                clientSc.close();
            }catch(IOException ex){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Server Connection error");
                alert.setContentText("Server not available");
                alert.showAndWait();
                throw new exceptions.ConnectionException();
                
            }
             
        }catch(NumberFormatException ex){
           LOG.log(Level.SEVERE, "An error occurred in serverConnection", ex);
        }  
    }
    
}
