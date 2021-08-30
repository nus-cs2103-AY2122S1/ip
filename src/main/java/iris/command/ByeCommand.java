package iris.command;

import iris.TaskList;

public class ByeCommand extends Command {
    @Override
    public String run(TaskList tasks) {
        return "Bye. Hope to see you again soon!";
    }
}
