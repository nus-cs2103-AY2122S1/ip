package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duke.exception.EmptyTodoDescriptionException;
import duke.exception.InvalidFileFormatException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidTaskIndexException;
import duke.storage.Event;
import duke.util.Parser;
import duke.util.Ui;

/**
 * This class encapsulates a list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor which takes in no arguments.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor which takes in a List of strings.
     *
     * @param lines The lines from the application save file.
     */
    public TaskList(List<String> lines) {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        try {
            for (String line : lines) {
                Task newTask;
                String[] values = line.split(",", -1);

                // Checks to ensure correct line format
                if (!((line.matches("T,[0,1],.*") && values.length == 3) || (
                        line.matches("[D,E],[0,1],.*") && values.length == 4))) {
                    throw new InvalidFileFormatException();
                }

                switch (values[0]) {
                case "T": // Todo task
                    newTask = new Todo(values[2], values[1].equals("1"));
                    break;
                case "D": // Deadline task
                    LocalDate date;
                    try {
                        date = LocalDate.parse(values[3]);
                    } catch (DateTimeParseException e) {
                        throw new InvalidFileFormatException();
                    }
                    newTask = new Deadline(values[2], date, values[1].equals("1"));
                    break;
                case "E": // Event task
                    newTask = new Event(values[2], values[3], values[1].equals("1"));
                    break;
                default:
                    // Should not reach this case due to regex check above
                    throw new InvalidFileFormatException();
                }
                assert newTask != null;
                loadedTasks.add(newTask);
            }
            this.tasks = loadedTasks;
        } catch (InvalidFileFormatException e) {
            Ui.printMessage(e.getMessage() + "\n");
        }
    }

    /**
     * Returns the formatted string for the "list" command.
     *
     * @return Formatted string of tasks.
     */
    public String list() {
        StringBuilder str = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            str.append(i + 1).append(".").append(this.tasks.get(i)).append("\n");
        }

        return str.toString();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param command The input command from the user.
     * @return Formatted string containing the task marked as done, to be displayed to the user.
     * @throws InvalidTaskIndexException When the index is out of bounds.
     * @throws InvalidFormatException    When the input command is of the wrong format.
     */
    public String markAsDone(String command) throws InvalidTaskIndexException, InvalidFormatException {
        int taskIdx = Parser.getIndexFromCommand(command);
        boolean isValidIndex = taskIdx >= 1 && taskIdx <= tasks.size();

        if (isValidIndex) {
            Task t = tasks.get(taskIdx - 1);
            assert t != null;
            t.markAsDone();
            return String.format("Nice! I've marked this task as done:\n  %s\n", t);
        } else {
            throw new InvalidTaskIndexException();
        }
    }

    /**
     * Adds a new todo task to the TaskList.
     *
     * @param command The input command from the user.
     * @return Formatted string containing the added todo task, to be displayed to the user.
     * @throws InvalidFormatException        When the input command is of the wrong format.
     * @throws EmptyTodoDescriptionException When the description is empty.
     */
    public String addTodo(String command) throws InvalidFormatException, EmptyTodoDescriptionException {
        String regex = "^todo (.+)";
        String validFormatDescription = "todo {description}";
        String[] matches = Parser.validateRegexAndMatch(command, regex, validFormatDescription);
        String description = matches[1].trim();

        if (description.equals("")) {
            throw new EmptyTodoDescriptionException();
        }
        Todo newTodo = new Todo(description);
        assert newTodo != null;
        this.tasks.add(newTodo);
        return this.formatAddTaskString(newTodo);
    }

    /**
     * Adds a new deadline task to the TaskList.
     *
     * @param command The input command from the user.
     * @return Formatted string containing the added deadline task, to be displayed to the user.
     * @throws InvalidFormatException When the input command is of the wrong format.
     */
    public String addDeadline(String command) throws InvalidFormatException {
        String regex = "^deadline (.+) /by (.+)";
        String validFormatDescription = "deadline {description} /by {date}";
        String[] matches = Parser.validateRegexAndMatch(command, regex, validFormatDescription);

        LocalDate date;
        try {
            date = LocalDate.parse(matches[2].trim());
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Please ensure the date is in YYYY-MM-DD format!");
        }

        Deadline newDeadline = new Deadline(matches[1].trim(), date);
        assert newDeadline != null;
        tasks.add(newDeadline);
        return formatAddTaskString(newDeadline);
    }

    /**
     * Adds a new event task to the TaskList.
     *
     * @param command The input command from the user.
     * @return Formatted string containing the added event task, to be displayed to the user.
     * @throws InvalidFormatException When the input command is of the wrong format.
     */
    public String addEvent(String command) throws InvalidFormatException {
        String regex = "^event (.+) /at (.+)";
        String validFormatDescription = "event {description} /at {time}";
        String[] matches = Parser.validateRegexAndMatch(command, regex, validFormatDescription);

        Event newEvent = new Event(matches[1].trim(), matches[2].trim());
        assert newEvent != null;
        tasks.add(newEvent);
        return formatAddTaskString(newEvent);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param command The input command from the user.
     * @return Formatted string containing the task deleted, to be displayed to the user.
     * @throws InvalidTaskIndexException When the index is out of bounds.
     * @throws InvalidFormatException    When the input command is of the wrong format.
     */
    public String delete(String command) throws InvalidTaskIndexException, InvalidFormatException {
        int taskIdx = Parser.getIndexFromCommand(command);
        boolean isValidIndex = taskIdx >= 1 && taskIdx <= tasks.size();

        if (isValidIndex) {
            Task t = tasks.get(taskIdx - 1);
            assert t != null;
            tasks.remove(taskIdx - 1);
            return String.format("Noted. I've removed this task:\n  %s\n%s\n", t, formatNumTaskString());
        } else {
            throw new InvalidTaskIndexException();
        }
    }

    /**
     * Finds a task with the specified keyword(s).
     *
     * @param command The input command from the user.
     * @return Formatted string containing the tasks found.
     * @throws InvalidFormatException When the input command is of the wrong format.
     */
    public String find(String command) throws InvalidFormatException {
        String regex = "^find (.+)";
        String validFormatDescription = "find {search term}";
        String[] matches = Parser.validateRegexAndMatch(command, regex, validFormatDescription);
        String searchTerm = matches[1];

        StringBuilder str = new StringBuilder("Here are the matching tasks in your list:\n");
        int counter = 1;
        for (Task t : this.tasks) {
            if (t.getDescription().contains(searchTerm)) {
                str.append(counter).append(".").append(t).append("\n");
                counter++;
            }
        }
        return str.toString();
    }

    /**
     * Converts the TaskList to a string for saving.
     *
     * @return The formatted string for saving.
     */
    public String toSaveFormat() {
        StringBuilder str = new StringBuilder();
        this.tasks.forEach(task -> str.append(task.toSaveFormat()).append("\n"));
        return str.toString();
    }

    /**
     * Returns the list of tasks. To be used solely for testing purposes.
     *
     * @return The list of tasks in the instance of TaskList.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Clears and empties the current TaskList.
     */
    public void clearAllTasks() {
        tasks = new ArrayList<>();
    }

    /**
     * Converts the list of strings into the TaskList format and appends
     * them to this TaskList.
     *
     * @param lines The list of strings from storage file.
     */
    public void appendListOfStrings(List<String> lines) {
        TaskList temp = new TaskList(lines);
        this.tasks.addAll(temp.tasks);
    }

    /**
     * Formats the inputted task as a string to be displayed back to the user.
     *
     * @param task The Task created.
     * @return The string to be displayed to the user during addition of a new task.
     */
    private String formatAddTaskString(Task task) {
        return String.format("Got it. I've added this task:\n  %s\n%s\n",
                task, formatNumTaskString());
    }

    /**
     * Formats the number of tasks as a string that is to be displayed to the user.
     *
     * @return The formatted string containing number of tasks.
     */
    private String formatNumTaskString() {
        return String.format("Now you have %d task%s in the list.",
                this.tasks.size(), this.tasks.size() == 1 ? "" : "s");
    }
}
