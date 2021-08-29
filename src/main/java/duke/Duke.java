package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static Parser parser;
    private static Storage storage;

    /**
     * Creates Duke object
     *
     */
    public Duke(String fileName) {
        storage = new Storage(fileName);
        try {
            storage.loadTasks();
        } catch (IOException e) {
            Ui.showLoadingError();
        }
        parser = new Parser(storage.getTasks(), storage);
    }

    /**
     * Runs the main logic of program
     *
     */
    public void run() {
        Ui.welcome();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (parser.isExit(input)) {
                Ui.bye();
                break;
            } else {
                try {
                    String[] results = parser.parseInput(input);
                    Ui.printAll(results);
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }

    /**
     * Runs program
     *
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();

    }


}
