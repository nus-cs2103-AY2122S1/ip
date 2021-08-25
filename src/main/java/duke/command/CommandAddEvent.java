package duke.command;

import task.TaskDeadline;
import task.TaskEvent;
import task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

public class CommandAddEvent extends Command{
    private final TaskList taskList;
    private final Matcher input;

    public CommandAddEvent(TaskList tasklist, Matcher groups) {
        this.commandName = "event <string> /at DD/MM/YYYY xxxxH";
        this.description = "Creates a deadline task (Optional time argument)";

        this.taskList = tasklist;
        input = groups;
    }

    @Override
    public void execute() {
        try {
            taskList.add(new TaskEvent(input.group(1), getDate(input.group(2)), input.group(3), false));
        } catch (DateTimeParseException e) {
            System.out.println("Please enter a valid date! :(");
        }
    }

    private static LocalDate getDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
