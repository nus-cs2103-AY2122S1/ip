package duke.main;

import java.util.Scanner;

import duke.task.Storage;

/**
 * Drives the main program.
 */
public class Duke {

    /** Scanner to get standard input from a user */
    private static Scanner sc;
    /** Storage that is responsible for saving and loading information from your hard disk into a TaskList */
    private static Storage storage;
    /** Parser that handles and interprets user input in order to perform appropriate actions */
    private static Parser parser;
    /** Ui instance responsible for handling User Interaction (UI) */
    private static Ui ui;

    /**
     * Constructs a Duke instance to initialise fields required to drive the program.
     */
    public Duke() {
        TaskList taskList = new TaskList();
        storage = new Storage(taskList);
        parser = new Parser(taskList);
        sc = new Scanner(System.in);
        ui = new Ui();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the program.
     */
    private void run() {
        // Obtain data from save file if it exists
        storage.copyFromFileToList();

        // Gets the user input
        ui.getInput(sc, parser, storage);

        // Exits
        exit();
    }

    /**
     * Exits the program.
     */
    private void exit() {
        System.out.println("Output: Goodbye! See you again!");
        Ui.printDoubleDivider();
        sc.close();
    }
}

