package com.itau.transactions.transaction.usecase;

import com.itau.transactions.transaction.dto.StatisticsData;
import com.itau.transactions.transaction.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.DoubleSummaryStatistics;

@Slf4j
@Component
public class CalculateStatistics {

    @Autowired
    private TransactionRepository transactionRepository;

    public StatisticsData execute(long lookbackSeconds) {
        log.info("Iniciando cálculo de estatísticas para os últimos {} segundos.", lookbackSeconds);

        var stats = new DoubleSummaryStatistics();
        var transactions = transactionRepository.findMany(lookbackSeconds);

        transactions.forEach(item -> stats.accept(item.getValue()));

        StatisticsData result = new StatisticsData(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin(),
                stats.getMax()
        );

        log.info("Estatísticas calculadas");
        return result;
    }
}
