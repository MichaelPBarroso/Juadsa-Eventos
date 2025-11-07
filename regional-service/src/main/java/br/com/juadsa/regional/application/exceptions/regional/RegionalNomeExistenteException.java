package br.com.juadsa.regional.application.exceptions.regional;

import br.com.juadsa.regional.application.exceptions.SystemBaseException;
import lombok.Getter;

@Getter
public class RegionalNomeExistenteException extends SystemBaseException {

    private final String code = "regional.regionalNomeJaExiste";
    private final String message = "Regional com o nome já existe";
    private final Integer httpStatus = 422;

}
