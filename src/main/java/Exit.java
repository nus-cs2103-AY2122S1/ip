public class Exit extends Command {
    public Exit() {
    }

    public void execute(ToDo taskList, Ui ui, Storage storage) throws KermitException {
        ui.showGoodbyeMessage();
        storage.save(taskList);
    }

    public boolean isExit() {
        return true;
    }
}