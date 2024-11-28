package com.example.csvapi.service;

import com.example.csvapi.util.CoordinateConverter;
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
            boolean isFirstLine = true;
            List<String> headers = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // Split line by comma, handling quoted values
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                // Remove leading/trailing whitespaces and quotes
                List<String> cleanedValues = new ArrayList<>();
                for (String value : values) {
                    cleanedValues.add(value.trim().replaceAll("^\"|\"$", ""));
                }

                // Store headers separately
                if (isFirstLine) {
                    headers = cleanedValues;
                    headers.add("Latitude");  // Add header for latitude
                    headers.add("Longitude"); // Add header for longitude
                    isFirstLine = false;
                    continue;
                }

                // Convert GeoX and GeoY to latitude and longitude
                if (!cleanedValues.isEmpty() && !cleanedValues.get(4).isEmpty() && !cleanedValues.get(5).isEmpty()) {
                    try {
                        double geoX = Double.parseDouble(cleanedValues.get(4));
                        double geoY = Double.parseDouble(cleanedValues.get(5));
                        double[] latLong = CoordinateConverter.convertOSGB36ToWGS84(geoX, geoY);
                        cleanedValues.add(String.valueOf(latLong[0])); // Add latitude
                        cleanedValues.add(String.valueOf(latLong[1])); // Add longitude
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                records.add(cleanedValues);
            }
            reader.close();

            // Add headers back to the records
            if (!headers.isEmpty()) {
                records.add(0, headers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
}
