package jwbot;

import jwbot.command.Command;
import jwbot.data.TaskList;
import jwbot.data.exception.JwBotException;
import jwbot.parser.Parser;
import jwbot.storage.Storage;
import jwbot.ui.Ui;

/**
 * Main class for the chat bot
 *
 * @author Yim Jaewon
 */
public class JwBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * The constructor of JWBot.
     *
     * @param filePath the path of the txt file that the tasks will be recorded on
     */
    public JwBot(String filePath) {
        assert filePath != null : "filePath is null";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JwBotException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void exit() {
            System.exit(0);
    }

    public String getResponse(String input) throws JwBotException {
        assert input != null;
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (JwBotException e) {
            return ui.showError(e.getMessage());
        }
    }

}
