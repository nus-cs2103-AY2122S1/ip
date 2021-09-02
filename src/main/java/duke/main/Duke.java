package duke.main;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import duke.task.Storage;

/**
 * Drives the main program.
 */
public class Duke {

    /** Storage that is responsible for saving and loading information from your hard disk into a TaskList */
    private static Storage storage;
    /** Parser that handles and interprets user input in order to perform appropriate actions */
    private static Parser parser;

    /**
     * Constructs a Duke instance to initialise fields required to drive the program.
     */
    public Duke() {
        TaskList taskList = new TaskList();
        storage = new Storage(taskList);
        parser = new Parser(taskList, storage);

        // Obtain data from save file if it exists
        storage.copyFromFileToList();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        // Solution adapted from:
        // https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java

        // Create a stream to hold the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;

        // Tell Java to use your special stream
        System.setOut(printStream);

        // Print some output: goes to your special stream
        parser.handleInput(input);

        // Put things back
        System.out.flush();
        System.setOut(old);

        // Return what was printed
        return outputStream.toString();
    }
}

