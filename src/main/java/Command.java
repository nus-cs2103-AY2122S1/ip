import java.io.IOException;
import java.time.LocalDateTime;

public class Command {

    public enum COMMANDS {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT, BY, AT, ALL, HELP
    }
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

    public Command(COMMANDS command) {
        this.command = command;
        this.type = TYPES.SINGLE_INPUT;
    }

    public Command(COMMANDS command, int index) {
        this.command = command;
        this.type = TYPES.INT_INPUT;
        this.index = index;
    }

    public Command(COMMANDS command, String description) {
        this.command = command;
        this.type = TYPES.STR_INPUT;
        this.description = description;
    }

    public Command(COMMANDS command, String[] subitems) {
        this.command = command;
        this.type = TYPES.STR_ARR_INPUT;
        this.subInputs = subitems;
    }

    public Command(COMMANDS command, LocalDateTime dateTime) {
        this.command = command;
        this.type = TYPES.DATETIME_INPUT;
        this.dateTime = dateTime;
    }

    public boolean isExit() {
        return this.isExit;
    }

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
