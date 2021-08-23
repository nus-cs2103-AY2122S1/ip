public class ListCommand extends Command {

    public ListCommand() {
        super("list");
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        ui.println("Here are the tasks in your list:");
        if (tasks.isEmpty()) {
            ui.println("  You currently have no tasks. Why not add a task?");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                ui.println((i + 1) + ". " + currTask);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
