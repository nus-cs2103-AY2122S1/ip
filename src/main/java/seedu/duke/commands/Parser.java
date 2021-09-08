package seedu.duke.commands;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Events;
import seedu.duke.tasks.PeriodTask;
import seedu.duke.tasks.TimedTask;
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

        CommandType commandType = CommandType.getCommandType(action);

        switch (commandType) {
        case BYE:
            return new ExitCommand();

        case LIST:
            return new ListCommand();

        case DONE:
            return new DoneCommand(getDescriptions(actionDescription));

        case TODO:
            descriptions = getDescriptions(actionDescription);
            if (isBlank(descriptions)) {
                return new UiCommand(Ui.ERROR_MSG_EMPTY_DESCRIPTION);
            }
            ToDos todos = new ToDos(descriptions);
            return new AddCommand(todos);

        case DEADLINE:
            descriptions = getDescriptions(actionDescription);
            if (isBlank(descriptions)) {
                return new UiCommand(Ui.ERROR_MSG_EMPTY_DESCRIPTION);
            }
            Deadline deadline = new Deadline(getOnlyDescription(descriptions), getDeadlineDateTime(descriptions));
            return new AddCommand(deadline);

        case EVENT:
            descriptions = getDescriptions(actionDescription);
            if (isBlank(descriptions)) {
                return new UiCommand(Ui.ERROR_MSG_EMPTY_DESCRIPTION);
            }
            Events event = new Events(getOnlyDescription(descriptions), getEventDateTime(descriptions));
            return new AddCommand(event);

        case TIMEDTASK:
            descriptions = getDescriptions(actionDescription);
            if (isBlank(descriptions)) {
                return new UiCommand(Ui.ERROR_MSG_EMPTY_DESCRIPTION);
            }
            TimedTask timedTask = new TimedTask(getOnlyDescription(descriptions), getTimeNeeded(descriptions));
            return new AddCommand(timedTask);

        case PERIODTASK:
            descriptions = getDescriptions(actionDescription);
            if (isBlank(descriptions)) {
                return new UiCommand(Ui.ERROR_MSG_EMPTY_DESCRIPTION);
            }
            PeriodTask periodTask = new PeriodTask(getOnlyDescription(descriptions), periodTaskGetFrom(descriptions),
            periodTaskGetTo(descriptions));
            return new AddCommand(periodTask);

        case DELETE:
            return new DeleteCommand(getDescriptions(actionDescription));

        case FIND:
            return new FindCommand(getDescriptions(actionDescription));

        case EMPTY:
            return new EmptyCommand();

        default:
            return new UiCommand(Ui.ERROR_MSG_UNKOWN_MSG);
        }

    }

    private String dateTimeFormatter(String unformattedDate) {
        String stringDate = unformattedDate.split(" ")[0];
        String time = unformattedDate.split(" ")[1];

        LocalDate date = LocalDate.of(Integer.parseInt(stringDate.split("/")[2]),
                Integer.parseInt(stringDate.split("/")[1]), Integer.parseInt(stringDate.split("/")[0]));

        LocalDateTime dateTime = date.atTime(Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(2, 4)));

        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    }

    private String getDescriptions(String[] actionDescription) {
        return actionDescription[1];
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

    private boolean isBlank(String descriptions) {
        return descriptions.equals("");
    }

    private String getTimeNeeded(String descriptions) {
        return descriptions.split(" /needs ")[1];
    }

    private String periodTaskGetFrom(String descriptions) {
        String period = descriptions.split(" /between ")[1];
        System.out.println(period);
        return period.split(" and ")[0];
    }
    
    private String periodTaskGetTo(String descriptions) {
        String period = descriptions.split(" /between ")[1];
        System.out.println(period);
        return period.split(" and ")[1];
    }
}
