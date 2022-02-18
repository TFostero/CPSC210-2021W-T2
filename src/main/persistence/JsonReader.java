package persistence;

import model.LaunchPad;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public LaunchPad read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLaunchPad(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses launch pad from JSON object and returns it
    private LaunchPad parseLaunchPad(JSONObject jsonObject) {
        LaunchPad pad = new LaunchPad();
        addRockets(pad, jsonObject);
        return pad;
    }

    // MODIFIES: pad
    // EFFECTS: parses launch parameters array into rockets in the launch pad
    private void addRockets(LaunchPad pad, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("launch params");
        for (Object json : jsonArray) {
            JSONObject nextRocket = (JSONObject) json;
            addRocket(pad, nextRocket);
        }
    }

    // MODIFIES: pad
    // EFFECTS: parses one rocket's launch parameters and adds that rocket to the launch pad
    private void addRocket(LaunchPad pad, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double angle = jsonObject.getDouble("angle");
        double fuel = jsonObject.getDouble("fuel");
        double thrust = jsonObject.getDouble("thrust");
        pad.addRocket(angle, thrust, fuel, name);
    }
}
