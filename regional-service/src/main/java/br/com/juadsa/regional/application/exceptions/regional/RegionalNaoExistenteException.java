package br.com.juadsa.regional.application.exceptions.regional;

import br.com.juadsa.regional.application.exceptions.SystemBaseException;
import lombok.Getter;

@Getter
public class RegionalNaoExistenteException extends SystemBaseException {

    private final String code = "regional.regionalNaoExiste";
    private final String message = "Regional não existe";
    private final Integer httpStatus = 422;

}
