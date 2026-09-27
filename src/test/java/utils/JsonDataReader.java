package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonDataReader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode readJson(String fileName) {
        try (InputStream inputStream =
                     getClass().getClassLoader().getResourceAsStream("testdata/" + fileName)) {

            if (inputStream == null) {
                throw new RuntimeException(fileName + " not found in test resources");
            }

            return objectMapper.readTree(inputStream);

        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file: " + fileName, e);
        }
    }


}

