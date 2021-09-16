package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

import duke.ui.Ui;
import task.Task;
import task.TaskList;

/**
 * Command to sort the tasklist
 */
public class CommandSort extends Command {

    private final TaskList taskList;
    private final String sortFilters;

    /**
     * Constructor for this command.
     *
     * @param taskList Task list to list.
     * @param sortFilters Un-parsed list of filters.
     */
    public CommandSort(TaskList taskList, String sortFilters) {
        this.commandName = "list /name <name> /date DD/MM/YYYY";
        this.description = "Toggles completion of task. Order of arguments does not matter";
        this.arguments = new String[]{
                "/name Optional argument to search for particular name",
                "/date Optional date argument in DAY/MONTH/YEAR, "
                        + "to search for tasks on a particular date"
        };

        this.taskList = taskList;
        this.sortFilters = sortFilters;
    }

    /**
     * Lists out tasks based on given filters.
     * TODO
     */
    @Override
    public String execute() {
        try {
            taskList.sort(sortStringToFilter(sortFilters));
            return new CommandList(taskList, null).execute();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets arguments from a multi-argument string.
     *
     * @param stringToParse String to parse.
     * @return Arraylist with all filters to use to display list.
     * @throws DateTimeParseException Thrown if error in parsing dates.
     * @throws IllegalArgumentException Thrown if an argument is in a wrong format.
     */
    private ArrayList<Comparator<Task>> sortStringToFilter(String stringToParse)
            throws DateTimeParseException, IllegalArgumentException {

        if (stringToParse == null) {
            ArrayList<Comparator<Task>> results = new ArrayList<>();
            results.add(createNameComparator());
            return results;
        }

        // Separate individual commands
        String[] args = stringToParse.split(" /");

        ArrayList<Comparator<Task>> results = new ArrayList<>();

        for (String str : args) {

            // Ignore any empty strings
            if (str.equals("")) {
                continue;
            }

            switch (str) {
            case ("name"):
                results.add(createNameComparator());
                break;
            case ("date"):
                results.add(createDateTimeComparator());
                break;
            default:
                throw new IllegalArgumentException(Ui.MESSAGE_INVALID_ARG);
            }
        }

        return results;
    }

    private Comparator<Task> createNameComparator() {
        return Comparator.comparing(Task::getDescription);
    }

    private Comparator<Task> createDateTimeComparator() {
        return (o1, o2) -> {
            int compareDate = o1.getDate().compareTo(o2.getDate());
            return compareDate == 0
                    ? o1.getTime().compareTo(o2.getTime())
                    : o1.getDate().compareTo(o2.getDate());
        };
    }
}