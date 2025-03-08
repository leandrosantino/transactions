package com.itau.transactions.transaction.controller;

import com.itau.transactions.transaction.dto.StatisticsData;
import com.itau.transactions.transaction.usecase.CalculateStatistics;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@Tag(name = "Statistics")
public class StatisticsController {

    @Autowired
    CalculateStatistics calculateStatisticsUseCase;

    @GetMapping("/{lookbackSeconds}")
    @Operation(description = "Calcula estatísticas das transações")
    private ResponseEntity<StatisticsData> getStatistics(
        @PathVariable
        @Parameter(description = "Intervalo de tempo para o calculo das estatísticas em segundos")
        long lookbackSeconds
    ){
        StatisticsData statisticsData = calculateStatisticsUseCase.execute(lookbackSeconds);
        return ResponseEntity.ok().body(statisticsData);
    }

}
