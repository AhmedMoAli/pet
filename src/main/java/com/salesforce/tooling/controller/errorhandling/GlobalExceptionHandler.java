package com.salesforce.tooling.controller.errorhandling;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.salesforce.tooling.dto.StandardErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle 500 internal server error for various exceptions (we assume that all stuff happens in the back-end is our
     * fault as we are not expecting any user input for this engineering tool PoC).
     *
     * @param request
     *            current request.
     * @param exception
     *            the exception.
     * @return a API {@link StandardErrorResponse}.
     */
    @ExceptionHandler({ BadRequestException.class, RuntimeException.class })
    public ResponseEntity<StandardErrorResponse> handleInternalError(final HttpServletRequest request,
        final Exception exception) {
        return new ResponseEntity<>(
            StandardErrorResponseFactory.create(request.getServletPath(), exception, HttpStatus.INTERNAL_SERVER_ERROR),
            this.jsonHttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected HttpHeaders jsonHttpHeaders() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

}