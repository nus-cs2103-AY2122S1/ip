package daisy;

import java.util.ArrayList;

import daisy.command.Command;
import daisy.command.Parser;
import daisy.task.Storage;
import daisy.task.TaskList;

/**
 * Daisy class that starts and runs the Daisy bot.
 */
public class Daisy {

    static final String START_MESSAGE = "Hello! I'm Daisy\n" + "What can I do for you?";
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs the Daisy object.
     */
    public Daisy() {
        storage = new Storage("data/daisy.txt");
        try {
            taskList = new TaskList(storage.getFile());
        } catch (DaisyException e) {
            e.printStackTrace();
            taskList = new TaskList(new ArrayList<>());
        }
    }

    public String start() {
        return START_MESSAGE;
    }

    /**
     * Gets the response from the Daisy bot for the input.
     *
     * @param input String input message.
     * @return Response string.
     */
    public String getResponse(String input) throws DaisyException {
        assert input != null : "Input is null";
        Command c = Parser.parse(input);
        return c.execute(taskList, storage);
    }
}
