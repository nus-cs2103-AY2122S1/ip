package petal.components;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import petal.exception.EmptyDescException;
import petal.exception.InvalidInputException;
import petal.task.Deadline;
import petal.task.Event;
import petal.task.Task;
import petal.task.Timeable;
import petal.task.ToDo;

/**
 * The TaskList class represents the tasks, and handles the operations
 * done to the tasks
 */
public class TaskList {

    private final List<Task> tasks;
    private final Ui ui;
    private final Calendar calendar;

    /**
     * Constructor for TaskList
     *
     * @param ui The instance of Ui used
     */
    public TaskList(Ui ui) {
        this.ui = ui;
        this.tasks = new ArrayList<>();
        calendar = new Calendar();
    }

    /**
     * Adds the list of previously saved tasks to the list of tasks
     *
     * @param addTasks The arraylist of previously saved tasks
     */
    public void addTask(ArrayList<Task> addTasks) {
        tasks.addAll(addTasks);
        ui.output(Responses.WELCOME_BACK);
    }

    /**
     * Adds a task to the list of tasks
     *
     * @param task The task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
        String plural = (tasks.size() + 1) > 0 ? " tasks!" : " task!";
        ui.output("Okay. I've added this task:\n" + task + "\nYou now have " + tasks.size() + plural);
    }

    /**
     * Deletes a task from the list of tasks
     *
     * @param index The message given by the user input
     * @throws InvalidInputException Thrown if no index inputted by the user or
     *                               when index is out-of-bounds/not valid int or when
     *                               desc is empty
     */
    public void deleteTask(String index) throws InvalidInputException {
        try {
            int indexOfTask = Integer.parseInt(index) - 1;
            Task toBeDeleted = tasks.remove(indexOfTask);
            if (toBeDeleted.isTimeable()) {
                calendar.updateCalendar(tasks);
            }
            ui.output("Okay. I've deleted this task:\n" + toBeDeleted + "\nYou now have " + tasks.size()
                    + " task(s)!");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException(Responses.INVALID_TASK_NUMBER, e);
        }
    }

    /**
     * Handles the task, depending on the command given
     *
     * @param type The type of task: To.Do, deadline, event
     * @param message The desc/time of the task
     * @throws EmptyDescException Thrown when the task lacks a description
     * @throws InvalidInputException Thrown when an invalid format is given or when a time is not given
     */
    public void handleTask(String type, String message) throws EmptyDescException, InvalidInputException {
        Task task;
        String[] deadlineEvent = type.equals("deadline") ? message.split("/by")
                : message.split("/at");
        if (message.isBlank() || deadlineEvent[0].isBlank()) {
            throw new EmptyDescException(Responses.EMPTY_DESCRIPTION);
        }
        if ((type.equals("deadline") || type.equals("event")) && deadlineEvent.length < 2) {
            //No time given or the command /by or /at wasn't given by the user
            throw new InvalidInputException(Responses.INVALID_FORMAT);
        }
        switch (type) {
        case "todo":
            task = new ToDo(message, false);
            break;
        case "deadline":
            try {
                task = new Deadline(deadlineEvent[0], deadlineEvent[1], false);
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                throw new InvalidInputException(Responses.INVALID_DATE_TIME);
            }
            break;
        default: //Represents the Event task
            try {
                task = new Event(deadlineEvent[0], deadlineEvent[1], false);
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                throw new InvalidInputException(Responses.INVALID_DATE_TIME);
            }
        }
        addTask(task);
        if (task.isTimeable()) {
            calendar.addToCalendar((Timeable) task);
        }
    }

    /**
     * Marks a particular task as done
     *
     * @param indexOfTask String representation of the index of the task
     * @throws IndexOutOfBoundsException Thrown if string is not within size of list
     * @throws NumberFormatException Thrown if string cannot be converted into valid int
     */
    public void markTaskAsDone(String indexOfTask) throws InvalidInputException {
        try {
            Task taskToBeCompleted = tasks.get(Integer.parseInt(indexOfTask) - 1);
            taskToBeCompleted.taskDone();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException(Responses.INVALID_TASK_NO);
        }
    }

    /**
     * Returns a string representation of the tasks
     *
     * @return String containing the number, type, and description of tasks
     */
    public String printList() {
        if (tasks.size() == 0) {
            return "No items in list yet!";
        }
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
     * Displays the tasks on the given date
     *
     * @param date The date given
     * @throws InvalidInputException Thrown if the parameter has an invalid format/input
     */
    public void showTaskOnDate(String date) throws InvalidInputException {
        ui.output(calendar.showTasksOnDate(Parser.parseDate(date)));
    }

    /**
     * Displays tasks with the keyword
     *
     * @param keyword The keyword
     */
    public void findTaskWithKeyword(String keyword) throws InvalidInputException {
        keyword = keyword.trim();
        if (keyword.equals("")) {
            throw new InvalidInputException(Responses.INVALID_FORMAT);
        } else {
            int count = 1;
            StringBuilder result = new StringBuilder("Here are the tasks:");
            for (Task t : tasks) {
                if (t.isKeyWordPresent(keyword)) {
                    result.append('\n').append(count++).append(". ").append(t);
                }
            }
            if (count == 1) { //No tasks appended
                ui.output("No tasks!");
            } else {
                ui.output(result.toString());
            }
        }
    }

    /**
     * Returns a formatted string representation of the list of tasks that can be used for saving
     *
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
                result.append(m.formatStrForSaving());
            } else {
                result.append("\n").append(m.formatStrForSaving());
            }
            count++;
        }
        return result.toString();
    }

}
