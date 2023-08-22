package com.gfa.pfp.models.dto.export;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.annotation.AliasFor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)

public class DateRangeDTO {
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDate startDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDate endDate;
}
