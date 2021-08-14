import java.util.Scanner;

/**
 * Encapsulates a chatbot that greets the user,
 * echoes commands entered by the user,
 * and exits when the user types `bye`.
 */
public class Duke {
    public static void main(String[] args) {
        // Greet
        DukeGreetingMessage greetingMessage = new DukeGreetingMessage("Hello! I'm Duke, what's up?");
        System.out.println(greetingMessage.getFormattedMessage());

        // Echo
        String inputExitMessage = "bye";
        Scanner inputScanner = new Scanner(System.in);
        String inputMessage = inputScanner.nextLine();

        while (!inputMessage.equals(inputExitMessage)) {
            DukeOutputMessage outputMessage = new DukeOutputMessage(inputMessage);
            System.out.println(outputMessage.getFormattedMessage());
            inputMessage = inputScanner.nextLine();
        }

        // Exit
        DukeExitMessage exitMessage = new DukeExitMessage("Bye, see you again");
        System.out.println(exitMessage.getFormattedMessage());
    }
}
