package duke;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.MarkDoneCommand;

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

    private static final String TIME_MARKER = "/at";
    private static final String DEADLINE_MARKER = "/by";

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
    public Command parse(String input) {
        String[] splitInput = input.split(" ", 2);
        String firstWord = splitInput[0];

        switch (firstWord) {
        case "bye":
            return new ExitCommand(taskList, storage, ui, duke);
        case "delete":
            return deleteFromList(splitInput);
        case "deadline":
            return addDeadline(splitInput);
        case "done":
            return setTaskDone(splitInput);
        case "list":
            ui.setMessage(taskList.toString());
            break;
        case "event":
            return addEvent(splitInput);
        case "find":
            return findTask(splitInput);
        case "todo":
            return addTodo(splitInput);
        default:
            ui.showError(new InvalidInputException("Command not recognised."));
            break;
        }
        return null;
    }

    /**
     * Parses a user input to add a Todo type task.
     * @param input user input split into individual words.
     * @return Command to add a new TodoTask, null if error occurs.
     */
    private Command addTodo(String[] input) {
        try {
            String name = input[1];
            TodoTask todo = new TodoTask(name);
            return new AddTaskCommand(taskList, storage, ui, todo);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showError(new InvalidInputException("Description for To-do cannot be empty." +
                    "\nexample:\ntodo buy groceries"));
            return null;
        }
    }

    /**
     * Parses a user input to add a Event type task.
     * @param input user input split into individual words.
     * @return Command to add a new EventTask, null if error occurs.
     */
    private Command addEvent(String[] input) {

        try {
            String[] taskAndTime = input[1].split(TIME_MARKER, 2);
            EventTask event;
            if (taskAndTime.length > 1) {
                event = new EventTask(taskAndTime[0], taskAndTime[1]);
                return new AddTaskCommand(taskList, storage, ui, event);
            } else {
                ui.showError(new InvalidInputException("Need to specify event name and time." +
                        "\nexample:\nevent meeting /at Tuesday 12pm"));
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showError(new InvalidInputException("Description for Event cannot be empty." +
                    "\nexample:\nevent meeting /at Tuesday 12pm"));
            return null;
        } catch (DateTimeException e) {
            ui.showError(new InvalidInputException("Unsupported date format, must be in format yyyy-mm-dd."));
            return null;
        }
    }

    /**
     * Parses a user input to add a Deadline type task.
     * @param input user input split into individual words.
     * @return Command to add a new DeadlineTask, null if error occurs.
     */
    private Command addDeadline(String[] input) {

        try {
            String[] taskAndTime = input[1].split(DEADLINE_MARKER, 2);
            if (taskAndTime.length > 1) {
                DeadlineTask deadlineTask = new DeadlineTask(taskAndTime[0], taskAndTime[1]);
                return new AddTaskCommand(taskList, storage, ui, deadlineTask);
            } else {
                ui.showError(new InvalidInputException("Need to specify task name and deadline." +
                        "\nexample:\ndeadline return book /by Sunday"));
                return null;
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showError(new InvalidInputException("Description for deadline cannot be empty.\" +\n" +
                    "                    \"\\nexample:\\ndeadline return book /by Sunday"));
            return null;
        } catch (DateTimeException e) {
            ui.showError(new InvalidInputException("Unsupported date format, must be in format yyyy-mm-dd."));
            return null;
        }

    }

    /**
     * Parses a user input to delete a task from task list.
     * @param input user input split into individual words.
     * @return Command to delete a Task from TaskList, null if error occurs.
     */
    private Command deleteFromList(String[] input) {
        if (taskList.getSize() == 0) {
            ui.showError(new EmptyListException());
            return null;
        } else {
            try {
                int index = Integer.parseInt(input[1]);
                return new DeleteCommand(taskList, storage, ui, index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.showError(new InvalidInputException("Invalid input, please enter a number from 1 to "
                        + taskList.getSize()));
                return null;
            }
        }
    }

    /**
     * Parses a user input to set a task as 'done'.
     * @param input user input split into individual words.
     * @return Command to set a task as done, null if error occurs.
     */
    private Command setTaskDone(String[] input) {

        if (taskList.getSize() == 0) {
            ui.showError(new EmptyListException());
            return null;
        } else {
            try {
                int index = Integer.parseInt(input[1]);
                return new MarkDoneCommand(taskList, storage, ui, index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.showError(new InvalidInputException("Invalid input, please enter a number from 1 to "
                        + taskList.getSize()));
                return null;
            }
        }
    }

    /**
     * Finds a Task from the TaskList.
     * @param input String of user input.
     * @return FindCommand, null if error.
     */
    private Command findTask(String[] input) {
        try {
            String[] searchTerms = input[1].split(" ");
            return new FindCommand(taskList, storage, ui, searchTerms);
        } catch (IndexOutOfBoundsException e) {
            ui.showError(new InvalidInputException("Please specify search terms."));
            return null;
        }
    }
}
