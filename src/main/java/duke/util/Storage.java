package duke.util;

import duke.exception.InvalidInputException;
import duke.task.Task;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private String filePath;
    private FileWriter fileWriter;

    /**
     * Default constructor for a Storage instance.
     *
     * @param filePath The specified file path to store the save data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses the file from its JSON format.
     *
     * @param filePath The specified file to parse.
     * @return An ArrayList of Tasks.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> parseFromJson(String filePath) {
        ArrayList<Task> tasks = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
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

    /**
     * Writes the TaskList into a JSON File format and saves it.
     *
     * @param tasks The TaskList to convert into JSON file format.
     */
    @SuppressWarnings("unchecked")
    public String write(TaskList tasks) {
        String output = "";
        JSONArray arr = new JSONArray();
        tasks.forEach((task) -> arr.add(task.toJsonObject()));
        output = "Farewell, may we never meet again.";
        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write(arr.toJSONString());
        } catch (IOException e) {
            output = e.getMessage();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                output = e.getMessage();
            }
        }
        return output;
    }

    /**
     * Loads the TaskList from a JSON File format.
     *
     * @return An ArrayList of Tasks loaded from the save data.
     * @throws FileNotFoundException When the specified file is not found.
     */
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
