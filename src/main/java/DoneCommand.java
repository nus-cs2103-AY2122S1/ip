public class DoneCommand extends Command {

    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws EightBitException {
        if (index >= taskList.size()) {
            throw new EightBitException("OOPS!!! Task " + (index + 1) + " does not exist.");
        }

        taskList.get(index).markAsDone();
        storage.rewriteFileWithTasks(taskList);
        ui.printWithLines("Great job on completing this task!\n" + taskList.get(index).toString());
    }
}
