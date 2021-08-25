import exceptions.DukeException;
import exceptions.DukeUnknownCommandException;
import exceptions.DukeEmptyTodoDescriptionException;
import java.util.ArrayList;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


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
            ui.executeCommand(command, description);
            command = sc.next();
            if(!command.equals("bye")) {
                description = sc.nextLine();
            }
            storage.saveData();
        }
        ui.displayByeMessage();
    }

}
