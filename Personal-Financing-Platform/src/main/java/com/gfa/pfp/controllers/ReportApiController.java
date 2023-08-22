package com.gfa.pfp.controllers;

import com.gfa.pfp.exception.dataexportexceptions.DateRangeMissingException;
import com.gfa.pfp.exception.dataexportexceptions.DateRangeTooBigException;
import com.gfa.pfp.services.ExpenseService;
import com.gfa.pfp.services.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportApiController {

    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    @GetMapping("/period/expense")
    public ResponseEntity<Double> getExpenseForPeriod(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate,
                                                      @RequestHeader String authorization) throws DateRangeMissingException, DateRangeTooBigException {

        return ResponseEntity.ok().body(expenseService.getTotalExpenseBetweenDates(authorization, startDate, endDate));
    }

    @GetMapping("/period/income")
    public ResponseEntity<Double> getIncomeForPeriod(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate,
                                                     @RequestHeader String authorization) throws DateRangeMissingException, DateRangeTooBigException {

        return ResponseEntity.ok().body(incomeService.getTotalIncomeBetweenDates(authorization, startDate, endDate));
    }



}








