package duke.task;

import duke.exception.DukeException;
import duke.storage.Storage;

import java.util.ArrayList;

/**
 * Represents the central task class that implements all operations on a task. This
 * class is responsible for the behaviour of all its subclasses; Todo, Event and Deadline.
 *
 * @author yeo-yiheng
 */
public class TaskList {

    protected String description;
    private boolean isDone;
    protected static final Storage FILE = new Storage("data/tasks.txt");
    protected static ArrayList<TaskList> tasks = new ArrayList<>();

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
        FILE.overwriteList(tasks);
    }

    /**
     * Sets the task with input index as done. This causes the
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
            return false;
        } else {
            tasks.get(index).setDone();
            return true;
        }
    }

    /**
     * Removes the task at the specified index on the task list. Thereafter,
     * a file overwrite is called to update the file contents.
     *
     * @param index the index of the task on the task list to be deleted
     */
    public void delete(int index) {
        tasks.remove(index);
        FILE.overwriteList(tasks);
    }

    /**
     * Distinguishes the type of task to be created as inputted by the user. If
     * it is none of the 3 subclass tasks, a custom DukeException is thrown to
     * warn the users that the input is unrecognized. Once recognized, if the
     * description of the task is empty, a custom DukeException is thrown to warn
     * the users that the input lacks a description. Additionally, the timeline of
     * the Deadline and Event subclasses cannot be empty or another custom DukeException
     * is thrown to warn users that the input lacks a timeline.
     *
     * @param input the user input from the command line
     */
    public TaskList add(String input) throws DukeException {
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

            String todoDescription = splitTask[1].trim() + " ";

            if (checkDuplicate(todoDescription)) {
                throw new DukeException.DuplicateTaskException(todoDescription);
            }

            return new Todo(todoDescription, false);
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

            String deadlineDescription = splitTime[0].trim() + " ";

            if (checkDuplicate(deadlineDescription)) {
                throw new DukeException.DuplicateTaskException(deadlineDescription);
            }

            return new Deadline(deadlineDescription, splitTime[1], false);
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

            String eventDescription = splitTimeEvent[0].trim() + " ";

            if (checkDuplicate(eventDescription)) {
                throw new DukeException.DuplicateTaskException(eventDescription);
            }

            return new Event(eventDescription, splitTimeEvent[1], false);
        default:
            throw new DukeException.InvalidCommandException(input);
        }

        if (emptyDescription) {
            throw new DukeException.EmptyDescriptionException();
        } else {
            throw new DukeException.EmptyTimelineDescription(emptyTimelineError);
        }
    }

    /**
     * Checks for duplicate tasks in the task list.
     *
     * @param description description of task inputted by user
     * @return true if the task is a duplicate; false otherwise
     */
    private boolean checkDuplicate(String description) {
        boolean isDuplicate = false;
        for (TaskList t : tasks) {
            String currentDescription = t.getDescription();
            if (currentDescription.equals(description)) {
                isDuplicate = true;
                break;
            }
        }
        return isDuplicate;
    }

    /**
     * Responsible for creating the task based on the file storage contents. This
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
     * In-charge of loading the contents of the file storage into the temporary
     * arraylist that holds the tasks in order. This ensures that the user is always interacting
     * with the contents of the storage file at the start.
     */
    public void loadArrayList() {
        FILE.loadFile();
        FILE.overwriteList(tasks);
    }

    /**
     * Retrieves the task based on the index in the task list.
     *
     * @param index the index of the task in the task list to be retrieved
     * @return the specified task retrieved from the task list
     */
    public static TaskList getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            return null;
        } else {
            return tasks.get(index);
        }
    }

    /**
     * Finds the tasks that matches the input keyword and subsequently
     * returns a collection of all tasks that matches the keyword.
     *
     * @param searchWord the provided keyword to match to the tasks
     * @return a collection of all tasks that matches the keyword
     */
    public static ArrayList<TaskList> findMatching(String searchWord) {
        ArrayList<TaskList> matchingTasks = new ArrayList<>();
        for (TaskList t : tasks) {
            String description = t.getDescription();
            if (description.contains(searchWord)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    public String getDescription() {
        return this.description;
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
    public static String displayList() {
        return FILE.printTaskFile();
    }
}
