package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.function.Predicate;

import duke.ui.Ui;
import duke.util.DukeParser;
import task.Task;
import task.TaskList;

/**
 * Command to list out tasks.
 */
public class CommandList extends Command {

    private final TaskList taskList;
    private final String unparsedFilters;

    /**
     * Constructor for this command.
     *
     * @param taskList Task list to list.
     * @param unparsedFilters Un-parsed list of filters.
     */
    public CommandList(TaskList taskList, String unparsedFilters) {
        this.commandName = "list /name <name> /date DD/MM/YYYY";
        this.description = "Toggles completion of task. Order of arguments does not matter";
        this.arguments = new String[]{
            "/name Optional argument to search for particular name",
            "/date Optional date argument in DAY/MONTH/YEAR, "
                    + "to search for tasks on a particular date"
        };

        this.taskList = taskList;
        this.unparsedFilters = unparsedFilters;
    }

    /**
     * Lists out tasks based on given filters.
     */
    @Override
    public String execute() {
        if (unparsedFilters == null) {
            // Display all if no filters
            ArrayList<Predicate<Task>> filter = new ArrayList<>();
            filter.add(task -> true);
            return taskList.displayList(filter);
        }

        // Extract modifiers and filter
        try {
            ArrayList<Predicate<Task>> filters = listStringToFilter(unparsedFilters);
            return taskList.displayList(filters);
        } catch (DateTimeParseException e) {
            return Ui.MESSAGE_INVALID_DATE;
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
    private ArrayList<Predicate<Task>> listStringToFilter(String stringToParse)
            throws DateTimeParseException, IllegalArgumentException {

        // Separate individual commands
        String[] args = stringToParse.split(" /");

        ArrayList<Predicate<Task>> results = new ArrayList<>();

        for (String str : args) {

            // Ignore any empty strings
            if (str.equals("")) {
                continue;
            }

            // Get command and argument information
            String command = getCommand(str);
            String arg = getArgument(str);

            switch (command) {
            case ("name"):
                results.add(task -> task.getDescription()
                        .toLowerCase().contains(arg.toLowerCase()));
                break;
            case ("date"):
                LocalDate date = DukeParser.getDate(arg);
                results.add(task -> task.isDate(date));
                break;
            default:
                throw new IllegalArgumentException(Ui.MESSAGE_INVALID_ARG);
            }
        }

        return results;
    }

    private String getCommand(String str)
            throws IllegalArgumentException{
        int index = getCommandArgumentSplitIndex(str);
        return str.substring(0, index);
    }

    private String getArgument(String str)
            throws IllegalArgumentException {

        // Ensure validity; has a command and an argument
        int index = getCommandArgumentSplitIndex(str);
        return str.substring(index).trim();
    }

    private int getCommandArgumentSplitIndex(String str)
            throws IllegalArgumentException {
        // Ensure validity; has a command and an argument
        int index = str.indexOf(' ');
        if (index == -1) {
            throw new IllegalArgumentException(Ui.MESSAGE_INVALID_ARG);
        }

        return index;
    }
}
