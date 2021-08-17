import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        chat();
    }

    /**
     * Prints (to screen) Duke's response to the user input, entered from the Command Line.
     */
    private static void chat() {
        String input;
        ArrayList<String> savedInputs = new ArrayList<>(100);

        while (true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else if (input.equals("list")) {
                StringBuilder outputList = new StringBuilder();
                for (int i = 1; i <= savedInputs.size(); i++) {
                    outputList.append(i + ". " + savedInputs.get(i-1) + "\n");
                }
                System.out.println(outputList.toString());
            } else {
                System.out.println(input);
                savedInputs.add(input);
            }
        }
    }
}
