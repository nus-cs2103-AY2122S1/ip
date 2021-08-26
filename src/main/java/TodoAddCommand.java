public class TodoAddCommand extends AddCommand {

    private String description;

    public TodoAddCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int totalTasks = taskList.addToList("T", description, "NA");
        storage.addToText("T", description, "NA");
        ui.addingTask(totalTasks, description, "NA", "T");
    }
}
