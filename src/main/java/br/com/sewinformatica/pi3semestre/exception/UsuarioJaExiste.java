package br.com.sewinformatica.pi3semestre.exception;

import java.io.Serial;

public class UsuarioJaExiste extends Exception {

    /**
     * Metodo para verificar no banco de dados de ja existe algum registro com mesmo nome de usuario
     * @author Kevin
     * @param message String - mensagem de erro
     */
    public UsuarioJaExiste(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
