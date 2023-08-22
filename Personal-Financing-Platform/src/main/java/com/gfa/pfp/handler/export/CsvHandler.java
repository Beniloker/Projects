package com.gfa.pfp.handler.export;

import com.gfa.pfp.models.entities.finance.Finance;
import com.gfa.pfp.repositories.FinanceRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class CsvHandler {
  private final FinanceRepository financeRepository;

  public void writeToCsv(OutputStream outputStream, Long userId, LocalDate startDate,
                         LocalDate endDate) throws IOException {
    List<Finance> finances =
        financeRepository.findByUserIdAndTransactionDateBetween(userId, startDate, endDate);
    CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(outputStream),
        CSVFormat.DEFAULT.withHeader("id", "income", "incomeType", "expense", "category",
            "transactionDate", "recurring"));
    for (Finance finance : finances) {
      csvPrinter.printRecord(finance.getId(), finance.getIncome(), finance.getIncomeType(),
          finance.getExpense(), finance.getCategory().getName(), finance.getTransactionDate(),
          finance.getRecurring());
    }
    csvPrinter.flush();
  }
}
