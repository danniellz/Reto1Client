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
     * @return 
     */
    @Override
    public User signIn(User user) {
        try{
           LOG.log(Level.INFO, "Starting SignIn Process for {0}...", user.getLogin());
           Message msg = new Message();
           msg.setUser(user);
           msg.setAccion(Accion.SIGNIN);
           
           serverConnection(msg);
           
        }catch(ConnectionException ex){
            LOG.log(Level.SEVERE, "An error occurred in SignIn process", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignableImplement.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return user;
    }

    /**
     * Request a Sign Up
     * 
     * @param user the user object containing the user data
     */
    @Override
    public void signUp(User user) {
        try{
            LOG.log(Level.INFO, "Starting SignUp Process for {0}...", user.getLogin());
            Message msg = new Message();
            msg.setUser(user);
            msg.setAccion(Accion.SIGNUP);
            
            serverConnection(msg);
        }catch(ConnectionException ex){
            LOG.log(Level.SEVERE, "An error occurred in SignUp process", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignableImplement.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    /**
     * Connect with the server project sending a user object and the type of request (SignIn or SignUp)
     * 
     * @param message the message class contains the user and the request type
     * @throws exceptions.ConnectionException an error message shows if the connection failed
     * @throws java.lang.ClassNotFoundException
     */
    public void serverConnection(Message message) throws ConnectionException, ClassNotFoundException{
        //local host, data can be change in the configuration file (config.properties)
        final int PORT = Integer.parseInt(ResourceBundle.getBundle("signupsigninclient.file/config").getString("PORT"));
        final String SERVER = ResourceBundle.getBundle("signupsigninclient.file/config").getString("SERVER");
        ObjectInputStream inO;
        ObjectOutputStream outO;
        try{
            LOG.info("Initializing Client...");
            try{
                Socket clientSc = new Socket(SERVER, PORT);
                LOG.info("Client > Initialized");
                //Send message
                outO = new ObjectOutputStream(clientSc.getOutputStream());
                outO.writeObject(message);
                
                //Message sent log
                if(message.getAccion() == Accion.SIGNIN){
                    LOG.info("Client > SIGN IN REQUEST SENT");
                }
                if(message.getAccion() == Accion.SIGNUP){
                    LOG.info("Client > SIGN UP REQUEST SENT");
                }
                
                /*//Receive message
                inO = new ObjectInputStream(clientSc.getInputStream()); //Recibir mensaje
                message = (Message) inO.readObject();*/
                
                //Close
                outO.close();
                //inO.close();
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
