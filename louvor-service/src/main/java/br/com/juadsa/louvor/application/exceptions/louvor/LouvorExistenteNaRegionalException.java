package br.com.juadsa.louvor.application.exceptions.louvor;

import br.com.juadsa.louvor.application.exceptions.SystemBaseException;
import lombok.Getter;

@Getter
public class LouvorExistenteNaRegionalException extends SystemBaseException {

    private final String code = "louvor.louvorJaExiste";
    private final String message = "Louvor Já Está Cadastrado";
    private final Integer httpStatus = 422;

}
