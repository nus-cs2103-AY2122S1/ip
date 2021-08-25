package duke;

import duke.classes.TaskList;
import duke.exceptions.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DukeMain {

    private DukeStorage storage;
    private TaskList taskList;
    private DukeUI ui;
    private DukeParser parser;

    /**
     * Class Constructor.
     * Initializes a DukeMain class and creates supporting classes for usage when running the program
     */
    public DukeMain() {
        ui = new DukeUI();
        storage = new DukeStorage();

        try {
            taskList = new TaskList(storage.retrieve("localList.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred with the saved file");
            e.printStackTrace();
            System.exit(0);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        parser = new DukeParser(taskList, ui);
    }

    /**
     * Runs Duke.
     * Begins running duke and awaits user input to continue
     */
    public void run() {
        boolean hasQuit = false;
        Scanner userInput = new Scanner(System.in);

        ui.greetings();
        while (!hasQuit) {
            try {
                String input = userInput.nextLine();
                hasQuit = parser.parse(input);

            } catch (DukeException e) {
                ui.printError(e.getMessage());
            } catch (DateTimeParseException ex) {
                ui.printDateTimeError();
            }
        }
        try{
            storage.saveList(taskList);
        } catch (IOException e) {
            System.out.println("A file error occurred.");
            e.printStackTrace();
        }
        ui.exit();
    }

    public static void main(String[] args) {
        new DukeMain().run();
    }
}
