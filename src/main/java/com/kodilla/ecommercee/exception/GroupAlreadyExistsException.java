package com.kodilla.ecommercee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GroupAlreadyExistsException extends ResponseStatusException {
    public GroupAlreadyExistsException(){ super( HttpStatus.valueOf("Group already exists") ); }
}
