package duke.command;

import java.time.format.DateTimeParseException;

import duke.ui.Ui;
import duke.util.DukeParser;
import task.TaskEvent;
import task.TaskList;

/**
 * Command to add an event.
 */
public class CommandAddEvent extends Command {
    private final TaskList taskList;
    private final String[] input;

    /**
     * Constructor for this command.
     *
     * @param taskList Task list to add to.
     * @param input Array with info needed to create the class.
     */
    public CommandAddEvent(TaskList taskList, String[] input) {
        this.commandName = "event <string> /at DD/MM/YYYY xxxx";
        this.description = "Creates a deadline task (Optional time argument)";
        this.arguments = new String[]{
            "<string> Description of Event",
            "/at Date in Day/Month/Year format",
            "Optional time argument (24 Hour format)"
        };

        this.taskList = taskList;
        this.input = input;
    }

    /**
     * Adds an event to the task list if valid.
     */
    @Override
    public String execute() {
        try {
            return taskList.add(new TaskEvent(
                    input[0],
                    DukeParser.getDate(input[1]),
                    input[2],
                    false));
        } catch (DateTimeParseException e) {
            return Ui.MESSAGE_INVALID_DATE;
        }
    }
}
