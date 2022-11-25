package br.com.sewinformatica.pi3semestre.exception;

import java.io.Serial;

public class UsuarioJaExiste extends Exception {
    public UsuarioJaExiste(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
