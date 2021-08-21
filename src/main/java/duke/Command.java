package duke;

import duke.data.Storage;
import duke.data.TaskList;
import duke.io.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.time.LocalDateTime;

public class Command {

    /** Enumeration of valid commands */
    public enum COMMANDS {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT, BY, AT, ALL, HELP
    }
    /** Types of commands */
    public enum TYPES {
        SINGLE_INPUT, INT_INPUT, STR_INPUT, STR_ARR_INPUT, DATETIME_INPUT
    }
    private COMMANDS command;
    private TYPES type;
    private LocalDateTime dateTime;
    private int index;
    private String[] subInputs;
    private String description;
    private boolean isExit = false;

    /**
     * Returns a new Command.
     * Used to initializing SINGLE_INPUT commands
     */
    public Command(COMMANDS command) {
        this.command = command;
        this.type = TYPES.SINGLE_INPUT;
    }

    /**
     * Returns a new Command.
     * Used to initializing INT_INPUT commands
     */
    public Command(COMMANDS command, int index) {
        this.command = command;
        this.type = TYPES.INT_INPUT;
        this.index = index;
    }

    /**
     * Returns a new Command.
     * Used to initializing STR_INPUT commands
     */
    public Command(COMMANDS command, String description) {
        this.command = command;
        this.type = TYPES.STR_INPUT;
        this.description = description;
    }

    /**
     * Returns a new Command.
     * Used to initializing STR_ARR_INPUT commands
     */
    public Command(COMMANDS command, String[] subitems) {
        this.command = command;
        this.type = TYPES.STR_ARR_INPUT;
        this.subInputs = subitems;
    }

    /**
     * Returns a new Command.
     * Used to initializing DATETIME_INPUT commands
     */
    public Command(COMMANDS command, LocalDateTime dateTime) {
        this.command = command;
        this.type = TYPES.DATETIME_INPUT;
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
            if (this.command.equals(COMMANDS.BYE)) {
                this.isExit = true;
            }
            break;
        case INT_INPUT:
            Task t;
            if (this.command.equals(COMMANDS.DONE)) {
                t = tasks.markDone(this.index, storage);
            } else {
                t = tasks.removeTask(this.index, storage);
            }
            ui.displayCommand(this.command, this.index, t, tasks);
            storage.save();
            break;
        case STR_INPUT:
            tasks.addItem(new Todo(this.description), storage);
            ui.displayCommand(this.command, tasks);
            storage.save();
            break;
        case STR_ARR_INPUT:
            Task task;
            if (this.command.equals(COMMANDS.DEADLINE)) {
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
        }
    }
}
