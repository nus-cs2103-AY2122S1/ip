import java.io.IOException;
import java.util.Scanner;

public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(Duke duke, Scanner sc, int index) {
        super(duke, sc);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList) throws IOException {
        this.duke.setTaskAsDone(this.index);
        Ui.printDoneMessage(taskList.getTask(this.index - 1));
        duke.saveTasks();
    }
}

