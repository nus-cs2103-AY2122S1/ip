package duke.util;

import duke.exceptions.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

/**
 * Storage class to retrieve and store tasks in Duke.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor method of Storage.
     *
     * @param filePath file path for data storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads tasks from file.
     *
     * @throws DukeException If the file cannot be found.
     */
    public static void loadFromFile(TaskList taskList) throws DukeException {
        try {
            File file = setupStorage();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String row = sc.nextLine();
                String[] itemDetails = row.split("/");
                handleData(itemDetails, taskList);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Something went wrong: cannot find the file");
        }
    }

    /**
     * Returns file for storage.
     *
     * @return File.
     * @throws DukeException Duke exception.
     */
    public static File setupStorage() throws DukeException {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();

        Path path = Paths.get(s, "data", "Duke.txt");
        File file = new File(String.valueOf(path));
        try {
            if (!file.exists()) {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Error loading from storage");
        }
        return file;
    }


    /**
     * Processes data from itemDetails and add them into taskList.
     *
     * @param itemDetails Task details in string array.
     * @param taskList    TaskList that manages tasks.
     */
    public static void handleData(String[] itemDetails, TaskList taskList) throws DukeException {
        String task_type = itemDetails[0];
        switch (task_type) {
        case "T":
            Todo todo = new Todo(itemDetails[2]);
            if (itemDetails[1].equals("1")) {
                todo.markAsDone();
            }
            taskList.addTask(todo);
            break;
        case "D":
            Deadline deadline = new Deadline(itemDetails[2], itemDetails[3]);
            if (itemDetails[1].equals("1")) {
                deadline.markAsDone();
            }
            taskList.addTask(deadline);
            break;
        case "E":
            Event event = new Event(itemDetails[2], itemDetails[3]);
            if (itemDetails[1].equals("1")) {
                event.markAsDone();
            }
            taskList.addTask(event);
            break;
        default:
            throw new DukeException("Something went wrong: cannot process the data.");
        }
    }


    /**
     * Writes to file.
     *
     * @param tasks The list of tasks to write from.
     * @throws IOException In case of invalid directory.
     */
    public static void writeToFile(TaskList tasks) throws DukeException {
        assert tasks != null : "Task list has not been initialized";
        try {
            File file = setupStorage();
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks.getTasks()) {
                String[] data = task.formatTaskInArray();
                if (data.length == 3) {
                    fw.write(data[0] + "/" + data[1] + "/" + data[2] + System.lineSeparator());
                } else if (data.length == 4) {
                    fw.write(data[0] + "/" + data[1] + "/" + data[2] + "/" + data[3] + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong: cannot load from Storage.");
        }
    }
}
