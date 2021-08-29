public class UnknownCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-( " +
                "Type \"help\" for a list of commands I understand.");
    }

    public boolean isExit() {
        return false;
    }
}
