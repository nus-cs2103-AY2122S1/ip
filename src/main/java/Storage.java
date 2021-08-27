import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private FileWriter fileWriter;
    private JSONParser jsonParser;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Task> parseFromJson(String filePath) {
        ArrayList<Task> tasks = new ArrayList<>();
        jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            JSONArray arr = (JSONArray) jsonParser.parse(reader);
            arr.forEach((task) -> {
                try {
                    tasks.add(Task.fromJsonObject((JSONObject) task));
                } catch (InvalidInputException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void write(TaskList tasks) {
        JSONArray arr = new JSONArray();
        tasks.forEach((task) -> arr.add(task.toJsonObject()));
        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write(arr.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // need a file path generator method?
    public ArrayList<Task> load() throws FileNotFoundException {
        try {
            File file = new File(filePath);
            boolean directoryExists = !file.getParentFile().mkdir();
            boolean fileExists = !file.createNewFile();
            if (directoryExists && fileExists) {
                return parseFromJson(filePath);
            }
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

}
