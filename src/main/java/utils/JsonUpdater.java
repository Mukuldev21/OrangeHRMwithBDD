package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

public class JsonUpdater {
    public static void updateLastName(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = mapper.readValue(new File(filePath), Map.class);

        // Generate a random last name
        String randomLastName = "LastName" + new Random().nextInt(10000);
        jsonMap.put("lastName", randomLastName);

        // Write back to the file
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), jsonMap);
    }
}