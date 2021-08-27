package duke;

import java.io.IOException;
import java.time.LocalDateTime;

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
    private boolean isExit = false;

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
     * Returns whether the exit command has been executed
     *
     * @return true if the exit command is executed else returns false.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes a command.
     *
     * @param tasks Lists of tasks to be executed on.
     * @param ui Ui object used to print to screen.
     * @param storage Storage object used to save any modifications
     * @throws DukeException If there is missing information or the declaration of the task is of the wrong format.
     * @throws IOException  If data file cannot be saved to.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        switch (this.type) {
        case SINGLE_INPUT:
            ui.displayCommand(this.command, tasks);
            if (this.command.equals(Commands.BYE)) {
                this.isExit = true;
            }
            break;
        case INT_INPUT:
            Task t;
            if (this.command.equals(Commands.DONE)) {
                t = tasks.markDone(this.index, storage);
            } else {
                t = tasks.removeTask(this.index, storage);
            }
            ui.displayCommand(this.command, this.index, t, tasks);
            storage.save();
            break;
        case STR_INPUT:
            if (this.command.equals(Commands.TODO)) {
                tasks.addItem(new Todo(this.description), storage);
                ui.displayCommand(this.command, tasks);
                storage.save();
            } else {
                ui.displayCommand(this.command, this.description, tasks);
            }
            break;
        case STR_ARR_INPUT:
            Task task;
            if (this.command.equals(Commands.DEADLINE)) {
                task = new Deadline(subInputs);
            } else {
                task = new Event(subInputs);
            }
            tasks.addItem(task, storage);
            ui.displayCommand(this.command, tasks);
            storage.save();
            break;
        case DATETIME_INPUT:
            ui.displayCommand(this.command, tasks, this.dateTime);
            break;
        default:
            break;
        }
    }
}
