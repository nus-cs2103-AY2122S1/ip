package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<String> lines) {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        try {
            for (String line : lines) {
                Task newTask;
                String[] values = line.split(",", -1);

                // Checks to ensure correct line format
                if (!((line.matches("T,[0,1],.*") && values.length == 3) ||
                        (line.matches("[D,E],[0,1],.*") && values.length == 4))) {
                    throw new InvalidFileFormatException();
                }

                switch (values[0]) {
                    case "T":   // Todo task
                        newTask = new Todo(values[2], values[1].equals("1"));
                        break;
                    case "D":   // Deadline task
                        LocalDate date;
                        try {
                            date = LocalDate.parse(values[3]);
                        } catch (DateTimeParseException e) {
                            throw new InvalidFileFormatException();
                        }
                        newTask = new Deadline(values[2], date, values[1].equals("1"));
                        break;
                    case "E":   // Event task
                        newTask = new Event(values[2], values[3], values[1].equals("1"));
                        break;
                    default:
                        // Should not reach this case due to regex check above
                        throw new InvalidFileFormatException();
                }
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
     * @throws InvalidFormatException When the input command is of the wrong format.
     */
    public String done(String command) throws InvalidTaskIndexException, InvalidFormatException {
        int taskIdx = Parser.getIndexFromCommand(command);

        // Handle invalid index
        if (taskIdx >= 1 && taskIdx <= tasks.size()) {
            Task t = tasks.get(taskIdx - 1);
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
     * @throws InvalidFormatException When the input command is of the wrong format.
     * @throws EmptyTodoDescriptionException When the description is empty.
     */
    public String todo(String command) throws InvalidFormatException, EmptyTodoDescriptionException {
        String[] matches = Parser.validateRegexAndMatch(command, "^todo (.+)", "todo {description}");
        String description = matches[1].trim();
        if (description.equals("")) {
            throw new EmptyTodoDescriptionException();
        }
        Todo newTodo = new Todo(description);
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
    public String deadline(String command) throws InvalidFormatException {
        String[] matches = Parser.validateRegexAndMatch(command, "^deadline (.+) /by (.+)",
                "deadline {description} /by {date}");

        LocalDate date;
        try {
            date = LocalDate.parse(matches[2].trim());
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Please ensure the date is in YYYY-MM-DD format!");
        }

        Deadline newDeadline = new Deadline(matches[1].trim(), date);
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
    public String event(String command) throws InvalidFormatException {
        String[] matches = Parser.validateRegexAndMatch(command, "^event (.+) /at (.+)",
                "event {description} /at {time}");

        Event newEvent = new Event(matches[1].trim(), matches[2].trim());
        tasks.add(newEvent);
        return formatAddTaskString(newEvent);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param command The input command from the user.
     * @return Formatted string containing the task deleted, to be displayed to the user.
     * @throws InvalidTaskIndexException When the index is out of bounds.
     * @throws InvalidFormatException When the input command is of the wrong format.
     */
    public String delete(String command) throws InvalidTaskIndexException, InvalidFormatException {
        int taskIdx = Parser.getIndexFromCommand(command);

        // Handle invalid index
        if (taskIdx >= 1 && taskIdx <= tasks.size()) {
            Task t = tasks.get(taskIdx - 1);
            tasks.remove(taskIdx - 1);
            return String.format("Noted. I've removed this task:\n  %s\n%s\n", t, formatNumTaskString());
        } else {
            throw new InvalidTaskIndexException();
        }
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
