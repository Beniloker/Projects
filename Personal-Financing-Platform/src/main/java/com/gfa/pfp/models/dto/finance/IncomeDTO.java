package com.gfa.pfp.models.dto.finance;

import com.gfa.pfp.models.entities.finance.IncomeType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IncomeDTO {

    Double income;

    Boolean recurring;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate transactionDate;

    IncomeType incomeType;

}