package com.example.csvapi.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    public List<List<String>> readCsv(String filePath) {
        List<List<String>> records = new ArrayList<>();
        try {
            URL url = new URL(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                // Split line by comma, handling quoted values
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                // Remove leading/trailing whitespaces and quotes
                List<String> cleanedValues = new ArrayList<>();
                for (String value : values) {
                    cleanedValues.add(value.trim().replaceAll("^\"|\"$", ""));
                }

                records.add(cleanedValues);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
}
