public abstract class Command {

    protected String input;

    public Command(String input) {
        this.input = input;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JWBotException;

    public abstract boolean isExit();
}
