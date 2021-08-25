abstract class Command {
    abstract void execute(TaskList taskList, Ui ui, Storage storage) throws Exception;
    abstract boolean shouldExit();
}
