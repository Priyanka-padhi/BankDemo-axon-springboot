package com.example.Library.event;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;


//Event is serving as a DTO
@Data
public class AccountCreatedEvent {

    private final UUID id;
    private final BigDecimal initialBalance;
    private final String accountHolderName;

}
