package com.gfa.pfp.controllers;

import com.gfa.pfp.exception.dataexportexceptions.DateRangeMissingException;
import com.gfa.pfp.exception.dataexportexceptions.DateRangeTooBigException;
import com.gfa.pfp.models.dto.export.DateRangeDTO;
import com.gfa.pfp.services.DataExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDate;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DataExportApiController {
  private final DataExportService dataExportService;

  @GetMapping(value = "/exportAll", produces = "application/zip")
  public ResponseEntity<Resource> exportDataAsZIP(@RequestBody DateRangeDTO dateRangeDTO,
                                                  @RequestHeader String authorization)
      throws IOException, DateRangeMissingException, DateRangeTooBigException {
    LocalDate startDate = dateRangeDTO.getStartDate();
    LocalDate endDate = dateRangeDTO.getEndDate();
    if (endDate == null) {
      endDate = LocalDate.now();
    }
    DataExportApiController.checkExceptions(startDate, endDate);
    if (startDate.isAfter(endDate)) {
      LocalDate temp = startDate;
      startDate = endDate;
      endDate = temp;
    }
    Resource zipFileResource = dataExportService.exportDataAsZIP(startDate, endDate, authorization);
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=financial_data_" + startDate.toString() + "_" + endDate.toString() +
            ".zip").contentType(MediaType.parseMediaType("application/zip")).body(zipFileResource);
  }

  @GetMapping(value = "/exportCSV", produces = "text/csv")
  public ResponseEntity<Resource> exportDataAsCSV(@RequestBody DateRangeDTO dateRangeDTO,
                                                  @RequestHeader String authorization)
      throws IOException, DateRangeTooBigException, DateRangeMissingException {
    LocalDate startDate = dateRangeDTO.getStartDate();
    LocalDate endDate = dateRangeDTO.getEndDate();
    if (endDate == null) {
      endDate = LocalDate.now();
    }
    DataExportApiController.checkExceptions(startDate, endDate);
    if (startDate.isAfter(endDate)) {
      LocalDate temp = startDate;
      startDate = endDate;
      endDate = temp;
    }
    Resource csvFileResource = dataExportService.exportDataAsCSV(startDate, endDate, authorization);
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=financial_data_" + startDate.toString() + "_" + endDate.toString() +
            ".csv").contentType(MediaType.parseMediaType("text/csv")).body(csvFileResource);
  }

  @GetMapping(value = "/exportJSON", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Resource> exportDataAsJSON(@RequestBody DateRangeDTO dateRangeDTO,
                                                   @RequestHeader String authorization)
      throws IOException, DateRangeTooBigException, DateRangeMissingException {
    LocalDate startDate = dateRangeDTO.getStartDate();
    LocalDate endDate = dateRangeDTO.getEndDate();
    if (endDate == null) {
      endDate = LocalDate.now();
    }
    DataExportApiController.checkExceptions(startDate, endDate);
    if (startDate.isAfter(endDate)) {
      LocalDate temp = startDate;
      startDate = endDate;
      endDate = temp;
    }
    Resource jsonFileResource =
        dataExportService.exportDataAsJSON(startDate, endDate, authorization);
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=financial_data_" + startDate.toString() + "_" + endDate.toString() +
                ".json").contentType(MediaType.parseMediaType("application/json"))
        .body(jsonFileResource);
  }

  private static void checkExceptions(LocalDate startDate, LocalDate endDate)
      throws DateRangeMissingException, DateRangeTooBigException {
    if (startDate == null) {
      throw new DateRangeMissingException();
    }
    if (endDate.isAfter(startDate.plusDays(30))) {
      throw new DateRangeTooBigException();
    }
  }
}
