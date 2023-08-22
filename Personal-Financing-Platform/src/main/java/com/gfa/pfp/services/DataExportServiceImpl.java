package com.gfa.pfp.services;

import com.gfa.pfp.handler.export.CsvHandler;
import com.gfa.pfp.handler.export.JsonHandler;
import com.gfa.pfp.models.entities.user.User;
import com.gfa.pfp.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@RequiredArgsConstructor
public class DataExportServiceImpl implements DataExportService {

  private final JwtService jwtService;
  private final UserService userService;
  private final CsvHandler csvHandler;
  private final JsonHandler jsonHandler;

  @Override
  public Resource exportDataAsZIP(LocalDate startDate, LocalDate endDate, String request) throws IOException {
    var userIdFromJwt = userService.getByEmailOrderById(jwtService.extractUserNameFromHeader(request)).getId();
    final User user = userService.findById(userIdFromJwt);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (ZipOutputStream zipOutputStream = new ZipOutputStream(baos)) {
      // add CSV file to zip
      String csvFilename = "financial_data.csv";
      ZipEntry csvEntry = new ZipEntry(csvFilename);
      zipOutputStream.putNextEntry(csvEntry);
      csvHandler.writeToCsv(zipOutputStream, user.getId(), startDate, endDate);

      // add JSON file to zip
      String jsonFilename = "financial_data.json";
      ZipEntry jsonEntry = new ZipEntry(jsonFilename);
      zipOutputStream.putNextEntry(jsonEntry);
      jsonHandler.writeToJson(zipOutputStream, user.getId(), startDate, endDate);
    }
    return new ByteArrayResource(baos.toByteArray());
  }

  @Override
  public Resource exportDataAsCSV(LocalDate startDate, LocalDate endDate, String request) throws IOException {
    var userIdFromJwt = userService.getByEmailOrderById(jwtService.extractUserNameFromHeader(request)).getId();
    final User user = userService.findById(userIdFromJwt);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    csvHandler.writeToCsv(baos, user.getId(), startDate, endDate);
    return new ByteArrayResource(baos.toByteArray());
  }

  @Override
  public Resource exportDataAsJSON(LocalDate startDate, LocalDate endDate, String request) throws IOException {
    var userIdFromJwt = userService.getByEmailOrderById(jwtService.extractUserNameFromHeader(request)).getId();
    final User user = userService.findById(userIdFromJwt);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    jsonHandler.writeToJson(baos, user.getId(), startDate, endDate);
    return new ByteArrayResource(baos.toByteArray());
  }
}