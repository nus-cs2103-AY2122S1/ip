package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.*;

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
     * @param filePath the path to the text file that will be used as storage
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks into a text file.
     * @param tasks the list of tasks to be saved
     * @return true if the tasks are saved and false otherwise
     * @throws DukeException if the file can not be found
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

    private Task convertStringToTask(String string) {
        String[] arr = string.split(",");
        Task task = null;
        if (arr[0].trim().contains("T")) {
            task = new ToDo(arr[2].trim(), arr[1].trim().equals("1"));
        } else if (arr[0].trim().contains("E")) {
            task = new Event(arr[2].trim(), arr[1].trim().equals("1"), LocalDate.parse(arr[3].trim()));
        } else if (arr[0].trim().contains("D")) {
            task = new Deadline(arr[2].trim(), arr[1].trim().equals("1"), LocalDate.parse(arr[3].trim()));
        }
        return task;
    }

    /**
     * Reads the tasks in a text file at filePath and
     * returns the tasks as a List.
     * @return the tasks a List
     * @throws DukeException if the file can not be found
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
