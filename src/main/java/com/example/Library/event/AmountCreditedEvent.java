package com.example.Library.event;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class AmountCreditedEvent {

    private final UUID accId;
    private final BigDecimal creditAmount;
}
