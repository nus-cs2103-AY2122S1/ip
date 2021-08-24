package mango;

import mango.task.*;

import java.util.ArrayList;

/**
 * Represents a list of tasks that can be manipulated. A <code>TaskList</code> corresponds to
 * an ArrayList of <code>Task</code> objects.
 */
public class TaskList {
    private ArrayList<Task> list;
    private int listIndex = 0;

    /**
     * Constructor for a TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for a TaskList, initialised with a list of tasks.
     *
     * @param tasks An ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
        this.listIndex = tasks.size();
    }

    /**
     * Returns the task list.
     *
     * @return The task list.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Interprets an input string into its corresponding Task object and adds it to the task list.
     * Prints a confirmation that the Task is added, and the new total number of tasks in the list.
     *
     * @param str The input string that contains the user's command.
     * @throws DukeException If the input string does not make sense.
     */
    public void add(String str) throws DukeException {
        Task newTask = null;
        String[] arr1 = str.split(" ", 2);
        String type = arr1[0];
        String desc;
        String date;
        String[] arr2;

        switch (type) {
        case "todo":
            if (Todo.isValid(arr1)) {
                desc = arr1[1];
                newTask = new Todo(desc);
            }
            break;
        case "deadline":
            if (Deadline.isValid(arr1)) {
                arr2 = arr1[1].split(" /", 2);
                desc = arr2[0];
                date = arr2[1].substring(3);
                newTask = new Deadline(desc, date);
            }
            break;
        case "event":
            if (Event.isValid(arr1)) {
                arr2 = arr1[1].split(" /", 2);
                desc = arr2[0];
                date = arr2[1].substring(3);
                newTask = new Event(desc, date);
            }
            break;
        default:
            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        list.add(listIndex, newTask);
        String word;
        if (listIndex == 0) {
            word = "task";
        } else {
            word = "tasks";
        }

        System.out.println("Got it. I've added this task:");
        System.out.printf("   [%s][%s] %s\n", newTask.getType(), newTask.getStatusIcon(), newTask.getDescription());
        System.out.printf("Now you have %d %s in the list.%n", listIndex + 1, word);

        listIndex++;
    }

    /**
     * Marks a Task in the task list as completed.
     * Prints a confirmation that the Task has been marked complete.
     *
     * @param completedTask The task that is to be marked as done.
     */
    public void complete(int completedTask) {
        Task currentTask = list.get(completedTask - 1);
        if (!currentTask.isDone()) {
            currentTask.markDone();
        }

        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("[%s][X] %s\n", currentTask.getType(), currentTask.getDescription());
    }

    /**
     * Removes a Task from the task list. Prints a confirmation that the Task has been deleted,
     * and the new total number of tasks in the list.
     *
     * @param deleteTask The task to be deleted.
     */
    public void delete(int deleteTask) {
        Task delTask = list.remove(deleteTask - 1);
        listIndex--;
        System.out.println("Noted. I've removed this task:");
        System.out.printf("[%s][%s] %s\n", delTask.getType(), delTask.getStatusIcon(), delTask.getDescription());

        String word;
        if (listIndex == 1) {
            word = "task";
        } else {
            word = "tasks";
        }

        System.out.printf("Now you have %d %s in the list.%n", listIndex, word);
    }

    /**
     * Prints the current task list.
     */
    public void printList() {
        int i = 0;
        System.out.println("Here are the tasks in your list:");
        while (i < listIndex) {
            int num = i+1;
            Task curr = list.get(i);
            System.out.printf("%d. [%s][%s] %s\n", num, curr.getType(), curr.getStatusIcon(), curr.getDescription());
            i++;
        }
    }
}
