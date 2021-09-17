package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Responsible for loading and saving user's tasks on disk.
 */
public class Storage {
    public final static String DIR_PATH = "./data";
    public final static String FILE_NAME = "tasks";

    /**
     * Returns file object which contains the tasks stored on disk.
     *
     * @return file object which contains tasks stored on disk.
     */
    private static File getTaskFile() {
        File file = new File(DIR_PATH + "/" + FILE_NAME + ".txt");
        return file;
    }

    /**
     * Loads tasks stored in file on disk into the task list.
     *
     * @param tasks Task list.
     * @throws StorageException If file storing the tasks is incorrectly formatted.
     */
    public static void loadTasks(TaskList tasks) throws StorageException {
        try {
            File taskFile = getTaskFile();
            Scanner sc = new Scanner(taskFile);
            while (sc.hasNext()) {
                String taskString = sc.nextLine();
                Task task = parseTaskFromStorage(taskString);

                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException ignored) {
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new StorageException("Txt file for loading tasks is wrongly formatted. "
                    + "Some tasks were not loaded");
        }
    }

    private static Task parseTaskFromStorage(String taskString) throws
            ArrayIndexOutOfBoundsException, DateTimeParseException {

        String[] splitTaskString = taskString.split(Task.STORAGE_DELIMITER);
        String taskIdentifier = splitTaskString[0];
        String taskDescription = splitTaskString[1];
        Boolean isTaskDone = Boolean.parseBoolean(splitTaskString[2]);

        Task task = null;

        switch (taskIdentifier) {
        case Todo.IDENTIFIER:
            task = new Todo(taskDescription, isTaskDone);
            break;
        case Event.IDENTIFIER:
            String eventDate = splitTaskString[3];
            task = new Event(taskDescription, isTaskDone, eventDate);
            break;
        case Deadline.IDENTIFIER:
            String dueDate = splitTaskString[3];
            task = new Deadline(taskDescription, isTaskDone, dueDate);
            break;
        }
        return task;
    }

    /**
     * Saves users tasks in a file on disk.
     *
     * @param tasks List of users tasks.
     * @param fileName Name of file where tasks should be saved.
     * @throws StorageException If unable to write tasks to a file on disk.
     */
    public static void saveTasksToFile(TaskList tasks, String fileName) throws StorageException {
        assert tasks != null : "Task list should not be null in order to save tasks on disk";

        try {
            File base_dir = new File(DIR_PATH);
            if (!base_dir.exists()) {
                base_dir.mkdirs();
            }
            FileWriter fw = new FileWriter(DIR_PATH + "/" + fileName + ".txt");
            fw.write(tasks.toStorageFormat());
            fw.close();
        } catch (IOException e) {
            throw new StorageException("There was an error in saving your tasks to disk");
        }
    }
}
