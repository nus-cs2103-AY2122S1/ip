package duke.command;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

import task.TaskDeadline;
import task.TaskList;

/**
 * Command to add a deadline
 */
public class CommandAddDeadline extends Command {
    private final TaskList taskList;
    private final Matcher input;

    /**
     * Constructor
     *
     * @param taskList Task list
     * @param groups Matcher with info needed to create the class
     */
    public CommandAddDeadline(TaskList taskList, Matcher groups) {
        this.commandName = "deadline <string> /by DD/MM/YYYY xxxx";
        this.description = "Creates a deadline task";
        this.arguments = new String[]{
            "<string> Description of Deadline",
            "/by Date in Day/Month/Year format",
            "Optional time argument (24 Hour format)"
        };

        this.taskList = taskList;
        input = groups;
    }

    /**
     * Adds a deadline to the task list if valid
     */
    @Override
    public void execute() {
        try {
            taskList.add(new TaskDeadline(input.group(1), Command.getDate(input.group(2)), input.group(3), false));
        } catch (DateTimeParseException e) {
            System.out.println("Please enter a valid date! :(");
        }
    }
}
