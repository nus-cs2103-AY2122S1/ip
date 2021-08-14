import java.util.Scanner;

/**
 * Encapsulates a chatbot that greets the user,
 * echoes commands entered by the user,
 * and exits when the user types `bye`.
 */
public class Duke {
    public static void main(String[] args) {
        // Greet
        DukeGreetingMessage greetingMessage = new DukeGreetingMessage("Hello! I'm Duke, what shall we do today?");
        System.out.println(greetingMessage.getFormattedMessage());

        // Add or list
        String inputExitMessage = "bye";
        String inputListMessage = "list";

        Scanner inputScanner = new Scanner(System.in);
        String inputMessage = inputScanner.nextLine();

        while (!inputMessage.equals(inputExitMessage)) {
            DukeOutputMessage outputMessage;

            if (inputMessage.equals(inputListMessage)) {
                // Output the list
                outputMessage = new DukeListMessage(DukeList.getList());
            } else {
                // Add input to list
                DukeList.addItemToList(inputMessage);
                outputMessage = new DukeAddedMessage(inputMessage);
            }

            System.out.println(outputMessage.getFormattedMessage());
            inputMessage = inputScanner.nextLine();
        }

        // Exit
        DukeExitMessage exitMessage = new DukeExitMessage("Bye, see you again");
        System.out.println(exitMessage.getFormattedMessage());
    }
}
