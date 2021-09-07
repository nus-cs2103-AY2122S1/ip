package duke.command;

public class Command {
    String cmd;
    String fn;
    String format;

    public Command(String cmd, String fn, String format) {
        this.cmd = cmd;
        this.fn = fn;
        this.format = format;
    }

    public String getCmd() {
        return this.cmd;
    }

    public String getFn() {
        return this.fn;
    }

    public String getFormat() {
        return this.format;
    }
}
