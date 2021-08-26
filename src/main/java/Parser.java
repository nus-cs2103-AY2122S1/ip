package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.IllegalCommandException;
import duke.exception.IllegalTaskException;

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

    /**
     * Parses the input received by the UI.
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
            int toComplete = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new DoneCommand(toComplete);
        case "delete":
            int toDelete = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new DeleteCommand(toDelete);
        case "todo": {
            String task = fullCommand.replaceFirst("todo ", "");
            if (task.equals("todo")) {
                throw new IllegalTaskException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                return new AddCommand(new ToDo(task));
            }
        }
        case "deadline": {
            String[] taskDate = fullCommand.replaceFirst("deadline ", "").split("/by ");
            String task = taskDate[0];
            String date = taskDate[1];
            String[] splitDateTime = date.split(" ");
            String[] splitDate = splitDateTime[0].split("/");
            LocalDate localDate;
            if (splitDate[1].length() == 1) {
                localDate = LocalDate.parse(splitDate[2] + "-0" + splitDate[1] + "-" + splitDate[0]);
            } else if (splitDate[0].length() == 1) {
                localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-0" + splitDate[0]);
            } else {
                localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-" + splitDate[0]);
            }
            LocalTime localTime;
            localTime = LocalTime.parse(splitDateTime[1], DateTimeFormatter.ofPattern("HHmm"));
            return new AddCommand(new Deadline(task, localDate, localTime));
        }
        case "event": {
            String[] taskDate = fullCommand.replaceFirst("event ", "").split("/at ");
            String task = taskDate[0];
            String date = taskDate[1];
            String[] splitDateTime = date.split(" ");
            String[] splitDate = splitDateTime[0].split("/");
            LocalDate localDate;
            if (splitDate[1].length() == 1) {
                localDate = LocalDate.parse(splitDate[2] + "-0" + splitDate[1] + "-" + splitDate[0]);
            } else if (splitDate[0].length() == 1) {
                localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-0" + splitDate[0]);
            } else {
                localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-" + splitDate[0]);
            }
            LocalTime localTime;
            localTime = LocalTime.parse(splitDateTime[1], DateTimeFormatter.ofPattern("HHmm"));
            return new AddCommand(new Event(task, localDate, localTime));
        }
        case "bye":
            return new ExitCommand();
        default:
            throw new IllegalCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}