package iris.command;

import iris.IrisException;
import iris.TaskList;

public class DeadlineCommand extends AddCommand {
    private String name;
    private String by;

    public DeadlineCommand(String name, String by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public void runSilently(TaskList tasks) throws IrisException {
        tasks.addDeadline(this.name, this.by);
    }
}
