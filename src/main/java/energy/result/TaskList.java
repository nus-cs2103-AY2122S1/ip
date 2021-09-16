package energy.result;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javafx.util.Pair;

import energy.task.Deadline;
import energy.task.Event;
import energy.task.Task;
import energy.task.Todo;
import energy.util.EnergyException;
import energy.util.Parser;
import energy.util.Ui;

/**
 * A class that contains the base functionality for a task list,
 * including adding, deleting, listing, and marking tasks as done.
 */
public class TaskList {
    private static final int DEADLINE_EXTRA_LENGTH = 3;
    private static final int EVENT_EXTRA_LENGTH = 4;

    private final List<Task> tasks;

    /**
     * Creates a task list with no tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a task list with a given list of tasks and a message.
     *
     * @param tasks A list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
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
     * @param ui     Object that handles printing/formatting of messages.
     * @param inputs A variable amount of strings representing the user input.
     * @return A pair containing a TaskList instance containing the new task,
     * and a string representing an output message.
     * @throws EnergyException If input contains |, or is in an invalid format.
     */
    public Pair<TaskList, String> addTask(Ui ui, String... inputs) throws EnergyException {
        for (String input : inputs) {
            if (input.contains("|")) {
                throw new EnergyException("Input contains |, which is an invalid/reserved character.");
            }
        }
        List<Task> newTasks = new ArrayList<>(tasks);
        Task newTask;
        String aliasString = inputs[0].toLowerCase();
        String commandString = Parser.getAliasHandler().convertAlias(aliasString);
        // If the code enters this function, the command must be one of the words below
        assert (commandString.equals("todo")
                || commandString.equals("deadline")
                || commandString.equals("event"));
        switch (commandString) {
        case "todo":
            newTask = createTodo(inputs);
            break;
        case "deadline":
            newTask = createDeadline(inputs);
            break;
        case "event":
            newTask = createEvent(inputs);
            break;
        default:
            throw new EnergyException("Invalid add command found.");
        }

        // Common functionality: add task to list, print task and list size
        newTasks.add(newTask);
        TaskList newTaskList = new TaskList(newTasks);
        String message = ui.showAddTaskMessage(newTasks.size(), newTask);
        return new Pair<>(newTaskList, message);
    }

    /**
     * Creates a todo task and returns it.
     *
     * @param inputs A variable amount of strings representing the user input.
     * @return A new todo task with a task name.
     */
    private Todo createTodo(String... inputs) {
        String taskName = Parser.parseTaskNameFromInput(inputs, 1, inputs.length);
        return new Todo(taskName);
    }

    /**
     * Creates a deadline task and returns it.
     *
     * @param inputs A variable amount of strings representing the user input.
     * @return A new deadline task with a task name, date and time.
     * @throws EnergyException If the input is in an invalid format.
     */
    private Deadline createDeadline(String... inputs) throws EnergyException {
        // Placeholder variable
        int byIndex = -1;
        // Locate the /by keyword in the inputs
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].equals("/by")) {
                byIndex = i;
                break;
            }
        }

        boolean hasByKeyword = (byIndex != -1);
        /* Inputs should contain 2 more input strings, representing date and time.
           byIndex is used as the 'pivot' as task name consists of a variable/non-fixed
           number of input strings. */
        boolean hasValidLength = (inputs.length == byIndex + DEADLINE_EXTRA_LENGTH);

        if (!hasValidLength || !hasByKeyword) {
            throw new EnergyException("Command must be in the format: deadline [taskName] /by "
                    + "[date(YYYY-MM-DD)] [time(HH:MM)].");
        }
        String taskName = Parser.parseTaskNameFromInput(inputs, 1, byIndex);
        LocalDate date = Parser.parseDateFromInput(inputs[byIndex + 1]);
        LocalTime time = Parser.parseTimeFromInput(inputs[byIndex + 2]);
        return new Deadline(taskName, date, time);
    }

    /**
     * Creates an event task and returns it.
     *
     * @param inputs A variable amount of strings representing the user input.
     * @return A new event task with a task name, date, start time and end time.
     * @throws EnergyException If the input is in an invalid format.
     */
    private Event createEvent(String... inputs) throws EnergyException {
        // Placeholder variable
        int atIndex = -1;
        // Locate the /at keyword in the inputs
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].equals("/at")) {
                atIndex = i;
                break;
            }
        }

        boolean hasAtKeyword = (atIndex != -1);
        /* Inputs should contain 3 more input strings, representing date, start time
           and end time. atIndex is used as the 'pivot' as task name consists of a
           variable/non-fixed number of input strings. */
        boolean hasValidLength = (inputs.length == atIndex + EVENT_EXTRA_LENGTH);

        if (!hasValidLength || !hasAtKeyword) {
            throw new EnergyException("Command must be in the format: event [taskName] /at "
                    + "[date(YYYY-MM-DD)] [start time(HH:MM)] [end time(HH:MM)].");
        }
        String taskName = Parser.parseTaskNameFromInput(inputs, 1, atIndex);
        LocalDate date = Parser.parseDateFromInput(inputs[atIndex + 1]);
        LocalTime startTime = Parser.parseTimeFromInput(inputs[atIndex + 2]);
        LocalTime endTime = Parser.parseTimeFromInput(inputs[atIndex + 3]);
        return new Event(taskName, date, startTime, endTime);
    }

    /**
     * Deletes a task from the task list.
     * Index of deleted task depends on the input.
     *
     * @param ui    Object that handles printing/formatting of messages.
     * @param input String containing user input.
     * @return A pair containing a TaskList instance with the selected task removed,
     * and a string representing an output message.
     * @throws EnergyException If input is in an invalid format, or specified index is out of bounds.
     */
    public Pair<TaskList, String> deleteTask(Ui ui, String input) throws EnergyException {
        String[] splitInputs = input.split(" ");
        if (splitInputs.length != 2) {
            throw new EnergyException("Input should be of the format: delete [taskNumber]");
        }
        List<Task> newTasks = new ArrayList<>(tasks);
        String taskNumberString = splitInputs[1];

        boolean isNumber = taskNumberString.matches("\\d+");
        if (!isNumber) {
            throw new EnergyException("Please type in a valid task number to delete.");
        }

        int taskIndex = Integer.parseInt(taskNumberString) - 1;
        boolean isWithinBounds = (taskIndex < tasks.size() && taskIndex >= 0);
        if (!isWithinBounds) {
            String outOfBoundsErrorString =
                    String.format("Please type in a valid task number. (From %d to %d inclusive)",
                            1, tasks.size());
            throw new EnergyException(outOfBoundsErrorString);
        }

        Task removedTask = newTasks.remove(taskIndex);
        TaskList newTaskList = new TaskList(newTasks);
        String message = ui.showDeleteTaskMessage(newTasks.size(), removedTask);
        return new Pair<>(newTaskList, message);
    }

    /**
     * Marks a task as done on in the task list.
     * Index of marked task depends on the input.
     *
     * @param ui    Object that handles user interface functionality. (e.g. printing)
     * @param input String containing user input.
     * @return A pair containing a TaskList instance with the selected task marked as done,
     * and a string representing an output message.
     * @throws EnergyException If input is in an invalid format, or specified index is out of bounds.
     */
    public Pair<TaskList, String> markTask(Ui ui, String input) throws EnergyException {
        String[] splitInputs = input.split(" ");
        if (splitInputs.length != 2) {
            throw new EnergyException("Input should be of the format: done [taskNumber]");
        }
        List<Task> newTasks = new ArrayList<>(tasks);
        String taskNumberString = splitInputs[1];

        boolean isNumber = taskNumberString.matches("\\d+");
        if (!isNumber) {
            throw new EnergyException("Please type in a valid task number to mark as done.");
        }

        int taskIndex = Integer.parseInt(taskNumberString) - 1;
        boolean isWithinBounds = (taskIndex < tasks.size() && taskIndex >= 0);
        if (!isWithinBounds) {
            String outOfBoundsErrorString =
                    String.format("Please type in a valid task number. (From %d to %d inclusive)",
                            1, tasks.size());
            throw new EnergyException(outOfBoundsErrorString);
        }

        Task doneTask = newTasks.get(taskIndex).markAsDone();
        newTasks.set(taskIndex, doneTask);
        TaskList newTaskList = new TaskList(newTasks);
        String message = ui.showDoneTaskMessage(doneTask);
        return new Pair<>(newTaskList, message);
    }

    /**
     * Gets all tasks containing a certain keyphrase, depending on the user's input.
     *
     * @param input String containing user input.
     * @return A new TaskList instance containing all tasks that contain the input phrase.
     * @throws EnergyException If input is in an invalid format.
     */
    public TaskList findTasks(String input) throws EnergyException {
        String[] splitInputs = input.split(" ");
        if (splitInputs.length < 2) {
            throw new EnergyException("Input should be of the format: find [keyphrase]");
        }
        int keyphraseIndex = splitInputs[0].length();
        String keyphrase = input.substring(keyphraseIndex);
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
     * @throws EnergyException If input is in an invalid format.
     */
    public TaskList getDueTasks(String input) {
        String[] splitInputs = input.split(" ");
        if (splitInputs.length != 2) {
            throw new EnergyException("Command must be of the form: due [integer][h/d/m] "
                    + "(h = hours, d = days, m = months)");
        }
        String offset = splitInputs[1];
        boolean isValidInput = offset.matches("\\d+");
        if (!isValidInput) {
            throw new EnergyException("Command must be of the form: due [integer][h/d/m] "
                    + "(h = hours, d = days, m = months)");
        }

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
            throw new EnergyException("Command must be of the form: due [integer][h/d/m] "
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
     * @throws EnergyException If input is in an invalid format, or an invalid date is given.
     */
    public TaskList getOnDateTasks(String input) {
        String[] splitInputs = input.split(" ");
        if (splitInputs.length != 2) {
            throw new EnergyException("Date must be of the form YYYY-MM-DD, and must be a real/valid date.");
        }
        String dateString = splitInputs[1];
        LocalDate date = Parser.parseDateFromInput(dateString);
        List<Task> onDateTasks = tasks.stream()
                .filter(task -> task.hasSameDate(date))
                .collect(Collectors.toList());
        return new TaskList(onDateTasks);
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
            listString.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return listString.toString().trim();
    }
}
