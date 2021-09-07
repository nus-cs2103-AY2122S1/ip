package duke.storage;

import java.io.IOException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.FileParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Stores the tasks located in the user's hard disk.
 *
 * @author botr99
 */
public class TaskStorage extends Storage {
    /**
     * Constructs a TaskStorage that reads in lines from the specified
     * file location.
     *
     * @param fileLocation The file location to read the lines from.
     * @throws IOException When an error occurs when reading to the file.
     */
    public TaskStorage(String fileLocation) throws IOException {
        super(fileLocation);
    }

    /**
     * Parses the file and generates the user's tasks.
     *
     * @return The tasks constructed from parsing each line in the file, stored
     *         in an ArrayList.
     * @throws FileParseException When there is an error in retrieving the tasks
     *                            from the file due to invalid file format.
     */
    public ArrayList<Task> loadTasks() throws FileParseException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            for (String line : getLines()) {
                tasks.add(parseLineToTask(line));
            }
            return tasks;
        } catch (DukeException | ArrayIndexOutOfBoundsException e) {
            throw new FileParseException("The contents of the file in storage are formatted wrongly.");
        }
    }

    private Task parseLineToTask(String line) throws DukeException, FileParseException {
        String[] params = line.split(" \\| ");
        String taskType = params[0];

        boolean isTaskDone;
        switch (params[1]) {
        case "0":
        case "1":
            isTaskDone = params[1].equals("1");
            break;
        default:
            throw new FileParseException("The contents of the file in storage are formatted wrongly.");
        }

        String description = params[2];

        Task task;
        switch (taskType) {
        case "T":
            task = new Todo(description, isTaskDone);
            break;
        case "D":
            task = new Deadline(description, params[3], isTaskDone);
            break;
        case "E":
            task = new Event(description, params[3], isTaskDone);
            break;
        default:
            throw new FileParseException("The contents of the file in storage are formatted wrongly.");
        }

        return task;
    }

    /**
     * Updates the task storage with a task.
     *
     * @param task The task to be added.
     * @throws IOException When an error occurs when writing to the file.
     */
    public void addTaskToStorage(Task task) throws IOException {
        addLine(task.toStorageString());
    }

    /**
     * Removes the task in the task storage
     * which corresponds to the line number.
     *
     * @param lineNumber The line to be removed from the task storage.
     * @throws IOException When an error occurs when writing to the file.
     */
    public void removeTaskFromStorage(int lineNumber) throws IOException {
        removeLine(lineNumber);
    }

    /**
     * Modifies a line specified by the line number that corresponds to a task number,
     * in the task storage.
     *
     * @param lineNumber The line number to modify the task stored in the task storage.
     * @param task The task to be replaced with in the task storage.
     * @throws IOException When an error occurs when writing to the file.
     */
    public void editTaskFromStorage(int lineNumber, Task task) throws IOException {
        editLine(lineNumber, task.toStorageString());
    }

}
