package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.NoSuchTaskException;
import duke.gui.Ui;
import duke.tasks.TaskList;


/**
 * Encapsulates the information for a Duke object that contains a Storage, Parser and TaskList object.
 */
public class Duke {
    private TaskList items;
    private final Storage storage;
    private final Parser parser;

    /**
     * Constructs a Duke object with the specified file path for the data file.
     */
    public Duke() {
        this.items = new TaskList();
        this.storage = new Storage("data/duke.txt");
        this.parser = new Parser();
        this.start();
    }

    /**
     * Starts the chat bot by attempting to load the previously stored data.
     * Chat bot will receive, interpret, execute and save data from the recognisable commands from user.
     */
    private void start() {
        try {
            this.items = this.storage.loadTask();
        } catch (IOException | NoSuchTaskException e) {
            Ui.notifyError(e.getMessage());
        }
    }

    /**
     * Generates a response to the user after the user enter an input in the GUI.
     *
     * @param input User's input
     * @return A String representing the response of the chatbot to the user input.
     */
    public String getResponse(String input) {
        try {
            String exitTag = "bye";
            if (input.equalsIgnoreCase(exitTag)) {
                return closeApplicationAfterMessage();
            } else {
                Command action = this.parser.checkCommandTag(input);
                this.storage.saveTask(this.items);
                return action.executeCommand(this.items);
            }
        } catch (DukeException | IOException e) {
            return Ui.notifyError(e.getMessage());
        }
    }

    /**
     * Closes the GUI of the Application after 1.25 seconds.
     * Prints the goodbye message on the GUI before closing.
     *
     * @return A String representing the goodbye message.
     */
    private String closeApplicationAfterMessage() {
        new Thread(() -> {
            try {
                Thread.sleep(1250);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.exit(0);
            }
        }).start();

        return Ui.exit();
    }

}
