package duke;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents Duke, a text-based Java chatbot that helps to
 * keep track of various tasks.
 *
 * @author Joshua Yong
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor.
     *
     * @param filePath The file path of the text file to be used for storage.
     *                 The data in the file will be read and Duke will be initialized
     *                 with task information from the file.
     *                 If the file does not exist or if the data in the file is corrupted,
     *                 Duke will be initialized without prior task information and the file
     *                 will be reset.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage() + "\nStarting Duke without saved tasks...");
            tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot.
     */
    private void start() {
        ui.hello();
        String userInput = ui.getUserInput();
        Parser parser = new Parser(tasks);
        while (!userInput.equals("bye")) {
            try {
                ui.printMessage(parser.parse(userInput));
                storage.save(tasks);
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            }
            userInput = ui.getUserInput();
        }
        ui.goodbye();
    }

    /**
     * The entry point of the application.
     *
     * @param args Command line arguments (currently not used).
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").start();
    }

}
