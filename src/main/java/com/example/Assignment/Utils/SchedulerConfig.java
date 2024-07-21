package com.example.Assignment.Utils;

import com.example.Assignment.Service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Slf4j
public class SchedulerConfig {

    @Autowired
    private ReportService reportService;

    @Scheduled(cron = "0 0 0 * * ?") // Example cron expression for daily at midnight
    public void scheduledReportGeneration() {
        log.info("Starting scheduled report generation.");

        // Define paths or fetch from properties/config
        String inputFilePath = "path/to/input.csv";
        String referenceFilePath = "path/to/reference.csv";
        String outputFilePath = "path/to/output.csv";

        reportService.generateReport(inputFilePath, referenceFilePath, outputFilePath);

        log.info("Scheduled report generation completed successfully.");
    }
}
