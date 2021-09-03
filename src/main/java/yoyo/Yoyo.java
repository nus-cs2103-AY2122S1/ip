package yoyo;

import yoyo.command.Command;
import yoyo.core.DialogHandler;
import yoyo.core.Parser;
import yoyo.core.Storage;
import yoyo.exception.YoyoException;
import yoyo.task.TaskList;

public class Yoyo {
    private final Storage storage;
    private final DialogHandler dialogHandler;
    private TaskList tasks;

    /**
     * Constructor for Yoyo program.
     */
    public Yoyo() {
        dialogHandler = new DialogHandler();
        storage = new Storage("data/yoyo.txt");
        try {
            tasks = storage.load();
        } catch (YoyoException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Reads the user input, executes corresponding command and return message.
     *
     * @param input Input from user.
     * @return Message to displayed.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage, dialogHandler);
        } catch (YoyoException e) {
            return e.getMessage();
        }

    }

}
