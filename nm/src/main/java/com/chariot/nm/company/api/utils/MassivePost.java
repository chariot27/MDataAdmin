package com.chariot.nm.company.api.utils;

import com.chariot.nm.company.api.models.User;
import com.chariot.nm.company.api.repositories.UserRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/massive")
public class MassivePost {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            // Read Excel file using Apache POI
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through each row in the Excel file
            for (Row row : sheet) {
                // Skip header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Create a new User object from Excel row data
                User user = new User(
                        row.getCell(0).getStringCellValue(),
                        row.getCell(1).getStringCellValue(),
                        row.getCell(2).getStringCellValue()
                );

                // Save the User object to the database
                userRepository.save(user);
            }

            return ResponseEntity.ok("Excel file uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error uploading Excel file: " + e.getMessage());
        }
    }
}
