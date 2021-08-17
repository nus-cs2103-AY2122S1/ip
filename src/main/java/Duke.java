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
        ArrayList<Task> savedInputs = new ArrayList<>(100);

        while (true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else if (input.equals("list")) {
                StringBuilder outputList = new StringBuilder();
                outputList.append("Here are the tasks in your list:\n");
                for (int i = 1; i <= savedInputs.size(); i++) {
                    outputList.append(i + "." + savedInputs.get(i-1).toString() + "\n");
                }
                System.out.println(outputList.toString());
            } else if (input.startsWith("done")) {
                int pos = Integer.valueOf(input.split(" ")[1]);
                if (pos > 0 && pos <= savedInputs.size()) {
                    savedInputs.get(pos - 1).markAsDone();
                } else {
                    System.out.println("Invalid task number to mark as done");
                }
            } else {
                System.out.println(input);
                savedInputs.add(new Task(input));
            }
        }
    }
}
