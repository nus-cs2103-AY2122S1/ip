import java.io.IOException;

public class Command { //Base Command class
    private final boolean isRunning;

    public Command(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
    }
}

