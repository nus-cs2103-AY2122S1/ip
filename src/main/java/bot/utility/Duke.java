package Bot.utility;

/**
 * Simulates the Duke chatbot.
 */
public class Duke {
    private Parser parser;
    private Logger logger;
    private TaskList tasks;
    private Ui ui;

    /**
     * Runs the Duke chatbot
     */
    public void run() {
        start();
        analyzeLog();
    }

    private void start() {
        logger = new Logger("Bot.tasks.txt");
        parser = new Parser();
        ui = new Ui();
        ui.greet();
        tasks = new TaskList(logger.loadList());
    }

    /**
     * Primary response function of the chatbot
     */
    private void analyzeLog() {
        do {
            String input = ui.getUserInput();
            parser.takeInput(input);
            parser.interactWith(ui, logger, tasks);
        } while (parser.flag == 1);
    }
}
