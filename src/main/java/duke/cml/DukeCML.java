package duke.cml;

import duke.command.Command;
import duke.command.Response;
import duke.command.Storage;
import duke.command.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * A CML version of Duke which contains the same functionality of the GUI version of Duke.
 */
public class DukeCML {
    private Command command;

    /**
     * Constructor of DukeCML.
     *
     * @throws IOException Throws when there is a IOException thrown during the creation of storage object.
     */
    public DukeCML() throws IOException {
        TaskList taskList = new TaskList();
        Response response = new Response(taskList);
        Storage storage = new Storage("src/main/Resources/duke.txt", taskList);
        this.command = new Command(taskList, response, storage);
    }

    private void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(this.command.welcomeToUser());
        printSeparateLine();

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            printSeparateLine();
            StringBuilder dukeOutput = command.getCorrespondingMessage(userInput);
            System.out.println(dukeOutput.toString());
            printSeparateLine();
            if (userInput.equals("bye")) {
                break;
            }
        }
    }

    private void printSeparateLine() {
        System.out.println("----------------------------------------------------------");
    }

    public static void main(String[] args) {
        try {
            DukeCML duke = new DukeCML();
            duke.run();
        } catch (IOException ioException) {
            System.out.println("Sorry I cannot start the DukeCML program");
        }
    }
}
