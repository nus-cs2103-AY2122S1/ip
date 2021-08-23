public class CommandList extends Command {
    CommandList(String[] inputTokens) {
        super(inputTokens);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        int currListLength = tasks.size();
        ui.printTaskList(tasks);
    }
}
