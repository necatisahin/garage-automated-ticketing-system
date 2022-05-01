package garage.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        log.error("GarageAutomatedTicketingSystemRequestException", ex);

        HttpErrorDTO httpError = HttpErrorDTO.builder()
                .message(ex.getMessage())
                .build();

        if (ex instanceof GarageAutomatedTicketingSystemRequestException) {
            GarageAutomatedTicketingSystemRequestException businessException = (GarageAutomatedTicketingSystemRequestException) ex;
            httpError.setCode(businessException.getErrorConstants().getCode());
            httpError.setMessage(businessException.getErrorConstants().getMessage());
            return handleExceptionInternal(ex, httpError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        }
        return handleExceptionInternal(ex, httpError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
