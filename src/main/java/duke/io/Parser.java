package duke.io;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Encapsulates parsing of user inputs into the list of tasks.
 */
public class Parser {
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for the parser class. Initialises tasks and ui.
     *
     * @param tasks the list the parser stores tasks into.
     * @param ui the ui object used to print to the user.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Parses input and handles user input accordingly.
     *
     * @param input command given by user input.
     * @throws DukeException exception thrown due to invalid command or file writing error.
     */
    public void parse(String input) throws DukeException {
        Command command;
        String[] userInput = input.split(" ", 2);

        if (!userInput[0].equals(userInput[0].toLowerCase())) {
            throw new DukeException(
                    String.format("Please input command with lowercase! Did you mean %s?", userInput[0].toLowerCase()));
        }

        try {
            command = Command.valueOf(userInput[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        switch (command) {
        case LIST:
            tasks.print(ui);
            break;
        case DONE:
            setTaskDone(userInput);
            break;
        case TODO:
            addTodo(userInput);
            break;
        case DEADLINE:
            addDeadline(userInput);
            break;
        case EVENT:
            addEvent(userInput);
            break;
        case DELETE:
            deleteTask(userInput);
            break;
        case FIND:
            findTask(userInput);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Adds the input to the list of tasks and prints out the input.
     *
     * @param input to be added and printed.
     */
    private void addTask(Task input) {
        tasks.add(input);
        ui.print(String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.",
                input, tasks.size(), tasks.size() == 1 ? "task" : "tasks"));
    }

    /**
     * Adds a todo task to the list of tasks.
     *
     * @param userInput given by user.
     */
    private void addTodo(String[] userInput) throws DukeException {
        try {
            addTask(new Todo(userInput[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("duke.task.Todo description cannot be empty");
        }
    }

    /**
     * Adds a deadline task to the list of tasks.
     *
     * @param userInput given by user.
     */
    private void addDeadline(String[] userInput) throws DukeException {
        try {
            String deadlineDescription = userInput[1].split(" /by ")[0];
            String by = userInput[1].split(" /by ")[1];
            addTask(new Deadline(deadlineDescription, by));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("duke.task.Deadline description and time by cannot be empty");
        }
    }

    /**
     * Adds a event task to the list of tasks.
     *
     * @param userInput given by user.
     */
    private void addEvent(String[] userInput) throws DukeException {
        try {
            String eventDescription = userInput[1].split(" /at ")[0];
            String by = userInput[1].split(" /at ")[1];
            addTask(new Event(eventDescription, by));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("duke.task.Event description and time at cannot be empty");
        }
    }

    /**
     * Deletes a task given it's index from the list of tasks.
     *
     * @param userInput given by user.
     */
    private void deleteTask(String[] userInput) throws DukeException {
        try {
            int i = Integer.parseInt(userInput[1]);
            Task task = tasks.get(i - 1);
            tasks.remove(i - 1);
            String taskWord = tasks.size() == 1
                    ? "task"
                    : "tasks";
            ui.print(String.format("Noted. I've removed this task:\n  %s\nNow you have %d %s in the list.",
                    task, tasks.size(), taskWord));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please give a valid number!");
        }
    }

    /**
     * Prints the tasks filtered by keyword.
     *
     * @param userInput given by user.
     */
    private void findTask(String[] userInput) throws DukeException {
        try {
            tasks.filter(userInput[1]).print(ui, "Here are the matching tasks in your list:");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter something to find for!");
        }
    }

    /**
     * Set i-th task to be done and prints confirmation message.
     *
     * @param userInput given by user.
     */
    private void setTaskDone(String[] userInput) throws DukeException {
        try {
            int i = Integer.parseInt(userInput[1]);
            Task task = tasks.get(i - 1);
            task.setDone();
            ui.print(String.format("Nice! I've marked this task as done:\n  %s", task.toString()));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please give a valid number!");
        }
    }
}
