package duke.command;

public class ExitCommand extends Command {
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        ui.add("Bye. Hope to see you again soon!");
        ui.print();
        storage.saveTasks(taskList.getTasks());
    }

    public boolean isExit() {
        return true;
    }
}
