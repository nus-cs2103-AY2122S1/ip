package duke;

import java.io.IOException;

/**
 * Find class to maintain find commands.
 */
public class Find implements GeneralCommand {
    private String description;
    private TaskList tasks;
    private Ui ui;
    private TaskList matchingTasks;

    /**
     * Constructs Find object.
     *
     * @param command Parsed command.
     * @param tasks TaskList of current tasks.
     * @param ui Ui to return String.
     * @throws FindException If find is incomplete.
     */
    public Find(String command, TaskList tasks, Ui ui) throws FindException {
        this.description = command.substring(4);
        this.tasks = tasks;
        this.ui = ui;
        this.matchingTasks = new TaskList();

        String desc = command.substring(4);
        if (desc.isEmpty()) {
            throw new FindException();
        }
        assert desc.substring(1).length() > 0 : "Description should be present";
    }

    /**
     * Executes finding of task.
     *
     * @return String to be printed on GUI.
     * @throws IOException If an input or output operation is failed or interpreted.
     */
    @Override
    public String execute() throws IOException {
        for (Task t : tasks) {
            if (t.toString().contains(description)) {
                matchingTasks.add(t);
            }
        }
        if (matchingTasks.isEmpty()) {
            return ui.nothingFoundMessageToString();
        } else {
            return ui.listMessageToString("matching", matchingTasks);
        }
    }
}
