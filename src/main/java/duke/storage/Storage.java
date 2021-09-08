package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.Task;
import duke.commands.Todo;
import duke.data.TaskList;

/**
 * Encapsulates a Storage object that handles loading and saving of Tasks.
 *
 * @author Owen Tan
 * @version duke.Duke Level-9
 */
public class Storage {
    private String path;
    private TaskList tasks;

    /**
     * Public constructor of Storage.
     *
     * @param path Path of text file stored.
     * @param tasks Associated TaskList.
     */
    public Storage(String path, TaskList tasks) {
        this.path = path;
        this.tasks = tasks;
    }

    /**
     * Loads the file into Storage and parses Tasks into TaskList.
     */
    public void load() {
        try {
            File fileDir = new File(path).getParentFile();
            File file = new File(path);

            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            if (!file.createNewFile()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    tasks.add(parseTask(line));
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with loading");
        }
    }

    /**
     * Saves Tasks from TaskList into text file.
     */
    public void save() {
        try {
            FileWriter writer = new FileWriter(path);
            ArrayList<Task> lst = tasks.getList();
            for (Task task: lst) {
                writer.write(task.printInSaveFormat());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong with saving");
        }
    }

    private Task parseTask(String task) {
        // Format to Parse: T | 0 | description | addInfo
        String[] tokens = task.split(" \\| ");
        Task t = null;
        boolean isDone = tokens[1].equals("1");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime timestamp = null;
        assert tokens[0].equals("T") || tokens[0].equals("D") || tokens[0].equals("E");
        switch (tokens[0]) {
        case "T":
            t = new Todo(tokens[2], isDone);
            break;
        case "D":
            try {
                timestamp = LocalDateTime.parse(tokens[3], format);
                t = new Deadline(tokens[2], isDone, timestamp);
            } catch (DateTimeParseException e) {
                System.out.println("Error parsing task from saved file");
            }
            break;
        case "E":
            try {
                timestamp = LocalDateTime.parse(tokens[3], format);
                t = new Event(tokens[2], isDone, timestamp);
            } catch (DateTimeParseException e) {
                System.out.println("Error parsing task from saved file");
            }
            break;
        default:
        }
        return t;
    }
}
