package com.gfa.pfp.models.dto.finance;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonthlyExpensesDTO {

    String month;

    double expenses;

    String category;

}