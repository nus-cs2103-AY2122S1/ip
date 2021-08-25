package duke.command;

import task.TaskDeadline;
import task.TaskEvent;
import task.TaskList;
import task.TaskTodo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

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
        this.commandName = "deadline <string> /by DD/MM/YYYY xxxxH";
        this.description = "Creates a deadline task (Optional time argument)";

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
