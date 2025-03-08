package com.itau.transactions.transaction.controller;

import com.itau.transactions.transaction.Transaction;
import com.itau.transactions.transaction.repository.TransactionRepository;
import com.itau.transactions.transaction.usecase.CreateTransaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@Tag(name = "Transactions")
public class TransactionController {

    @Autowired
    CreateTransaction createTransactionUseCase;
    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping()
    @Operation(description = "Cria um transação")
    private ResponseEntity<Void> createTransaction(@RequestBody Transaction transaction){
        createTransactionUseCase.execute(transaction);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    @Operation(description = "Apaga todas a transações realizadas")
    private ResponseEntity<Void> deleteAllTransactions() {
        transactionRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    @Operation(description = "Retorna todas as transações salvas")
    private ResponseEntity<List<Transaction>> getAll(){
        return ResponseEntity.ok().body(this.transactionRepository.getAll());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

}
