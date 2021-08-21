import action.DukeAction;
import entity.list.DukeTaskList;
import entity.message.ExitMessage;
import entity.message.GreetMessage;
import entity.message.Message;

import entity.data.Data;
import entity.data.DukeFile;
import exception.DukeException;

import java.io.IOException;
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

        // Scan data to a list
        DukeTaskList list = Data.scanListFileDataToList(listFile);
        if (list == null) {
            return;
        }

        // Greet
        GreetMessage greetingMessage = new GreetMessage("Hello! I'm Duke, what shall we do today?");
        greetingMessage.print();

        // Process input
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
            } catch (IOException e) {
                outputMessage = new Message("There is a problem when changing data to the file");
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
