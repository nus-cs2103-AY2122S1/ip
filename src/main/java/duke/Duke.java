package duke;
import duke.exceptions.DukeException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *  Duke class that contains run and main method to run the program.
 *
 * @author Chua Sue-Ann
 */
public class Duke {

    private duke.Storage storage;
    private duke.TaskList taskList;
    private PrintWriter writer;

    /**
     * Public constructor for Duke class. Creates instance of Duke class.
     *
     * @param file File that data will be stored in later.
     * @return Instance of Duke class.
     */
    public Duke(File file) {
        this.storage = new duke.Storage(file);
        try {
            this.taskList = new duke.TaskList();
            this.writer = this.storage.load();
        } catch (IOException e) {

        }
    }

    public Duke() {}

    /**
     * Run method triggers file to be created and welcomes user with welcome message.
     *
     * @throws DukeException
     */
    public void run() throws DukeException {
        File dukeFile = new File("data/duke.txt");

        Ui.showWelcomeMessage();

        Parser.parseCommand(taskList, dukeFile, writer);
    }

    /**
     * Generates a response to user input.
     *
     * @param input The string input provided by the user.
     * @return The string response to the user input.
     */
    public String getResponse(String input) {
        try {
            File dukeFile = this.storage.getFile();
            return Parser.getCommandResponse(this.taskList, dukeFile, writer, input);
        } catch (DukeException e) {
            return "ERROR! Please provide the correct input!";
        }
    }

    /**
     * Main method for program. Creates file path and creates file.
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Files.createDirectories(Paths.get("data/"));
        File dukeFile = new File("data/duke.txt");
        new Duke(dukeFile).run();
    }
}

