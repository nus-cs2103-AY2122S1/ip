package duke.main;

import java.util.Scanner;

import duke.task.Storage;

public class Duke {
    private static Storage storage;
    private static Scanner sc;
    private static Parser parser;
    private static Ui ui;

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

    private void run() {
        // Obtain data from save file if it exists
        storage.copyFromFileToList();

        // Gets the user input
        ui.getInput(sc, parser, storage);

        // Exits
        exit();
    }

    private void exit() {
        System.out.println("Output: Goodbye! See you again!");
        Ui.printDoubleDivider();
        sc.close();
    }
}

