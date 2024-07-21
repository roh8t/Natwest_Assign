package com.example.Assignment.Service;

import com.example.Assignment.Model.InputRecord;
import com.example.Assignment.Model.OutputRecord;
import com.example.Assignment.Model.ReferenceRecord;
import com.example.Assignment.Utils.CsvUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileProcessorService {

    public List<InputRecord> readInputFile(String filePath) {
        log.info("Reading input file from path: {}", filePath);
        List<InputRecord> records = CsvUtils.readCsvFile(filePath, InputRecord.class);
        log.info("Successfully read {} records from input file", records.size());
        return records;
    }

    public Map<String, ReferenceRecord> readReferenceFile(String filePath) {
        log.info("Reading reference file from path: {}", filePath);
        List<ReferenceRecord> records = CsvUtils.readCsvFile(filePath, ReferenceRecord.class);
        Map<String, ReferenceRecord> referenceData = records.stream()
                .collect(Collectors.toMap(
                        record -> record.getRefkey1() + record.getRefkey2(),
                        record -> record
                ));
        log.info("Successfully read {} records from reference file", records.size());
        return referenceData;
    }

    public void writeOutputFile(String filePath, List<OutputRecord> outputRecords) {
        log.info("Writing output file to path: {}", filePath);
        CsvUtils.writeCsvFile(filePath, outputRecords, OutputRecord.class);
        log.info("Successfully wrote {} records to output file", outputRecords.size());
    }
}
