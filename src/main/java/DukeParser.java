import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DukeParser {

    final TaskList taskList;
    Pattern donePattern = Pattern.compile("done (\\d+)", Pattern.CASE_INSENSITIVE);
    Pattern deletePattern = Pattern.compile("delete (\\d+)", Pattern.CASE_INSENSITIVE);
    Pattern todoPattern = Pattern.compile("todo (.+)", Pattern.CASE_INSENSITIVE);
    Pattern deadlinePattern = Pattern.compile("deadline (.+) /by (.+)", Pattern.CASE_INSENSITIVE);
    Pattern eventPattern = Pattern.compile("event (.+) /at (.+)", Pattern.CASE_INSENSITIVE);

    public DukeParser(TaskList tasks) {
        this.taskList = tasks;
    }

    public void parseInput(String input) {
        Matcher checkDone = donePattern.matcher(input);
        Matcher checkDelete = deletePattern.matcher(input);
        Matcher checkTodo = todoPattern.matcher(input);
        Matcher checkDeadline = deadlinePattern.matcher(input);
        Matcher checkEvent = eventPattern.matcher(input);

        if (input.equals("list")) {
            // Display items
            taskList.displayList();

        } else if (checkDone.matches()) {
            // Toggles completion of a task
            taskList.toggleDone(Integer.parseInt(checkDone.group(1)));

        } else if (checkDelete.matches()) {
            // Remove a task from list
            taskList.delete(Integer.parseInt(checkDelete.group(1)));

        } else if (checkTodo.matches()) {
            // Add a to-do task to list
            System.out.println(Ui.OUTPUT_DISPLAY + "Got it. I've added a To-do.");
            taskList.add(checkTodo, TaskType.TODO);

        } else if (checkDeadline.matches()) {
            // Add a deadline to list
            System.out.println(Ui.OUTPUT_DISPLAY + "Got it. I've added a Deadline.");
            taskList.add(checkDeadline, TaskType.DEADLINE);

        } else if (checkEvent.matches()) {
            // Add an event to list
            System.out.println(Ui.OUTPUT_DISPLAY + "Got it. I've added an Event. Don't miss it!");
            taskList.add(checkEvent, TaskType.EVENT);

        } else {
            // Invalid command
            System.out.println(Ui.OUTPUT_DISPLAY + "☹ eeeeeee~dameda!! " + input + " isn't a valid command!");
        }
    }
}
