public class CommandNotUsedException extends DukeException {
    private String command;

    public CommandNotUsedException(String command) {
        super("☹ OOPS!!! You did not provide the time." +
                "\n\t Please use the command " + command);
    }
}
