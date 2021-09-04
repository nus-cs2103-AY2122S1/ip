package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * Presents a storage manager that deals with loading tasks
 * from a file and saving tasks into a file.
 *
 * @author ruiquan
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage given a String that represent a path to a text file.
     *
     * @param filePath The path to the text file that will be used as storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks into a text file.
     *
     * @param tasks The list of tasks to be saved.
     * @return True if the tasks are saved and false otherwise.
     * @throws DukeException If the file can not be found.
     */
    public boolean save(TaskList tasks) throws DukeException {
        Path dirPath = Paths.get("data");
        boolean dataPathExists = Files.exists(dirPath);
        if (!dataPathExists) {
            File dataDir = new File("data");
            dataDir.mkdir();
        }

        try {
            File file = new File(filePath);
            PrintWriter writer = new PrintWriter(file);
            tasks.getTasks().forEach(task -> writer.write(task.format() + System.lineSeparator()));
            writer.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("File could not be found");
        }
        return true;
    }

    private Task convertStringToTask(String string) throws DukeException {
        String[] arr = string.split(",");
        String type = arr[0].trim();
        Task task;
        switch (type) {
        case "T":
            task = new ToDo(arr[2].trim(), arr[1].trim().equals("1"));
            break;
        case "E":
            task = new Event(arr[2].trim(), arr[1].trim().equals("1"), LocalDate.parse(arr[3].trim()));
            break;
        case "D":
            task = new Deadline(arr[2].trim(), arr[1].trim().equals("1"), LocalDate.parse(arr[3].trim()));
            break;
        default:
            throw new DukeException("Save file is corrupted");
        }
        return task;
    }

    /**
     * Reads the tasks in a text file at filePath and
     * returns the tasks as a List.
     *
     * @return The tasks a List.
     * @throws DukeException If the file can not be found.
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                tasks.add(convertStringToTask(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            save(new TaskList());
        }
        return tasks;
    }
}
