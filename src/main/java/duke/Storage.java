package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Handles data persistence for duke.Duke.
 */
public class Storage {

    /**
     * Delimiter character for separating fields.
     */
    public static final char DELIMITER = 30;

    /**
     * File object for accessing the storage file.
     */
    private final File file;

    Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Read the tasks from the storage file.
     * @return List of Tasks read from storage
     */
    List<Task> readTasks() {
        try {
            Scanner fileScanner = new Scanner(this.file);

            List<Task> tasks = new ArrayList<>();

            while (fileScanner.hasNext()) {
                tasks.add(decodeLine(fileScanner.nextLine()));
            }

            return tasks;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Serialize the tasks and write them to the storage file.
     * @param tasks List of tasks to be saved
     */
    void saveTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() > 0) {
            for (int i = 0; i < tasks.size() - 1; ++i) {
                sb.append(tasks.get(i).encode());
                sb.append("\n");
            }
            sb.append(tasks.get(tasks.size() - 1).encode());
        }
        try {
            this.file.getParentFile().mkdirs();
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(this.file, false);
            fileWriter.write(sb.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("I could not save your tasks to memory! " + e.getMessage());
        }
    }

    /**
     * Converts the line to the corresponding duke.tasks.Task
     * @param line Serialized String containing the data of the duke.tasks.Task
     * @return duke.tasks.Task obtained from deserializing the line
     */
    private Task decodeLine(String line) {
        String[] parts = line.split(String.valueOf(DELIMITER));
        String taskType = parts[0];
        boolean completed = parts[1].equals("1");

        switch (taskType) {
        case "t":
            String description = joinFrom(String.valueOf(DELIMITER), parts, 2);
            return new ToDo(description, completed);
        case "d":
            String by = parts[2];
            return new Deadline(
                    joinFrom(String.valueOf(DELIMITER), parts, 3),
                    completed,
                    LocalDate.parse(parts[2], DateTimeFormatter.ISO_LOCAL_DATE)
            );
        case "e":
            String at = parts[2];
            return new Event(
                    joinFrom(String.valueOf(DELIMITER), parts, 3),
                    completed,
                    LocalDate.parse(parts[2], DateTimeFormatter.ISO_LOCAL_DATE)
            );
        default:
            throw new DukeException("Invalid memory!");
        }
    }

    /**
     * Joins an array of Strings starting from an index.
     * @param delimiter Delimiter string to place between elements
     * @param elements Array of Strings to be joined
     * @param from Index of starting element
     * @return
     */
    private String joinFrom(String delimiter, String[] elements, int from) {
        StringBuilder sb = new StringBuilder();
        for (int i = from; i < elements.length - 1; ++i) {
            sb.append(elements[i]);
            sb.append(delimiter);
        }
        sb.append(elements[elements.length - 1]);
        return sb.toString();
    }
}
