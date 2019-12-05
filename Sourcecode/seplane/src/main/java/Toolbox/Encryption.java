package Toolbox;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;


public class Encryption {
    private static String user = "testuser";
    private static String password = "1234";



    private static final int iterations = 20*1000;
    private static final int saltLen = 32;
    private static final int desiredKeyLen = 256;


    public Encryption(){ }

    //Für passwörter
    public static String getSaltedHash(String password) throws Exception {
        byte[] salt = new byte[0];
        try {
            salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // store the salt with the password
        return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
    }

    /** Checks whether given plaintext password corresponds
     to a stored salted hash of the password. */
    public static boolean check(String password, String stored) {
        String[] saltAndHash = stored.split("\\$");
        if (saltAndHash.length != 2) {
            throw new IllegalStateException(
                    "The stored password must have the form 'salt$hash'");
        }
        String hashOfInput = null;
        try {
            hashOfInput = hash(password, Base64.decodeBase64(saltAndHash[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public static void main(String[] args) throws Exception {
        int i=0;
        System.out.println(new Encryption().zeichen[++i]);
    }

    //für nachrichten
    // hallo
    private final char[] zeichen = "abcdefghijklmnopqrstuvwxyz0123456789!?.,".toCharArray();
//
//    public int checkForCharPos(char ch, char[] inArray){
//        for(int i = 0; i<inArray.length;i++)
//        {
//            if(inArray[i] == ch)
//            {
//                return i;
//            }
//        }
//        return ;
//    }
//    public char getCharFromArray(char[] arr, int pos){
//        if(pos > arr.length)
//        {
//            return 'a';
//        }
//        for(int i =0; i<pos;i++)
//        {
//            return arr[i];
//        }
//        return 'a';
//    }

    public String caesarEncryption(String plaintext){
        char[] decrypted = plaintext.toCharArray();
        char[] encrypted = new char[plaintext.length()];

        for(int i=0; i<plaintext.length();i++)
        {
            if(decrypted[i] >= zeichen[0] && decrypted[i] <= zeichen[zeichen.length]){
              //  encrypted[i] = (decrypted[i] + );
            }

        }
        return Arrays.toString(encrypted);
    }

}
