package br.com.sewinformatica.pi3semestre.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

    /**
     * Metodo para criptografar senha
     * @author Kevin
     * @param senha String - senha a ser criptografada
     * @return String - senha criptografada
     * @throws NoSuchAlgorithmException
     */
    public static String md5(String senha) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, messageDigest.digest(senha.getBytes()));

        return hash.toString();
    }
}
