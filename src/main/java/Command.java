import java.io.IOException;

public abstract class Command {

    public abstract void execute(Tasklist taskList, Ui ui, Storage storage) throws NoSuchTaskException, IOException;

    public boolean isExit() {
        return false;
    }
}