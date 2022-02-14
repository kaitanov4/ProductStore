package kz.kaitanov.setronica.controller.exceptionHandler;

import kz.kaitanov.setronica.config.exception.ProductNotFoundException;
import kz.kaitanov.setronica.model.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class GlobalRestControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalRestControllerExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseDto<Void> illegalArgumentException(Exception exception) {
        LOGGER.error("Application throw IllegalArgumentException - {}", exception.getMessage());
        return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), Boolean.FALSE);
    }

    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public ResponseDto<Void> authenticationException(Exception exception) {
        LOGGER.error("Application throw AuthenticationException - {}", exception.getMessage());
        return new ResponseDto<>(HttpStatus.UNAUTHORIZED.value(), exception.getMessage(), Boolean.FALSE);
    }

    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseDto<Void> productNotFoundException(Exception exception) {
        LOGGER.error("Application throw ProductNotFoundException - {}", exception.getMessage());
        return new ResponseDto<>(HttpStatus.NOT_FOUND.value(), exception.getMessage(), Boolean.FALSE);
    }

}