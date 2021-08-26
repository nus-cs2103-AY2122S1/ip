public class DoneCommand extends Command {
    private final int taskNumber;

    public DoneCommand(String userInput) {
        this.taskNumber = Integer.parseInt(
                userInput.replaceAll(
                        "[^0-9]",
                        ""));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsg(tasks.markAsDone(this.taskNumber));
        storage.write(tasks.getSaveData());
    }
}
