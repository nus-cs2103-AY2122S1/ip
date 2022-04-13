package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeIncorrectCommandWord;
import duke.exception.DukeIncorrectTaskDescription;
import duke.exception.DukeMissingTaskDescription;

/**
 * Represents a Task object that can be added
 * to users' task list.
 * A Task can be a ToDo, Deadline or Event.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public abstract class Task implements Comparable<Task> {
    protected boolean isDone;
    protected String task;

    protected final int GREATER = 1;
    protected final int SMALLER = -1;
    protected final int EQUAL = 0;

    protected Task(String task) {
        this.isDone = false;
        this.task = task;
    }

    /**
     * Creates a new Task object.
     *
     * @param task String description of the task.
     * @throws DukeException if Duke cannot create the task.
     */
    public static Task createTask(String task) throws DukeException {
        String[] taskArr = task.split(" ", 2);
        String firstWord = taskArr[0];

        if (taskArr.length < 2) {
            deriveTaskError(firstWord);
        }

        Task toCreate = deriveTask(firstWord, taskArr);

        return toCreate;
    }

    private static void deriveTaskError(String firstWord) throws DukeException {
        switch (firstWord) {
        case "todo":
            throw new DukeMissingTaskDescription(new ToDo(" "), new IllegalArgumentException());
        case "deadline":
            throw new DukeMissingTaskDescription(new Deadline(new String[2]), new IllegalArgumentException());
        case "event":
            throw new DukeMissingTaskDescription(new Event(new String[2]), new IllegalArgumentException());
        default:
            throw new DukeIncorrectCommandWord(new IllegalArgumentException());
        }
    }

    private static Task deriveTask(String firstWord, String[] taskArr) throws DukeException {
        switch (firstWord) {
        case "todo":
            return new ToDo(taskArr[1]);
        case "deadline":
            checkFormat(firstWord, taskArr);
            return new Deadline(taskArr[1].split("/by", 2));
        case "event":
            checkFormat(firstWord, taskArr);
            return new Event(taskArr[1].split("/at", 2));
        default:
            throw new DukeIncorrectCommandWord(new IllegalArgumentException());
        }
    }

    private static void checkFormat(String firstWord, String[] taskArr) {
        switch (firstWord) {
        case "deadline":
            if (taskArr[1].split("/by", 2).length < 2) {
                String[] emptyString = new String[2];
                throw new DukeIncorrectTaskDescription(new Deadline(emptyString), new IllegalArgumentException());
            }
            return;
        case "event":
            if (taskArr[1].split("/at", 2).length < 2) {
                String[] emptyString = new String[2];
                throw new DukeIncorrectTaskDescription(new Event(emptyString), new IllegalArgumentException());
            }
            return;
        default:
            return;
        }
    }

    /**
     * Sets the task to be done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Represents the format in which the File is saved to hard-drive.
     */
    public String saveToFile() {
        String checker = this.isDone ? "1" : "0";
        return checker + " | " + this.task;
    }

    /**
     * String representation of the Task object
     */
    @Override
    public String toString() {
        String checker = this.isDone ? "X" : " ";
        return "[" + checker + "]" + " " + this.task;
    }
}
