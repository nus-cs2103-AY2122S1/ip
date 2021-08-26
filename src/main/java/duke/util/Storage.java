package duke.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import duke.exception.FileNotFoundException;
import duke.exception.InvalidDateException;
import duke.exception.UnknownTaskTypeException;
import duke.task.Task;

/** Utility class that handles reading and writing from device storage. */
public class Storage {
    /** The FileWriter object. */
    private static FileWriter fileWriter;

    /** The JSONParser object. */
    private static JSONParser jsonParser;

    /** The file path to read and write from. */
    private String filePath;

    /** Default Storage constructor. Use for JUnit tests ONLY.*/
    protected Storage() {
        filePath = null;
    }

    /**
     * Storage constructor.
     *
     * @param filePath The file path to read and write from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the program file from the file path if the file exists, else creates the file and its associated
     * directories. Returns an ArrayList of Tasks constructed from data in the program file.
     *
     * @return An ArrayList of Tasks.
     * @throws FileNotFoundException If the file in specified in the filepath does not exist.
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

    /**
     * Writes the tasks to a JSON file in the specified file path.
     *
     * @param tasks The TaskList created from the instance of the Duke program.
     */
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

    /**
     * Parses the JSON file from the specified path and returns an ArrayList of Tasks constructed from data in the
     * specified file.
     *
     * @param filePath The file path to read and write from.
     * @return An ArrayList of Tasks.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> parseFromJson(String filePath) {
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
