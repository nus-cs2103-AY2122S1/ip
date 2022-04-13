package duke.task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;

/**
 * Contains the task list.
 */
public class TaskList {
    private static final String ALREADY_EXIST = "This task has already been added! Please add another task.";
    private static final String DATE_TIME_FORMAT = "Please follow this format when entering date and time:\n"
            + "DD/MM/YYYY 24-Hour Time Format" + " e.g. (01/01/2020 2359)";
    private static final String EMPTY_TASKLIST = "You do not have any task. Please add a task first!";
    private static final String INVALID_INDEX = "Please enter a valid task number between 1 and %d!";

    /** A collection of the task */
    private final ArrayList<Task> taskList;

    /**
     * Constructor of TaskList.
     * Initialises TaskList according to a file loaded by Storage.
     *
     * @param data String representation of task given by Storage.
     * @throws DukeException Throw DukeException.
     */
    public TaskList(String data) throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("No previous data found.\nLet's start a new To-Do List!");
        }

        taskList = new ArrayList<>();
        Scanner sc = new Scanner(data);

        while (sc.hasNext()) {
            int taskType = Integer.parseInt(sc.nextLine());
            boolean isDone = Boolean.parseBoolean(sc.nextLine());

            assert taskType < 3 && taskType >= 0 : "File is corrupted!";

            switch (taskType) {
            case 0:
                String tDescription = sc.nextLine();
                Todo tTask = new Todo(isDone, tDescription);

                taskList.add(tTask);
                break;
            case 1:
                String dDescription = sc.nextLine();
                String by = sc.nextLine();
                Deadline dTask = new Deadline(isDone, dDescription, by);

                taskList.add(dTask);
                break;
            case 2:
                String eDescription = sc.nextLine();
                String at = sc.nextLine();
                Event eTask = new Event(isDone, eDescription, at);

                taskList.add(eTask);
                break;
            default:
                throw new DukeException("File is corrupted!");
            }
        }
    }

    /**
     * Constructor for TaskList.
     * Starts a new task list.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param input String representation of task description.
     * @param taskType Type of task.
     * @return The Task object added to task list.
     * @throws DukeException Throw DukeException.
     */
    public Task add(int taskType, String... input) throws DukeException {
        Task task;

        assert taskType < 3 && taskType >= 0 : "TaskType is invalid!";

        try {
            switch (taskType) {
            case 0:
                String tDescription = input[0];

                Todo todo = new Todo(false, tDescription);

                if (todo.checkIfAlreadyAdded(taskList)) {
                    throw new DukeException(ALREADY_EXIST + "todo");
                } else {
                    task = todo;
                }
                break;
            case 1:
                String dDescription = input[0];
                String by = input[1];

                Deadline deadline = new Deadline(false, dDescription, by);

                if (deadline.checkIfAlreadyAdded(taskList)) {
                    throw new DukeException(ALREADY_EXIST + "deadline");
                } else {
                    task = deadline;
                }
                break;
            case 2:
                String eDescription = input[0];
                String at = input[1];

                Event event = new Event(false, eDescription, at);

                if (event.checkIfAlreadyAdded(taskList)) {
                    throw new DukeException(ALREADY_EXIST + "event");
                } else {
                    task = event;
                }
                break;
            default:
                throw new DukeException("Not a valid Task!!");

            }
        } catch (DateTimeParseException e) {
            throw new DukeException(DATE_TIME_FORMAT);
        }
        taskList.add(task);
        return task;
    }

    /**
     * Marks the specified task as done.
     *
     * @param i The task number of the task that is to be marked as done.
     * @return The Task object that is marked as done.
     * @throws DukeException Throw DukeException.
     */
    public Task done(int i) throws DukeException {
        checkForIndex(i);

        if (!taskList.get(i - 1).markAsDone()) {
            return null;
        }

        return taskList.get(i - 1);
    }

    /**
     * Deletes the specified task.
     *
     * @param i The task number of the task that is to be deleted.
     * @return The Task object that is deleted.
     * @throws DukeException Throw DukeException.
     */
    public Task delete(int i) throws DukeException {
        checkForIndex(i);

        return taskList.remove(i - 1);
    }

    /**
     * Throws a `DukeException` is the index is invalid.
     *
     * @param i Index of the task.
     * @throws DukeException Throw a DukeException is index is invalid.
     */
    public void checkForIndex(int i) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException(EMPTY_TASKLIST);
        }

        if (i > taskList.size() || i < 1) {
            throw new DukeException(String.format(INVALID_INDEX,
                    taskList.size()));
        }
    }


    /**
     * Looks for keyword in TaskList.
     * Adds `Task` object with keyword into a list.
     *
     * @param relatedList The list that tasks with keyword are added to.
     * @param keyword The keyword.
     */
    public void find(ArrayList<Task> relatedList, String keyword) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).hasKeyword(keyword)) {
                relatedList.add(taskList.get(i));
            }
        }
    }

    /**
     * Converts all the tasks in TaskList into String representation.
     * Given to Storage to be saved into a file.
     *
     * @return String representation of all the tasks in task list.
     */
    public String checkOut() {
        String str = "";

        for (int i = 0; i < taskList.size(); i++) {
            str += taskList.get(i).saveAsData();
        }

        return str;
    }

    /**
     * Returns the number of tasks in TaskList.
     *
     * @return The number of tasks in task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Checks if TaskList is empty/
     *
     * @return True if task list is empty. Returns false otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Returns a String representation of all the Task objects in TaskList.
     *
     * @return String representation of all the tasks in task list.
     */
    @Override
    public String toString() {
        String str = "";
        int size = taskList.size();

        for (int i = 0; i < size - 1; i++) {
            str += String.format("%d.%s\n", i + 1, taskList.get(i));
        }

        str += String.format("%d.%s", size, taskList.get(size - 1));

        return str;
    }

}
