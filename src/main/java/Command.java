import java.io.FileNotFoundException;

public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws FileNotFoundException;

    public boolean isExit() {
        return false;
    }
}
