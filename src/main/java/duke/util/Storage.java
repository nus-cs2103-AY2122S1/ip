package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.Duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Class that stores Duke data to the hard disk and reads Duke data from the hard disk.
 */
public class Storage {
    private static String saveLocation;

    /**
     * Returns a new Storage object.
     * @param filePath The path to save the Duke file to.
     */
    public Storage(String filePath) {
        saveLocation = String.valueOf(Paths.get(System.getProperty("user.home"), filePath));
    }

    /**
     * Parses a task as String to a Task object.
     * @param input The input task.
     * @return The task as a Task object.
     * @throws DukeException If the task is of an unexpected type.
     */
    public static Task parseTask(String input) throws DukeException {
        try {
            char taskType = input.charAt(4);
            boolean isDone = input.charAt(7) == 'X';
            String taskName = input.substring(10);
            Task newTask;

            assert taskType == 'T' || taskType == 'D' || taskType == 'E' : "Unexpected task type in save file";

            switch (taskType) {
            case 'T':
                newTask = new Todo(taskName, isDone);
                break;
            case 'D':
                newTask = new Deadline(taskName, isDone);
                break;
            case 'E':
                newTask = new Event(taskName, isDone);
                break;
            default:
                throw new DukeException("OOPS! Unexpected task type: " + taskType);
            }
            return newTask;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Corrupted file");
        }
    }

    /**
     * Reads the Duke file data.
     * @return The tasks.
     * @throws DukeException If the Duke file is not found.
     */
    public static List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(saveLocation);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                tasks.add(parseTask(data));
            }

            reader.close();
        } catch (IOException | DukeException e) {
            throw new DukeException("Corrupted file");
        }

        return tasks;
    }

    /**
     * Saves the Duke file to hard disk.
     * @param input The tasks.
     */
    public static void saveFile(String input) {
        try {
            FileWriter writer = new FileWriter(saveLocation);
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
