public abstract class Command {

    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage);
}