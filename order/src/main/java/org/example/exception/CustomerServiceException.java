package org.example.exception;

import org.springframework.web.client.RestClientException;

public class CustomerServiceException extends Throwable {
    public CustomerServiceException(String s, RestClientException e) {
        super(s,e);
    }

    public CustomerServiceException(String s) {
        super(s);
    }
}
