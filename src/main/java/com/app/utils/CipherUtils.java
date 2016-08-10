package com.app.utils;

import com.app.reusable.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


import static javax.crypto.Cipher.getInstance;

/**
 * Created by Oscar Garcia on 8/4/2016.
 */
public class CipherUtils {
    private final static Logger logger = LoggerFactory.getLogger(Component.class);
    private Cipher cipher = null;
    private static SecretKeySpec key=null;

    public String encrypt(String value)  {
        String returnStr=null;
        setKey();

        try {
            cipher = getInstance("Blowfish");
        } catch (NoSuchAlgorithmException e) {
            logger.info("Context", Arrays.toString(e.getStackTrace()));
        } catch (NoSuchPaddingException e) {
            logger.info("Context", Arrays.toString(e.getStackTrace()));
        }

        //initialize for encrypting
        try {
            byte[] encryptedVal = cipher.doFinal(value.getBytes());
            returnStr = Arrays.toString(encryptedVal);
        } catch (IllegalBlockSizeException e) {
            logger.info("Context", Arrays.toString(e.getStackTrace()));
        } catch (BadPaddingException e) {
            logger.error(e.getMessage());
        }
        return  returnStr;

    }

    public String decrypt(byte[] encryptedStr){
        String returnStr=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE,getKey());
            byte[] decrypted = cipher.doFinal(encryptedStr);
            returnStr = new String(decrypted);
        } catch (InvalidKeyException e) {
            logger.info("Context", Arrays.toString(e.getStackTrace()));
        } catch (BadPaddingException e) {
            logger.info("Context", Arrays.toString(e.getStackTrace()));
        } catch (IllegalBlockSizeException e) {
            logger.info("Context", Arrays.toString(e.getStackTrace()));
        }
        return returnStr;
    }

    protected static SecretKeySpec getKey(){
        return key;
    }

    protected static void setKey(){
        key = new SecretKeySpec("OG4RKP$".getBytes(),"Blowfish");
    }

}
