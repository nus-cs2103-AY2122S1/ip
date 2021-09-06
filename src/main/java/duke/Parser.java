package duke;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.*;
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
    private enum Command { Exit, List, Done, Delete, Clear, Todo, Deadline, Event, CheckDate, Find, Archive, Invalid };

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
     * @throws DukeException
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

            String by = input.split(" /by ")[1];
            try {
                LocalDate deadlineDate = LocalDate.parse(by);
                String deadlineDescription = input.split(" /by ")[0].substring(9);

                Deadline deadline = new Deadline(deadlineDescription, deadlineDate);
                c = new AddCommand(deadline);

            } catch (DateTimeParseException dateTimeParseException) {
                throw new DukeException("Invalid date/time input. Format: yyyy-mm-dd");
            }
            break;

        case Event:
            isValidEvent(input);

            String at = input.split(" /at ")[1];
            try {
                LocalDate eventDate = LocalDate.parse(at);
                String eventDescription = input.split(" /at ")[0].substring(6);

                Event event = new Event(eventDescription, eventDate);
                c = new AddCommand(event);

            } catch (DateTimeParseException dateTimeParseException) {
                throw new DukeException("Invalid date/time input. Format: yyyy-mm-dd");
            }

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
            if (input.contains("/delete")) {
                String fileName = input.split(" /delete ")[1];
                c = new DeleteArchiveCommand(fileName);

            } else if (input.contains("/load")) {
                String fileName = input.split(" /load ")[1];
                c = new LoadArchiveCommand(fileName);

            } else if (input.contains("/saveAs")) {
                String fileName = input.split(" /saveAs ")[1];
                c = new NewArchiveCommand(fileName);

            } else if (input.equals("archive /list")) {
                c = new ListArchiveCommand();

            } else {
                throw new DukeException("invalid Archive command");
            }
            break;
        default:
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
        } else{
            return Command.Invalid;
        }
    }

    private void isValidDone(String input) throws DukeException {
        if (!input.startsWith("done ")) {
            throw new DukeException("Oops! Improper formatting for done. " + "Please use: done <task number>");
        }
        if (input.length() == 5) {
            throw new DukeException("Oops! The number following 'done' cannot be empty.");
        }
        int donePos = Integer.valueOf(input.substring(5));

        //checks if the task to be marked as done is within the range of the list
        if (donePos <= 0 || donePos > taskList.getTasks().size()) {
            throw new DukeException("Oops! " + "The task to mark as done is not within the range of the list.");
        }
    }

    private void isValidDelete(String input) throws DukeException {
        if (!input.startsWith("delete ")) {
            throw new DukeException("Oops! Improper formatting for delete. " + "Please use: delete <task number>");
        }
        if (input.length() == 7) {
            throw new DukeException("Oops! The number following 'delete' cannot be empty.");
        }
        int deletePos = Integer.valueOf(input.substring(7));
        if (deletePos <= 0 || deletePos > this.taskList.getTasks().size()) {
            throw new DukeException("Oops! " + "The task to delete is not within the range of the list.");
        }
    }

    private void isValidTodo(String input) throws DukeException {
        // handles any characters after 'todo' that are not white space
        if (!input.startsWith("todo ")) {
            throw new DukeException("Oops! Improper formatting for todo. " + "Please use: todo <description>");
        }
        if (input.length() == 5) {
            throw new DukeException("Oops! The description of todo cannot be empty.");
        }
    }

    private void isValidDeadline(String input) throws DukeException {
        // handles any characters after 'deadline' that are not white space
        if (!input.startsWith("deadline ")) {
            throw new DukeException("Oops! Improper formatting for deadline. "
                    + "Please use: deadline <description> /by <date/time>");
        }
        // handles case of no description and date/time
        if (input.length() == 9) {
            throw new DukeException("Oops! The description and date/time of deadline cannot be empty.");
        }
        if (!input.contains(" /by ")) {
            throw new DukeException("Oops! Inappropriate formatting for deadlines.");
        }
        // handles: deadline /by <time>
        if (input.split(" /by ")[0].equals("deadline")) {
            throw new DukeException("Oops! Missing description for deadline.");
        }
    }

    private void isValidEvent(String input) throws DukeException {
        // handles any characters after 'event' that are not white space
        if (!input.startsWith("event ")) {
            throw new DukeException("Oops! Improper formatting for event. "
                    + "Please use: event <description> /at <date/time>");
        }
        if (input.length() == 6) {
            throw new DukeException("Oops! The description and date/time of event cannot be empty.");
        }
        if (!input.contains(" /at ")) {
            throw new DukeException("Oops! Inappropriate formatting for events.");
        }
        if (input.split(" /at ")[0].equals("event")) {
            throw new DukeException("Oops! Missing description for event.");
        }
    }
}
