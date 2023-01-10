package com.example.Library.service;

import com.example.Library.command.CreateAccountCommand;
import com.example.Library.command.CreditAmountCommand;
import com.example.Library.command.DebitAmountCommand;
import com.example.Library.controller.dto.AccountCreationDTO;
import com.example.Library.controller.dto.AmountDTO;
import com.example.Library.model.BankAccount;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.example.Library.service.ServiceUtils.formatUuid;

@Service
@AllArgsConstructor
public class AccountCommandService {        // will dispatch the Commands to the Axon Engine

    private final CommandGateway commandGateway;    // to dispatch command we have CommandGateway interface

    public CompletableFuture<BankAccount> createAccount(AccountCreationDTO accountCreationDTO){

        return this.commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID(),
                accountCreationDTO.getInitialBalance(),
                accountCreationDTO.getAccountHolderName()
        ));
    }

    public CompletableFuture<String> creditAmountToAccount(UUID accId, AmountDTO amountCreditDTO){

        return this.commandGateway.send(new CreditAmountCommand(
                accId,
                amountCreditDTO.getAmount()
        ));
    }

    public CompletableFuture<String> debitAmountFromAccount(String accId, AmountDTO amountDebitDTO){

        return this.commandGateway.send(new DebitAmountCommand(
                formatUuid(accId),
                amountDebitDTO.getAmount()
        ));
    }

}
