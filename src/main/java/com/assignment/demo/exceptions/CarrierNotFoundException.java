package com.assignment.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not Found")
public class CarrierNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -2398602220381643393L;
}