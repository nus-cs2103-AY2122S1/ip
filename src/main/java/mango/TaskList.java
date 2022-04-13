package mango;

import mango.task.Deadline;
import mango.task.Event;
import mango.task.Task;
import mango.task.Todo;

import java.util.ArrayList;

/**
 * Represents a list of tasks that can be manipulated. A <code>TaskList</code> corresponds to
 * an ArrayList of <code>Task</code> objects.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int listIndex = 0;

    /**
     * Constructor for a TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for a TaskList, initialised with a list of tasks.
     *
     * @param tasks An ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        assert tasks != null;
        this.listIndex = tasks.size();
    }

    /**
     * Returns the task list.
     *
     * @return The task list.
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Interprets an input string into its corresponding Task object and adds it to the task list.
     * Returns a confirmation that the Task is added, and the new total number of tasks in the list.
     *
     * @param str The input string that contains the user's command.
     * @return The confirmation message that the task has been added.
     * @throws MangoException If the input string does not make sense.
     */
    public String addTask(String str) throws MangoException {
        Task newTask = null;
        String[] splitUserInput = str.split(" ", 2);
        String taskType = splitUserInput[0];

        switch (taskType) {
        case "todo":
            if (Todo.isValid(splitUserInput)) {
                newTask = createTodo(splitUserInput);
            }
            break;
        case "deadline":
            if (Deadline.isValid(splitUserInput)) {
                newTask = createDeadline(splitUserInput);
            }
            break;
        case "event":
            if (Event.isValid(splitUserInput)) {
                newTask = createEvent(splitUserInput);
            }
            break;
        default:
            throw new MangoException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        tasks.add(listIndex, newTask);
        assert newTask != null;
        listIndex++;

        return getAddTaskMessage(newTask);
    }

    /**
     * Returns a confirmation that the Task is added, and the new total number of tasks in the list.
     * @param newTask The task that was added.
     * @return A confirmation that the Task is added, and the new total number of tasks in the list.
     */
    public String getAddTaskMessage(Task newTask) {
        // for correct grammar
        String word;
        if (this.listIndex == 0) {
            word = "task";
        } else {
            word = "tasks";
        }

        return "Got it. I've added this task:\n" + String.format("   [%s][%s] %s %s\n",
                newTask.getType(), newTask.getStatusIcon(), newTask.getDescription(), "#" + newTask.getTag())
                + String.format("Now you have %d %s in the list.%n", this.listIndex, word);
    }

    /**
     * Creates a new Event based on the user input given.
     * @param splitUserInput The user's input.
     * @return An Event that corresponds to the user's input.
     */
    public Event createEvent(String[] splitUserInput) {
        String[] splitUserInputForDateAndTag = splitUserInput[1].split(" /", 3);
        String taskDesc = splitUserInputForDateAndTag[0];
        String taskDate = splitUserInputForDateAndTag[1].substring(3);
        String taskTag = splitUserInputForDateAndTag[2].substring(4);

        return new Event(taskDesc, taskDate, taskTag);
    }

    /**
     * Creates a new Todo based on the user input given.
     * @param splitUserInput The user's input.
     * @return A Todo that corresponds to the user's input.
     */
    public Todo createTodo(String[] splitUserInput) {
        String[] splitUserInputForTag = splitUserInput[1].split(" /", 2);
        String taskDesc = splitUserInputForTag[0];
        String taskTag = splitUserInputForTag[1].substring(4);
        return new Todo(taskDesc, taskTag);
    }

    /**
     * Creates a new Deadline based on the user input given.
     * @param splitUserInput The user's input.
     * @return A Deadline that corresponds to the user's input.
     */
    public Deadline createDeadline(String[] splitUserInput) {
        String[] splitUserInputForDateAndTag = splitUserInput[1].split(" /", 3);
        String taskDesc = splitUserInputForDateAndTag[0];
        String taskDate = splitUserInputForDateAndTag[1].substring(3);
        String taskTag = splitUserInputForDateAndTag[2].substring(4);

        return new Deadline(taskDesc, taskDate, taskTag);
    }

    /**
     * Marks a Task in the task list as completed.
     * Returns a confirmation that the Task has been marked complete.
     *
     * @param completedTask The task that is to be marked as done.
     * @return The confirmation message that the task has been marked completed.
     */
    public String completeTask(int completedTask) {
        Task currentTask = tasks.get(completedTask - 1);

        if (!currentTask.isDone()) {
            currentTask.markDone();
        }

        return getCompleteTaskMessage(currentTask);
    }

    /**
     * Returns a confirmation that the Task has been marked complete.
     * @param currentTask The task that was marked as done.
     * @return The confirmation message that the task has been marked completed.
     */
    public String getCompleteTaskMessage(Task currentTask) {
        return "Nice! I've marked this task as done:\n"
                + String.format("[%s][X] %s %s\n",
                currentTask.getType(), currentTask.getDescription(), "#" + currentTask.getTag());

    }

    /**
     * Removes a Task from the task list. Returns a confirmation that the Task has been deleted,
     * and the new total number of tasks in the list.
     *
     * @param deleteTask The task to be deleted.
     * @return The confirmation message that the task has been deleted.
     */
    public String deleteTask(int deleteTask) {
        Task delTask = tasks.remove(deleteTask - 1);
        listIndex--;

        return getDeleteTaskMessage(delTask);
    }

    /**
     * Returns a confirmation that the Task is deleted, and the new total number of tasks in the list.
     * @param delTask The task that was deleted.
     * @return A confirmation that the Task is deleted, and the new total number of tasks in the list.
     */
    public String getDeleteTaskMessage(Task delTask) {
        // for correct grammar
        String word;
        if (this.listIndex == 1) {
            word = "task";
        } else {
            word = "tasks";
        }

        return "Noted. I've removed this task:\n" + String.format("[%s][%s] %s %s\n",
                delTask.getType(), delTask.getStatusIcon(), delTask.getDescription(), "#" + delTask.getTag()) +
                String.format("Now you have %d %s in the list.%n", this.listIndex, word);
    }


    /**
     * Searches in the task list for tasks that contain the string given.
     * @param str The string to find.
     * @return The tasks that match the keyword.
     */
    public String findTasks(String str) {
        int i = 0;
        String output = "Here are the matching tasks in your list:\n";

        while (i < listIndex) {
            int num = i+1;
            Task curr = tasks.get(i);
            if (curr.getDescription().contains(str)) {
                output += String.format("%d. [%s][%s] %s %s\n",
                        num, curr.getType(), curr.getStatusIcon(), curr.getDescription(), "#" + curr.getTag());
            }
            i++;
        }
        return output;
    }

    /**
     * Returns the current task list in a string.
     *
     * @return All tasks in the task list in a string.
     */
    public String toString() {
        int i = 0;
        String completeList = "Here are the tasks in your list:\n";

        while (i < listIndex) {
            int num = i+1;
            Task curr = tasks.get(i);
            completeList += String.format("%d. [%s][%s] %s %s\n",
                    num, curr.getType(), curr.getStatusIcon(), curr.getDescription(), "#" + curr.getTag());
            i++;
        }

        return completeList;
    }
}
