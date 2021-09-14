package duke.common;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.common.task.Deadline;
import duke.common.task.Event;
import duke.common.task.Task;
import duke.common.task.Todo;

/**
 * Parses inputs provided by ui, and directs ui to print corresponding output.
 */
public class Parser {
    /**
     * Checks whether provided string input is associated with a valid Duke action,
     * then if so executes action associated with provided string input and returns a string response for UI to pass
     * back to the user.
     *
     * @param input    command associated with an action
     * @param taskList storage used to carry out actions
     * @return whether Duke has completed executing
     */
    public static String parse(String input, TaskList taskList, Ui ui) {
        Pattern todoPattern = Pattern.compile("todo (.*)");
        Pattern deadlinePattern = Pattern.compile("deadline (.*) /by (.*)");
        Pattern eventPattern = Pattern.compile("event (.*) /at (.*)");
        Pattern findPattern = Pattern.compile("find (.*)");

        Pattern updatePattern = Pattern.compile("update (/.*) (.*)");

        // Print out list
        if (input.equals("list")) {
            return ui.printResponse(taskList.list());
        }

        // Find related tasks
        Matcher findMatcher = findPattern.matcher(input);
        if (findMatcher.find()) {
            ArrayList<Task> tasks = taskList.find(findMatcher.group(1));
            return ui.printFind(tasks);
        }

        // Complete a task
        if (Pattern.matches("done \\d", input)) {
            String[] items = input.split(" ");
            return ui.printResponse(taskList.done(Integer.parseInt(items[1])));
        }

        // Delete a task
        if (Pattern.matches("delete \\d", input)) {
            String[] items = input.split(" ");
            return ui.printResponse(taskList.delete(Integer.parseInt(items[1])));
        }

        // Add a Todo type task
        Matcher todoMatcher = todoPattern.matcher(input);
        if (todoMatcher.find()) {
            String response = taskList.addCustom(new Todo(todoMatcher.group(1)));
            return ui.printResponse(response);
        }

        // Add a Deadline type task
        Matcher deadlineMatcher = deadlinePattern.matcher(input);
        if (deadlineMatcher.find()) {
            String response = taskList.addCustom(new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2)));
            return ui.printResponse(response);
        }

        // Add an Event type task
        Matcher eventMatcher = eventPattern.matcher(input);
        if (eventMatcher.find()) {
            String response = taskList.addCustom(new Event(eventMatcher.group(1), eventMatcher.group(2)));
            return ui.printResponse(response);
        }

        // Exit application
        if (input.equals("bye")) {
            return ui.terminate();
        }

        // Identify reason for mis-input
        if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
            return ui.printResponse("OOPS!!! The description of a todo cannot be empty.");
        }
        return ui.fail();
    }
}
