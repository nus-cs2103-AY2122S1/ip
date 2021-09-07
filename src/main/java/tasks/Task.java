package tasks;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.NoSuchCommandException;
import exceptions.NoTaskNameException;

/**
 * The Task class encapsulates all the details of each task.
 *
 * @author Quan Teng Foong
 */

public abstract class Task {
    private final String message;
    private Boolean completed;

    /**
     * Constructor for Task object.
     *
     * @param message name of Task
     */
    public Task(String message) {
        this.message = message;
        this.completed = false;
    }

    /**
     * Factory method for a Task
     *
     * @param command the type of Task to create
     * @param input the contents of the task
     * @return the corresponding Task
     * @throws NoSuchCommandException if the command does not match any of the known
     * commands
     * @throws NoTaskNameException if there is no task name
     */
    public static Task createTask(String command, String input) throws NoSuchCommandException, NoTaskNameException {
        if (input.isEmpty()) {
            throw new NoTaskNameException("No task name, please try again.");
        }
        input = input.trim();
        switch (command) {
        case "todo":
            return new ToDo(input);
        case "event":
            String[] messageAndEventDate = Parser.splitBy(input, "/at");
            return new Event(messageAndEventDate[0], messageAndEventDate[1]);
        case "deadline":
            String[] messageAndEndTime = Parser.splitBy(input, "/by");
            return new Deadline(messageAndEndTime[0], messageAndEndTime[1]);
        default:
            throw new NoSuchCommandException("No such command!");
        }
    }

    /**
     * Creates a Task from the String used in storage.
     *
     * @param line read from the storage file
     * @return the corresponding Task
     * @throws NoSuchCommandException if the command does not match any of the known
     * commands
     * @throws NoTaskNameException if there is no task name
     */
    public static Task createTaskFromString(String line) throws NoTaskNameException, NoSuchCommandException {
        String[] parameters = line.split("\\|");
        String command;
        switch (parameters[0]) {
        case "T":
            command = "todo";
            break;
        case "E":
            command = "event";
            break;
        default:
            command = "deadline";
        }
        String input = parameters[2];
        Task task = Task.createTask(command, input);
        if (parameters[1].equals("1")) {
            task.doTask();
        }
        return task;
    }

    /**
     * Overrides toString() method.
     * @return String representation of the Task object
     */
    @Override
    public String toString() {
        return "[" + (this.completed ? "X" : " ") + "] " + this.message;
    }

    /**
     * Sets the Task as complete.
     */
    public void doTask() {
        this.completed = true;
    }

    /**
     * Sets the Task as complete.
     *
     * @param taskList the current TaskList
     */
    public void doTask(TaskList taskList) {
        this.completed = true;
    }

    /**
     * Converts contents to a storable String.
     *
     * @return a String that represents this Task in storage
     */
    public String toStorage() {
        return (this.completed ? "1|" : "0|") + this.message;
    }

    /**
     * Returns the message field of the Task.
     *
     * @return the message field as a String
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Overrides clone method.
     */
    @Override
    public abstract Task clone();
}
