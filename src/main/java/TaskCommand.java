import java.io.IOException;
import java.util.Scanner;

public class TaskCommand extends Command {
    private String description;
    public TaskCommand(Duke duke, Scanner sc, String description) {
        super(duke, sc);
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList) throws IOException, DukeException {
        Ui.printAddedTaskMessage(this.duke.addTaskToList(description), taskList.getTotal());
        this.duke.saveTasks();
    }
}
