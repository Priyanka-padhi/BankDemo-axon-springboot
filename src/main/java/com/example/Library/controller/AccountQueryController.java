package com.example.Library.controller;

import com.example.Library.model.BankAccount;
import com.example.Library.service.AccountQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountQueryController {

    private final AccountQueryService accountQueryService;

    @GetMapping("/get/{accId}")
    public CompletableFuture<BankAccount> findById(@PathVariable("accId") String accId) {
        return this.accountQueryService.findById(accId);
    }

    @GetMapping("/events/{accId}")
    public List<Object> listEventsForAccount(@PathVariable(value = "accId") String accId) {
        return this.accountQueryService.listEventsForAccount(accId);
    }

}
