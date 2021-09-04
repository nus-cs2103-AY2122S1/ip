package duke;
import java.util.List;

public class Pair {
    private Command cmd;
    private List<String> lst;

    public Pair(Command cmd, List<String> lst) {
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
