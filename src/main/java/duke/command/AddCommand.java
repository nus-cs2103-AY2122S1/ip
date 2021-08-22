package duke.command;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        taskList.add(this.task);
        ui.add("Got it. I've added this task:");
        ui.add("  " + task);
        ui.add(String.format("Now you have %d task(s) in the list.", taskList.size()));
        ui.print();
        storage.saveTasks(taskList.getTasks());
    }
}
