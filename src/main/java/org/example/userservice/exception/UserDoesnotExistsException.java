package org.example.userservice.exception;

public class UserDoesnotExistsException extends Exception{

     public UserDoesnotExistsException(String message){
        super(message);
    }
}
