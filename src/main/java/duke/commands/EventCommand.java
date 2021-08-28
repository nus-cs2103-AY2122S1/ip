package duke.commands;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.TaskList;

/**
 * EventCommand class used to represent an Event Command.
 * Contains methods that
 * (i)    executes the EventCommand
 */
public class EventCommand extends Command {
    public EventCommand() {
        super("event");
    }

    /**
     * execute() method in EventCommand to add an Event task into the TaskList.
     *
     * @param des   the user input into the Duke chat-box.
     * @param tList the TaskList object used to keep track of all tasks.
     * @return String object to describe execution of EventCommand.
     * @throws DukeException if input for Delete command is not properly formatted.
     */
    @Override
    public String execute(String des, TaskList tList) throws DukeException {
        if (des.equals("event")) {
            throw new DukeException("\"event\" command not correctly formatted \n"
                    + "Please insert task and timeframe arguments");
        }
        if (!des.contains("/at")) {
            throw new DukeException("\"event\" command not correctly formatted \n"
                    + "Please do not forget to include \"at\" and insert timeframe argument");
        }
        try {
            if (des.chars().filter(c -> c == '/').count() > 1) {
                throw new DukeException("Format Error. "
                        + "Do not use the special character \"/\" within your task description.");
            }
            String description = des.substring(6, des.indexOf('/') - 1);
            LocalDate date = Storage.extractDate(des);
            ArrayList<LocalTime> startEnd = Storage.extractTimeEvent(des);
            Event atHand = new Event(description, date, startEnd.get(0), startEnd.get(1));
            tList.add(atHand);
            Storage.createFile();
            Storage.writeToFile(tList);
            return "Sure. The following task has been added: \n"
                    + atHand.formatString()
                    + "\n"
                    + "\n"
                    + numberOfTasks(tList)
                    + "\n";
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("\"event\" command not correctly formatted");
        }
    }
}
