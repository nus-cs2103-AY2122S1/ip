package duke;

import java.time.LocalDate;
import java.time.LocalTime;
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
     * Marks a specified task as completed and
     * updates the array list of tasks. Returns response from Duke.
     * @param index The index of the task.
     * @return Response of Duke after successful marking of completed Task.
     */
    public String markDone(int index) {
        assert tasks != null : "TaskList cannot be found!";
        tasks.get(index).markAsDone();

        String temp = tasks.get(index).toString();
        return "Nice! I've marked this task as done: \n" + temp;
    }

    /**
     * Adds a Todo to the TaskList and
     * updates the array list of tasks. Returns response from Duke.
     * @param details The description of the Todo.
     * @return Response of Duke after successful addition of Todo.
     */
    public String addTodo(String details) {
        Todo task = new Todo(details);

        assert tasks != null : "TaskList cannot be found!";
        tasks.add(task);

        return "Got it. I've added this task: \n" + task + "\n" +
                "Now you have " + tasks.size() + " task(s) in the list.";

    }

    /**
     * Adds a Deadline to the TaskList and
     * updates the array list of tasks. Returns response from Duke.
     * @param details The description of the Deadline.
     * @param date The date of the Deadline.
     *
     * @return Response of Duke after successful addition of Deadline.
     */
    public String addDeadline(String details, LocalDate date, LocalTime time) {
        Deadline task = new Deadline(details, date, time);

        assert tasks != null : "TaskList cannot be found!";
        tasks.add(task);

        return "Got it. I've added this task: \n" + task + "\n" +
                "Now you have " + tasks.size() + " task(s) in the list.";

    }

    /**
     * Adds an Event to the TaskList and
     * updates the array list of tasks. Returns response from Duke.
     * @param details The description of the event.
     * @param startDate The start date of the event.
     * @param startTime The start time of the event.
     * @param endDate The end date of the event.
     * @param endTime The end time of the event.
     * @return Response of Duke after successful addition of Event.
     */
    public String addEvent(String details, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        Event task = new Event(details, startDate, startTime, endDate, endTime);

        assert tasks != null : "TaskList cannot be found!";
        tasks.add(task);

        return "Got it. I've added this task: \n" + task + "\n" +
                "Now you have " + tasks.size() + " task(s) in the list.";
    }

    /**
     * Deletes a specified task from the Task List and
     * updates the array list of tasks. Returns response from Duke.
     * @param index The task index.
     * @return Response of Duke after successful delete.
     */
    public String deleteTask(int index) {
        String temp = tasks.get(index).toString();

        assert tasks != null : "TaskList cannot be found!";
        tasks.remove(index);

        return "Got it. I've removed this task: \n" + temp + "\n" +
                "Now you have " + tasks.size() + " task(s) in the list.";
    }

    /**
     * Lists out all tasks in the Task List.
     * @return The string representation of the full Task List.
     */
    public String listTasks() {
        assert tasks != null : "TaskList cannot be found!";

        if (tasks.size() == 0) {
            return "There is no task for now :)";
        }

        String response = "Here are the tasks in your list: \n";
        for (int i = 1; i < tasks.size() + 1; i++) {
            String task = i + "." + tasks.get(i - 1).toString() + "\n";
            response = response + task;
        }
        return response;
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

    public String sortByDate() {
        if (tasks.size() == 0) {
            return "There is no task for now :)";
        } else {
            ArrayList<Task> sorted = sortHelper(tasks);

            String response = "We have sorted the list by Date for you!: \n";
            for (int i = 1; i < sorted.size() + 1; i++) {
                String task = i + "." + sorted.get(i - 1).toString() + "\n";

                response = response + task;
            }
            return response;
        }

    }

    private ArrayList<Task> sortHelper(ArrayList<Task> unsorted) {
        if (unsorted.size() <= 0) {
            return unsorted;
        }
        ArrayList<Task> smaller = new ArrayList<>();
        ArrayList<Task> greater = new ArrayList<>();

        Task pivot = unsorted.get(0);  // used as pivot

        for (int index = 1; index < unsorted.size(); index++) {
            Task current = unsorted.get(index);

            if (current.compareTo(pivot) <= 0) {
                smaller.add(current);
            } else {
                greater.add(current);
            }
        }

        smaller = sortHelper(smaller);
        greater = sortHelper(greater);

        ArrayList<Task> sorted = new ArrayList<>(smaller);

        sorted.add(pivot);
        sorted.addAll(greater);

        return sorted;
    }


}
