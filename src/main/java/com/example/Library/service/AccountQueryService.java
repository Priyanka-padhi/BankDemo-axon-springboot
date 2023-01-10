package com.example.Library.service;

import com.example.Library.model.BankAccount;
import com.example.Library.query.FindBankAccount;
import lombok.AllArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.example.Library.service.ServiceUtils.formatUuid;

@Service
@AllArgsConstructor
public class AccountQueryService {

    private final QueryGateway queryGateway;        // to dispatch query
    private final EventStore eventStore;

    public CompletableFuture<BankAccount> findById(String accID){
        return this.queryGateway.query(new FindBankAccount(formatUuid(accID)),
                ResponseTypes.instanceOf(BankAccount.class));
    }

    public List<Object> listEventsForAccount(String accId){

        return this.eventStore.readEvents(formatUuid(accId).toString())
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }

}
