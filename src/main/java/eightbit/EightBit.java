package eightbit;

import eightbit.command.Command;
import eightbit.util.Parser;
import eightbit.util.Storage;
import eightbit.util.TaskList;

/**
 * Represents the chat bot.
 */
public class EightBit {

    private final String filepath;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructs the chat bot.
     */
    public EightBit() {
        this.filepath = "data/eightBit.txt";
        this.storage = new Storage(filepath);
        this.taskList = new TaskList(storage.loadFileContents());
    }

    /**
     * Executes the user command and returns the response to be printed.
     *
     * @param input User command.
     * @return Response to be printed after executing the command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(taskList, storage);
            return response;
        } catch (EightBitException e) {
            return e.toString();
        }
    }
}
