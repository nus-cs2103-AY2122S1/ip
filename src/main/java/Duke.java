/** This class implements the Duke assistant/chat-bot
 * @author damithc
 * edited by Wanyu
 */
public class Duke {
    private static TaskList tasks;
    private final Parser parser;
    private final Ui ui;
    private final Storage unit;


    public Duke() {
        Ui.welcomeMessage();
        this.ui = new Ui();
        this.unit = new Storage();
        try {
            unit.checkFiles();
        } catch (DukeException e) {
            Ui.showInput("something went wrong: " + e.getMessage() + "\nexiting program...");
            terminate();
        }
        tasks = new TaskList(unit.loadSaves());
        parser = new Parser();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
                String next = ui.readLine();
                Ui.showAsUserInput(next);
                Command command = parser.parse(next);
                if (command != null) {
                    command.execute(tasks, ui, unit);
                    exit = command.isExit();
                }
            }
    }

    public static void terminate() {
        Ui.showExitMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
