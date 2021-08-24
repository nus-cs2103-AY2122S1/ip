package duke.command;

import duke.*;
import duke.task.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class CommandEvent extends Command {
    public static final String KEYWORD = "event";
    private ArrayList<String> arguments;


    public CommandEvent(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isArgumentValid() {
        try {

            if (arguments.size() >= 4 && arguments.get(1).contains("/at")) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(arguments.get(2), dateFormatter);

                String[] segments = arguments.get(3).split("-");
                String timeStart = segments[0];
                String timeEnd = segments[1];
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("k:mm");
                LocalTime timeS = LocalTime.parse(timeStart, timeFormatter);
                LocalTime timeE = LocalTime.parse(timeEnd, timeFormatter);
                return true;
            } else {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public void execute(TaskList tl, Storage st, Ui ui) {
        if (isArgumentValid()) {
            Event newEvent;
            newEvent = new Event(arguments.get(0), arguments.get(2), arguments.get(3));
            tl.addTask(newEvent);
        } else {
            throw new DukeException("Invalid argument for duke.command: event");
        }
    }

}
