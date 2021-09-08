package duke.command;

import duke.DukeDate;
import duke.exception.DukeArgumentException;
import duke.exception.DukeCommandException;
import duke.task.Event;
import duke.task.TaskList;

/**
 * Represents a command to add an Event task.
 */
public class CommandEvent extends DukeCommand {
    private Event task;

    /**
     * Creates a new CommandEvent.
     *
     * @param t Event to be added.
     */
    public CommandEvent(Event t) {
        this.task = t;
    }

    /**
     * Adds the event to the task list.
     *
     * @param tl Task list for the user.
     */
    @Override
    public String execute(TaskList tl) {
        return tl.addTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses the user input into the right format for the command
     *
     * @param userArgs Arguments to the command as provided by the user.
     */
    public static DukeCommand parseCommand(String[] userArgs) throws DukeCommandException, DukeArgumentException {
        assert userArgs != null;
        assert userArgs.length != 0;
        assert userArgs[0].equals("event");

        if (userArgs.length < 2) {
            throw new DukeCommandException("event");
        }

        assert userArgs.length == 2;

        String[] nameAndTime = userArgs[1].split(" /at ", 2);
        if (nameAndTime.length < 2) {
            throw new DukeCommandException("event");
        } else if (nameAndTime[0].equals("")) {
            throw new DukeArgumentException("Event name cannot be empty");
        } else if (nameAndTime[0].contains("|")) {
            throw new DukeArgumentException("Event name cannot contain \"|\".");
        }

        Event task = new Event(nameAndTime[0], DukeDate.parseDateInput(nameAndTime[1]));
        return new CommandEvent(task);
    }
}
