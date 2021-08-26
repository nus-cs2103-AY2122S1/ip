// Only for wrong syntax
class InvalidCommand extends Command {
    private final String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(message);
    }
}
