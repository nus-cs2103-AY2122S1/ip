package duke.io;

import duke.command.Command;
import duke.exception.DukeException;
import duke.place.Place;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Period;
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
     * @return response by duke in the form of a string.
     * @throws DukeException exception thrown due to invalid command or file writing error.
     */
    public String parse(String input) throws DukeException {
        String[] userInput = input.split(" ", 2);
        Command command = parseInputToCommand(userInput[0]);
        return executeCommand(command, userInput.length > 1 ? userInput[1] : null);
    }

    /**
     * Checks and parses the input, returning a command of Command type.
     *
     * @param userInput of type string.
     * @return command as given by user input.
     * @throws DukeException exception converting user input into command.
     */
    private Command parseInputToCommand(String userInput) throws DukeException {
        Command command;
        if (!userInput.equals(userInput.toLowerCase())) {
            throw new DukeException(
                    String.format("Please input command with lowercase! Did you mean %s?", userInput.toLowerCase()));
        }

        try {
            command = Command.valueOf(userInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }

    /**
     * Executes the command with the relevant arguments.
     *
     * @param command the command to execute.
     * @param args the arguments to fill the command with.
     * @return string response by system after command execution.
     * @throws DukeException exception arising from failed execution.
     */
    private String executeCommand(Command command, String args) throws DukeException {
        switch (command) {
        case LIST:
            return tasks.print();
        case SORT:
            return tasks.sort();
        case DONE:
            return setTaskDone(args);
        case TODO:
            return addTodo(args);
        case DEADLINE:
            return addDeadline(args);
        case EVENT:
            return addEvent(args);
        case PERIOD:
            return addPeriod(args);
        case PLACE:
            return addPlace(args);
        case DELETE:
            return deleteTask(args);
        case FIND:
            return findTask(args);
        case BYE:
            return Ui.sayBye();
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Adds the input to the list of tasks and prints out the input.
     *
     * @param input to be added and printed.
     * @return string to inform user of successful command.
     */
    private String addTask(Task input) {
        assert input != null : "Cannot add a null task";
        tasks.add(input);
        return Ui.print(String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.",
                input, tasks.size(), tasks.size() == 1 ? "task" : "tasks"));
    }

    /**
     * Adds a todo task to the list of tasks.
     *
     * @param args given by user.
     * @return string to inform user of successful command.
     * @throws DukeException error when adding task.
     */
    private String addTodo(String args) throws DukeException {
        try {
            return addTask(new Todo(args));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Todo description cannot be empty");
        }
    }

    /**
     * Adds a deadline task to the list of tasks.
     *
     * @param args given by user.
     * @return string to inform user of successful command.
     * @throws DukeException error when adding task.
     */
    private String addDeadline(String args) throws DukeException {
        try {
            String deadlineDescription = args.split(" /by ")[0];
            String by = args.split(" /by ")[1];
            return addTask(new Deadline(deadlineDescription, by));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("duke.task.Deadline description and time by cannot be empty");
        }
    }

    /**
     * Adds a event task to the list of tasks.
     *
     * @param args given by user.
     * @return string to inform user of successful command.
     * @throws DukeException error when adding task.
     */
    private String addEvent(String args) throws DukeException {
        try {
            String eventDescription = args.split(" /at ")[0];
            String by = args.split(" /at ")[1];
            return addTask(new Event(eventDescription, by));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Event description and time at cannot be empty");
        }
    }

    /**
     * Adds a period task to the list of task.
     *
     * @param args given by user.
     * @return string to inform user of successful command.
     * @throws DukeException error when adding task.
     */
    private String addPeriod(String args) throws DukeException {
        try {
            String eventDescription = args.split(" /start ")[0];
            String start = args.split(" /start ")[1].split(" /end ")[0];
            String end = args.split(" /start ")[1].split(" /end ")[1];
            return addTask(new Period(eventDescription, start, end));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Period description and times cannot be empty");
        }
    }

    /**
     * Adds a place to the list of tasks.
     *
     * @param args given by user.
     * @return string to inform user of successful command.
     * @throws DukeException error when adding place to task.
     */
    public String addPlace(String args) throws DukeException {
        try {
            int i = Integer.parseInt(args.split(" /at ")[0]);
            Task task = tasks.get(i - 1);
            String taskWord = tasks.size() == 1
                    ? "task"
                    : "tasks";
            task.addPlace(new Place(args.split(" /at ")[1]));
            return Ui.print(String.format("Noted. I've added the place to the task:\n  "
                    + "%s\nNow you have %d %s in the list.", task, tasks.size(), taskWord));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please give a valid number!");
        }
    }

    /**
     * Deletes a task given it's index from the list of tasks.
     *
     * @param args given by user.
     * @return string to inform user of successful command.
     * @throws DukeException error when adding task.
     */
    private String deleteTask(String args) throws DukeException {
        try {
            int i = Integer.parseInt(args);
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
     * @param args given by user.
     * @return string to inform user of successful command.
     * @throws DukeException error when adding task.
     */
    private String findTask(String args) throws DukeException {
        try {
            return tasks.filter(args).print("Here are the matching tasks in your list:");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter something to find for!");
        }
    }

    /**
     * Sets i-th task to be done and prints confirmation message.
     *
     * @param args given by user.
     * @return string to inform user of successful command.
     * @throws DukeException error when adding task.
     */
    private String setTaskDone(String args) throws DukeException {
        try {
            int i = Integer.parseInt(args);
            Task task = tasks.get(i - 1);
            task.setDone();
            return Ui.print(String.format("Nice! I've marked this task as done:\n  %s", task.toString()));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please give a valid number!");
        }
    }
}
