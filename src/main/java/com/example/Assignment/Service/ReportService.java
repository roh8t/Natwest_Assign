package com.example.Assignment.Service;

import com.example.Assignment.Model.InputRecord;
import com.example.Assignment.Model.OutputRecord;
import com.example.Assignment.Model.ReferenceRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ReportService {

    @Autowired
    private FileProcessorService fileProcessorService;

    public void generateReport(String inputFilePath, String referenceFilePath, String outputFilePath) {
        log.info("Starting report generation with input file: {}, reference file: {}, and output file: {}", inputFilePath, referenceFilePath, outputFilePath);

        List<InputRecord> inputRecords = fileProcessorService.readInputFile(inputFilePath);
        Map<String, ReferenceRecord> referenceData = fileProcessorService.readReferenceFile(referenceFilePath);

        List<OutputRecord> outputRecords = transformData(inputRecords, referenceData);

        fileProcessorService.writeOutputFile(outputFilePath, outputRecords);

        log.info("Report generation completed successfully.");
    }

    private List<OutputRecord> transformData(List<InputRecord> inputRecords, Map<String, ReferenceRecord> referenceData) {
        log.info("Transforming data based on input records and reference data.");
        List<OutputRecord> outputRecords = new ArrayList<>();
        for (InputRecord input : inputRecords) {
            ReferenceRecord ref = referenceData.get(input.getRefkey1() + input.getRefkey2());
            OutputRecord output = new OutputRecord();
            output.setOutfield1(input.getField1() + input.getField2());
            output.setOutfield2(ref.getRefdata1());
            output.setOutfield3(ref.getRefdata2() + ref.getRefdata3());
            output.setOutfield4(new BigDecimal(input.getField3()).multiply(input.getField5().max(ref.getRefdata4())));
            output.setOutfield5(input.getField5().max(ref.getRefdata4()));
            outputRecords.add(output);
        }
        log.info("Data transformation completed successfully.");
        return outputRecords;
    }
}
