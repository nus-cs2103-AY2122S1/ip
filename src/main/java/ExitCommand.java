public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
