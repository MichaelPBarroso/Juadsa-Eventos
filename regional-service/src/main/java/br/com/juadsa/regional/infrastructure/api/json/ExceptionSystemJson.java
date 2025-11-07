package br.com.juadsa.regional.infrastructure.api.json;

import br.com.juadsa.regional.application.exceptions.SystemBaseException;
import lombok.Getter;

@Getter
public class ExceptionSystemJson extends ExceptionJson {

    public ExceptionSystemJson(String code, String message){
        setCode(code);
        setMessage(message);
    }

    public ExceptionSystemJson(final SystemBaseException baseException){
        setCode(baseException.getCode());
        setMessage(baseException.getMessage());
    }
}
