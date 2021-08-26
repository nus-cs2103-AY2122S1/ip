import java.io.IOException;

public abstract class Command {
    protected final String input;

    // For commands that do not need to further read input (e.g. list, bye)
    public Command() {
        this.input = "";
    }

    public Command(String input) {
        this.input = input;
    }

    public abstract TaskList execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public boolean isTerminated() {
        return false;
    }
}