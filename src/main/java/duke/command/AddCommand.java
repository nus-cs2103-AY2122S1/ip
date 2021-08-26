package duke.command;

abstract class AddCommand implements Command {
    @Override
    public boolean isExit() {
        return false;
    }

}
