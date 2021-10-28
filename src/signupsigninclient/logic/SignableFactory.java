package signupsigninclient.logic;

import signable.Signable;

/**
 *
 * @author Daniel Brizuela
 * @version 1.0
 */
public class SignableFactory {
    public Signable getSignable(){
        return new SignableImplement();
    }  
}
