package Duke;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A list that contains all the tasks saved in the file.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * A public constructor to create a TaskList
     * that takes in an array list of tasks.
     * @param tasks ArrayList of the tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * A public constructor to create a TaskLIst
     * that takes in no argument.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Marks a specified task as completed
     * and returns an updated array list of tasks.
     * @param index The index of the task.
     * @return An updated array list of tasks.
     */
    public ArrayList<Task> markDone(int index) {

        tasks.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(index).toString());
            return tasks;
    }

    /**
     * Adds a Todo to the TaskList and
     * returns an updated array list of tasks.
     * @param details The description of the Todo.
     * @return The updated array list of tasks.
     */
    public ArrayList<Task> addTodo(String details) {
        Todo task = new Todo(details);

        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");

        return tasks;
    }

    /**
     * Adds a Deadline to the TaskList and
     * returns an updated array list of tasks.
     * @param details The description of the Deadline.
     * @param date The date of the Deadline.
     * @return The updated array list of tasks.
     */
    public ArrayList<Task> addDeadline(String details, LocalDate date) {
        Deadline task = new Deadline(details, date);

        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");

        return tasks;
    }

    /**
     * Adds an Event to the TaskList and
     * returns an updated array list of tasks.
     * @param details The description of the event.
     * @param at The details of the event.
     * @return The updated array list of tasks.
     */
    public ArrayList<Task> addEvent(String details, String at) {
        Event task = new Event(details, at);
        tasks.add(task);


        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");

        return tasks;
    }

    /**
     * Deletes a specified task from the Task List and
     * returns an updated array list of tasks.
     * @param index The task index.
     * @return An updated array list of tasks.
     */
    public ArrayList<Task> deleteTask(int index) {
        String temp = tasks.get(index).toString();

        tasks.remove(index);

        System.out.println("Noted. I've removed this task: ");
        System.out.println(temp);

        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");

        return tasks;
    }

    /**
     * Lists out all tasks in the Task List.
     */
    public void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("There is no task for now :)");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < tasks.size() + 1; i++) {
                System.out.println(i + "." + tasks.get(i - 1).toString());
            }
        }
    }

    /**
     * Returns the length of the task list.
     * @return the length of the task list.
     */
    public int arrSize() {
        return tasks.size();
    }

    /**
     * Returns the Array List representation of the TaskList.
     * @return the Array List representation of the TaskList.
     */
    public ArrayList<Task> inArrayList() {
        return tasks;
    }


}
