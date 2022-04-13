package chadbot;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import chadbot.data.Storage;
import chadbot.data.TaskList;
import chadbot.io.Ui;
import chadbot.task.Deadline;
import chadbot.task.Event;
import chadbot.task.Task;
import chadbot.task.Todo;

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
     * @throws ChadException If there is missing information or the declaration of the task is of the wrong format.
     * @throws IOException  If data file cannot be saved to.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChadException, IOException,
            DateTimeParseException, NumberFormatException {
        assert this.command != null : "Command should not be null";
        assert this.type != null : "Type should not be null";
        switch (this.type) {
        case SINGLE_INPUT:
            return executeSingleInputCommand(tasks, ui);
        case INT_INPUT:
            return executeIntInputCommand(tasks, ui, storage);
        case STR_INPUT:
            return executeStrInputCommand(tasks, ui, storage);
        case STR_ARR_INPUT:
            return executeStrArrInputCommand(tasks, ui, storage);
        case DATETIME_INPUT:
            return executeDateTimeInputCommand(tasks, ui);
        default:
            throw new ChadException(ChadException.Type.EXECUTE);
        }
    }

    private String executeDateTimeInputCommand(TaskList tasks, Ui ui) {
        assert this.dateTime != null : "dateTime should not be null";
        return ui.displayDateTimeFilteredCommand(this.command, tasks, this.dateTime);
    }

    private String executeStrArrInputCommand(TaskList tasks, Ui ui, Storage storage) throws ChadException, IOException {
        assert this.subInputs != null : "subInputs should not be null";
        Task task;
        if (this.command.equals(Commands.DEADLINE)) {
            task = new Deadline(subInputs);
        } else if (this.command.equals(Commands.EVENT)) {
            task = new Event(subInputs);
        } else {
            throw new ChadException(ChadException.Type.EXECUTE);
        }
        tasks.addItem(task, storage);
        storage.save();
        return ui.displayAddOrSingleInputCommand(this.command, tasks);
    }

    private String executeStrInputCommand(TaskList tasks, Ui ui, Storage storage) throws ChadException, IOException {
        assert this.description != null : "Description should not be null";
        if (this.command.equals(Commands.TODO)) {
            tasks.addItem(new Todo(this.description), storage);
            storage.save();
            return ui.displayAddOrSingleInputCommand(this.command, tasks);
        } else if (this.command.equals(Commands.FIND)) {
            return ui.displayFindTaskCommand(this.command, this.description, tasks);
        }
        throw new ChadException(ChadException.Type.EXECUTE);
    }

    private String executeIntInputCommand(TaskList tasks, Ui ui, Storage storage) throws ChadException, IOException {
        Task t;
        if (this.command.equals(Commands.DONE)) {
            t = tasks.markDone(this.index, storage);
        } else if (this.command.equals(Commands.DELETE)) {
            t = tasks.removeTask(this.index, storage);
        } else {
            throw new ChadException(ChadException.Type.EXECUTE);
        }
        storage.save();
        return ui.displayTaskModificationCommand(this.command, t, tasks);
    }

    private String executeSingleInputCommand(TaskList tasks, Ui ui) throws ChadException {
        if (this.command.equals(Commands.BYE)) {
            Main.exit();
        } else if (this.command.equals(Commands.LIST) || this.command.equals(Commands.HELP)) {
            return ui.displayAddOrSingleInputCommand(this.command, tasks);
        }
        throw new ChadException(ChadException.Type.EXECUTE);
    }
}
