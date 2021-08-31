package duke;

public class ListCommand implements Command {
    public ListCommand() {
        super();
    }

    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    };

    public boolean isExit() {
        return false;
    }
}
