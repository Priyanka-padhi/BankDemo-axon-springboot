package com.example.Library.event;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class AmountDebitedEvent {

    private final UUID accId;
    private final BigDecimal debitAmount;
}
