package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the list of tasks a user has.
 *
 * @author botr99
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Storage storage;

    /**
     * Constructs a task list with the storage from
     * the user's hard disk.
     *
     * @param storage The storage from the user's hard disk.
     * @throws FileParseException When the file is not of the right format.
     */
    public TaskList(Storage storage) throws FileParseException {
        retrieveTaskListFromLines(storage.getLines());
        this.storage = storage;
    }

    private void retrieveTaskListFromLines(ArrayList<String> lines) throws FileParseException {
        try {
            for (String line : lines) {
                parseLineToTask(line);
            }
        } catch (DukeException | ArrayIndexOutOfBoundsException e) {
            throw new FileParseException("The contents of the file in storage are formatted wrongly.");
        }
    }

    private void parseLineToTask(String line) throws DukeException {
        String[] params = line.split(" \\| ");
        String taskType = params[0];
        boolean isTaskDone = params[1].equals("1");
        String description = params[2];

        switch (taskType) {
        case "T":
            tasks.add(new Todo(description, isTaskDone));
            break;
        case "D":
            tasks.add(new Deadline(description, params[3], isTaskDone));
            break;
        case "E":
            tasks.add(new Event(description, params[3], isTaskDone));
            break;
        }
    }

    /**
     * Adds a task to the task list and updates the storage.
     *
     * @param task The task to be added.
     * @return The added task.
     * @throws IOException When an error occurs when writing to the file.
     */
    public Task addTask(Task task) throws IOException {
        storage.addLine(task);
        tasks.add(task);
        return task;
    }

    /**
     * Marks the nth task in the task list
     * to be done, whereby n represents the task number;
     * and updates the storage.
     *
     * @param taskNumber The number n to access the nth task in the task list.
     * @return The task that was marked as done.
     * @throws IOException When an error occurs when writing to the file.
     */
    public Task markTask(int taskNumber) throws IOException {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        storage.editLine(taskNumber, task);
        return task;
    }

    /**
     * Deletes the nth task in the task list,
     * whereby n represents the task number;
     * and updates the storage.
     *
     * @param taskNumber The number n to access the nth task in the task list.
     * @return The task that was deleted.
     * @throws IOException When an error occurs when writing to the file.
     */
    public Task deleteTask(int taskNumber) throws IOException {
        storage.removeLine(taskNumber);
        return tasks.remove(taskNumber - 1);
    }

    /**
     * Gets the current size of the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets the task located at the index.
     *
     * @param index The zero-based index of the task list.
     * @return The task accessed from the index of the task list.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns a string whereby the task list is represented
     * as a numbered list.
     *
     * @return The string representation of a task list.
     */
    @Override
    public String toString() {
        StringBuilder tasksString = new StringBuilder();

        for (int i = 0; i < getSize(); i++) {
            int taskNumber = i + 1;
            tasksString
                    .append(taskNumber)
                    .append(".")
                    .append(getTask(i))
                    .append(i == (getSize() - 1) ? "" : "\n");
        }

        return tasksString.toString();
    }

}
