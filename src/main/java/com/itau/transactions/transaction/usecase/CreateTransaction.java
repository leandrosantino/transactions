package com.itau.transactions.transaction.usecase;

import com.itau.transactions.transaction.Transaction;
import com.itau.transactions.transaction.exceptions.UnprocessableEntity;
import com.itau.transactions.transaction.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;


@Component
@Slf4j
public class CreateTransaction {

    @Autowired
    private TransactionRepository transactionRepository;

    public void execute(Transaction transaction) throws IllegalArgumentException {
        log.info("Iniciando criação de transação: {}", transaction);

        if (transaction.getValue() == null || transaction.getDateTime() == null) {
            log.warn("Falha ao criar transação: valor ou data ausentes.");
            throw new UnprocessableEntity("O valor e a data da transação são obrigatórios.");
        }

        if (transaction.getDateTime().isAfter(OffsetDateTime.now())) {
            log.warn("Falha ao criar transação: data está no futuro. Data informada: {}", transaction.getDateTime());
            throw new UnprocessableEntity("A data da transação não pode estar no futuro.");
        }

        if (transaction.getValue() < 0) {
            log.warn("Falha ao criar transação: valor negativo. Valor informado: {}", transaction.getValue());
            throw new UnprocessableEntity("O valor da transação não pode ser negativo.");
        }

        transactionRepository.create(transaction);
        log.info("Transação criada com sucesso: {}", transaction);
    }
}

