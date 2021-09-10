package duke.task;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Represents a TaskList object.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class TaskList {

    /** ArrayList to store the tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructor for creating a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for creating a TaskList object.
     *
     * @param tasks ArrayList of tasks.
     * @return a TaskList object.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private Integer getTaskNumber(String input) {
        String[] arr = input.split(" ", 2);
        Integer taskNumber = Integer.parseInt(arr[1]);
        return taskNumber;
    }

    /**
     * Adds a Todo task.
     *
     * @param description Description of the Todo.
     * @return String of response after an Event.
     */
    public String addToDo(String description) {
        return addComplete(new Todo(description));
    }

    /**
     * Parses and adds a Event task.
     *
     * @param description Description of Event.
     * @param time Time of the event.
     * @return String of response after adding an Event.
     * @throws InvalidInputException if date/time is entered in the wrong format.
     */
    public String addEvent(String description, String time) throws InvalidInputException {
        String[] split = time.split(" ", 2);
        LocalDate date;
        LocalTime timing;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            date = LocalDate.parse(split[0], formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Format should be in yyyy-mm-dd!");
        }
        if (split.length == 1) {
            return addComplete(new Event(description, date));
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                timing = LocalTime.parse(split[1], formatter);
            } catch (DateTimeParseException e) {
                return "Format should be in HH:mm";
            }
            return addComplete(new Event(description, date, timing));
        }
    }

    /**
     * Parses and adds a Deadline task.
     *
     * @param description Description of Deadline.
     * @param time Time to do the task by.
     * @return String of response after adding a Deadline.
     * @throws InvalidInputException if date/time is entered in the wrong format.
     */
    public String addDeadline(String description, String time) throws InvalidInputException {
        String[] split = time.split(" ", 2);
        LocalDate date;
        LocalTime timing;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            date = LocalDate.parse(split[0], formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Format should be in yyyy-mm-dd!");
        }
        if (split.length == 1) {
            return addComplete(new Deadline(description, date));
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                timing = LocalTime.parse(split[1], formatter);
            } catch (DateTimeParseException e) {
                return "Format should be in HH:mm";
            }
            return addComplete(new Deadline(description, date, timing));
        }
    }

    private String addComplete(Task t) {
        tasks.add(t);
        Storage.saveData(this);
        return Ui.formatMessage("Got it. I've added this task: ", t.toString(), printTaskNumber(this));
    }

    /**
     * Parses input from the user and marks task as done.
     *
     * @param input String that the user inputs.
     */
    public String markTaskDone(String input) {
        try {
            int taskDoneNum = getTaskNumber(input);
            Task taskDone = tasks.get(taskDoneNum - 1);
            taskDone.markAsDone();
            Storage.saveData(this);
            return Ui.formatMessage("Nice, I've marked this task as done:", taskDone.toString());
        } catch (IndexOutOfBoundsException e) {
            return "☹ OOPS!!! No such task can be marked as done!";
        }
    }

    /**
     * Deletes a task from the existing TaskList.
     *
     * @param input String that the user inputs.
     */
    public String deleteTask(String input) {
        try {
            int taskDeleteNum = getTaskNumber(input);
            Task taskToDelete = tasks.get(taskDeleteNum - 1);
            taskToDelete.markUndone();
            tasks.remove(taskDeleteNum - 1);
            Storage.saveData(this);
            return Ui.formatMessage("Noted, I've removed this task: " + taskToDelete, printTaskNumber(this));
        } catch (IndexOutOfBoundsException e) {
            return "☹ OOPS!!! No such task can be deleted!";
        }
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns size of the existing taskList.
     *
     * @return an integer containing the number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index of the Task to be retrieved.
     * @return Task to be retrieved.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns an ArrayList of the existing TaskList.
     *
     * @return ArrayList containing the tasks.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Prints all the tasks in the TaskList.
     *
     * @param tasks a Tasklist object containing the tasks.
     */
    public static String printTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1 + "." + tasks.get(i).toString() + '\n');
        }
        return sb.toString();
    }

    /**
     * Prints the number of tasks in the list.
     *
     * @param tasks a TaskList object containing the tasks.
     */
    public String printTaskNumber(TaskList tasks) {
        return "Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list.";
    }

    /**
     * Prints out tasks with given keyword.
     *
     * @param keyword Keyword to search for.
     * @return String of response of 'find' command.
     */
    public String printTasksWithKeyword(String keyword) {
        String[] tasksFiltered = tasks.stream()
                .map(Task::toString)
                .filter(x -> x.contains(keyword))
                .toArray(String[]::new);

        if (tasksFiltered.length < 1) {
            return "There are no tasks that matches this keyword!";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasksFiltered.length; i++) {
                sb.append(i + 1).append(".").append(tasksFiltered[i]).append('\n');
            }
            return Ui.formatMessage("Here are the tasks that matches your keyword:", sb.toString());
        }
    }
}
