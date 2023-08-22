package com.gfa.pfp.models.dto.limit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class LimitResponseDTO {

    String category;

    Double limit;

    Double current;

    String remaining;

    String percentage;

    String percentageRemaining;

    LocalDate start;

    LocalDate end;

}