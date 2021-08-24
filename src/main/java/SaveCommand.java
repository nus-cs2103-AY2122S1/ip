import java.io.IOException;

public class SaveCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.writeCurrentData(tasks.getTasks());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        ui.displaySaveMessage();
    }
}
