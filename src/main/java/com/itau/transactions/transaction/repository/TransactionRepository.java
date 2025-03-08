package com.itau.transactions.transaction.repository;

import com.itau.transactions.transaction.Transaction;

import java.util.List;

public interface TransactionRepository {
    void create(Transaction entity);
    void deleteAll ();
    List<Transaction> getAll();
    List<Transaction> findMany(long intervalTimeInSeconds);
}
