public abstract class Command {

    String space = Ui.space;

    abstract void execute(TaskList tasks, Ui ui);

}
