package com.chariot.nm.company.api.utils;

import com.chariot.nm.company.api.models.User;
import com.chariot.nm.company.api.repositories.UserRepository;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/massive")
public class MassivePost {

    private final UserRepository userRepository;

    @Autowired
    public MassivePost(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {

            if (!file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                return ResponseEntity.badRequest().body("Invalid file type. Only Excel files are allowed.");
            }

            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);

            List<User> users = new ArrayList<>();

            for (Row row : sheet) {

                if (row.getRowNum() == 0) {
                    continue;
                }

                if (row.getCell(0) == null || row.getCell(1) == null || row.getCell(2) == null) {
                    return ResponseEntity.badRequest().body("Invalid data. All columns must be filled.");
                }

                User user = new User(
                        row.getCell(0).getStringCellValue(),
                        row.getCell(1).getStringCellValue(),
                        row.getCell(2).getStringCellValue()
                );

                if (!user.isValid()) {
                    return ResponseEntity.badRequest().body("Invalid user data.");
                }

                users.add(user);
            }

            userRepository.saveAll(users);

            return ResponseEntity.ok("Excel file uploaded successfully!");
        } catch (InvalidFormatException e) {
            return ResponseEntity.badRequest().body("Error parsing Excel file: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error reading Excel file: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error uploading Excel file: " + e.getMessage());
        }
    }
}