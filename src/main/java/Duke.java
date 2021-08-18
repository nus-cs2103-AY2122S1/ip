import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        displayWelcomeMessage();
        String input = br.readLine();
        while(!input.equals("bye")) {
            displayCommand(input);
            input = br.readLine();
        }
        displayByeMessage();
    }

    static void printLines() {
        System.out.println("------------------------------------------------------------------");
    }

    static void displayWelcomeMessage() {
        printLines();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printLines();
    }

    static void displayByeMessage() {
        printLines();
        System.out.println("Bye. Hope to see you again soon!");
        printLines();
    }

    static void displayCommand(String command) {
        printLines();
        System.out.println(command);
        printLines();
    }
}
