package br.com.juadsa.regional.infrastructure.api.json;

import br.com.juadsa.regional.application.exceptions.SystemBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionJsonNew {

    private final String code;
    private final String message;

    public ExceptionJsonNew(final SystemBaseException baseException){
        this.code = baseException.getCode();
        this.message = baseException.getMessage();
    }

}