package iris.command;

import javafx.application.Platform;

import iris.TaskList;

public class ByeCommand extends Command {
    @Override
    public String run(TaskList tasks) {
        Platform.exit();
        return "Bye. Hope to see you again soon!";
    }
}
