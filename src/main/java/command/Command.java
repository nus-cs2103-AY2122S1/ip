package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;
import exceptions.NoSuchCommandException;

import java.io.IOException;

/**
 * Abstract class for all Commands to extend from.
 *
 * @author Quan Teng Foong
 */
public abstract class Command {
    private final String extraInput;

    public Command(String extraInput) {
        this.extraInput = extraInput;
    }

    public Command() {
        this.extraInput = "";
    }

    /**
     * Factory method to return the appropriate command for the given strings.
     *
     * @param command command name
     * @param extraInput further details
     * @return a Command object that encapsulates the necessary actions.
     */
    public static Command of(String command, String extraInput) throws NoSuchCommandException {
        switch(command) {
        case "bye":
            return new SaveAndExitCommand();
        case "list":
            return new ShowListCommand();
        case "done":
            return new TaskCompletedCommand(extraInput);
        case "delete":
            return new DeleteTaskCommand(extraInput);
        case "find":
            return new SearchCommand(extraInput);
        case "todo":
            return new CreateNewToDoCommand(extraInput);
        case "event":
            return new CreateNewEventCommand(extraInput);
        case "deadline":
            return new CreateNewDeadlineCommand(extraInput);
        default:
            throw new NoSuchCommandException("No such command!");
        }
    }

    /**
     * Returns the extra input field for further action by the command.
     *
     * @return extraInput String
     */
    public String getExtraInput() {
        return this.extraInput;
    }

    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

    public boolean isExit() {
        return false;
    }
}
