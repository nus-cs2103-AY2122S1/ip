package iris.command;

import iris.IrisException;
import iris.TaskList;

public class DeadlineCommand extends AddCommand {
    private String name;
    private String by;

    /**
     * Creates a new DeadlineCommand
     *
     * @param name name of the deadline
     * @param by   due date for this deadline e.g. "2021-08-23"
     */
    public DeadlineCommand(String name, String by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public void runSilently(TaskList tasks) throws IrisException {
        tasks.addDeadline(this.name, this.by);
    }
}
