package com.itau.transactions;

import com.itau.transactions.transaction.Transaction;
import com.itau.transactions.transaction.exceptions.UnprocessableEntity;
import com.itau.transactions.transaction.repository.TransactionRepository;
import com.itau.transactions.transaction.usecase.CreateTransaction;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CreateTransactionTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private CreateTransaction createTransaction;

    @Test
    void shouldCreateTransactionSuccessfully(){
        Transaction transaction = new Transaction(0.1, OffsetDateTime.now());

        createTransaction.execute(transaction);
        Mockito.verify(transactionRepository, Mockito.times(1)).create(transaction);
    }

    @Test
    void shouldThrowsExceptionIfAnyPropertyOfTransactionIsNull (){
        Transaction transaction = new Transaction(null, OffsetDateTime.now());

        Exception exception = assertThrows(UnprocessableEntity.class, () -> {
            createTransaction.execute(transaction);
        });

        assertEquals("O valor e a data da transação são obrigatórios.", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenTransactionHasFutureDate() {
        Transaction transaction = new Transaction(100.0, OffsetDateTime.now().plusDays(1));

        Exception exception = assertThrows(UnprocessableEntity.class, () -> {
            createTransaction.execute(transaction);
        });

        assertEquals("A data da transação não pode estar no futuro.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTransactionHasNegativeValue() {
        Transaction transaction = new Transaction(-10.0, OffsetDateTime.now());

        Exception exception = assertThrows(UnprocessableEntity.class, () -> {
            createTransaction.execute(transaction);
        });

        assertEquals("O valor da transação não pode ser negativo.", exception.getMessage());
    }

}
