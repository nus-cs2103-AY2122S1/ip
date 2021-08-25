package duke;

import task.Task;
import task.TaskList;
import task.TaskType;

import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses any command that requires inputs
 */
public class DukeParser {

    final private TaskList taskList;

    /**
     * Patterns for the Parser to look out for in the input
     */
    final private Pattern listPattern = Pattern.compile("list( .+)?", Pattern.CASE_INSENSITIVE);
    final private Pattern donePattern = Pattern.compile("done (\\d+)", Pattern.CASE_INSENSITIVE);
    final private Pattern deletePattern = Pattern.compile("delete (\\d+)", Pattern.CASE_INSENSITIVE);
    final private Pattern todoPattern = Pattern.compile("todo (.+)", Pattern.CASE_INSENSITIVE);
    final private Pattern deadlinePattern = Pattern.compile(
            "deadline (.+) /by (\\d{1,2}/\\d{1,2}/\\d{4}+)( \\d{4}+)?",
            Pattern.CASE_INSENSITIVE);
    final private Pattern eventPattern = Pattern.compile(
            "event (.+) /at (\\d{1,2}/\\d{1,2}/\\d{4}+)( \\d{4}+)?",
            Pattern.CASE_INSENSITIVE);

    /**
     * Constructor; instantiates the task list to be edited
     *
     * @param tasks Task list to edit using this Parser object
     */
    public DukeParser(TaskList tasks) {
        this.taskList = tasks;
    }

    /**
     * Matches input with any regex, and passes it to the correct function
     *
     * @param input String input from the Listener given by the User
     */
    public void parseInput(String input) {
        final Matcher checkList = listPattern.matcher(input);
        final Matcher checkDone = donePattern.matcher(input);
        final Matcher checkDelete = deletePattern.matcher(input);
        final Matcher checkTodo = todoPattern.matcher(input);
        final Matcher checkDeadline = deadlinePattern.matcher(input);
        final Matcher checkEvent = eventPattern.matcher(input);

        if (checkList.matches()) {
            // List tasks
            if (checkList.group(1) != null) {
                // Extract modifiers and filter
                try {
                    ArrayList<Predicate<Task>> filters = TaskType.listStringToFilter(checkList.group(1));
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

        } else if (checkDone.matches()) {
            // Toggles completion of a task
            taskList.toggleDone(Integer.parseInt(checkDone.group(1)));

        } else if (checkDelete.matches()) {
            // Remove a task from list
            taskList.delete(Integer.parseInt(checkDelete.group(1)));

        } else if (checkTodo.matches()) {
            // Add a to-do task to list
            taskList.add(checkTodo, TaskType.TODO);

        } else if (checkDeadline.matches()) {
            // Add a deadline to list
            taskList.add(checkDeadline, TaskType.DEADLINE);

        } else if (checkEvent.matches()) {
            // Add an event to list
            taskList.add(checkEvent, TaskType.EVENT);

        }  else {
            // Invalid command
            System.out.println(Ui.OUTPUT_DISPLAY + "☹ eeeeeee~dameda!! " + input + " isn't a valid command!");
        }
    }


}
