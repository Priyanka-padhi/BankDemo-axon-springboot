package com.example.Library.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;

//For the CreateAccountCommand we need to create AccountCreatedEvent that will be used to say that a command has been received.
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateAccountCommand {

    @TargetAggregateIdentifier
    private UUID accountId;

    private BigDecimal initialBalance;
    private String accountHolderName;
}
