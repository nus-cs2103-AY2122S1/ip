package duke.util;

import duke.exception.FileNotFoundException;
import duke.exception.InvalidDateException;
import duke.exception.NoSuchTaskException;
import duke.exception.UnknownTaskTypeException;
import duke.task.Task;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static FileWriter fileWriter;
    private static JSONParser jsonParser;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        try {
            File file = new File(filePath);
            boolean directoryExists = !file.getParentFile().mkdir();
            boolean fileExists = !file.createNewFile();
            if (directoryExists && fileExists) {
                return parseFromJSON(filePath);
            }
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    @SuppressWarnings("unchecked")
    public void write(TaskList tasks) {
        JSONArray arr = new JSONArray();
        tasks.forEach((task) -> arr.add(task.toJSONObject()));
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

    @SuppressWarnings("unchecked")
    public ArrayList<Task> parseFromJSON(String filePath) {
        ArrayList<Task> tasks = new ArrayList<>();
        jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            JSONArray arr = (JSONArray) jsonParser.parse(reader);
            arr.forEach((task) -> {
                try {
                    tasks.add(Task.fromJSONObject((JSONObject) task));
                } catch (UnknownTaskTypeException | InvalidDateException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
