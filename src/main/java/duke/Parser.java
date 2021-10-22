package duke;

import duke.command.*;

import duke.exception.DukeException;
import duke.exception.EmptyListException;
import duke.exception.InvalidInputException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.TodoTask;

import java.time.DateTimeException;

public class Parser {

    private Duke duke;
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    private static final String START_MARKER = " /from ";
    private static final String END_MARKER = " /to ";
    private static final String DEADLINE_MARKER = " /by ";

    /**
     * Constructor for a Parser for Duke.
     * @param duke Duke instance the Parser is being used for.
     * @param taskList TaskList used by the Duke.
     * @param storage Storage used by the Duke.
     * @param ui Ui used by the Duke.
     */
    public Parser(Duke duke, TaskList taskList, Storage storage, Ui ui) {
        this.duke = duke;
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Parses the user input string.
     * @param input user input
     * @return true if user enters the exit command, false otherwise.
     */
    public Command parse(String input) throws DukeException {

        Command command;

        String[] splitInput = input.split(" ", 2);
        String firstWord = splitInput[0];

        switch (firstWord) {
        case "bye":
            command = new ExitCommand(taskList, storage, ui, duke);
            break;
        case "delete":
            command = deleteFromList(splitInput);
            break;
        case "deadline":
            command = addDeadline(splitInput);
            break;
        case "done":
            command = setTaskDone(splitInput);
            break;
        case "list":
            command = new ListCommand(taskList, storage, ui);
            break;
        case "event":
            command = addEvent(splitInput);
            break;
        case "find":
            command = findTask(splitInput);
            break;
        case "todo":
            command = addTodo(splitInput);
            break;
        default:
            throw new InvalidInputException("Command not recognised.");
        }

        return command;
    }

    /**
     * Parses a user input to add a Todo type task.
     * @param input user input split into individual words.
     * @return Command to add a new TodoTask, null if error occurs.
     */
    private Command addTodo(String[] input) throws InvalidInputException {
        assert input != null : "input cannot be null";
        try {
            String name = input[1];
            TodoTask todo = new TodoTask(name);
            return new AddTaskCommand(taskList, storage, ui, todo);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException("Description for To-do cannot be empty." +
                    "\nexample:\ntodo buy groceries");
        }
    }

    /**
     * Parses a user input to add a Event type task.
     * @param input user input split into individual words.
     * @return Command to add a new EventTask, null if error occurs.
     */
    private Command addEvent(String[] input) throws InvalidInputException {
        assert input != null : "addEvent: input cannot be null";

        EventTask event;

        try {
            String[] taskAndTime = input[1].split(START_MARKER, 2);
            String[] startAndEnd = taskAndTime[1].split(END_MARKER, 2);
            String start = startAndEnd[0];
            String end = startAndEnd[1];
            event = new EventTask(taskAndTime[0], start, end);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException("Invalid format, need to specify event name, start and end time."
                    + "\nexample:\nevent meeting /from 2021-09-11 09:00 /to 2021-09-11 10:30");
        } catch (DateTimeException e) {
            throw new InvalidInputException("Unsupported date time format, must be in format yyyy-MM-dd HH:mm.");
        } catch (InvalidInputException e) {
            throw e;
        }

        return new AddTaskCommand(taskList, storage, ui, event);
    }

    /**
     * Parses a user input to add a Deadline type task.
     * @param input user input split into individual words.
     * @return Command to add a new DeadlineTask, null if error occurs.
     */
    private Command addDeadline(String[] input) throws InvalidInputException {
        assert input != null : "addDeadline: input cannot be null";

        try {
            String[] taskAndTime = input[1].split(DEADLINE_MARKER, 2);
            if (taskAndTime.length > 1) {
                DeadlineTask deadlineTask = new DeadlineTask(taskAndTime[0], taskAndTime[1]);
                return new AddTaskCommand(taskList, storage, ui, deadlineTask);
            } else {
                throw new InvalidInputException("Need to specify task name and deadline." +
                        "\nexample:\ndeadline return book /by 2021-09-11");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException("Description for deadline cannot be empty.\" +\n" +
                    "\nexample:\ndeadline return book /by 2021-09-11");
        } catch (DateTimeException e) {
            throw new InvalidInputException("Unsupported date format, must be in format yyyy-mm-dd.");
        }

    }

    /**
     * Parses a user input to delete a task from task list.
     * @param input user input split into individual words.
     * @return Command to delete a Task from TaskList, null if error occurs.
     */
    private Command deleteFromList(String[] input) throws DukeException {
        assert input != null : "deleteFromList: input cannot be null";

        if (taskList.getSize() == 0) {
            throw new EmptyListException();
        }

        try {
            int index = Integer.parseInt(input[1]);
            return new DeleteCommand(taskList, storage, ui, index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException("Invalid input, please enter a number from 1 to "
                    + taskList.getSize());
        }

    }

    /**
     * Parses a user input to set a task as 'done'.
     * @param input user input split into individual words.
     * @return Command to set a task as done, null if error occurs.
     */
    private Command setTaskDone(String[] input) throws DukeException {
        assert input != null : "setTaskDone: input cannot be null";

        if (taskList.getSize() == 0) {
            throw new EmptyListException();
        }

        try {
            int index = Integer.parseInt(input[1]);
            return new MarkDoneCommand(taskList, storage, ui, index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException("Invalid input, please enter a number from 1 to "
                    + taskList.getSize());
        }
    }

    /**
     * Finds a Task from the TaskList.
     * @param input String of user input.
     * @return FindCommand, null if error.
     */
    private Command findTask(String[] input) throws InvalidInputException {
        try {
            String[] searchTerms = input[1].split(" ");
            return new FindCommand(taskList, storage, ui, searchTerms);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Please specify search terms.");
        }
    }
}
