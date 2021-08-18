import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> tasks;

    public Duke() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Formats an output by adding a top and bottom border and displays it to the terminal
     * @param output The terminal output to format
     */
    private static void display(String output) {
        String horizontalLine = "\t ____________________________________________________________ \n";
        System.out.println(horizontalLine + "\t " + output + "\n" + horizontalLine);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        // Greet the user
        String introduction = "Hello! I'm Horacio, your personal assistant. \n" +
                "\t What can I do for you? \n";
        display(introduction);

        // Read and handle inputs from the user
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                // Exit when the user types bye
                display("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                // Display list of tasks when the user types list
                String output = "";
                for (int i = 0; i < duke.tasks.size(); i++) {
                    output += String.valueOf(i + 1) + ". " + duke.tasks.get(i) + "\n\t ";
                }
                display(output);
            } else if (input.substring(0, 4).equals("done")) {
                // Mark task as done when the user types "done x" where x is an integer
                int index = Integer.parseInt(input.substring(5, 6));
                Task t = duke.tasks.get(index - 1);
                t.markAsDone();
                display("Nice! I've marked this task as done: \n\t\t " + t);
            } else {
                // Otherwise, store task into list
                Task t = new Task(input);
                duke.tasks.add(t);
                display("added: " + input);
            }
        }
        scanner.close();
    }
}
