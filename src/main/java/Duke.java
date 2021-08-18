import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> listOfItems = new ArrayList<>();

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
            } else if (input.equals("list")) {
                // Display list of items when the user types list
                String output = "";
                for (int i = 0; i < listOfItems.size(); i++) {
                    output += String.valueOf(i + 1) + ". " + listOfItems.get(i) + "\n\t ";
                }
                display(output);
            } else {
                // Otherwise, store input text into list
                listOfItems.add(input);
                display("added: " + input);
            }
        }
        scanner.close();
    }
}
