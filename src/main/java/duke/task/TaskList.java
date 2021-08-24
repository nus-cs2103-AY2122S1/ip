package duke.task;

import duke.storage.Storage;
import duke.ui.UserInterface;
import duke.exception.DukeException;

import java.util.ArrayList;

/**
 * Represents the central task class that implements all operations on a task. This
 * class is responsible for the behaviour of all its subclasses; Todo, Event and Deadline.
 */
public class TaskList {

    protected String description;
    private boolean isDone;
    protected static final Storage file = new Storage("data/tasks.txt");
    protected static final ArrayList<TaskList> tasks = new ArrayList<>();
    protected static final UserInterface userInterface = new UserInterface();

    /**
     * Creates an instance of a TaskList class. This is the parent task class which
     * supports and implements the behavior of its subclasses.
     *
     * @param description the description of our task to be logged
     */
    protected TaskList(String description) {
        this.description = description;
        this.isDone = false;
        tasks.add(this);
    }

    /**
     * An empty and default TaskList constructor.
     */
    public TaskList() {}

    /**
     * Sets the status of a task to be done, i.e., isDone = true.
     */
    private void setDone() {
        this.isDone = true;
        file.overwriteList(tasks);
    }

    /**
     * This method sets the task with input index as done. This causes the
     * method call of setDone on that particular instance of task. Thereafter,
     * a file overwrite is called to update the file contents. This method
     * similarly checks if the input index is valid. If it isn't valid, a warning
     * will be printed by our User Interface class.
     *
     * @param index the index of the task on the task list to be marked done
     * @return true if task is successfully marked done; false otherwise
     */
    public boolean markDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            userInterface.invalidIntegerWarning();
            return false;
        } else {
            tasks.get(index).setDone();
            return true;
        }
    }

    /**
     * This method removes the task at the specified index on the task list. Thereafter,
     * a file overwrite is called to update the file contents.
     *
     * @param index the index of the task on the task list to be deleted
     */
    public void delete(int index) {
        tasks.remove(index);
        file.overwriteList(tasks);
    }

    /**
     * This method is responsible for distinguishing the type of task to be created
     * as inputted by the user. If it is none of the 3 subclass tasks, a custom
     * DukeException is thrown to warn the users that the input is unrecognized.
     * Once recognized, if the description of the task is empty, a custom DukeException
     * is thrown to warn the users that the input lacks a description. Additionally,
     * the timeline of the Deadline and Event subclasses cannot be empty or another
     * custom DukeException is thrown to warn users that the input lacks a timeline.
     *
     * @param input the user input from the command line
     */
    public void add(String input) {
        input = input.trim();
        String emptyTimelineError = null;
        String[] splitTask = input.split(" ", 2);
        boolean emptyDescription = false;

        switch (splitTask[0]) {
        case "todo":
            if (splitTask.length == 1) {
                emptyDescription = true;
                break;
            }
            new Todo(splitTask[1], false);
            break;
        case "deadline":
            if (splitTask.length == 1) {
                emptyDescription = true;
                break;
            }

            String[] splitTime = splitTask[1].split("/", 2);
            if (splitTime.length == 1) {
                emptyTimelineError = "deadline";
                break;
            }

            new Deadline(splitTime[0], splitTime[1], false);
            break;
        case "event":
            if (splitTask.length == 1) {
                emptyDescription = true;
                break;
            }

            String[] splitTimeEvent = splitTask[1].split("/", 2);
            if (splitTimeEvent.length == 1) {
                emptyTimelineError = "event";
                break;
            }

            new Event(splitTimeEvent[0], splitTimeEvent[1], false);
            break;
        default:
            throw new DukeException.InvalidCommandException(input);
        }

        if (emptyDescription) {
            throw new DukeException.EmptyDescriptionException();
        } else if (emptyTimelineError != null) {
            throw new DukeException.EmptyTimelineDescription(emptyTimelineError);
        }
    }

    /**
     * This method is responsible for creating the task based on the file storage contents. This
     * method differs from the original add method as it does not go through any checks and parsing
     * is already done.
     *
     * @param taskChar the identifier for the type of task class to be created
     * @param taskStatus the identifier for the status of task class to be created
     * @param description the description of the task
     * @param timeline the timeline of the task
     * @return the new task created based on the given parameters
     */
    public TaskList addExisting(char taskChar, char taskStatus, String description, String timeline) {
        TaskList currentTask;

        if (taskChar == 'T') {
            currentTask = new Todo(description, true);
        } else if (taskChar == 'D') {
            currentTask = new Deadline(description, timeline, true);
        } else {
            currentTask = new Event(description, timeline, true);
        }

        if (taskStatus == 'X') {
            currentTask.setDone();
        }
        return currentTask;
    }

    /**
     * This method is in charge of loading the contents of the file storage into the temporary
     * arraylist that holds the tasks in order. This ensures that the user is always interacting
     * with the contents of the storage file at the start.
     */
    public void loadArrayList() {
        file.loadFile();
        file.overwriteList(tasks);
    }

    /**
     * This method retrieves the task based on the index in the task list.
     *
     * @param index the index of the task in the task list to be retrieved
     * @return the specified task retrieved from the task list
     */
    public static TaskList getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            userInterface.invalidIntegerWarning();
            return null;
        } else {
            return tasks.get(index);
        }
    }

    /**
     * Returns the status icon of the task, depending on whether it is marked
     * done or not. Done is denoted by X, and whitespace otherwise.
     *
     * @return a String representing whether the task is done, where X denotes done and whitespace
     * denotes otherwise
     */
    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the length of the task list.
     *
     * @return length of task list
     */
    public static int listLength() {
        return tasks.size();
    }

    /**
     * Prints the contents of the task list onto the command line.
     */
    public static void displayList() {
        userInterface.taskListHeader();
        file.printTaskFile();
    }
}
