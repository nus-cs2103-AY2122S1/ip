package retriever;

import retriever.exception.RetrieverException;
import retriever.task.TaskList;

/**
 * A Chatbot to help manage your daily schedule.
 */
public class Retriever {

    /** To perform operations on the tasks stored in the text file*/
    private Storage taskStorage;
    private Ui ui;
    private Parser parser;
    private TaskList taskList;

    public Retriever(String filePath) {
        taskStorage = new Storage(filePath);
        taskList = new TaskList(taskStorage);
        parser = new Parser(taskList);
        ui = new Ui();
    }

    /**
     * Main body of the Retriever Chatbot.
     */
    public void run() {
        // To show the welcome message
        ui.printWelcomeMessage();

        // To store the user input string.
        String userInput;

        // Main body which is repeated till the "bye" keyword is encountered.
        do {
            // Taking input
            userInput = ui.readCommand();

            try {
                parser.parseUserInput(userInput);
            } catch (RetrieverException e) {
                // Catching various exceptions and alerting the user.
                ui.printErrorMessage(e.getMessage());
            }
            ui.printDashedLine();
        } while (!parser.isExit());

        // Printing the good-bye message.
        ui.printGoodByeMessage();
    }

    /**
     * Returns an interactive session with the Chatbot. Basically a mean to interact with it.
     *
     * @param args Here, usually nothing is passed.
     */
    public static void main(String[] args) {
        // Calling the run() method to start the Chatbot.
        new Retriever("tasksList.txt").run();
    }
}

