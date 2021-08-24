public class ExitCommand extends Command{

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            storage.writeToDisk(taskList.compileTasks());
        } catch (DukeException e) {
            e.printStackTrace();
            ui.respond(e.getMessage());
        }
    }
}
