package duke.commands;

import duke.tasks.TaskList;
import duke.util.Ui;

public class FalseCommand extends Command {
    private final String input;

    public FalseCommand(String input) {
        this.input = input;
    }
    /**
     * Returns an error String message as input command cannot be recognised.
     *
     * @param tasks Current taskList.
     * @param ui User interface of Duke.
     * @return Error String message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return input.trim().equals("")
                ? "Please make an input! You may refer to our user guide for more info on how to use Duke :)"
                : "OOPS! Invalid command input! Please try again :)";
    }
}
