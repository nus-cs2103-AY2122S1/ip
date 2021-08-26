public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        String saveFileString = tasks.save();
        storage.save(saveFileString);
        Ui.endDuke();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
