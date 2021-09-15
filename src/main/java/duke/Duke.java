package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    protected static List<Task> todoList;
    protected Storage storage;
    private Ui ui;
    /**
     * Generate new Duke
     *
     * @param filePath path to store data
     */
    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            Duke.todoList = new ArrayList<>();
            loadDataToTodoList();
        } catch (IOException e) {
            e.printStackTrace();
            Duke.todoList = new ArrayList<>();
        }
    }

    private void loadDataToTodoList() throws IOException {
        storage.createFile();
        storage.downloadDataToTodoList();
    }

    /**
     * Run Duke with CLI (Command Line Interface)
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
        duke.finish();
    }

    private void run() {
        System.out.println(this.ui.printWelcome());
        process(true);
    }

    /**
     * Take the user's input and process it
     *
     * @param isContinue determine if the method should continue
     */
    private void process(boolean isContinue) {
        if (!isContinue) {
            return;
        }
        String userInput = this.ui.getUserInput();
        Response response = this.getResponse(userInput);
        this.ui.printResponse(response);
        process(response.isContinue());
    }

    /**
     * Print Welcome message and process user's command
     */
    public Response getResponse(String input) {
        UserInput userInputProcessed = Command.analyze(input);
        Response response;
        try {
            response = Command.process(userInputProcessed);
        } catch (DukeException e) {
            response = new Response(new ResponseMessage(e.toString()));
        }

        return response;
    }

    /**
     * Updating user's data into hard-drive
     */
    public void finish() {
        try {
            this.storage.upload();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
