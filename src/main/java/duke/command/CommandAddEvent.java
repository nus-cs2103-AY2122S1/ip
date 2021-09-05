package duke.command;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

import task.TaskEvent;
import task.TaskList;

/**
 * Command to add an event
 */
public class CommandAddEvent extends Command {
    private final TaskList taskList;
    private final String[] input;

    /**
     * Constructor
     *
     * @param taskList Task list
     * @param input Matcher with info needed to create the class
     */
    public CommandAddEvent(TaskList taskList, String[] input) {
        this.commandName = "event <string> /at DD/MM/YYYY xxxxH";
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
     * Add an event to the task list if valid
     */
    @Override
    public String execute() {
        try {
            return taskList.add(new TaskEvent(
                    input[0],
                    Command.getDate(input[1]),
                    input[2],
                    false));
        } catch (DateTimeParseException e) {
            return "Please enter a valid date! :(";
        }
    }
}
