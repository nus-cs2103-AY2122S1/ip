public abstract class Command {

    protected String input;

    public Command(String input) {
        this.input = input;
    }
    public abstract boolean execute(TaskList taskList, Ui ui, Storage storage) throws Exception;
}
