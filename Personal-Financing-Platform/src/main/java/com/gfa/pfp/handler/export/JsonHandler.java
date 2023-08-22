package com.gfa.pfp.handler.export;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.gfa.pfp.models.entities.finance.Finance;
import com.gfa.pfp.repositories.FinanceRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class JsonHandler {
  private final FinanceRepository financeRepository;

  public void writeToJson(OutputStream outputStream, Long userId, LocalDate startDate,
                          LocalDate endDate) throws IOException {
    List<Finance> finances =
        financeRepository.findByUserIdAndTransactionDateBetween(userId, startDate, endDate);
    JsonGenerator jsonGenerator =
        new JsonFactory().createGenerator(outputStream, JsonEncoding.UTF8);
    jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());
    jsonGenerator.writeStartArray();
    for (Finance finance : finances) {
      if (finance.getIncome() == 0.0) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", finance.getId());
        jsonGenerator.writeNumberField("expense", finance.getExpense());
        jsonGenerator.writeStringField("expenseType", finance.getCategory().getName());
        jsonGenerator.writeStringField("transactionDate", finance.getTransactionDate().toString());
        jsonGenerator.writeEndObject();
      } else if (finance.getExpense() == 0.0) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", finance.getId());
        jsonGenerator.writeNumberField("income", finance.getIncome());
        jsonGenerator.writeStringField("incomeType", finance.getIncomeType().toString());
        jsonGenerator.writeStringField("transactionDate", finance.getTransactionDate().toString());
        jsonGenerator.writeBooleanField("recurring", finance.getRecurring());
        jsonGenerator.writeEndObject();
      }
    }
    jsonGenerator.writeEndArray();
    jsonGenerator.close();
  }
}
