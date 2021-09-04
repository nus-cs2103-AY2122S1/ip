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
     * @throws DukeException If the input string does not make sense.
     */
    public String addTask(String str) throws DukeException {
        Task newTask = null;
        String[] splitUserInput = str.split(" ", 2);
        String taskType = splitUserInput[0];
        String taskDesc;
        String taskDate;
        String[] splitUserInputForDate;

        switch (taskType) {
        case "todo":
            if (Todo.isValid(splitUserInput)) {
                taskDesc = splitUserInput[1];
                newTask = new Todo(taskDesc);
            }
            break;
        case "deadline":
            if (Deadline.isValid(splitUserInput)) {
                splitUserInputForDate = splitUserInput[1].split(" /", 2);
                taskDesc = splitUserInputForDate[0];
                taskDate = splitUserInputForDate[1].substring(3);
                newTask = new Deadline(taskDesc, taskDate);
            }
            break;
        case "event":
            if (Event.isValid(splitUserInput)) {
                splitUserInputForDate = splitUserInput[1].split(" /", 2);
                taskDesc = splitUserInputForDate[0];
                taskDate = splitUserInputForDate[1].substring(3);
                newTask = new Event(taskDesc, taskDate);
            }
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        tasks.add(listIndex, newTask);

        String word;
        if (listIndex == 0) {
            word = "task";
        } else {
            word = "tasks";
        }

        assert newTask != null;
        String output = "Got it. I've added this task:\n"
                + String.format("   [%s][%s] %s\n", newTask.getType(), newTask.getStatusIcon(), newTask.getDescription())
                + String.format("Now you have %d %s in the list.%n", listIndex + 1, word);

        listIndex++;
        return output;
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

        String output = "Nice! I've marked this task as done:\n"
                + String.format("[%s][X] %s\n", currentTask.getType(), currentTask.getDescription());

        return output;
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
        String output = "Noted. I've removed this task:\n"
                + String.format("[%s][%s] %s\n", delTask.getType(), delTask.getStatusIcon(), delTask.getDescription());

        String word;
        if (listIndex == 1) {
            word = "task";
        } else {
            word = "tasks";
        }

        output += String.format("Now you have %d %s in the list.%n", listIndex, word);
        return output;
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
                output += String.format("%d. [%s][%s] %s\n", num, curr.getType(), curr.getStatusIcon(), curr.getDescription());
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
            completeList += String.format("%d. [%s][%s] %s\n", num, curr.getType(), curr.getStatusIcon(), curr.getDescription());
            i++;
        }

        return completeList;
    }
}
