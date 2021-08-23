public class DeleteTaskCommand extends Command {
    private int indexToDelete;

    public DeleteTaskCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return String.format("Got it. I've removed this task:\n  %s\n%s", taskList.remove(indexToDelete).toString(),
                taskList.getOverview());
    }
}
