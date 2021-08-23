package petal.components;

import petal.exception.*;
import petal.task.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    //List of user inputted tasks
    private final List<Task> tasks;
    private final Ui ui;

    public TaskList(Ui ui) {
        this.ui = ui;
        tasks = new ArrayList<>();
    }

    /**
     * Overloaded method to add previously saved tasks to the list of tasks
     * @param addTasks The arraylist of previously saved tasks
     */
    public void addTask(ArrayList<Task> addTasks) {
        tasks.addAll(addTasks);
        ui.output(Responses.WELCOME_BACK);
    }

    /**
     * Method to add a task to list of tasks
     * @param task The task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
        String plural = (tasks.size() + 1) > 0 ? " tasks!" : " task!";
        ui.output("Okay. I've added this task:\n" + task + "\nYou now have " + tasks.size() + plural);
    }

    /**
     * Method to delete a task from the list of tasks
     * @param index The message given by the user input
     * @throws InvalidInputException Thrown if no index inputted by the user or
     *                               when index is out-of-bounds/not valid int or when
     *                               desc is empty
     */
    public void deleteTask(String index) throws InvalidInputException {
        try {
            int indexOfTask = Integer.parseInt(index) - 1;
            Task toBeDeleted = tasks.remove(indexOfTask);
            ui.output("Okay. I've deleted this task:\n" + toBeDeleted  + "\nYou now have " + tasks.size()
                    + " task(s)!");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException("Invalid task number given! Please enter another value!", e);
        }
    }

    /**
     * Method to handle the tasks, depending on the command given
     * @param type The type of task: To.Do, deadline, event
     * @param message The desc/time of the task
     * @throws EmptyDescException Thrown when the task lacks a description
     * @throws InvalidInputException Thrown when an invalid format is given or when a time is not given
     */
    public void handleTasks(String type, String message) throws EmptyDescException, InvalidInputException {
        Task task;
        String[] deadlineEvent = type.equals("deadline") ? message.split("/by")
                : message.split("/at");
        if (message.isBlank() || deadlineEvent[0].isBlank()) {
            throw new EmptyDescException("The description cannot be empty! Enter a valid one! :(");
        }
        if ((type.equals("deadline") || type.equals("event")) && deadlineEvent.length < 2) {
            //No time given or the command /by or /at wasn't given by the user
            throw new InvalidInputException("The format used was wrong! Try again :(");
        }
        switch (type) {
            case "todo":
                task = new ToDo(message, false);
                break;
            case "deadline":
                try {
                    task = new Deadline(deadlineEvent[0], deadlineEvent[1], false);
                } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                    throw new InvalidInputException("The date/time format used was wrong! Try again :(");
                }
                break;
            default: //Represents the Event task
                try {
                    task = new Event(deadlineEvent[0], deadlineEvent[1], false);
                } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                    throw new InvalidInputException("The date/time format used was wrong! Try again :(");
                }
        }
        addTask(task);
    }

    /**
     * Method to mark a particular task as done
     * @param indexOfTask String representation of the index of the task
     * @throws IndexOutOfBoundsException Thrown if string is not within size of list
     * @throws NumberFormatException Thrown if string cannot be converted into valid int
     */
    public void markTaskAsDone(String indexOfTask) throws InvalidInputException {
        try {
            Task taskToBeCompleted = tasks.get(Integer.parseInt(indexOfTask) - 1);
            taskToBeCompleted.taskDone();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException("Invalid task number given! Please enter another value!", e);
        }
    }

    /**
     * Method that returns the string representations of the tasks
     * @return String containing the number, type, and description of tasks
     */
    public String printList() {
        if (tasks.size() == 0)
            return "No items in list yet!";
        int count = 1;
        StringBuilder list = new StringBuilder();
        for (Task m : tasks) {
            //I do this check to ensure there isn't a newline at the top
            if (count == 1) {
                list.append(count++).append(". ").append(m);
            } else {
                list.append("\n").append(count++).append(". ").append(m);
            }
        }
        return list.toString();
    }

    /**
     * Method that takes the tasks and returns a formatted string representation
     * which can be easily parsed by retrieveTasks() once the program is run again
     * @return Formatted string representation of all the user-added tasks
     */
    public String formatForSaving() {
        if (tasks.size() == 0) {
            return "";
        }
        int count = 1;
        StringBuilder result = new StringBuilder();
        for (Task m : tasks) {
            if (count == 1) {
                result.append(m.strForSaving());
            } else {
                result.append("\n").append(m.strForSaving());
            }
            count++;
        }
        return result.toString();
    }

    public void tasksOnThisDay(String date) throws InvalidInputException {
        try {
            String deadlines = Deadline.deadlinesOnDate(date);
            String events = Event.eventsOnDate(date);
            ui.output(deadlines + "\n" + events);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException("The date/time format used was wrong! Try again :(");
        }

    }

}
