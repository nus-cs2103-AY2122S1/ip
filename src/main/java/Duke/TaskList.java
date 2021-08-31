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
    public String markDone(int index) {

        tasks.get(index).markAsDone();

        String temp = tasks.get(index).toString();
        return "Nice! I've marked this task as done: \n" + temp;
    }

    /**
     * Adds a Todo to the TaskList and
     * returns an updated array list of tasks.
     * @param details The description of the Todo.
     * @return The updated array list of tasks.
     */
    public String addTodo(String details) {
        Todo task = new Todo(details);

        tasks.add(task);
        return "Got it. I've added this task: /n" + task + "\n" +
                "Now you have " + tasks.size() + " task(s) in the list.";

    }

    /**
     * Adds a Deadline to the TaskList and
     * returns an updated array list of tasks.
     * @param details The description of the Deadline.
     * @param date The date of the Deadline.
     * @return The updated array list of tasks.
     */
    public String addDeadline(String details, LocalDate date) {
        Deadline task = new Deadline(details, date);

        tasks.add(task);
        return "Got it. I've added this task: \n" + task + "\n" +
                "Now you have " + tasks.size() + " task(s) in the list.";

    }

    /**
     * Adds an Event to the TaskList and
     * returns an updated array list of tasks.
     * @param details The description of the event.
     * @param at The details of the event.
     * @return The updated array list of tasks.
     */
    public String addEvent(String details, String at) {
        Event task = new Event(details, at);
        tasks.add(task);


        return "Got it. I've added this task: \n" + task + "\n" +
                "Now you have " + tasks.size() + " task(s) in the list.";
    }

    /**
     * Deletes a specified task from the Task List and
     * returns an updated array list of tasks.
     * @param index The task index.
     * @return An updated array list of tasks.
     */
    public String deleteTask(int index) {
        String temp = tasks.get(index).toString();

        tasks.remove(index);

        return "Got it. I've removed this task: \n" + temp + "\n" +
                "Now you have " + tasks.size() + " task(s) in the list.";
    }

    /**
     * Lists out all tasks in the Task List.
     */
    public String listTasks() {
        if (tasks.size() == 0) {
            return "There is no task for now :)";
        } else {
            String res = "Here are the tasks in your list: \n";

            for (int i = 1; i < tasks.size() + 1; i++) {
                String task = i + "." + tasks.get(i - 1).toString() + "\n";
                res = res + task;
            }
            return res;
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
