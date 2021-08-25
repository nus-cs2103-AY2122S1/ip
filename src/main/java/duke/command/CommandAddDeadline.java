package duke.command;

import task.TaskDeadline;
import task.TaskEvent;
import task.TaskList;
import task.TaskTodo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

public class CommandAddDeadline extends Command {
    private final TaskList taskList;
    private final Matcher input;

    public CommandAddDeadline(TaskList tasklist, Matcher groups) {
        this.commandName = "deadline <string> /by DD/MM/YYYY xxxxH";
        this.description = "Creates a deadline task (Optional time argument)";

        this.taskList = tasklist;
        input = groups;
    }

    @Override
    public void execute() {
        try {
            taskList.add(new TaskDeadline(input.group(1), getDate(input.group(2)), input.group(3), false));
        } catch (DateTimeParseException e) {
            System.out.println("Please enter a valid date! :(");
        }
    }

    private static LocalDate getDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
