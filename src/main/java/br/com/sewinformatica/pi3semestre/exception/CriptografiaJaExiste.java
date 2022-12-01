package br.com.sewinformatica.pi3semestre.exception;

import java.io.Serial;

public class CriptografiaJaExiste extends Exception {

    /**
     * Metodo para verificar se a criptografia ja existe
     * @author Kevin
     * @param message String - mensagem de erro
     */
    public CriptografiaJaExiste(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
