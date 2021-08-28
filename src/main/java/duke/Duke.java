package duke;

import java.util.Scanner;

/**
 *  This class is the entry point into the program, Duke.
 *  Duke is a chat bot that helps manage tasks that you have.
 *
 * @author Ryan Tian Jun.
 */
public class Duke {
    private Storage storage;
    private Intro intro;
    private Farewell farewell;

    public Duke(String filePath) {
        this.intro = new Intro();
        this.farewell = new Farewell();
        this.storage = new Storage(filePath);
    }

    /**
     * Driver method for the entire program.
     * Used to instantiate the chat bot to receive user input.
     *
     */
    public void run() {
        this.intro.printIntro();
        // Starts reading user input
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!command.toLowerCase().equals("bye")) {
            command = scanner.nextLine();
            // Processes user input
            if (!command.toLowerCase().equals("bye")) {
                Ui feature = new Feature(command);

            }
        }
        // Save commands on hard drive
        TaskList.saveList();
        farewell.printFarewell();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
