package com.itau.transactions;

import com.itau.transactions.transaction.Transaction;
import com.itau.transactions.transaction.dto.StatisticsData;
import com.itau.transactions.transaction.repository.TransactionRepository;
import com.itau.transactions.transaction.usecase.CalculateStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.time.OffsetDateTime;

@SpringBootTest
class CalculateStatisticsTest {

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private CalculateStatistics calculateStatistics;

	private List<Transaction> sampleTransactions;

	@BeforeEach
	void setUp() {
		sampleTransactions = List.of(
				new Transaction(10.0, OffsetDateTime.now()),
				new Transaction(20.0, OffsetDateTime.now()),
				new Transaction(30.0, OffsetDateTime.now())
		);
	}

	@Test
	void shouldCalculateStatisticsCorrectly() {
		Mockito.when(transactionRepository.findMany(60)).thenReturn(sampleTransactions);

		StatisticsData result = calculateStatistics.execute(60);

		assertEquals(3, result.count());
		assertEquals(60.0, result.sum());
		assertEquals(20.0, result.avg());
		assertEquals(10.0, result.min());
		assertEquals(30.0, result.max());

		Mockito.verify(transactionRepository, Mockito.times(1)).findMany(60);
	}

	@Test
	void shouldReturnEmptyStatisticsWhenNoTransactionsExist() {
		Mockito.when(transactionRepository.findMany(60)).thenReturn(List.of());

		StatisticsData result = calculateStatistics.execute(60);

		assertEquals(0, result.count());
		assertEquals(0.0, result.sum());
		assertEquals(0.0, result.avg()); // Média de um conjunto vazio é NaN
		assertEquals(Double.POSITIVE_INFINITY, result.min());
		assertEquals(Double.NEGATIVE_INFINITY, result.max());

		Mockito.verify(transactionRepository, Mockito.times(1)).findMany(60);
	}


}
