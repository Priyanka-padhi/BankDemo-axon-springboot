package com.example.Library.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreditAmountCommand {

    @TargetAggregateIdentifier
    private UUID accountId;
    private BigDecimal creditAmount;
}
