package com.gfa.pfp.services;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.time.LocalDate;

public interface DataExportService {
  Resource exportDataAsZIP(LocalDate startDate, LocalDate endDate, String request)
      throws IOException;

  Resource exportDataAsCSV(LocalDate startDate, LocalDate endDate, String request)
      throws IOException;

  Resource exportDataAsJSON(LocalDate startDate, LocalDate endDate, String request)
      throws IOException;
}
