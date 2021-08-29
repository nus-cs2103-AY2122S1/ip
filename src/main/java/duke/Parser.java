package duke;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private Ui ui;

    /**
     * Parses inputs provided by ui, and directs ui to print corresponding output.
     * @param ui ui used to interact with users.
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Executes action associated with provided string input, and prints out relevant details to console.
     * @param input command associated with an action
     * @param taskList storage used to carry out actions
     * @return whether Duke has completed executing
     */
    public boolean parse(String input, TaskList taskList) {
        Pattern todoPattern = Pattern.compile("todo (.*)");
        Pattern deadlinePattern = Pattern.compile("deadline (.*) /by (.*)");
        Pattern eventPattern = Pattern.compile("event (.*) /at (.*)");
        Pattern findPattern = Pattern.compile("find (.*)");

        // print out list
        if (input.equals("list")) {
            taskList.list();
            return false;
        }

        // finds related tasks
        Matcher findMatcher = findPattern.matcher(input);
        if (findMatcher.find()) {
            ArrayList<Task> tasks = taskList.find(findMatcher.group(1));
            this.ui.printFind(tasks);
            return false;
        }

        // complete a task
        if (Pattern.matches("done \\d", input)) {
            String[] items = input.split(" ");
            taskList.done(Integer.parseInt(items[1]));
            return false;
        }

        if (Pattern.matches("delete \\d", input)) {
            String[] items = input.split(" ");
            taskList.delete(Integer.parseInt(items[1]));
            return false;
        }

        Matcher todoMatcher = todoPattern.matcher(input);
        if (todoMatcher.find()) {
            taskList.addCustom(new Todo(todoMatcher.group(1)));
            return false;
        }

        Matcher deadlineMatcher = deadlinePattern.matcher(input);
        if (deadlineMatcher.find()) {
            taskList.addCustom(new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2)));
            return false;
        }

        Matcher eventMatcher = eventPattern.matcher(input);
        if (eventMatcher.find()) {
            taskList.addCustom(new Event(eventMatcher.group(1), eventMatcher.group(2)));
            return false;
        }

        // exit application
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        }

        // identify reason for misinput
        if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
            return false;
        }
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        return false;
    }
}
