package duke;
import java.util.List;

/**
 * Pair encapsulates a Command Enum value and a List of string arguments, facilitating communication between classes.
 */
public class Pair {
    private Command cmd;
    private List<String> lst;

    public Pair(Command cmd, List<String> lst) {
        assert cmd != null;
        assert lst != null;
        this.cmd = cmd;
        this.lst = lst;
    }

    public Command getCommand() {
        return this.cmd;
    }

    public java.util.List<String> getList() {
        return this.lst;
    }
}
