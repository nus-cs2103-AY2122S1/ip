package duke;
import duke.exceptions.DukeException;
import duke.Storage;
import duke.TaskList;

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

    private Storage storage;
    private TaskList taskList;
    private PrintWriter writer;

    /**
     * Public constructor for Duke class. Creates instance of Duke class.
     *
     * @param file File that data will be stored in later.
     * @return Instance of Duke class.
     */
    public Duke(File file) {
        this.storage = new Storage(file);
        try {
            this.taskList = new TaskList();
            this.writer = this.storage.load();
        } catch (IOException e) {

        }
    }

    public Duke() {}

    /**
     * Run method triggers file to be created and welcomes user with welcome message.
     *
     * @throws DukeException If an exception is caught in parse command.
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
     * @param args Arguments of main method.
     * @throws IOException If createDirectories fails to be executed.
     * @throws DukeException If program cannot be run.
     */
    public static void main(String[] args) throws IOException, DukeException {
        Files.createDirectories(Paths.get("data/"));
        File dukeFile = new File("data/duke.txt");
        new Duke(dukeFile).run();
    }
}

