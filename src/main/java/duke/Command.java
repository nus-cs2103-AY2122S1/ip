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

    public enum Commands {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT, BY, AT, ALL, HELP
    }
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

    public Command(Commands command) {
        this.command = command;
        this.type = Types.SINGLE_INPUT;
    }

    public Command(Commands command, int index) {
        this.command = command;
        this.type = Types.INT_INPUT;
        this.index = index;
    }

    public Command(Commands command, String description) {
        this.command = command;
        this.type = Types.STR_INPUT;
        this.description = description;
    }

    public Command(Commands command, String[] subitems) {
        this.command = command;
        this.type = Types.STR_ARR_INPUT;
        this.subInputs = subitems;
    }

    public Command(Commands command, LocalDateTime dateTime) {
        this.command = command;
        this.type = Types.DATETIME_INPUT;
        this.dateTime = dateTime;
    }

    public boolean isExit() {
        return this.isExit;
    }

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
            tasks.addItem(new Todo(this.description), storage);
            ui.displayCommand(this.command, tasks);
            storage.save();
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
        }
    }
}
