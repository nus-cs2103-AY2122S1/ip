package duke;

public abstract class Command {
    public abstract void execute(TaskList t, Ui u, Storage storage);
}
