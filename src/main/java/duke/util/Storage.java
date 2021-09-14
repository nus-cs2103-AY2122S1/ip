package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.LoadingException;
import duke.exception.SavingException;
import duke.exception.StorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents the storage in the Duke program to store tasks created by users.
 */
public class Storage {
    private final File file;

    /**
     * Constructs a Storage for the Duke program.
     *
     * @param filePath File path of the storage.
     * @throws DukeException If file path does not exist.
     */
    public Storage(String filePath) throws DukeException {
        // Ensure parent directories exist
        String[] filePathComponents = filePath.split("/");
        String filePathTemp = filePathComponents[0];
        for (int i = 0; i < filePathComponents.length - 1; i++) {
            File dir = new File(filePathTemp);
            if (!dir.exists()) {
                dir.mkdir();
            }
            assert dir != null : "Directory should be created";
            filePathTemp += filePathComponents[i + 1];
        }

        // Ensure file exists
        file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException();
        }
        assert file != null : "File should be created";
    }

    /**
     * Loads saved tasks from the storage.
     *
     * @return Tasks saved in the storage.
     * @throws DukeException If saved tasks cannot be retrieved from storage.
     */
    public ArrayList<Task> load() throws DukeException {
        // Initialize task list
        ArrayList<Task> taskList = new ArrayList<>();

        // Instantiate scanner obj
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new LoadingException();
        }
        assert scanner != null : "Scanner should be created";

        while (scanner.hasNext()) {
            // Extract task components
            String taskRaw = scanner.nextLine();
            assert !taskRaw.equals("") : "taskRaw should not be an empty string";
            String[] taskComponents = taskRaw.split(" \\| ");
            String taskType = taskComponents[0];
            String taskStatus = taskComponents[1];
            String taskDescription = taskComponents[2];

            // Create task
            Task task;
            switch (taskType) {
            case "T":
                task = new Todo(taskDescription);
                break;
            case "D":
                String taskBy = taskComponents[3];
                task = new Deadline(taskDescription, LocalDate.parse(taskBy));
                break;
            case "E":
                String taskAt = taskComponents[3];
                task = new Event(taskDescription, taskAt);
                break;
            default:
                task = null;
                break;
            }
            assert task != null : "Task should not be null";

            // Mark task as done if applicable
            if (taskStatus.equals("1")) {
                task.markAsDone();
            }

            // Add task to taskList
            taskList.add(task);
        }

        // Return taskList
        return taskList;
    }

    /**
     * Saves tasks to storage.
     *
     * @param taskList Tasks to be saved to storage.
     * @throws DukeException If tasks cannot be saved to storage.
     */
    public void save(ArrayList<Task> taskList) throws DukeException {
        try {
            // Instantiate file writer obj
            FileWriter fw = new FileWriter(file);
            assert fw != null : "File writer should be created";

            // Write to file
            taskList.forEach(task -> {
                try {
                    fw.write(task.toStringData() + "\n");
                } catch (IOException e) {
                    System.err.println();
                }
            });

            // Close file writer obj
            fw.close();
        } catch (IOException e) {
            throw new SavingException();
        }
    }
}
