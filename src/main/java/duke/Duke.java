package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.command.Command;
import duke.storage.StorageDuke;
import duke.tasklist.TaskListDuke;

/**
 * Duke is a Personal Assistant Chatbot that helps a person
 * keep track of various things.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class Duke {
    private StorageDuke storage;
    private TaskListDuke tasks;
    private final String FILEPATH = "data/tasks.txt";

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.storage = new StorageDuke(FILEPATH);
        try {
            tasks = new TaskListDuke(this.storage.load());
        } catch (IOException e) {
            tasks = new TaskListDuke(new ArrayList<>());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                System.exit(0);
                            }
                        },
                        500
                );
            }
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    protected String getWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }
}
