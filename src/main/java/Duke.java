/**
 * This class encapsulates Duke, an interactive task management chat-bot.
 *
 * @author Kleon Ang
 */
public class Duke {
    private final Parser parser;
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    public Duke(String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError(fileName);
            this.tasks = new TaskList();
        }
        this.parser = new Parser(this.tasks);
    }

    public void run() {
        while (true) {
            try {
                String readIn = this.ui.nextLine();
                this.tasks = this.parser.parse(readIn);
                if (this.parser.toExit()) {
                    break;
                }
                if (this.parser.toRewrite()) {
                    this.storage.rewriteData(this.tasks);
                }
            } catch (DukeException e) {
                Ui.printReply(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
