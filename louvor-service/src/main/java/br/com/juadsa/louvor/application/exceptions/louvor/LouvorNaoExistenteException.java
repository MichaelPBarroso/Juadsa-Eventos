package br.com.juadsa.louvor.application.exceptions.louvor;

import br.com.juadsa.louvor.application.exceptions.SystemBaseException;
import lombok.Getter;

@Getter
public class LouvorNaoExistenteException extends SystemBaseException {

    private final String code = "louvor.louvorNaoExistente";
    private final String message = "Louvor não existe";
    private final Integer httpStatus = 422;

}
