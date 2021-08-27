package main.java;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DeadlineCommand extends Command {

    protected String input;

    protected ArrayList<Task> list;

    DeadlineCommand(String input, ArrayList<Task> list) {
        this.input = input;
        this.list = list;
    }

    public String reply() throws DukeException {

        int position = input.indexOf("/by");
        String newTask = input.substring(9, position);
        String newTime = input.substring(position + 4);
        if (newTask.length() == 0) {
            throw new DukeException("The description of a deadline cannot be empty. Please try again!");
        } else if (newTime.length() == 0) {
            throw new DukeException("The date of a deadline cannot be empty. Please try again!");
        } else {
            try {
                LocalDateTime time = LocalDateTime.parse(newTime.trim(), inputFormatter);
                list.add(new DeadlineTask(newTask, time));
                return addTask(newTask, 2, list.size() - 1, newTime.trim());
            } catch (DateTimeParseException e) {
                throw new DukeException("Your time format is wrong. Please enter the time in the format DD/MM/YYYY HHMM and try again!");
            }
        }
    }
}