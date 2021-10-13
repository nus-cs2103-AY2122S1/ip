package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.TaskList;
import duke.Todo;

/**
 * This class encapsulates an Add Command.
 *
 * @author Kleon Ang
 */
public class AddCommand extends Command {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-d H:mm");
    private final String taskType;

    /**
     * Constructor for an AddCommand.
     *
     * @param tasks The TaskList containing tasks to be accessed by the AddCommand.
     * @param taskType The type of task, which can be a 'todo', 'event' or 'deadline'.
     */
    public AddCommand(TaskList tasks, String taskType) {
        super(tasks);
        this.taskType = taskType;
    }

    @Override
    public String getReply(String arguments) throws DukeException {
        switch (taskType) {
        case "todo":
            return addTodo(arguments);
        case "event":
            return addEvent(arguments);
        case "deadline":
            return addDeadline(arguments);
        default:
            return "☹ OOPS!!! The task provided is not recognised.";
        }
    }

    private String addTodo(String arguments) throws DukeException {
        if (arguments.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of 'todo' cannot be empty.");
        }
        assert !arguments.equals("") : "Arguments required, cannot be empty.";
        return tasks.addTask(new Todo(arguments));
    }

    private String addEvent(String arguments) throws DukeException {
        if (arguments.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of 'event' cannot be empty.");
        }
        assert !arguments.equals("") : "Arguments required, cannot be empty.";
        String[] splitTask = arguments.split(" /at ");
        if (splitTask.length < 2) {
            throw new DukeException("Please indicate the event time frame using '/at'.");
        }
        String description = splitTask[0];
        String atString = splitTask[1];
        try {
            LocalDateTime eventDateTime = LocalDateTime.parse(atString, FORMATTER);
            return tasks.addTask(new Event(description, eventDateTime));
        } catch (DateTimeParseException e) {
            throw new DukeException("Datetime should be in YYYY-MM-DD hr:min (24h clock) format.");
        }
    }

    private String addDeadline(String arguments) throws DukeException {
        if (arguments.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of 'deadline' cannot be empty.");
        }
        assert !arguments.equals("") : "Arguments required, cannot be empty.";
        String[] splitTask = arguments.split(" /by ");
        if (splitTask.length < 2) {
            throw new DukeException("Please indicate a deadline using '/by'.");
        }
        String description = splitTask[0];
        String byString = splitTask[1];
        try {
            LocalDateTime deadlineDateTime = LocalDateTime.parse(byString, FORMATTER);
            return tasks.addTask(new Deadline(description, deadlineDateTime));
        } catch (DateTimeParseException e) {
            throw new DukeException("Datetime should be in YYYY-MM-DD hr:min (24h clock) format.");
        }
    }
}
