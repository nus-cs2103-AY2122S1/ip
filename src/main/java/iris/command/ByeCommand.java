package iris.command;

import iris.TaskList;
import javafx.application.Platform;

public class ByeCommand extends Command {
    @Override
    public String run(TaskList tasks) {
        Platform.exit();
        return "Bye. Hope to see you again soon!";
    }
}
