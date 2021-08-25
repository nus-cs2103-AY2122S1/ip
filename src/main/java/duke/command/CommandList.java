package duke.command;

import task.Task;
import task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class CommandList extends Command {

    private final TaskList taskList;
    private final String args;

    public CommandList(TaskList taskList, String args) {
        this.commandName = "done <index>";
        this.description = "Toggles completion of task";

        this.taskList = taskList;
        this.args = args;
    }

    @Override
    public void execute() {
        // List tasks
        if (args != null) {
            // Extract modifiers and filter
            try {
                ArrayList<Predicate<Task>> filters = listStringToFilter(args);
                taskList.displayList(filters);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date! :(");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            // Display items
            ArrayList<Predicate<Task>> filter = new ArrayList<>();
            filter.add(task -> true);
            taskList.displayList(filter);
        }
    }

    /**
     * Get arguments from a multi-argument string
     *
     * @param stringToParse String to parse
     * @return Arraylist with all filters to use to display list
     * @throws DateTimeParseException Thrown if error in parsing dates
     * @throws IllegalArgumentException Thrown if an argument is in a wrong format
     */
    public static ArrayList<Predicate<Task>> listStringToFilter(String stringToParse)
            throws DateTimeParseException, IllegalArgumentException {

        // Separate individual commands
        String[] args = stringToParse.split(" /");

        ArrayList<Predicate<Task>> results = new ArrayList<>();

        for (String str : args) {

            // Ignore any empty strings
            if (str.equals("")) {
                continue;
            }

            // Ensure validity; has a command and an argument
            int index = str.indexOf(' ');
            if (index == -1) {
                throw new IllegalArgumentException("Invalid argument found!");
            }

            // Get command and argument information
            String command = str.substring(0, index);
            String arg = str.substring(index).trim();

            switch (command) {
                case ("name"):
                    results.add(task -> task.getDescription().contains(arg));
                    break;
                case ("date"):
                    LocalDate date = getDate(arg);
                    results.add(task -> task.isDate(date));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid argument found! :(");
            }
        }

        return results;
    }

    /**
     * Converts a string to a date
     *
     * @param date String to convert
     * @return LocalDate object
     * @throws DateTimeParseException Thrown if error in parsing date
     */
    private static LocalDate getDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
