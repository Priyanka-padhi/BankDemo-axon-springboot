package com.example.Library.projection;

import com.example.Library.event.AccountCreatedEvent;
import com.example.Library.event.AmountCreditedEvent;
import com.example.Library.event.AmountDebitedEvent;
import com.example.Library.exception.AccountNotFoundException;
import com.example.Library.model.BankAccount;
import com.example.Library.query.FindBankAccount;
import com.example.Library.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class BankAccountProjection {            //Todo CRUD Operation

    private final BankAccountRepository bankAccountRepo;

    //We will define the handler for every emitted Event

    @EventHandler
    public void on(AccountCreatedEvent event){

        log.debug("Handling a Bank Account creation command..",event.getId());

        BankAccount bankAccount = new BankAccount(
                event.getId(),
                event.getAccountHolderName(),
                event.getInitialBalance()
        );

        this.bankAccountRepo.save(bankAccount);
    }

    @EventHandler
    public void on(AmountCreditedEvent event)throws AccountNotFoundException {

        log.debug("Handling a Amount credit command", event.getAccId());

        Optional<BankAccount> bankAccount = this.bankAccountRepo.findById(event.getAccId());
        if(bankAccount.isPresent()){
            BankAccount acc = bankAccount.get();
            acc.setBalance(acc.getBalance().add(event.getCreditAmount()));
            this.bankAccountRepo.save(acc);
        }else {
            throw new AccountNotFoundException();
        }
    }

    @EventHandler
    public void on(AmountDebitedEvent event) throws AccountNotFoundException{

        log.debug("Handling a Amount debit command", event.getAccId());

        Optional<BankAccount> bankAccount = this.bankAccountRepo.findById(event.getAccId());
        if(bankAccount.isPresent()){
            BankAccount acc = bankAccount.get();
            acc.setBalance(acc.getBalance().subtract(event.getDebitAmount()));
            this.bankAccountRepo.save(acc);
        }else {
            throw new AccountNotFoundException();
        }
    }

    @QueryHandler
    public BankAccount handle(FindBankAccount query){
        log.debug("Handling Find Bank Account query");
        return this.bankAccountRepo.findById(query.getAccountId()).orElse(null);
    }


}
