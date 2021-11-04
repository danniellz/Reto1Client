package signupsigninclient.logic;

import signable.Signable;

/**
 * Class that returns an Interface Signable with the SignableImplement
 * 
 * @author Daniel Brizuela
 * @version 1.0
 */
public class SignableFactory {
    public Signable getSignable(){
        return new SignableImplement();
    }  
}
