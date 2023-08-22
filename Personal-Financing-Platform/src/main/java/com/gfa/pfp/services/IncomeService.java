package com.gfa.pfp.services;

import com.gfa.pfp.exception.PfpException;
import com.gfa.pfp.exception.UnauthorizedException;
import com.gfa.pfp.exception.dataexportexceptions.DateRangeMissingException;
import com.gfa.pfp.exception.dataexportexceptions.DateRangeTooBigException;
import com.gfa.pfp.exception.financeexceptions.FinanceNotFoundException;
import com.gfa.pfp.models.dto.finance.FinancesMonthlyDTO;
import com.gfa.pfp.models.dto.finance.GetAllIncomeDTO;
import com.gfa.pfp.models.dto.finance.GetIncomeDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface IncomeService {

    GetIncomeDTO createNewIncome(String income, MultipartFile file, String request) throws FinanceNotFoundException, IOException;

    GetAllIncomeDTO getAllIncomes(String request) throws FinanceNotFoundException;

    GetIncomeDTO updateIncome(Long incomeId, String income, MultipartFile file, String request) throws UnauthorizedException, FinanceNotFoundException, IOException;

    void deleteIncome(Long incomeId, String request) throws UnauthorizedException, FinanceNotFoundException;

    double getTotalIncomeBetweenDates(String request, LocalDate startDate, LocalDate endDate) throws DateRangeMissingException, DateRangeTooBigException;

    List<FinancesMonthlyDTO> monthlyIncomes(String request) throws PfpException;

}