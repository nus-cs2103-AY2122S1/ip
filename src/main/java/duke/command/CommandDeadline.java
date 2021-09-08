package duke.command;

import duke.DukeDate;
import duke.exception.DukeArgumentException;
import duke.exception.DukeCommandException;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * Represents a command to add a Deadline task.
 */
public class CommandDeadline extends DukeCommand {
    private Deadline task;

    /**
     * Creates a new CommandDeadline.
     *
     * @param t Deadline to be added.
     */
    public CommandDeadline(Deadline t) {
        this.task = t;
    }

    /**
     * Adds the Deadline task to the task list.
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
        assert userArgs[0].equals("deadline");

        if (userArgs.length < 2) {
            throw new DukeCommandException("deadline");
        }

        assert userArgs.length == 2;

        String[] nameAndTime = userArgs[1].split(" /by ", 2);
        if (nameAndTime.length < 2) {
            throw new DukeCommandException("deadline");
        } else if (nameAndTime[0].equals("")) {
            throw new DukeArgumentException("Deadline name cannot be empty");
        } else if (nameAndTime[0].contains("|")) {
            throw new DukeArgumentException("Deadline name cannot contain \"|\".");
        }

        Deadline task = new Deadline(nameAndTime[0], DukeDate.parseDateInput(nameAndTime[1]));
        return new CommandDeadline(task);
    }
}
