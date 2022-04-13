package pika;

import java.io.IOException;

import pika.command.Command;
import pika.exception.PikaException;
import pika.task.TaskList;
import pika.ui.Parser;
import pika.ui.Storage;
import pika.ui.Ui;


/**
 * Main Class to run the Duke ChatBot.
 */
public class Pika {
    private final Storage storage;
    private TaskList taskLists;

    /**
     * Constructor for the duke class.
     *
     * @param filePath The path where the txt file is located/to be created.
     */
    public Pika(String filePath) {
        storage = new Storage(filePath);
        try {
            this.taskLists = new TaskList(storage.loadTasks());
        } catch (PikaException | IOException e) {
            Ui.loadingErrorMessage(); //Inform user that the existing file is of the wrong format
            this.taskLists = new TaskList(); //Creates a new empty list
        }
    }

    /**
     * Constructor for the duke class.
     */
    public Pika() {
        storage = new Storage("data");
        try {
            this.taskLists = new TaskList(storage.loadTasks());
        } catch (PikaException | IOException e) {
            Ui.loadingErrorMessage(); //Inform user that the existing file is of the wrong format
            this.taskLists = new TaskList(); //Creates a new empty list
        }
    }

    /**
     * Returns the message the bot will say after parsing the response.
     *
     * @param input User input.
     * @return The message the bot will reply to the user.
     * @throws PikaException if there are any errors with the input.
     * @throws IOException if there are any errors when loading the file.
     */
    public String getResponse(String input) throws PikaException, IOException {
        Command c = Parser.parse(input);
        String output;
        output = c.execute(taskLists, storage);
        return output;
    }
}
