package Toolbox;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;


public class Encryption {
    private static String user = "testuser";
    private static String password = "1234";



    private static final int iterations = 1000; //more iterations == safer
    private static final int saltLen = 32;
    private static final int desiredKeyLen = 256;



    public Encryption(){ }
    public static String getSaltedHash(String password) throws Exception {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        // store the salt with the password
        return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
    }


    //checks if password fits to a salted and hased password
    public static boolean checkIfPasswordIsRight(String password, String stored) throws Exception{
        String[] saltAndHash = stored.split("\\$");
        if (saltAndHash.length != 2) {
            throw new IllegalStateException(
                    "The stored password must have the form 'salt$hash'");
        }
        String hashOfInput = hash(password, Base64.decodeBase64(saltAndHash[0]));
        return hashOfInput.equals(saltAndHash[1]);
    }

    private static String hash(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");

        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
                password.toCharArray(), salt, iterations, desiredKeyLen));
        return Base64.encodeBase64String(key.getEncoded());
    }
    /*
    public static void main(String[] args) throws Exception {

        System.out.println("Vorher " + password);
        String tmp = getSaltedHash(password);
        System.out.println("Hashed Pw: " + tmp);
        System.out.println("Test: " + checkIfPasswordIsRight(password, tmp));
    }
    */
}
