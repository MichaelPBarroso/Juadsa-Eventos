package br.com.juadsa.regional.application.exceptions.regional;

import br.com.juadsa.regional.application.exceptions.SystemBaseException;
import lombok.Getter;

@Getter
public class RegionalSiglaExistenteException extends SystemBaseException {

    private final String code = "regional.regionalSiglaJaExiste";
    private final String message = "Regional com a sigla já existe";
    private final Integer httpStatus = 422;

}
