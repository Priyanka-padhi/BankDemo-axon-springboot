package com.example.Library.exception;

public class AccountNotFoundException extends Throwable{
    public AccountNotFoundException(){
        super("Cannot found Account!!");
    }
}
