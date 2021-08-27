package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class EventCommand extends Command {

    protected String input;

    protected ArrayList<Task> list;

    EventCommand(String input, ArrayList<Task> list) {
        this.input = input;
        this.list = list;
    }

    public String reply() throws DukeException {

        int position = input.indexOf("/at");
        String newTask = input.substring(6, position);
        String newTime = input.substring(position + 4);
        if (newTask.length() == 0) {
            throw new DukeException("The description of an event cannot be empty. Please try again!");
        } else if (newTime.length() == 0) {
            throw new DukeException("The date of an event cannot be empty. Please try again!");
        } else {
            try {
                LocalDateTime time = LocalDateTime.parse(newTime.trim(), inputFormatter);
                list.add(new EventTask(newTask, time));
                return addTask(newTask, 3, list.size() - 1, newTime.trim());
            } catch (DateTimeParseException e) {
                throw new DukeException(
                        "Your time format is wrong. Please enter the time in the format DD/MM/YYYY HHMM and try again!");
            }
        }
    }
}