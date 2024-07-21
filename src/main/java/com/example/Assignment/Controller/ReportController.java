package com.example.Assignment.Controller;

import com.example.Assignment.Service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateReport(@RequestParam String inputFilePath,
                                                 @RequestParam String referenceFilePath,
                                                 @RequestParam String outputFilePath) {
        log.info("Received request to generate report with input file: {}, reference file: {}, and output file: {}", inputFilePath, referenceFilePath, outputFilePath);
        reportService.generateReport(inputFilePath, referenceFilePath, outputFilePath);
        return ResponseEntity.ok("Report generated successfully");
    }
}
