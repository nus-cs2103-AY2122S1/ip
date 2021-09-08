package retriever;

import javafx.application.Platform;
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

    /**
     * Initializes various Objects, and also takes in the file path to
     * read the tasks present in that file.
     *
     * @param filePath The file path in which tasks may be stored.
     */
    public Retriever(String filePath) {
        taskStorage = new Storage(filePath);
        taskList = new TaskList(taskStorage);
        parser = new Parser(taskList);
        ui = new Ui();
    }

    /**
     * Initializes various Objects, and assumes the default file path to
     * read the tasks present in that file.
     */
    public Retriever() {
        // In case the user doesn't specify the file path, the default path is taken.
        String filePath = "tasksList.txt";
        taskStorage = new Storage(filePath);
        taskList = new TaskList(taskStorage);
        parser = new Parser(taskList);
        ui = new Ui();
    }

    /**
     * Returns a String, that is shown on the GUI.
     */
    public String getResponse(String input) {
        String retrieverResponse = "";

        try {
            parser.parseUserInput(input);
            retrieverResponse = ui.getRetrieverResponse();
        } catch (RetrieverException e) {
            // Catching various exceptions and alerting the user.
            retrieverResponse = e.getMessage();
        }

        if (parser.isSessionDone()) {
            retrieverResponse = "Sad To See You Go!";
            Platform.exit();
        }

        assert !retrieverResponse.equals("") : "Response Could Not Be Formed!";

        return retrieverResponse;
    }
}

