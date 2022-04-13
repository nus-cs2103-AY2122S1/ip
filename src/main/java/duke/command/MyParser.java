package duke.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class implements a parser that deals with making sense of user commands.
 *
 * CS2103T ip
 * AY21/22 Semester 1
 *
 * @author Kishendran Vendar Kon (Group G05)
 */
public class MyParser {
    private static final SimpleDateFormat DEADLINE_TIME_FORMAT = new SimpleDateFormat("d/MM/yyyy HHmm");
    /**
     * Makes sense of user commands.
     *
     * @param command First word of line inputted by user to decide command.
     * @param description Following words of line inputted by user.
     * @param duke Duke instance referred to.
     * @throws DukeException if line inputted by the user is formatted incorrectly.
     */
    public void parse(String command, String description, Duke duke) throws DukeException {
        String userDescription = description.trim();
        assert userDescription != null : "description field should never be null";
        switch (command) {
        case "bye":
            duke.executeByeCommand();
            break;
        case "list":
            duke.executeListCommand();
            break;
        case "done":
            parseDoneCommand(userDescription, duke);
            break;
        case "delete":
            parseDeleteCommand(userDescription, duke);
            break;
        case "todo":
            parseTodoCommand(userDescription, duke);
            break;
        case "deadline":
            parseDeadlineCommand(userDescription, duke);
            break;
        case "event":
            parseEventCommand(userDescription, duke);
            break;
        case "find":
            parseFindCommand(userDescription, duke);
            break;
        case "edit":
            parseEditCommand(userDescription, duke);
            break;
        default:
            parseUnknownCommand(command);
        }
    }

    private void parseDoneCommand(String userDescription, Duke duke) throws DukeException {
        if (userDescription.isBlank()) {
            throw new DukeException("OOPS!!! The description of done cannot be empty");
        }

        String regex = "[0-9]+";

        if (!userDescription.matches(regex)) {
            throw new DukeException("OOPS!!! The description of done must be an integer");
        }

        int targetTask = Integer.parseInt(userDescription);

        duke.executeDoneCommand(targetTask);
    }

    private void parseDeleteCommand(String userDescription, Duke duke) throws DukeException {
        if (userDescription.isBlank()) {
            throw new DukeException("OOPS!!! The description of delete cannot be empty");
        }

        String regex = "[0-9]+";

        if (!userDescription.matches(regex)) {
            throw new DukeException("OOPS!!! The description of delete must be an integer");
        }

        int targetTask = Integer.parseInt(userDescription);
        duke.executeDeleteCommand(targetTask);
    }

    private void parseTodoCommand(String userDescription, Duke duke) throws DukeException {
        if (userDescription.isBlank()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty");
        }

        duke.executeTodoCommand(userDescription);
    }

    private void parseDeadlineCommand(String userDescription, Duke duke) throws DukeException {
        int dueByIndex = userDescription.indexOf("/by ");

        if (userDescription.isBlank() || dueByIndex == 0) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty");
        }

        if (dueByIndex == -1) {
            throw new DukeException("OOPS!!! No deadline has been set. Input with '/by dd/mm/yyyy 0000'");
        }

        String desc = userDescription.substring(0, dueByIndex);
        String dueBy = userDescription.substring(dueByIndex + 4);

        Calendar deadlineCal = Calendar.getInstance();
        Date d;
        try {
            d = DEADLINE_TIME_FORMAT.parse(dueBy);
            deadlineCal.setTime(d);
        } catch (ParseException e) {
            throw new DukeException("OOPS!!! The date is not formatted as dd/mm/yyyy 0000");
        }
        duke.executeDeadlineCommand(desc, deadlineCal);
    }

    private void parseEventCommand(String userDescription, Duke duke) throws DukeException {
        int atIndex = userDescription.indexOf("/at ");
        if (userDescription.isBlank() || atIndex == 0) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty");
        }

        if (atIndex == -1) {
            throw new DukeException("OOPS!!! No event time has been set. Input with '/at dd/mm/yyyy 0000'");
        }

        String desc = userDescription.substring(0, atIndex);
        String at = userDescription.substring(atIndex + 4);

        Calendar eventCal = Calendar.getInstance();

        try {
            eventCal.setTime(DEADLINE_TIME_FORMAT.parse(at));
        } catch (ParseException e) {
            throw new DukeException("OOPS!!! The date is not formatted as dd/mm/yyyy 0000");
        }
        duke.executeEventCommand(desc, eventCal);
    }

    private void parseFindCommand(String userDescription, Duke duke) throws DukeException {
        if (userDescription.isBlank()) {
            throw new DukeException("OOPS!!! The description of find cannot be empty");
        }

        duke.executeFindCommand(userDescription);
    }

    private void parseEditCommand(String userDescription, Duke duke) throws DukeException {
        int editDescIndex = userDescription.indexOf("/desc ");
        int editTimeIndex = userDescription.indexOf("/time ");

        if (userDescription.isBlank() || (editDescIndex == 0 && editTimeIndex == 0)) {
            throw new DukeException("OOPS!!! The index of edit cannot be empty");
        }

        if (editDescIndex == -1 && editTimeIndex == -1) {
            throw new DukeException("OOPS!!! No field is being edited. Use /desc or /time followed by new info");
        }

        if (editDescIndex > 0) {
            parseEditDescriptionCommand(userDescription, editDescIndex, duke);
        } else if (editTimeIndex > 0) {
            parseEditTimeCommand(userDescription, editTimeIndex, duke);
        }
    }

    private void parseEditDescriptionCommand(String userDescription, int infoIndex, Duke duke) throws DukeException {
        String editIndex = userDescription.substring(0, infoIndex);
        editIndex = editIndex.trim();
        String info = userDescription.substring(infoIndex + 6);

        String regex = "[0-9]+";

        if (!editIndex.matches(regex)) {
            throw new DukeException("OOPS!!! An integer must follow after an edit command");
        }

        int targetTask = Integer.parseInt(editIndex);

        duke.executeEditDescriptionCommand(info, targetTask);
    }

    private void parseEditTimeCommand(String userDescription, int infoIndex, Duke duke) throws DukeException {
        String editIndex = userDescription.substring(0, infoIndex);
        editIndex = editIndex.trim();
        String info = userDescription.substring(infoIndex + 6);

        String regex = "[0-9]+";

        if (!editIndex.matches(regex)) {
            throw new DukeException("OOPS!!! An integer must follow after an edit command");
        }

        int targetTask = Integer.parseInt(editIndex);

        Calendar editCal = Calendar.getInstance();
        Date d;
        try {
            d = DEADLINE_TIME_FORMAT.parse(info);
            editCal.setTime(d);
        } catch (ParseException e) {
            throw new DukeException("OOPS!!! The date is not formatted as dd/mm/yyyy 0000");
        }

        duke.executeEditTimeCommand(editCal, targetTask);
    }

    private void parseUnknownCommand(String command) throws DukeException {
        throw new DukeException("OOPS!!! I'm Sorry, but I don't know what '" + command + "' means");
    }

}

