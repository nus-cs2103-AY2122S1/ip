package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.NoSuchTaskException;
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

    public String getResponse(String input) {
        try {
            String exitTag = "bye";
            if (input.equalsIgnoreCase(exitTag)) {
                new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(1250);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            System.exit(0);
                        }
                    }
                }.start();

                return Ui.exit();
            } else {
                Command action = this.parser.checkCommandTag(input);
                this.storage.saveTask(this.items);
                return action.executeCommand(this.items);
            }
        } catch (DukeException | IOException e) {
            return Ui.notifyError(e.getMessage());
        }
    }

}
