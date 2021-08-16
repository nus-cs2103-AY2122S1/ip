import java.util.Scanner;

/**
 * Encapsulates a chatbot that greets the user,
 * adds valid inputs to a task list,
 * updates tasks in the list,
 * and exits when the user types `bye`.
 */
public class Duke {
    public static void main(String[] args) {
        // Greet
        DukeGreetingMessage greetingMessage = new DukeGreetingMessage("Hello! I'm Duke, what shall we do today?");
        System.out.println(greetingMessage.getFormattedMessage());

        // Process input
        DukeTaskList list = new DukeTaskList();
        Scanner inputScanner = new Scanner(System.in);
        String inputMessage = inputScanner.nextLine();
        String exitCommand = "bye";

        while (!inputMessage.trim().equals(exitCommand)) {
            DukeOutputMessage outputMessage;

            try {
                DukeAction action = DukeAction.makeAction(inputMessage);
                action.executeAction(list);
                outputMessage = action.getOutputMessage();
            } catch (DukeException e) {
                outputMessage = e.getOutputMessage();
            }

            // Print output message
            System.out.println(outputMessage.getFormattedMessage());
            inputMessage = inputScanner.nextLine();
        }

        // Exit
        DukeExitMessage exitMessage = new DukeExitMessage("Bye, see you again");
        System.out.println(exitMessage.getFormattedMessage());
    }
}
