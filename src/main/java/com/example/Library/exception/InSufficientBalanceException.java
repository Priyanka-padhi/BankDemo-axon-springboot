package com.example.Library.exception;

import java.math.BigDecimal;
import java.util.UUID;

public class InSufficientBalanceException extends Throwable{

    public InSufficientBalanceException() {
        super("Insufficient Balance!!");
    }
}
