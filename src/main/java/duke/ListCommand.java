package duke;
public class ListCommand extends Command {

    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeException {
        ui.showList();
        int point = 0;
        while (point < tasks.size()) {
            Task temp = tasks.get(point);
            ui.listNumber(point, temp);
            point++;
        }
        ui.numberOfTasks(tasks);
    }
}
