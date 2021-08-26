package duke;

import duke.exceptions.DukeException;

import java.io.*;
import java.util.Scanner;

/**
 * Representing the Duke project. Used to store Tasks and can be marked complete
 * when done
 */

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private Ui ui;
    private Tasklist dukeList;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        dukeList= new Tasklist();
        storage = new Storage("./data/saved-tasks.txt");
    }

    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke();
        duke.runDuke();
    }


    public void runDuke() throws DukeException, IOException {
        storage.fetchData();
        ui.displayWelcomeMessage();
        String command = sc.next();
        String description = sc.nextLine();
        while(!command.equals("bye")) {
            handleCommandExecution(command,description);
            command = sc.next();
            if(!command.equals("bye")) {
                description = sc.nextLine();
            }
            storage.saveData();
        }
        ui.displayByeMessage();
    }

    public void handleCommandExecution(String command, String description)throws DukeException {
        ui.executeCommand(command,description);
    }

}
