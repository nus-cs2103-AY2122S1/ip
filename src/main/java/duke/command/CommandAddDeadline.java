package duke.command;

import java.time.format.DateTimeParseException;

import duke.ui.Ui;
import duke.util.DukeParser;
import task.TaskDeadline;
import task.TaskList;

/**
 * Command to add a deadline.
 */
public class CommandAddDeadline extends Command {
    private final TaskList taskList;
    private final String[] input;

    /**
     * Constructor for this command.
     *
     * @param taskList Task list to add to.
     * @param input Array with info needed to create the class.
     */
    public CommandAddDeadline(TaskList taskList, String[] input) {
        this.commandName = "deadline <string> /by DD/MM/YYYY xxxx";
        this.description = "Creates a deadline task";
        this.arguments = new String[]{
            "<string> Description of Deadline",
            "/by Date in Day/Month/Year format",
            "Optional time argument (24 Hour format)"
        };

        this.taskList = taskList;
        this.input = input;
    }

    /**
     * Adds a deadline to the task list if valid.
     */
    @Override
    public String execute() {
        try {
            return taskList.add(new TaskDeadline(
                    input[0],
                    DukeParser.getDate(input[1]),
                    input[2],
                    false));
        } catch (DateTimeParseException e) {
            return Ui.MESSAGE_INVALID_DATE;
        }
    }
}
