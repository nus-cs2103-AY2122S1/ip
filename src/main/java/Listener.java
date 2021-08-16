import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Waits for and processes a User Input
 */
public class Listener {

    final Scanner sc;
    final TaskList taskList;

    Pattern donePattern = Pattern.compile("done (\\d+)");
    Pattern todoPattern = Pattern.compile("todo (.+)");
    Pattern deadlinePattern = Pattern.compile("deadline (.+) /by (.+)");
//    Pattern eventPattern = Pattern.compile("event (.+) /at (.+)");

    public Listener(TaskList taskList) {
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
                System.out.println(taskList.printSize());

            } else if (checkDeadline.matches()) {
                // Add a deadline to list
                System.out.println(Display.OUTPUT_DISPLAY + "Got it. I've added a Deadline.");
                taskList.add(checkDeadline, TaskType.DEADLINE);
                System.out.println(taskList.printSize());
            } else {
                System.out.println(Display.OUTPUT_DISPLAY + "Nothing happened haha bruh");
            }

            System.out.println(Display.LINE);
        }

        // Quit the program after listening stops
        System.out.println(
                Display.OUTPUT_DISPLAY + "君の運命のヒトは僕じゃない\n"
                + Display.LINE
        );
    }


}
