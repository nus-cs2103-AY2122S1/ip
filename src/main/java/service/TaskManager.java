package service;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskManager class.
 *
 * This class acts as the manager of tasks held by the Duke.
 */
public class TaskManager {

    /** Error messages. */

    private static final String INVALID_TASK_ERROR_MESSAGE = "Task number '%d' is invalid.";
    private static final String FULL_CAPACITY_ERROR_MESSAGE = "Unable to execute as list is full.";
    private static final String EMPTY_LIST_ERROR_MESSAGE = "Unable to execute as list is empty.";

    /** Output messages for tasks. */

    private static final String EMPTY_LIST_MESSAGE = "List is empty, try adding some tasks first.";
    private static final String TASK_DONE_MESSAGE = "Nice! I've marked this task as done:\n\t%s";
    private static final String TASK_LIST_CONTENTS = "Here are the task(s) in your list:";
    private static final String TASK_DELETED_MESSAGE =
            "Noted. I've removed this task:\n\t%s\nNow you have %d task(s) in the list.";
    private static final String CREATE_TODO_MESSAGE =
            "Got it. I've added this TODO task:\n\t%s\nNow you have %d task(s) in the list.";
    private static final String CREATE_EVENT_MESSAGE =
            "Got it. I've added this EVENT task:\n\t%s\nNow you have %d task(s) in the list.";
    private static final String CREATE_DEADLINE_MESSAGE =
            "Got it. I've added this DEADLINE task:\n\t%s\nNow you have %d task(s) in the list.";


    private final static int MAX_STORAGE = 100;

    private final List<Task> TASK_LIST = new ArrayList<>();

    /**
     * Gets the current number of tasks stored.
     *
     * @return number of tasks stored currently
     */
    public int getTaskListSize() {
        return TASK_LIST.size();
    }

    /**
     * Adds a task to the task list. Returns saved task.
     *
     * @param newTask task to save
     * @return saved task
     * @throws DukeException if task cannot be saved, due to full capacity of task list
     */
    public Task addTask(Task newTask) throws DukeException {
        if (TASK_LIST.size() == MAX_STORAGE) {
            throw new DukeException(FULL_CAPACITY_ERROR_MESSAGE);
        }
        TASK_LIST.add(newTask);
        return newTask;
    }

    /**
     * Adds a Todo Task, returns an output message.
     *
     * @param userParams user input with parameters for a Todo
     * @return String message of successful creation of Todo
     * @throws DukeException if task list is full
     */
    public String addToDoTask(String userParams) throws DukeException {
        Todo todo = new Todo(userParams); // desc
        Task task = addTask(todo);
        return String.format(CREATE_TODO_MESSAGE, task, getTaskListSize());
    }

    /**
     * Adds an Event Task, returns an output message.
     *
     * @param userParams user input with parameters for a Deadline
     * @return String message of successful creation of Event
     * @throws DukeException if task list is full
     */
    public String addEventTask(String[] userParams) throws DukeException {
        Event event = new Event(userParams[0], userParams[1]); // desc, timing
        Task task = addTask(event);
        return String.format(CREATE_EVENT_MESSAGE, task, getTaskListSize());
    }

    /**
     * Adds a Deadline Task, returns an output message.
     *
     * @param userParams user input with parameters for a Deadline
     * @return String message of successful creation of Deadline
     * @throws DukeException if task list is full
     */
    public String addDeadlineTask(String[] userParams) throws DukeException {
        Deadline deadline = new Deadline(userParams[0], userParams[1]); // desc, by
        Task task = addTask(deadline);
        return String.format(CREATE_DEADLINE_MESSAGE, task, getTaskListSize());
    }

    /**
     * Marks a Task completed based on the input Task number fed as a String.
     * Provides an output message on return.
     *
     * @param taskNumber String format of the Task number to delete
     * @return String message of successful completion marking of Task
     * @throws DukeException if the Task number is not valid
     */
    public String markTaskAsDone(int taskNumber) throws DukeException {
        Task selectedTask = getTaskFromNumberString(taskNumber);
        selectedTask.markAsDone();
        return String.format(TASK_DONE_MESSAGE, selectedTask);
    }

    /**
     * Deletes a Task based on the input Task number fed as a String.
     * Provides an output message on return.
     *
     * @param taskNumber String format of the Task number to delete
     * @return String message of successful deletion of Task
     * @throws DukeException if the Task number is not valid
     */
    public String deleteTask(int taskNumber) throws DukeException {
        Task selectedTask = getTaskFromNumberString(taskNumber);
        TASK_LIST.remove(selectedTask); // remove shifts tasks to the right backwards
        return String.format(TASK_DELETED_MESSAGE, selectedTask, getTaskListSize());
    }

    /**
     * Gets the Task based on the input Task number fed as a String.
     *
     * @param taskNumber String format of the Task number to obtain
     * @return associated Task
     * @throws DukeException if the Task List is empty or the Task number is not valid
     */
    public Task getTaskFromNumberString(int taskNumber) throws DukeException {
        if (TASK_LIST.isEmpty()) {
            throw new DukeException(EMPTY_LIST_ERROR_MESSAGE);
        }
        if (taskNumber < 0 || TASK_LIST.size() < taskNumber) {
            throw new DukeException(String.format(INVALID_TASK_ERROR_MESSAGE, taskNumber));
        }
        return TASK_LIST.get(taskNumber - 1); // shift to 0-indexing
    }

    /**
     * Provides the Tasks in a formatted String, to be fed to a text printer.
     *
     * @return formatted tasks in String
     */
    public String getTaskList() {
        if (TASK_LIST.isEmpty()) {
            return EMPTY_LIST_MESSAGE;
        }
        StringBuilder taskManagerStringList = new StringBuilder();
        taskManagerStringList.append(TASK_LIST_CONTENTS);

        for (int idx = 0; idx < TASK_LIST.size(); idx ++) {
            Task task = TASK_LIST.get(idx);
            int taskNumber = idx + 1; // shift to 1-indexing
            taskManagerStringList.append(String.format("\n\t%d. %s", taskNumber, task));
        }
        return taskManagerStringList.toString();
    }
}
