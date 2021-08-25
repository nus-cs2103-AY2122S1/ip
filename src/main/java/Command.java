public abstract class Command {

    private String userInput;
    private boolean exit = false;

    public Command(String userInput) {
        this.userInput = userInput;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {}

    public void setExitTrue() {
        this.exit = true;
    }

    public boolean isExit() {
        return this.exit;
    }

    public String getUserInput() {
        return this.userInput;
    }
}
