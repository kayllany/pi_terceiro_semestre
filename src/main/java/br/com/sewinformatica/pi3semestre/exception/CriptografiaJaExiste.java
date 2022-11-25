package br.com.sewinformatica.pi3semestre.exception;

import java.io.Serial;

public class CriptografiaJaExiste extends Exception {
    public CriptografiaJaExiste(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
