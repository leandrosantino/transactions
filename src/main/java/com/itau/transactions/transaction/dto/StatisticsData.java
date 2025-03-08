package com.itau.transactions.transaction.dto;

public record StatisticsData(
    long count,
    double sum,
    double avg,
    double min,
    double max
) {}
