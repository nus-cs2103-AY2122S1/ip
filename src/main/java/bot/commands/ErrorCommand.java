package bot.commands;

public class ErrorCommand extends Command {
    private static final String DEFAULT_ERROR_MESSAGE = "\n\t My dictionary does not contain this sophisticated "
            + "language.\n\t Maybe someday :)";
    private final String errorMessage;
    public ErrorCommand() {
        this.errorMessage = DEFAULT_ERROR_MESSAGE;
    }
    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    @Override
    public String execute() {
        return errorMessage;
    }
}
