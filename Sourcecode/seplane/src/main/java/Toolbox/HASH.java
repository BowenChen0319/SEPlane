package Toolbox;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class HASH {

    private static byte[] salt={2};
    private static final int iterations = 1000; //more iterations == safer
    private static final int saltLen = 32;
    private static final int desiredKeyLen = 256;



    public HASH(){ }
    public static String getSaltedHash(String password) throws Exception {
//        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        // store the salt with the password
        return Base64.encodeBase64String(salt) + "$" + getResult(password);
    }


    //checks if password fits to a salted and hased password
//    public static boolean checkIfPasswordIsRight(String password, String stored) throws Exception{
//        String[] saltAndHash = stored.split("\\$");
//        if (saltAndHash.length != 2) {
//            throw new IllegalStateException(
//                    "The stored password must have the form 'salt$hash'");
//        }
//        String hashOfInput = getResult(password);
//        return hashOfInput.equals(saltAndHash[1]);
//    }

    public static String getResult(String password) throws Exception {
//        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);

//        if (password == null || password.length() == 0)
//            throw new IllegalArgumentException("Empty passwords are not supported.");

        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
                password.toCharArray(), salt, iterations, desiredKeyLen));
        return Base64.encodeBase64String(key.getEncoded());
    }

}
