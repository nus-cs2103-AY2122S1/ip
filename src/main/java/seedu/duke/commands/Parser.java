package seedu.duke.commands;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Events;
import seedu.duke.tasks.ToDos;

public class Parser {
    /**
     * Triggers and run the desired command which the user entered. Interpreting
     * what the user wants.
     * 
     * @param fullCommand is the command line which the user have entered.
     * @return a Command which can be run to trigger it.
     */
    public Command parseCommands(String fullCommand) {
        String descriptions;
        String[] actionDescription = fullCommand.split(" ", 2);
        String action = actionDescription[0];

        switch (action) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "done":
            return new DoneCommand(actionDescription[1]);

        case "todo":
            descriptions = actionDescription[1];
            if (descriptions.equals("")) {
                return new UiCommand(Ui.ERROR_MSG_EMPTY_DESCRIPTION);
            }

            ToDos todos = new ToDos(descriptions);
            return new AddCommand(todos);

        case "deadline":
            descriptions = actionDescription[1];
            if (descriptions.equals("")) {
                return new UiCommand(Ui.ERROR_MSG_EMPTY_DESCRIPTION);
            }

            Deadline deadline = new Deadline(getOnlyDescription(descriptions), getDeadlineDateTime(descriptions));
            return new AddCommand(deadline);

        case "event":
            descriptions = actionDescription[1];
            if (descriptions.equals("")) {
                return new UiCommand(Ui.ERROR_MSG_EMPTY_DESCRIPTION);
            }

            Events event = new Events(getOnlyDescription(descriptions), getEventDateTime(descriptions));
            return new AddCommand(event);

        case "delete":
            return new DeleteCommand(actionDescription[1]);

        case "find":
            return new FindCommand(actionDescription[1]);

        case "":
            return new EmptyCommand();

        default:
            return new UiCommand(Ui.ERROR_MSG_UNKOWN_MSG);
        }
    }

    private String dateTimeFormatter(String unformattedDate) {
        // 2/12/2019 1800
        String stringDate = unformattedDate.split(" ")[0];
        String time = unformattedDate.split(" ")[1];

        LocalDate date = LocalDate.of(Integer.parseInt(stringDate.split("/")[2]),
                Integer.parseInt(stringDate.split("/")[1]), Integer.parseInt(stringDate.split("/")[0]));

        LocalDateTime dateTime = date.atTime(Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(2, 4)));

        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    }

    private String getOnlyDescription(String descriptions) {
        return descriptions.split(" /")[0];
    }

    private String getDeadlineDateTime(String descriptions) {
        String dateTime = descriptions.split(" /by ")[1];
        dateTime = dateTimeFormatter(dateTime);
        return dateTime;
    }

    private String getEventDateTime(String descriptions) {
        return descriptions.split(" /at ")[1];
    }
}
