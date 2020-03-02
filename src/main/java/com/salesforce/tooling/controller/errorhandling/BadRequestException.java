package com.salesforce.tooling.controller.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Runtime wrapper for exception handling.
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 3135913252336393690L;

    /**
     * Constructor.
     *
     * @param message
     *            the error message.
     */
    public BadRequestException(final String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message
     *            the error message.
     * @param cause
     *            the cause.
     */
    public BadRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
