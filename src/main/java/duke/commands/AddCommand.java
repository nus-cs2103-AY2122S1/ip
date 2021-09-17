package duke.commands;

import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

public class AddCommand extends Command {
    private TaskType type;
    private String commands;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for AddCommand object.
     *
     * @param type type of task.
     * @param commands command input by user.
     */
    public AddCommand(TaskType type, String commands) {
        this.type = type;
        this.commands = commands;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        this.storage = storage;
        this.tasks = tasks;
        String response = "";
        switch (type) {
        case TO_DO: {
            return addToDo();
        }
        case DEADLINE: {
            return addDeadline();
        }
        case EVENT: {
            return addEvent();
        }
        default: {
            return response;
        }
        }
    }

    private String addToDo() throws DukeException {
        if (commands.length() > 0) {
            ToDo td = new ToDo(commands);
            tasks.addToList(td);
            storage.updateFile(tasks);
            return "Got it. I've added this task:\n" + td + "\nNow you have " + tasks.getLength() + " tasks in the list.";
        } else {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private String addDeadline() throws DukeException {
        if (commands.length() > 0) {
            try {
                String[] details = commands.split("/by ");
                if (details.length == 1) {
                    throw new DukeException("☹ OOPS!!! Add a '/by deadline'");
                } else {
                    Deadline deadline = new Deadline(details[0], details[1]);
                    tasks.addToList(deadline);
                    storage.updateFile(tasks);
                    return "Got it. I've added this task:\n" + deadline + "\nNow you have " + tasks.getLength() + " tasks in the list.";
                }
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! The date of the deadline is poorly formatted (d/MM/yyyy or d/MM/yyyy HHmm)");
            }

        } else {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    private String addEvent() throws DukeException {
        if (commands.length() > 0) {
            try {
                String[] details = commands.split("/at ");
                if (details.length == 1) {
                    throw new DukeException("☹ OOPS!!! Add a '/at time of event'");
                } else {
                    Event event = new Event(details[0], details[1]);
                    tasks.addToList(event);
                    storage.updateFile(tasks);
                    return "Got it. I've added this task:\n" + event + "\nNow you have " + tasks.getLength() + " tasks in the list.";
                }
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! The date of the event is poorly formatted (d/MM/yyyy)");
            }

        } else {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
    }
}
