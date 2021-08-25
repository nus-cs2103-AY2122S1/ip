import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static boolean parse(String input, TaskList storage) {
        Pattern todoPattern = Pattern.compile("todo (.*)");
        Pattern deadlinePattern = Pattern.compile("deadline (.*) /by (.*)");
        Pattern eventPattern = Pattern.compile("event (.*) /at (.*)");

        // print out list
        if (input.equals("list")) {
            storage.list();
            return false;
        }

        // complete a task
        if (Pattern.matches("done \\d", input)) {
            String[] items = input.split(" ");
            storage.done(Integer.parseInt(items[1]));
            return false;
        }

        if (Pattern.matches("delete \\d", input)) {
            String[] items = input.split(" ");
            storage.delete(Integer.parseInt(items[1]));
            return false;
        }

        Matcher todoMatcher = todoPattern.matcher(input);
        if (todoMatcher.find()) {
            storage.addCustom(new Todo(todoMatcher.group(1)));
            return false;
        }

        Matcher deadlineMatcher = deadlinePattern.matcher(input);
        if (deadlineMatcher.find()) {
            storage.addCustom(new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2)));
            return false;
        }

        Matcher eventMatcher = eventPattern.matcher(input);
        if (eventMatcher.find()) {
            storage.addCustom(new Event(eventMatcher.group(1), eventMatcher.group(2)));
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
