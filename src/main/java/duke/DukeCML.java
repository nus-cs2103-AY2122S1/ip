package duke;

import duke.command.Command;
import duke.command.Response;
import duke.command.Storage;

import java.io.IOException;
import java.util.Scanner;

public class DukeCML {
    private Command command;


    public DukeCML() throws IOException {
        TaskList taskList = new TaskList();
        Response response = new Response(taskList);
        Storage storage = new Storage("duke.txt", taskList);
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
