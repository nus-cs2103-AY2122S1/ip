package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Represents a list of Task
 */
public class TaskList {

    private ArrayList<Task> tasks;

    // For storing information of last action, use for undo method
    private Action lastAction = Action.NONE;
    private Task lastTask;
    private ArrayList<Task> lastTaskList;
    private int lastPosition;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Adds a task to task list.
     *
     * @param task a task to be added to list
     * @return string containing the return message
     */
    public String add(Task task) {
        tasks.add(task);
        lastAction = Action.ADD;
        lastPosition = tasks.size();
        StringBuilder resultString = new StringBuilder();
        resultString.append("Got it. I've added this task: \n" + task + "\n");
        resultString.append("Now you have " + tasks.size() + " tasks in the list.\n");
        save();
        return resultString.toString();
    }

    /** Removes the last added task.
     *
     * @return string containing the return message
     */
    private String undoAdd() {
        assert lastAction.equals(Action.ADD);
        String result = remove(lastPosition);
        save();
        return result;
    }

    /**
     * Marks a task as done.
     *
     * @param number the task number in the task list
     * @return string containing the return message
     */
    public String markDone(int number) {
        if (number < 1 || number > tasks.size()) {
            return "The number is out of range :(";
        }
        Task task = tasks.get(number - 1);
        lastAction = Action.DONE;
        lastTask = task;
        task.markDone();
        save();
        return "Nice! I've marked this task as done: \n" + task + "\n";
    }

    /** Marks previously marked task as not done. */
    private String undoMarkDone() {
        assert lastAction.equals(Action.DONE);
        lastTask.markNotDone();
        lastAction = Action.NONE;
        save();
        return "I have mark the task back to undone: \n" + lastTask + "\n";
    }

    /**
     * Removes task from task list.
     *
     * @param number the task number in the task list
     * @return string containing the return message
     */
    public String remove(int number) {
        if (number < 1 || number > tasks.size()) {
            return "The number is out of range :(";
        }
        Task task = tasks.get(number - 1);
        lastAction = Action.DELETE;
        lastTask = task;
        lastPosition = number;
        tasks.remove(number - 1);
        save();
        StringBuilder resultString = new StringBuilder();
        resultString.append("Noted. I've removed this task: \n" + task + "\n");
        resultString.append("Now you have " + tasks.size() + " tasks in the list.\n");
        return resultString.toString();
    }

    /** Adds previously removed task back into list. */
    private String undoRemove() {
        tasks.add(lastPosition - 1, lastTask);
        lastAction = Action.ADD;
        return "I have added the task back into the list: \n" + lastTask + "\n";
    }

    /**
     * Prints all the task in task list.
     */
    public void list() {
        System.out.print(this);
    }

    /**
     * Formats the tasks into a list form.
     *
     * @return String of all task in list form
     */
    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        int counter = 1;
        for (Task task: tasks) {
            resultString.append(counter + ". " + task + "\n");
            counter++;
        }
        if (counter == 1) {
            resultString.append("You have no more task!\n");
        }
        return resultString.toString();
    }

    /**
     * Prints all the task that contains the keyword.
     *
     * @param keyword a string to find task
     * @return string containing the result of finding the keyword
     */
    public String find(String keyword) {
        StringBuilder resultString = new StringBuilder();
        int counter = 1;
        resultString.append("The following tasks contain keyword: " + keyword + "\n");
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                resultString.append(counter + "." + task + "\n");
            }
            counter++;
        }
        return resultString.toString();
    }

    /**
     * Removes all task in task list.
     *
     * @return string containing the return message
     */
    public String clear() {
        lastAction = Action.CLEAR;
        lastTaskList = tasks;
        tasks = new ArrayList<>();
        save();
        return "All your tasks have been cleared!";
    }

    private String undoClear() {
        assert lastAction.equals(Action.CLEAR);
        tasks = lastTaskList;
        lastAction = Action.NONE;
        save();
        return "All your tasks have been added back!";
    }

    /**
     * Returns if task list is empty.
     *
     * @return if task list is empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns number of task in list.
     *
     * @return if task list is empty
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns task list saved from previous session.
     *
     * @return a TaskList object
     */
    public static TaskList load() throws FileNotFoundException {
        return new TaskList(Storage.retrieveTaskList());
    }

    /**
     * Stores task list on local computer as a text file.
     */
    public void save() {
        try {
            Storage.saveTaskList(tasks);
        } catch (Exception exception) {
            System.out.println("Saving failed: " + exception);
        }
    }

    /**
     * Undo the last action.
     *
     * @return string containing the return message
     */
    public String undo() {
        switch(lastAction) {
        case CLEAR:
            return undoClear();
        case DONE:
            return undoMarkDone();
        case ADD:
            return undoAdd();
        case DELETE:
            return undoRemove();
        case NONE:
            // Fallthrough
        default:
            return "No last action to undo.";
        }
    }
}
