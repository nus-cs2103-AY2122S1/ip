public class ByeCommand extends Command {
    public ByeCommand(){}
    
    public void executeCommand(Tasklist taskList, UI ui, PersistentStorage storage) throws DukeException{
        try {
            ui.showExitMsg();
            storage.saveTasks(taskList);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public Boolean isExit() {
        return true;
    }
}
