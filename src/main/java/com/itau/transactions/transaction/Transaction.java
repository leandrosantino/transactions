package com.itau.transactions.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@ToString
public class Transaction {

    private Double value;
    private OffsetDateTime dateTime;

}
