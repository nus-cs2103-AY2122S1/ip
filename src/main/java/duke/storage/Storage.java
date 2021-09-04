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

import duke.exception.DukeException;
import duke.exception.InvalidDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Represents a {@code Storage} object that can load and save data
 * to a given file path {@code pathName}.
 */
public class Storage {
    private final String pathName;

    /**
     * Initialises a {@code Storage} instance that works with the given {@code pathName}.
     *
     * @param pathName
     */
    public Storage(String pathName) {
        this.pathName = pathName;
    }

    /**
     * Load data into file stored locally. If file does not exist, create a new file,
     * otherwise overwrite it.
     *
     * @throws DukeException Error loading file.
     * @throws IOException   File exists but cannot be created or opened.
     */
    public ArrayList<Task> loadData() throws DukeException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        // Initialize save file and parent directory
        try {
            File file = new File(pathName);
            file.getParentFile().mkdirs();

            // If file already exists, overwrite it
            if (!file.getParentFile().mkdirs()) {
                file.delete(); // Needed for overwriting
                file.createNewFile();
            }

            String input;
            BufferedReader br = new BufferedReader(new FileReader(pathName));

            // Read data and add to list of tasks
            while ((input = br.readLine()) != null) {
                Task newTask;
                String taskName, time;
                int index;
                LocalDateTime dateTime;

                switch (input.charAt(1)) {
                case 'T':
                    // todo
                    newTask = new Todo(input.substring(7));
                    break;
                case 'D':
                    // deadline
                    index = input.indexOf(" (by: ");
                    taskName = input.substring(7, input.indexOf(" ("));
                    time = input.substring(index + 6, input.length() - 1);
                    try {
                        dateTime = LocalDateTime.parse(time,
                                DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
                    } catch (DateTimeParseException e) {
                        throw new InvalidDateException();
                    }
                    newTask = new Deadline(taskName, dateTime);
                    break;
                case 'E':
                    // event
                    index = input.indexOf(" (at: ");
                    taskName = input.substring(7, input.indexOf(" ("));
                    time = input.substring(index + 2, input.length() - 1);
                    try {
                        dateTime = LocalDateTime.parse(time,
                                DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
                    } catch (DateTimeParseException e) {
                        throw new InvalidDateException();
                    }
                    newTask = new Event(taskName, dateTime);
                    break;
                default:
                    throw new DukeException("Error loading file!");
                }

                if (input.charAt(4) == 'X') {
                    newTask.toggleDone();
                }

                tasks.add(newTask);
            }
            br.close();
        } catch (IOException e) {
            throw new DukeException("Folder failed to be created!");
        }
        return tasks;
    }

    /**
     * Saves data into file stored locally. If file does not exist,
     * create a new file, otherwise overwrite it.
     *
     * @param tasks The list of tasks.
     * @throws DukeException Save file failed.
     * @throws IOException File exists but cannot be created or opened.
     */
    public void saveData(ArrayList<Task> tasks) throws DukeException, IOException {
        try {
            FileWriter fw = new FileWriter(pathName, false);
            StringBuilder output = new StringBuilder();
            tasks.forEach(task -> output.append(task.toString()).append("\n"));
            fw.write(output.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Save file failed!");
        }
    }
}
