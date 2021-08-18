import java.util.Scanner;

public class Duke {

    /**
     * Formats an output by adding a top and bottom border and displays it to the terminal
     * @param output The terminal output to format
     */
    private static void display(String output) {
        String horizontalLine = "\t ____________________________________________________________ \n";
        System.out.println(horizontalLine + "\t " + output + "\n" + horizontalLine);
    }

    public static void main(String[] args) {
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
            } else {
                // Otherwise, echo commands entered by the user
                System.out.println(input);
            }
        }
        scanner.close();
    }
}
