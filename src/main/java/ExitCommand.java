public class ExitCommand extends Command {
    public ExitCommand() {
        setCommandString("bye");
    }

    @Override
    public void parse(String input) {
        // String data = input.substring(getCommandLength());
        Duke.exit();
    }
}
