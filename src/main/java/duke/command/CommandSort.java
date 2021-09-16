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
        this.commandName = "sort /name /date";
        this.description = "Sorts list based on arguments provided. Having no arguments would default to" +
                " sorting by /name. If there is more than 1 argument, the list will be sorted by the first." +
                "followed by the second, etc...";
        this.arguments = new String[]{
                "/name Optional argument to sort by name",
                "/date Optional argument to sort by date",
                "/done Optional argument to sort by completion status"
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
            case ("done"):
                results.add(createDoneComparator());
                break;
            case ("added"):
                results.add(createAddedComparator());
                break;
            default:
                throw new IllegalArgumentException(Ui.MESSAGE_INVALID_ARG);
            }
        }

        return results;
    }

    private Comparator<Task> createNameComparator() {
        return Comparator.comparing(t -> t.getDescription().toLowerCase());
    }

    private Comparator<Task> createDateTimeComparator() {
        return (t1, t2) -> {
            int compareDate = t1.getDate().compareTo(t2.getDate());
            return compareDate == 0
                    ? t1.getTime().compareTo(t2.getTime())
                    : t1.getDate().compareTo(t2.getDate());
        };
    }

    private Comparator<Task> createDoneComparator() {
        return Comparator.comparing(Task::getDone);
    }

    private Comparator<Task> createAddedComparator() {
        return Comparator.comparing(Task::getAdded);
    }
}