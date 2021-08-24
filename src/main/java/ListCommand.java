import java.io.IOException;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            storage.writeCurrentData(taskList.getTasks());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        ui.displayListOfTasks(taskList);
    }
}
