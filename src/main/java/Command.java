public abstract class Command {

    private String userInput;

    public Command(String userInput) {
        this.userInput = userInput;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();

    public String getUserInput() {
        return this.userInput;
    }

}
