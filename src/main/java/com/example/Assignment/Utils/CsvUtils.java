package com.example.Assignment.Utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

public class CsvUtils {

    public static <T> List<T> readCsvFile(String filePath, Class<T> clazz) {
        try (Reader reader = new FileReader(filePath)) {
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withType(clazz)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        } catch (Exception e) {
            throw new RuntimeException("Error reading CSV file", e);
        }
    }

    public static <T> void writeCsvFile(String filePath, List<T> records, Class<T> clazz) {
        try (Writer writer = new FileWriter(filePath)) {
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withApplyQuotesToAll(false)
                    .build();
            beanToCsv.write(records);
        } catch (Exception e) {
            throw new RuntimeException("Error writing CSV file", e);
        }
    }
}
