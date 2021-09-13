package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.IllegalCommandException;
import duke.exception.IllegalTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Represents a Parser that parses in the input received by the UI
 * <ul>
 * <li>If the first word of the input is "list", a ListCommand is created.</li>
 * <li>If the first word of the input is "done", a DoneCommand consisting the index of the task done is created.</li>
 * <li>If the first word of the input is "delete", a DeleteCommand consisting the index to the task to be deleted is
 * created.</li>
 * <li>If the first word of the input is "todo", the Parser checks for a task afterwards. If there is a task, an
 * AddCommand consisting a ToDo task which contains the task is created. If there is no task, an
 * IllegalTaskException is thrown.</li>
 * <li>If the first word of the input is "deadline", an AddCommand consisting a Deadline task which contains the task
 * and deadline is created.</li>
 * <li>If the first word of the input is "event", a new AddCommand consisting an Event Task which contains the
 * event and event date is created.</li>
 * <li> If the first word of the input is "bye", a new ExitCommand is created.</li>
 * <li> If the first word of the input matches none of the cases above, an IllegalCommandException is thrown.</li>
 * </ul>
 *
 */
public class Parser {

    private static final String TIMEFORMAT = "HHmm";
    /**
     * Parses the input received by the UI.
     * Dates should be input in the format dd/mm/yyyy HHmm
     *
     * @param fullCommand String containing the full command input by the user.
     * @return Commands depending on the first word of the input.
     * @throws IllegalCommandException in the case no task is given after "todo".
     * @throws IllegalTaskException in the case an invalid task is given.
     */
    public static Command parse(String fullCommand) throws IllegalCommandException, IllegalTaskException {
        String command = fullCommand.split(" ")[0];
        switch (command) {
        case "list":
            return new ListCommand();
        case "done":
            return createDoneCommand(fullCommand);
        case "delete":
            return createDeleteCommand(fullCommand);
        case "todo": {
            return createToDo(fullCommand);
        }
        case "deadline": {
            return createDeadline(fullCommand);
        }
        case "event": {
            return createEvent(fullCommand);
        }
        case "find" : {
            return createFindCommand(fullCommand);
        }
        case "bye": {
            return new ExitCommand();
        }
        default: {
            throw new IllegalCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        }
    }

    private static DoneCommand createDoneCommand(String fullCommand) {
        int toComplete = getCommandIndex(fullCommand);
        return new DoneCommand(toComplete);
    }

    private static DeleteCommand createDeleteCommand(String fullCommand) {
        int toDelete = getCommandIndex(fullCommand);
        return new DeleteCommand(toDelete);
    }

    private static AddCommand createToDo(String fullCommand) throws IllegalTaskException {
        String task = fullCommand.replaceFirst("todo ", "");
        if (task.equals("todo")) {
            throw new IllegalTaskException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return new AddCommand(new ToDo(task));
    }

    private static AddCommand createDeadline(String fullCommand) {
        String[] taskDate = getTaskDate(fullCommand, "deadline ", "/by ");
        return parseCommand("Deadline", taskDate);
    }

    private static AddCommand createEvent(String fullCommand) {
        String[] taskDate = getTaskDate(fullCommand, "event ", "/at ");
        return parseCommand("Event", taskDate);
    }

    private static FindCommand createFindCommand(String fullCommand) {
        String keyword = fullCommand.replaceFirst("find ", "");
        return new FindCommand(keyword);
    }

    private static AddCommand parseCommand(String type, String[] taskDate) {
        String task = taskDate[0];
        String fullDateTime = taskDate[1];
        LocalDate localDate = getLocalDate(fullDateTime);
        LocalTime localTime = getLocalTime(fullDateTime);
        String frequency = getFrequency(fullDateTime);
        return createAddCommandWithDateTime(type, task, localDate, localTime, frequency);
    }

    private static AddCommand createAddCommandWithDateTime(String type, String task, LocalDate localDate,
                                                           LocalTime localTime, String frequency) {
        if (type.equals("Event")) {
            return new AddCommand(new Event(task, localDate, localTime, frequency));
        }
        return new AddCommand(new Deadline(task, localDate, localTime, frequency));
    }

    private static String[] getTaskDate(String fullCommand, String type, String splitter) {
        return fullCommand.replaceFirst(type, "").split(splitter);
    }

    private static LocalDate getLocalDate (String fullDateTime) {
        String formattedDate = formatDate(fullDateTime);
        return LocalDate.parse(formattedDate);
    }

    private static LocalTime getLocalTime (String fullDateTime) {
        String time = getTime(fullDateTime);
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(TIMEFORMAT));
    }

    private static String getTime(String fullDateTime) {
        return fullDateTime.split(" ")[1];
    }

    private static String formatDate(String fullDateTime) {
        String fullDate = fullDateTime.split(" ")[0];
        String[] splitDate = fullDate.split("/");
        return rewriteDate(splitDate);
    }

    private static String rewriteDate(String[] splitDate) {
        if (splitDate[1].length() == 1) {
            return splitDate[2] + "-0" + splitDate[1] + "-" + splitDate[0];
        } else if (splitDate[0].length() == 1) {
            return splitDate[2] + "-" + splitDate[1] + "-0" + splitDate[0];
        }
        return splitDate[2] + "-" + splitDate[1] + "-" + splitDate[0];
    }

    private static String getFrequency(String fullDateTime) {
        if (fullDateTime.split(" ").length == 2) {
            return "once";
        }
        return fullDateTime.split(" ")[2];
    }

    private static Integer getCommandIndex(String fullCommand) {
        return Integer.parseInt(fullCommand.split(" ")[1]) - 1;
    }
}