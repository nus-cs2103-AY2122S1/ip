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

    /**
     * Constructor for the parser class. Initialises tasks and ui.
     *
     * @param tasks the list the parser stores tasks into.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses input and handles user input accordingly.
     *
     * @param input command given by user input.
     * @throws DukeException exception thrown due to invalid command or file writing error.
     */
    public String parse(String input) throws DukeException {
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
        String message = "";
        switch (command) {
        case LIST:
            message = tasks.print();
            break;
        case DONE:
            message = setTaskDone(userInput);
            break;
        case TODO:
            message = addTodo(userInput);
            break;
        case DEADLINE:
            message = addDeadline(userInput);
            break;
        case EVENT:
            message = addEvent(userInput);
            break;
        case DELETE:
            message = deleteTask(userInput);
            break;
        case FIND:
            message = findTask(userInput);
            break;
        case BYE:
            message = Ui.sayBye();
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return message;
    }

    /**
     * Adds the input to the list of tasks and prints out the input.
     *
     * @param input to be added and printed.
     * @return string to inform user of successful command.
     */
    private String addTask(Task input) {
        tasks.add(input);
        return Ui.print(String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.",
                input, tasks.size(), tasks.size() == 1 ? "task" : "tasks"));
    }

    /**
     * Adds a todo task to the list of tasks.
     *
     * @param userInput given by user.
     * @return string to inform user of successful command.
     */
    private String addTodo(String[] userInput) throws DukeException {
        try {
            return addTask(new Todo(userInput[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Todo description cannot be empty");
        }
    }

    /**
     * Adds a deadline task to the list of tasks.
     *
     * @param userInput given by user.
     * @return string to inform user of successful command.
     */
    private String addDeadline(String[] userInput) throws DukeException {
        try {
            String deadlineDescription = userInput[1].split(" /by ")[0];
            String by = userInput[1].split(" /by ")[1];
            return addTask(new Deadline(deadlineDescription, by));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("duke.task.Deadline description and time by cannot be empty");
        }
    }

    /**
     * Adds a event task to the list of tasks.
     *
     * @param userInput given by user.
     * @return string to inform user of successful command.
     */
    private String addEvent(String[] userInput) throws DukeException {
        try {
            String eventDescription = userInput[1].split(" /at ")[0];
            String by = userInput[1].split(" /at ")[1];
            return addTask(new Event(eventDescription, by));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("duke.task.Event description and time at cannot be empty");
        }
    }

    /**
     * Deletes a task given it's index from the list of tasks.
     *
     * @param userInput given by user.
     * @return string to inform user of successful command.
     */
    private String deleteTask(String[] userInput) throws DukeException {
        try {
            int i = Integer.parseInt(userInput[1]);
            Task task = tasks.get(i - 1);
            tasks.remove(i - 1);
            String taskWord = tasks.size() == 1
                    ? "task"
                    : "tasks";
            return Ui.print(String.format("Noted. I've removed this task:\n  %s\nNow you have %d %s in the list.",
                    task, tasks.size(), taskWord));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please give a valid number!");
        }
    }

    /**
     * Prints the tasks filtered by keyword.
     *
     * @param userInput given by user.
     * @return string to inform user of successful command.
     */
    private String findTask(String[] userInput) throws DukeException {
        try {
            return tasks.filter(userInput[1]).print("Here are the matching tasks in your list:");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter something to find for!");
        }
    }

    /**
     * Set i-th task to be done and prints confirmation message.
     *
     * @param userInput given by user.
     * @return string to inform user of successful command.
     */
    private String setTaskDone(String[] userInput) throws DukeException {
        try {
            int i = Integer.parseInt(userInput[1]);
            Task task = tasks.get(i - 1);
            task.setDone();
            return Ui.print(String.format("Nice! I've marked this task as done:\n  %s", task.toString()));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please give a valid number!");
        }
    }
}
