package angrybot;

import angrybot.command.AngryBotCommand;
import angrybot.command.ExitCommand;
import angrybot.exception.AngryBotException;
import angrybot.parser.Parser;
import angrybot.storage.Storage;
import angrybot.task.TaskList;
import angrybot.ui.Ui;

/**
 * Encapsulate a bot which helps user to keep track of a list of task.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class AngryBot {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;
    private boolean isActive;

    /**
     * Constructor of AngryBot
     */
    public AngryBot() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        taskList.loadFromList(storage.load());
        parser = new Parser(taskList, storage, ui);
        isActive = true;
    }

    /**
     * Takes in the user's input and returns the response
     * that AngryBot is going to display on the GUI.
     *
     * @param input The user's input.
     * @return A string containing the response to be displayed under AngryBot.
     */
    public String getResponse(String input) {
        if (isActive) {
            try {
                AngryBotCommand c = parser.processInput(input);
                if (c instanceof ExitCommand) {
                    isActive = false;
                }
                return c.execute();
            } catch (AngryBotException e) {
                return e.getMessage();
            }
        }
        return "";
    }
}
