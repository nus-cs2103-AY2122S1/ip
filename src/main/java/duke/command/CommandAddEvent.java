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
    private final Matcher input;

    /**
     * Constructor
     *
     * @param taskList Task list
     * @param groups Matcher with info needed to create the class
     */
    public CommandAddEvent(TaskList taskList, Matcher groups) {
        this.commandName = "event <string> /at DD/MM/YYYY xxxxH";
        this.description = "Creates a deadline task (Optional time argument)";
        this.arguments = new String[]{
            "<string> Description of Event",
            "/at Date in Day/Month/Year format",
            "Optional time argument (24 Hour format)"
        };

        this.taskList = taskList;
        input = groups;
    }

    /**
     * Add an event to the task list if valid
     */
    @Override
    public void execute() {
        try {
            taskList.add(new TaskEvent(input.group(1), Command.getDate(input.group(2)), input.group(3), false));
        } catch (DateTimeParseException e) {
            System.out.println("Please enter a valid date! :(");
        }
    }
}
