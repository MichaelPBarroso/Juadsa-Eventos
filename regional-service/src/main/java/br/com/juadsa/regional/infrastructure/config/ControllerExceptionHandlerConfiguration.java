package br.com.juadsa.regional.infrastructure.config;

import br.com.juadsa.regional.application.exceptions.SystemBaseException;
import br.com.juadsa.regional.infrastructure.api.json.ExceptionSystemJson;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionHandlerConfiguration {

    private static final String EXCEPTION_HANDLER_ERROR_TO_RESOLVE_EXCEPTION = "exceptionHandler.errorToResolveException";
    private static final String ARGUMENT_NOT_VALID = "argumentNotValid";
    private static final String ERROR_TO_RESOLVE_EXCEPTION_HANDLER = "Error to resolve exception handler";

    @ExceptionHandler({ SystemBaseException.class})
    @ResponseBody
    public ResponseEntity<ExceptionSystemJson> sasException(final SystemBaseException e, final HttpServletResponse response) {
        final ExceptionSystemJson exceptionJson = new ExceptionSystemJson(e);
        return new ResponseEntity<>(exceptionJson, new HttpHeaders(), e.getHttpStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ExceptionSystemJson genericError(final Throwable e) {
        log.error(e.getMessage(), e);
        return new ExceptionSystemJson(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ExceptionSystemJson validationException(final MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error(methodArgumentNotValidException.getMessage(), methodArgumentNotValidException);
        try {
            final StringBuilder errors = new StringBuilder();

            methodArgumentNotValidException
                    .getBindingResult()
                    .getFieldErrors()
                    .forEach(field -> errors.append(field.getField() + ":" + field.getDefaultMessage()+";"));

            return new ExceptionSystemJson(ARGUMENT_NOT_VALID, errors.toString());

        } catch (Exception exception) {
            log.error(ERROR_TO_RESOLVE_EXCEPTION_HANDLER, exception);
            return new ExceptionSystemJson(EXCEPTION_HANDLER_ERROR_TO_RESOLVE_EXCEPTION, exception.getMessage());
        }
    }


}
