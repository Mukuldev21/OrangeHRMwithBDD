package hooks;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureParser {
    private static Map<Integer, String> stepLines = new HashMap<>();

    public static void loadFeatureLines(URI featureUri) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(featureUri));
            for (int i = 0; i < lines.size(); i++) {
                stepLines.put(i + 1, lines.get(i).trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getStepInfo(int lineNumber) {
        return stepLines.getOrDefault(lineNumber, "Step info not found");
    }
}
