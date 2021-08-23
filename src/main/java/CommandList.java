public class CommandList extends DukeCommand {
    @Override
    public void execute(TaskList tl) {
        DukeUi.printLine(tl.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
