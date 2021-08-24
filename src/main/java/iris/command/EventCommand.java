package iris.command;

import iris.IrisException;
import iris.TaskList;

public class EventCommand extends AddCommand {
    private String name;
    private String at;

    public EventCommand(String name, String at) {
        this.name = name;
        this.at = at;
    }

    @Override
    public void runSilently(TaskList tasks) throws IrisException {
        tasks.addEvent(this.name, this.at);
    }
}
