import action.DukeAction;
import entity.list.DukeTaskList;
import entity.message.ExitMessage;
import entity.message.GreetMessage;
import entity.message.Message;

import entity.data.Data;
import entity.data.DukeFile;
import exception.DukeException;

import java.util.Scanner;

/**
 * Encapsulates a chatbot that greets the user,
 * adds valid inputs to a task list,
 * updates tasks in the list,
 * and exits when the user types `bye`.
 */
public class Duke {
    public static void main(String[] args) {
        // Load data
        DukeFile listFile = Data.loadListFile();
        if (listFile == null) {
            return;
        }

        // Greet
        GreetMessage greetingMessage = new GreetMessage("Hello! I'm Duke, what shall we do today?");
        greetingMessage.print();

        // Process input
        DukeTaskList list = new DukeTaskList();
        Scanner inputScanner = new Scanner(System.in);
        String inputMessage = inputScanner.nextLine();
        String exitCommand = "bye";

        while (!inputMessage.trim().equals(exitCommand)) {
            Message outputMessage;

            try {
                DukeAction action = DukeAction.makeAction(inputMessage);
                action.executeAction(list);
                outputMessage = action.getOutputMessage();
            } catch (DukeException e) {
                outputMessage = e.getOutputMessage();
            }

            // Print output message
            outputMessage.print();
            inputMessage = inputScanner.nextLine();
        }

        // Exit
        ExitMessage exitMessage = new ExitMessage("Bye, see you again");
        exitMessage.print();
    }
}
