package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Ui;

/**
 * A class that contains the base functionality for a task list,
 * including adding, deleting, listing, and marking tasks as done.
 */
public class TaskList {
    private final List<Task> tasks;
    private final String recentMessage;

    /**
     * Creates a task list with no tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        recentMessage = "";
    }

    /**
     * Creates a task list with a given list of tasks and a message.
     *
     * @param tasks A list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        this.recentMessage = "";
    }

    /**
     * Creates a task list with a given list of tasks and a message.
     *
     * @param tasks         A list of tasks.
     * @param recentMessage A string generated from running a command.
     */
    public TaskList(List<Task> tasks, String recentMessage) {
        this.tasks = new ArrayList<>(tasks);
        this.recentMessage = recentMessage;
    }

    /**
     * Creates a task list with a given TaskList and a message.
     *
     * @param otherTaskList A TaskList object representing a list of tasks.
     * @param recentMessage A string generated from running a command.
     */
    public TaskList(TaskList otherTaskList, String recentMessage) {
        this.tasks = new ArrayList<>(otherTaskList.tasks);
        this.recentMessage = recentMessage;
    }

    /**
     * Applies a function to every task in the task list.
     *
     * @param consumer A function that is to be applied to each task in the list.
     */
    public void forEach(Consumer<? super Task> consumer) {
        tasks.forEach(consumer);
    }

    /**
     * Adds a task to the task list with a task name.
     * Task is either a todo, deadline or event task depending on the input.
     *
     * @param input String containing user input.
     * @param ui    Object that handles user interface functionality. (e.g. printing)
     * @return A new TaskList instance containing the new task and an output message.
     * @throws DukeException If input contains |, or is in an invalid format.
     */
    public TaskList addTask(String input, Ui ui) throws DukeException {
        List<Task> newTasks = new ArrayList<>(tasks);
        String message = "";
        if (input.contains("|")) {
            throw new DukeException("Input contains |, which is an invalid character.");
        }
        Task task;
        String commandString = input.split(" ")[0].toUpperCase();
        switch (commandString) {
        case "TODO":
            // Add Todo task
            if (input.length() <= 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            task = new Todo(input.substring(5));
            break;
        case "DEADLINE":
            // Add Deadline task
            String errorMessage = "Command must be in the format: [taskName] /by "
                    + "[date(YYYY-MM-DD)] [time(HH:MM)].";
            String[] splitInputs = Parser.splitWith(input, 9, " /by ", errorMessage);
            String taskName = splitInputs[0];
            errorMessage = "Date and time must be in the format: YYYY-MM-DD HH:MM.";
            String[] dateTimeInputs = Parser.splitWith(splitInputs[1], 0, " ", errorMessage);
            String date = dateTimeInputs[0];
            String time = dateTimeInputs[1];
            task = new Deadline(taskName, Parser.parseDateFromInput(date), Parser.parseTimeFromInput(time));
            break;
        default: // default is guaranteed to be event task due to use of enum + outer control flow
            // Add Event task
            errorMessage = "Command must be in the format: [taskName] /at "
                    + "[date(YYYY-MM-DD)] [start time(HH:MM)] [end time(HH:MM)].";
            splitInputs = Parser.splitWith(input, 6, " /at ", errorMessage);
            taskName = splitInputs[0];
            errorMessage = "Date and times must be in the format: YYYY-MM-DD HH:MM HH:MM.";
            dateTimeInputs = Parser.splitWith(splitInputs[1], 0, " ", errorMessage);
            // If user only input in one time
            if (dateTimeInputs.length < 3) {
                throw new DukeException(errorMessage);
            }
            date = dateTimeInputs[0];
            String startTime = dateTimeInputs[1];
            String endTime = dateTimeInputs[2];
            task = new Event(taskName, Parser.parseDateFromInput(date),
                    Parser.parseTimeFromInput(startTime), Parser.parseTimeFromInput(endTime));
            break;
        }

        // Common functionality: add task to list, print task and list size, save tasks to file
        newTasks.add(task);
        message += ui.showMessage("Got it. The following task has been added: ");
        message += ui.showIndentedMessage(task.toString());
        message += ui.showMessage(String.format("Now you have %d task%s in the list.",
                newTasks.size(), newTasks.size() == 1 ? "" : "s"));
        return new TaskList(newTasks, message);
    }

    /**
     * Deletes a task from the task list.
     * Index of deleted task depends on the input.
     *
     * @param input String containing user input.
     * @param ui    Object that handles user interface functionality. (e.g. printing)
     * @return A new TaskList instance with the selected task removed and an output message.
     * @throws DukeException If input is in an invalid format, or specified index is out of bounds.
     */
    public TaskList deleteTask(String input, Ui ui) {
        List<Task> newTasks = new ArrayList<>(tasks);
        String message = "";
        if (input.length() <= 7) {
            throw new DukeException("Please type in a task number to delete.");
        }
        String taskNumberString = input.substring(7);
        if (taskNumberString.matches("\\d+")
                && (Integer.parseInt(taskNumberString) - 1 < tasks.size()
                && Integer.parseInt(taskNumberString) - 1 >= 0)) {
            int taskIndex = Integer.parseInt(taskNumberString) - 1;
            Task removedTask = newTasks.remove(taskIndex);
            message += ui.showMessage("Got it. The following task has been removed:");
            message += ui.showIndentedMessage(removedTask.toString());
            message += ui.showMessage(String.format("Now you have %d task%s in the list.",
                    newTasks.size(), newTasks.size() == 1 ? "" : "s"));
        } else {
            // Invalid input (not a number or invalid number)
            throw new DukeException("Please type in a valid task number to delete.");
        }
        return new TaskList(newTasks, message);
    }

    /**
     * Marks a task as done on in the task list.
     * Index of marked task depends on the input.
     *
     * @param input String containing user input.
     * @param ui    Object that handles user interface functionality. (e.g. printing)
     * @return A new TaskList instance with the selected task marked as done.
     * @throws DukeException If input is in an invalid format, or specified index is out of bounds.
     */
    public TaskList markTask(String input, Ui ui) {
        List<Task> newTasks = new ArrayList<>(tasks);
        String message = "";
        if (input.length() <= 5) {
            throw new DukeException("Please type in a task number to mark as done.");
        }
        String taskNumberString = input.substring(5);
        if (taskNumberString.matches("\\d+")
                && (Integer.parseInt(taskNumberString) - 1 < tasks.size()
                && Integer.parseInt(taskNumberString) - 1 >= 0)) {
            int taskIndex = Integer.parseInt(taskNumberString) - 1;
            Task doneTask = newTasks.get(taskIndex);
            message += ui.showMessage("Good work! This task is now marked as done:");
            message += ui.showIndentedMessage(doneTask.toString());
            newTasks.set(taskIndex, doneTask.markAsDone());
        } else {
            // Invalid input (not a number or invalid number)
            throw new DukeException("Please type in a valid task number to mark as done.");
        }
        return new TaskList(newTasks, message);
    }

    /**
     * Gets all tasks containing a certain keyphrase, depending on the user's input.
     *
     * @param input String containing user input.
     * @return A new TaskList instance containing all tasks that contain the input phrase.
     * @throws DukeException If input is in an invalid format.
     */
    public TaskList findTasks(String input) throws DukeException {
        if (input.length() <= 4) {
            throw new DukeException("Please type in a keyphrase to search your tasks with.");
        }
        String keyphrase = input.substring(5);
        List<Task> foundTasks = tasks.stream()
                .filter(task -> task.containsPhrase(keyphrase))
                .collect(Collectors.toList());
        return new TaskList(foundTasks);
    }

    /**
     * Gets all tasks due in x hours/days/months from now, depending on the user's input.
     *
     * @param input String containing user input.
     * @return A new TaskList instance containing all tasks due within the inputted period.
     * @throws DukeException If input is in an invalid format.
     */
    public TaskList getDueTasks(String input) {
        // Check if input is valid and input number is an integer
        if (input.length() <= 4 || !input.substring(4, input.length() - 1).matches("\\d+")) {
            throw new DukeException("Command must be of the form: due [integer][h/d/m] "
                    + "(h = hours, d = days, m = months)");
        }

        String offset = input.substring(4, input.length() - 1);
        LocalDateTime dateTime = LocalDateTime.now();
        switch (input.charAt(input.length() - 1)) {
        case ('h'):
            dateTime = dateTime.plusHours(Integer.parseInt(offset));
            break;
        case ('d'):
            dateTime = dateTime.plusDays(Integer.parseInt(offset));
            break;
        case ('m'):
            dateTime = dateTime.plusMonths(Integer.parseInt(offset));
            break;
        default:
            throw new DukeException("Command must be of the form: due [integer][h/d/m] "
                    + "(h = hours, d = days, m = months)");
        }

        // Copy dateTime to an effectively final variable for use in lambda
        LocalDateTime finalDateTime = dateTime;
        List<Task> beforeDateTasks = tasks.stream()
                .filter(task -> task.isBeforeDate(finalDateTime))
                .collect(Collectors.toList());
        return new TaskList(beforeDateTasks);
    }

    /**
     * Gets all tasks occurring on a given date, depending on the user's input.
     *
     * @param input String containing user input.
     * @return A new TaskList instance containing all tasks occurring on the input date.
     * @throws DukeException If input is in an invalid format, or an invalid date is given.
     */
    public TaskList getOnDateTasks(String input) {
        if (input.length() <= 7) {
            throw new DukeException("Date must be of the form YYYY-MM-DD, and must be a real/valid date.");
        }
        String dateString = input.substring(7);
        LocalDate date = Parser.parseDateFromInput(dateString);
        List<Task> onDateTasks = tasks.stream()
                .filter(task -> task.hasSameDate(date))
                .collect(Collectors.toList());
        return new TaskList(onDateTasks);
    }

    /**
     * Gets the string message generated after running a command.
     *
     * @return A string generated after running a command.
     */
    public String getRecentMessage() {
        return recentMessage;
    }

    /**
     * Converts the TaskList data into a string format.
     * The tasks are listed in a numerical order. (1, 2, 3...)
     *
     * @return A string representation of the TaskList instance.
     */
    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "No tasks found.";
        }
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                listString.append(String.format("%d. %s", i + 1, tasks.get(i)));
            } else {
                listString.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
            }
        }
        return listString.toString().trim();
    }
}
