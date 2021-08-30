package duke;

public abstract class Command {

    public Command() {
    }
    public abstract void execute(TaskList tasks) throws DukeException;
}
