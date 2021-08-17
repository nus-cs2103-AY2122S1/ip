import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Waits for and processes a User Input
 */
public class DukeListener {

    final Scanner sc;
    final TaskList taskList;

    Pattern donePattern = Pattern.compile("done (\\d+)", Pattern.CASE_INSENSITIVE);
    Pattern todoPattern = Pattern.compile("todo (.+)", Pattern.CASE_INSENSITIVE);
    Pattern deadlinePattern = Pattern.compile("deadline (.+) /by (.+)", Pattern.CASE_INSENSITIVE);
    Pattern eventPattern = Pattern.compile("event (.+) /at (.+)", Pattern.CASE_INSENSITIVE);

    public DukeListener(TaskList taskList) {
        sc = new Scanner(System.in);
        this.taskList = taskList;

    }

    public void startListen() {
        while(true) {
            // Receive Input text
            String input = sc.nextLine();
            System.out.println(Display.LINE);

            Matcher checkDone = donePattern.matcher(input);
            Matcher checkTodo = todoPattern.matcher(input);
            Matcher checkDeadline = deadlinePattern.matcher(input);
            Matcher checkEvent = eventPattern.matcher(input);

            if (input.equals("gubbai")) {
                // Stop listening if "gubbai" is mentioned
                break;
            } else if (input.equals("list")) {
                // Display items
                taskList.displayList();

            } else if (checkDone.matches()) {
                // Toggles completion of a task
                taskList.toggleDone(Integer.parseInt(checkDone.group(1)));

            } else if (checkTodo.matches()) {
                // Add a to-do task to list
                System.out.println(Display.OUTPUT_DISPLAY + "Got it. I've added a To-do.");
                taskList.add(checkTodo, TaskType.TODO);

            } else if (checkDeadline.matches()) {
                // Add a deadline to list
                System.out.println(Display.OUTPUT_DISPLAY + "Got it. I've added a Deadline.");
                taskList.add(checkDeadline, TaskType.DEADLINE);

            } else if (checkEvent.matches()) {
                // Add an event to list
                System.out.println(Display.OUTPUT_DISPLAY + "Got it. I've added an Event. Don't miss it!");
                taskList.add(checkEvent, TaskType.EVENT);

            } else {
                System.out.println(Display.OUTPUT_DISPLAY + "☹ eeeeeee~dameda!! " + input + " isn't a valid command!");
            }

            System.out.println(Display.LINE);
        }

        // Quit the program after listening stops
        System.out.println(
                Display.OUTPUT_DISPLAY + "kimi no unmei no hito wa boku jyanai\n"
                + Display.LINE
        );
    }


}
