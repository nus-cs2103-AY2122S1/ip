import java.io.IOException;

public abstract class Command {
    public boolean isExit = false;
    public abstract void execute(List list, Ui ui, Storage storage) throws IOException;
}
