public class InvalidCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Invalid input. Please try again.\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void invalidArguments() {
        // Not needed
    }
}
