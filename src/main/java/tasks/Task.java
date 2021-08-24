package tasks;

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

    public Task(String message){
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
    private static Task createTask(String command, String input) throws NoSuchCommandException, NoTaskNameException {
        if (input.isEmpty()) {
            throw new NoTaskNameException("No task name, please try again.");
        }
        switch (command) {
        case "todo":
            return new ToDo(input);
        case "event":
            String[] message_and_eventDate = input.split("/at ");
            return new Event(message_and_eventDate[0], message_and_eventDate[1]);
        case "deadline":
            String[] message_and_endTime = input.split("/by ");
            return new Deadline(message_and_endTime[0], message_and_endTime[1]);
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
        return "[" + (this.completed ? "X" : " ")  + "] " + this.message;
    }

    /**
     * Sets the Task to completed.
     */
    public void doTask() {
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
}
