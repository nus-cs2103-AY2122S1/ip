public class DeleteCommand extends Command {
    private final int parseInt;

    public DeleteCommand(int parseInt) {
        this.parseInt = parseInt;
    }

    @Override
    public void execute(Tasklist tasklist, Storage storage, Ui ui) throws AisuException {
        Task deletedTask = tasklist.deleteTask(this.parseInt);
        storage.save(tasklist);
        ui.showToUser(" Noted~ I've removed this task:",
                " - " + deletedTask,
                " Now you have " + tasklist.getListSize() + " task(s) in the list.\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}