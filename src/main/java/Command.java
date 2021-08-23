abstract class Command {
    protected void execute() throws DukeException {
    }

    protected boolean isExit() {
        return false;
    }
}
