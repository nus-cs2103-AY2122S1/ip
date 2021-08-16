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

    public Listener() {
        sc = new Scanner(System.in);
        taskList = new TaskList();
    }

    public void startListen() {
        while(true) {
            // Receive Input text
            String input = sc.nextLine();
            System.out.println(Display.LINE);

            Matcher checkDone = donePattern.matcher(input);

            if (input.equals("gubbai")) {
                // Stop listening if "gubbai" is mentioned
                break;
            } else if (input.equals("list")) {
                // Display items
                taskList.displayList();
            } else if (checkDone.matches()) {
                System.out.println("COMPLETE INDEX " + checkDone.group(1));
            } else {
                // Add input to list
                taskList.add(input);
                System.out.println(Display.OUTPUT_DISPLAY + "added: " + input);
                taskList.printSize();
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
