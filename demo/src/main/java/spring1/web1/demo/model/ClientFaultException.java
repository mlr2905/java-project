package spring1.web1.demo.model;

import org.springframework.http.HttpStatus;

public class ClientFaultException extends Exception {

    public ClientFaultException(String message) {
        super(message);
    }

    HttpStatus httpStatus;
}
