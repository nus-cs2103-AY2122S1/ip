package duke;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private Ui ui;

    /**
     * Parses inputs provided by ui, and directs ui to print corresponding output.
     *
     * @param ui ui used to interact with users.
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Executes action associated with provided string input, and prints out relevant details to console.
     *
     * @param input command associated with an action
     * @param taskList storage used to carry out actions
     * @return whether Duke has completed executing
     */
    public boolean parse(String input, TaskList taskList) {
        Pattern todoPattern = Pattern.compile("todo (.*)");
        Pattern deadlinePattern = Pattern.compile("deadline (.*) /by (.*)");
        Pattern eventPattern = Pattern.compile("event (.*) /at (.*)");
        Pattern findPattern = Pattern.compile("find (.*)");

        // Print out list
        if (input.equals("list")) {
            taskList.list();
            return false;
        }

        // Find related tasks
        Matcher findMatcher = findPattern.matcher(input);
        if (findMatcher.find()) {
            ArrayList<Task> tasks = taskList.find(findMatcher.group(1));
            this.ui.printFind(tasks);
            return false;
        }

        // Complete a task
        if (Pattern.matches("done \\d", input)) {
            String[] items = input.split(" ");
            taskList.done(Integer.parseInt(items[1]));
            return false;
        }

        // Delete a task
        if (Pattern.matches("delete \\d", input)) {
            String[] items = input.split(" ");
            taskList.delete(Integer.parseInt(items[1]));
            return false;
        }

        // Add a Todo type task
        Matcher todoMatcher = todoPattern.matcher(input);
        if (todoMatcher.find()) {
            String response = taskList.addCustom(new Todo(todoMatcher.group(1)));
            ui.printResponse(response);
            return false;
        }

        // Add a Deadline type task
        Matcher deadlineMatcher = deadlinePattern.matcher(input);
        if (deadlineMatcher.find()) {
            String response = taskList.addCustom(new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2)));
            ui.printResponse(response);
            return false;
        }

        // Add an Event type task
        Matcher eventMatcher = eventPattern.matcher(input);
        if (eventMatcher.find()) {
            String response = taskList.addCustom(new Event(eventMatcher.group(1), eventMatcher.group(2)));
            ui.printResponse(response);
            return false;
        }

        // Exit application
        if (input.equals("bye")) {
            ui.terminate();
            return true;
        }

        // Identify reason for mis-input
        if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
            ui.printResponse("OOPS!!! The description of a todo cannot be empty.");
            return false;
        }
        ui.fail();
        return false;
    }
}
