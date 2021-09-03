package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.data.Storage;
import duke.data.TaskList;
import duke.io.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Command {

    /** Enumeration of valid commands */
    public enum Commands {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT, BY, AT, ALL, HELP, FIND
    }

    /** Types of commands */
    public enum Types {
        SINGLE_INPUT, INT_INPUT, STR_INPUT, STR_ARR_INPUT, DATETIME_INPUT
    }

    private Commands command;
    private Types type;
    private LocalDateTime dateTime;
    private int index;
    private String[] subInputs;
    private String description;

    /**
     * Returns a new Command.
     * Used to initializing SINGLE_INPUT commands
     *
     * @param command Command type to be instantiated.
     */
    public Command(Commands command) {
        this.command = command;
        this.type = Types.SINGLE_INPUT;
    }

    /**
     * Returns a new Command.
     * Used to initializing INT_INPUT commands
     *
     * @param command Command type to be instantiated.
     * @param index Index to be used in the command.
     */
    public Command(Commands command, int index) {
        this.command = command;
        this.type = Types.INT_INPUT;
        this.index = index;
    }

    /**
     * Returns a new Command.
     * Used to initializing STR_INPUT commands
     *
     * @param command Command type to be instantiated.
     * @param description description to be used in the command.
     */
    public Command(Commands command, String description) {
        this.command = command;
        this.type = Types.STR_INPUT;
        this.description = description;
    }

    /**
     * Returns a new Command.
     * Used to initializing STR_ARR_INPUT commands
     *
     * @param command Command type to be instantiated.
     * @param subitems Array of strings to be used in the command.
     */
    public Command(Commands command, String[] subitems) {
        this.command = command;
        this.type = Types.STR_ARR_INPUT;
        this.subInputs = subitems;
    }

    /**
     * Returns a new Command.
     * Used to initializing DATETIME_INPUT commands
     *
     * @param command Command type to be instantiated.
     * @param dateTime Date and time to be used in the commnad.
     */
    public Command(Commands command, LocalDateTime dateTime) {
        this.command = command;
        this.type = Types.DATETIME_INPUT;
        this.dateTime = dateTime;
    }

    /**
     * Executes a command and returns a String that signifies proper execution.
     *
     * @param tasks Lists of tasks to be executed on.
     * @param ui Ui object used to print to screen.
     * @param storage Storage object used to save any modifications
     * @return String that shows the command has been executed.
     * @throws DukeException If there is missing information or the declaration of the task is of the wrong format.
     * @throws IOException  If data file cannot be saved to.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException,
            DateTimeParseException, NumberFormatException {
        assert this.command != null : "Command should not be null";
        assert this.type != null : "Type should not be null";
        switch (this.type) {
        case SINGLE_INPUT:
            if (this.command.equals(Commands.BYE)) {
                Main.exit();
            }
            return ui.displayCommand(this.command, tasks);
        case INT_INPUT:
            Task t;
            if (this.command.equals(Commands.DONE)) {
                t = tasks.markDone(this.index, storage);
            } else {
                t = tasks.removeTask(this.index, storage);
            }
            storage.save();
            return ui.displayCommand(this.command, this.index, t, tasks);
        case STR_INPUT:
            assert this.description != null : "Description should not be null";
            if (this.command.equals(Commands.TODO)) {
                tasks.addItem(new Todo(this.description), storage);
                storage.save();
                return ui.displayCommand(this.command, tasks);
            }
            return ui.displayCommand(this.command, this.description, tasks);
        case STR_ARR_INPUT:
            assert this.subInputs != null : "subInputs should not be null";
            Task task;
            if (this.command.equals(Commands.DEADLINE)) {
                task = new Deadline(subInputs);
            } else {
                task = new Event(subInputs);
            }
            tasks.addItem(task, storage);
            storage.save();
            return ui.displayCommand(this.command, tasks);
        case DATETIME_INPUT:
            assert this.dateTime != null : "dateTime should not be null";
            return ui.displayCommand(this.command, tasks, this.dateTime);
        default:
            return "Oops there is an error";
        }
    }
}
