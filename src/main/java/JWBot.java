/**
 * Main class for the chat bot
 *
 * @author  Yim Jaewon
 */
public class JWBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String txtPath = "jwbot.txt";

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
