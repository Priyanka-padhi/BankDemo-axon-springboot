package com.example.Library.controller;


import com.example.Library.controller.dto.AccountCreationDTO;
import com.example.Library.controller.dto.AmountDTO;
import com.example.Library.model.BankAccount;
import com.example.Library.service.AccountCommandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountCommandController {

    private final AccountCommandService accountCommandService;

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<BankAccount> createAccount(@RequestBody AccountCreationDTO accountCreationDTO){

        return this.accountCommandService.createAccount(accountCreationDTO);
    }

    @PutMapping("/credit/{accId}")
    public CompletableFuture<String> creditAmountToAccount(@PathVariable("accId") UUID accId, @RequestBody AmountDTO amountCreditDTO){

        return this.accountCommandService.creditAmountToAccount(accId,amountCreditDTO);
    }

    @PutMapping("/debit/{accId}")
    public CompletableFuture<String> debitAmountFromAccount(@PathVariable("accId") String accId, @RequestBody AmountDTO amountDebitDTO){

        return this.accountCommandService.debitAmountFromAccount(accId,amountDebitDTO);
    }



}
