package duke;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.CheckDateCommand;
import duke.command.ClearCommand;
import duke.command.DeleteArchiveCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.InvalidCommand;
import duke.command.ListArchiveCommand;
import duke.command.ListCommand;
import duke.command.LoadArchiveCommand;
import duke.command.NewArchiveCommand;
import duke.errors.ArchiveException;
import duke.errors.DukeException;
import duke.errors.InvalidDateException;
import duke.errors.InvalidDeadlineException;
import duke.errors.InvalidDeleteException;
import duke.errors.InvalidDoneException;
import duke.errors.InvalidEventException;
import duke.errors.InvalidTodoException;
import duke.errors.InvalidUserInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.testinginterface.TaskListInterface;


/**
 * Makes sense of user commands.
 *
 * Checks for errors and converts user input to pass into other methods.
 */
public class Parser {
    private TaskListInterface taskList;
    private enum Command { Exit, List, Help, Done, Delete, Clear, Todo, Deadline, Event,
        CheckDate, Find, Archive, Invalid };

    /**
     * Constructor.
     *
     * @param taskList
     */
    public Parser(TaskListInterface taskList) {
        this.taskList = taskList;
    }


    /**
     * Returns a type of Command, based on the String input from user.
     *
     * @param input by the user from Command Line.
     * @return Command.
     * @throws InvalidUserInputException
     */
    public duke.command.Command parse(String input) throws DukeException {
        assert input != null : "input to parse is null";
        Command caseId = findCase(input);
        duke.command.Command c = new InvalidCommand();

        switch (caseId) {
        case Exit:
            c = new ExitCommand();
            break;
        case List:
            c = new ListCommand();
            break;
        case Help:
            c = new HelpCommand();
            break;
        case Done:
            isValidDone(input);
            int donePos = Integer.valueOf(input.substring(5));
            c = new DoneCommand(donePos);
            break;
        case Delete:
            isValidDelete(input);
            int deletePos = Integer.valueOf(input.substring(7));
            c = new DeleteCommand(deletePos);
            break;
        case Clear:
            c = new ClearCommand();
            break;
        case Todo:
            isValidTodo(input);
            Todo todo = new Todo(input.substring(5));
            c = new AddCommand(todo);
            break;
        case Deadline:
            isValidDeadline(input);
            c = getDeadlineCommand(input);
            break;
        case Event:
            isValidEvent(input);
            c = getEventCommand(input);
            break;
        case CheckDate:
            String date = input.substring(6);
            LocalDate parsedDate = LocalDate.parse(date);
            c = new CheckDateCommand(parsedDate);
            break;
        case Find:
            String word = input.substring(5);
            c = new FindCommand(word);
            break;
        case Archive:
            isValidArchive(input);
            c = getArchiveCommand(input);
            break;
        default:
            throw new InvalidUserInputException("invalid command");
        }
        return c;
    }

    /**
     * Returns the identifier of each case (for switch in parse method).
     *
     * @param input User entered into Command Line.
     * @return CaseId of type Command (Enum).
     */
    private Command findCase(String input) {
        if (input.equals("bye")) {
            return Command.Exit;
        } else if (input.equals("list")) {
            return Command.List;
        } else if (input.equals("help")) {
            return Command.Help;
        } else if (input.startsWith("done")) {
            return Command.Done;
        } else if (input.startsWith("delete")) {
            return Command.Delete;
        } else if (input.startsWith("clear")) {
            return Command.Clear;
        } else if (input.startsWith("todo")) {
            return Command.Todo;
        } else if (input.startsWith("deadline")) {
            return Command.Deadline;
        } else if (input.startsWith("event")) {
            return Command.Event;
        } else if (input.startsWith("check")) {
            return Command.CheckDate;
        } else if (input.startsWith("find")) {
            return Command.Find;
        } else if (input.startsWith("archive")) {
            return Command.Archive;
        } else {
            return Command.Invalid;
        }
    }

    /**
     * Checks if the user input is a valid Done command.
     *
     * @param input by user.
     * @throws InvalidDoneException if format is invalid
     */
    private void isValidDone(String input) throws InvalidDoneException {
        if (!input.startsWith("done ")) {
            throw new InvalidDoneException("Please use: done <task number>");
        }
        if (input.length() == 5) {
            throw new InvalidDoneException("Missing number (following 'done').");
        }

        int donePos = 0;
        try {
            donePos = Integer.valueOf(input.substring(5));
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidDoneException("Number must be an integer.");
        }

        //checks if the task to be marked as done is within the range of the list
        if (donePos <= 0 || donePos > taskList.getTasks().size()) {
            throw new InvalidDoneException("Task to mark as done is not within the range of the list.");
        }
    }

    /**
     * Checks if user input is a valid Delete command.
     *
     * @param input by user.
     * @throws InvalidDeleteException if format is invalid.
     */
    private void isValidDelete(String input) throws InvalidDeleteException {
        if (!input.startsWith("delete ")) {
            throw new InvalidDeleteException("Please use: delete <task number>");
        }
        if (input.length() == 7) {
            throw new InvalidDeleteException("Missing number (following 'delete').");
        }
        int deletePos = 0;
        try {
            deletePos = Integer.valueOf(input.substring(7));
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidDeleteException("Number must be an integer.");
        }
        if (deletePos <= 0 || deletePos > this.taskList.getTasks().size()) {
            throw new InvalidDeleteException("Task to delete is not within the range of the list.");
        }
    }

    /**
     * Checks if user input is a valid Todo command.
     *
     * @param input by user.
     * @throws InvalidTodoException if format is invalid.
     */
    private void isValidTodo(String input) throws InvalidTodoException {
        // handles any characters after 'todo' that are not white space
        if (!input.startsWith("todo ")) {
            throw new InvalidTodoException("Please use: todo <description>");
        }
        if (input.length() == 5) {
            throw new InvalidTodoException("Missing Description.");
        }
    }

    /**
     * Checks if user input is a valid Deadline command.
     *
     * @param input by user.
     * @throws InvalidDeadlineException if format is invalid.
     */
    private void isValidDeadline(String input) throws InvalidDeadlineException {
        // handles any characters after 'deadline' that are not white space
        if (!input.startsWith("deadline ")) {
            throw new InvalidDeadlineException("Please use: deadline <description> /by <date/time>");
        }
        // handles case of no description and date/time
        if (input.length() == 9) {
            throw new InvalidDeadlineException("Description and date/time of deadline cannot be empty.");
        }
        if (!input.contains(" /by ")) {
            throw new InvalidDeadlineException("Missing /by field.");
        }
        // handles: deadline /by <time>
        if (input.split(" /by ")[0].equals("deadline")) {
            throw new InvalidDeadlineException("Missing description.");
        }
    }

    /**
     * Checks if user input is a valid Event command.
     *
     * @param input by user.
     * @throws InvalidEventException if format is invalid.
     */
    private void isValidEvent(String input) throws InvalidEventException {
        // handles any characters after 'event' that are not white space
        if (!input.startsWith("event ")) {
            throw new InvalidEventException("Please use: event <description> /at <date/time>");
        }
        if (input.length() == 6) {
            throw new InvalidEventException("Description and date/time of event cannot be empty.");
        }
        if (!input.contains(" /at ")) {
            throw new InvalidEventException("Missing /at field.");
        }
        if (input.split(" /at ")[0].equals("event")) {
            throw new InvalidEventException("Missing description.");
        }
    }

    private void isValidArchive(String input) throws ArchiveException {
        if (input.contains("/delete")) {
            if (input.split(" /delete ").length == 1) {
                throw new ArchiveException(": missing file name of Archive to delete.");
            }
        } else if (input.contains("/load")) {
            if (input.split(" /load ").length == 1) {
                throw new ArchiveException(": missing file name of Archive to load.");
            }
        } else if (input.contains("/saveAs")) {
            if (input.split(" /saveAs ").length == 1) {
                throw new ArchiveException(": missing file name of Archive to save as.");
            }
        } else {
            if (!input.equals("archive /list")) {
                throw new ArchiveException(": Archive command is invalid.");
            }
        }
    }

    private duke.command.Command getDeadlineCommand(String input) throws InvalidDateException {
        String by = input.split(" /by ")[1];
        duke.command.Command c = new InvalidCommand();
        try {
            LocalDate deadlineDate = LocalDate.parse(by);
            String deadlineDescription = input.split(" /by ")[0].substring(9);

            Deadline deadline = new Deadline(deadlineDescription, deadlineDate);
            c = new AddCommand(deadline);

        } catch (DateTimeParseException dateTimeParseException) {
            throw new InvalidDateException("Please use yyyy-mm-dd formatting.");
        }
        return c;
    }

    private duke.command.Command getEventCommand(String input) throws InvalidDateException {
        String at = input.split(" /at ")[1];
        duke.command.Command c = new InvalidCommand();
        try {
            LocalDate eventDate = LocalDate.parse(at);
            String eventDescription = input.split(" /at ")[0].substring(6);

            Event event = new Event(eventDescription, eventDate);
            c = new AddCommand(event);

        } catch (DateTimeParseException dateTimeParseException) {
            throw new InvalidDateException("Invalid date/time input. Format: yyyy-mm-dd");
        }
        return c;
    }

    private duke.command.Command getArchiveCommand(String input) {
        duke.command.Command c = new InvalidCommand();

        if (input.contains("/delete")) {
            String fileName = input.split(" /delete ")[1];
            c = new DeleteArchiveCommand(fileName);

        } else if (input.contains("/load")) {
            String fileName = input.split(" /load ")[1];
            c = new LoadArchiveCommand(fileName);
        } else if (input.contains("/saveAs")) {
            String fileName = input.split(" /saveAs ")[1];
            c = new NewArchiveCommand(fileName);
        } else {
            if (input.equals("archive /list")) {
                c = new ListArchiveCommand();
            }
        }
        return c;
    }
}
