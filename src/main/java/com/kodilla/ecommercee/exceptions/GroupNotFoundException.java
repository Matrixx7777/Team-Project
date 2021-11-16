package com.kodilla.ecommercee.exceptions;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException() { super("Group not found") ; }
}
