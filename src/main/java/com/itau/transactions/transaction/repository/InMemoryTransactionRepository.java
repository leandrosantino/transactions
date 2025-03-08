package com.itau.transactions.transaction.repository;

import com.itau.transactions.transaction.Transaction;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTransactionRepository implements TransactionRepository {

    List<Transaction> transactions = new ArrayList<>();

    @Override
    public void create(Transaction entity) {
        this.transactions.add(entity);
    }

    @Override
    public void deleteAll() {
        this.transactions.clear();
    }

    @Override
    public List<Transaction> getAll() {
        return this.transactions;
    }

    @Override
    public List<Transaction> findMany(long intervalTimeInSeconds) {
        var dateTimeInterval = OffsetDateTime.now().minusSeconds(intervalTimeInSeconds);
        return transactions
            .stream()
            .filter(transaction -> transaction.getDateTime().isAfter(dateTimeInterval))
            .toList();
    }

}
