package jwbot;

import jwbot.command.Command;
import jwbot.data.TaskList;
import jwbot.data.exception.JWBotException;
import jwbot.parser.Parser;
import jwbot.storage.Storage;
import jwbot.ui.Ui;

/**
 * Main class for the chat bot
 *
 * @author  Yim Jaewon
 */
public class JWBot {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private static final String txtPath = "jwbot.txt";

    /**
     * The constructor of JWBot.
     *
     * @param filePath the path of the txt file that the tasks will be recorded on
     */
    public JWBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JWBotException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts JWBot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JWBotException e) {
                ui.showError(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {

        new JWBot(txtPath).run();

    }
}
