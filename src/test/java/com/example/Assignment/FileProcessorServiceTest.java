package com.example.Assignment;

import com.example.Assignment.Model.InputRecord;
import com.example.Assignment.Model.OutputRecord;
import com.example.Assignment.Model.ReferenceRecord;
import com.example.Assignment.Service.FileProcessorService;
import com.example.Assignment.Utils.CsvUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FileProcessorServiceTest {

    @Mock
    private CsvUtils csvUtils;

    @InjectMocks
    private FileProcessorService fileProcessorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReadInputFile() {
        // Mock the CsvUtils.readCsvFile method
        InputRecord inputRecord = new InputRecord();
        inputRecord.setField1("f1");
        inputRecord.setField2("f2");
        inputRecord.setField3("f3");
        inputRecord.setField5(new BigDecimal("10"));
        inputRecord.setRefkey1("r1");
        inputRecord.setRefkey2("r2");

        when(csvUtils.readCsvFile("input.csv", InputRecord.class))
                .thenReturn(Collections.singletonList(inputRecord));

        List<InputRecord> records = fileProcessorService.readInputFile("input.csv");

        assertEquals(1, records.size());
        assertEquals("f1", records.get(0).getField1());
    }

    @Test
    public void testReadReferenceFile() {
        // Mock the CsvUtils.readCsvFile method
        ReferenceRecord referenceRecord = new ReferenceRecord();
        referenceRecord.setRefkey1("r1");
        referenceRecord.setRefdata1("ref1");
        referenceRecord.setRefkey2("r2");
        referenceRecord.setRefdata2("ref2");
        referenceRecord.setRefdata3("ref3");
        referenceRecord.setRefdata4(new BigDecimal("5"));

        when(csvUtils.readCsvFile("reference.csv", ReferenceRecord.class))
                .thenReturn(Collections.singletonList(referenceRecord));

        Map<String, ReferenceRecord> referenceData = fileProcessorService.readReferenceFile("reference.csv");

        assertEquals(1, referenceData.size());
        ReferenceRecord refRecord = referenceData.get("r1r2");
        assertEquals("ref1", refRecord.getRefdata1());
    }

    @Test
    public void testWriteOutputFile() {
        // Mock the CsvUtils.writeCsvFile method
        OutputRecord outputRecord = new OutputRecord();
        outputRecord.setOutfield1("out1");
        outputRecord.setOutfield2("out2");
        outputRecord.setOutfield3("out3");
        outputRecord.setOutfield4(new BigDecimal("20"));
        outputRecord.setOutfield5(new BigDecimal("30"));

        List<OutputRecord> outputRecords = Collections.singletonList(outputRecord);

        fileProcessorService.writeOutputFile("output.csv", outputRecords);

        // No exception means the method works as expected.
    }
}
