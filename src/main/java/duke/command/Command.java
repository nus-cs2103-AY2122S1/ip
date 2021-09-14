package duke.command;

/**
 * Stores description of usage and format of command.
 */
public class Command {
    String cmd;
    String fn;
    String format;

    /**
     * Constructs Command object
     *
     * @param cmd command
     * @param fn description of usage
     * @param format format
     */
    public Command(String cmd, String fn, String format) {
        this.cmd = cmd;
        this.fn = fn;
        this.format = format;
    }

    /**
     * Returns command
     *
     * @return command
     */
    public String getCmd() {
        return this.cmd;
    }

    /**
     * Returns description of usage
     *
     * @return description of usage
     */
    public String getFn() {
        return this.fn;
    }

    /**
     * Returns format
     *
     * @return format
     */
    public String getFormat() {
        return this.format;
    }
}
