package duke.command;

import duke.TaskList;

/**
 * Represents an edit command.
 */
public class EditCommand extends Command {

    private int task;
    private String edit;

    public EditCommand(String input) {
        String[] parsed = input.split(" ", 3);
        assert parsed.length == 3 : "Invalid command entered.";
        this.task = Integer.parseInt(parsed[1]);
        this.edit = parsed[2];
    }

    /**
     * Returns the result of the execution of the edit command.
     * @param tasks List of tasks the user has added.
     * @return Result of the execution of the edit command.
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.editTask(this.task, this.edit);
    }
}
