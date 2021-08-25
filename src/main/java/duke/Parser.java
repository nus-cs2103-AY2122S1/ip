package duke;

import duke.command.*;

import java.time.LocalDate;

public class Parser {

    public static Command parse(String commandMessage) throws DukeException {
        String[] message = commandMessage.split(" ", 2);
        String command = message[0];
        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "done": {
                int taskNumber = getTaskNumber(message[1]);
                return new EditCommand(EditCommand.EditType.DONE, taskNumber);
            }
            case "todo":
                return new AddCommand(AddCommand.TaskType.TODO, new String[] {checkToDoDescription(message[1])});
            case "deadline": {
                String description = checkDeadlineDescription(message[1]);
                String[] parameters = description.split(" /by ");
                return new AddCommand(AddCommand.TaskType.DEADLINE, parameters);
            }
            case "event": {
                String description = checkEventDescription(message[1]);
                String[] parameters = description.split(" /at ");
                return new AddCommand(AddCommand.TaskType.EVENT, parameters);
            }
            case "delete": {
                int taskNumber = getTaskNumber(message[1]);
                return new EditCommand(EditCommand.EditType.DELETE, taskNumber);
            }
            case "on": {
                String dateString = message[1]; // need to check string
                LocalDate date = LocalDate.parse(dateString.trim());
                return new OnCommand(date);
            }
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
        }
    }

    public static String checkDeadlineDescription(String des) throws DukeException {
        String description = des.trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty!\n");
        } else if (!description.contains(" /by ")) {
            throw new DukeException("The new deadline is missing a date!\n");
        } else {
            return description;
        }
    }

    public static String checkEventDescription(String des) throws DukeException {
        String description = des.trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty!\n");
        } else if (!description.contains(" /at ")) {
            throw new DukeException("The new event is missing a date!\n");
        } else {
            return description;
        }
    }

    public static String checkToDoDescription(String des) throws DukeException {
        String description = des.trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo task cannot be empty!\n");
        } else {
            return description;
        }
    }

    public static int getTaskNumber(String des) throws DukeException {
        String description = des.trim();
        if (description.isEmpty()) {
            throw new DukeException("I do not know which task to change!\n");
        } else {
            int taskNumber = Integer.parseInt(description);
            return taskNumber;
        }
    }
}
