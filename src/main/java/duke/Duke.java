package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    protected static List<Task> todoList;
    protected Storage storage;
    /**
     * Generate new Duke
     *
     * @param filePath path to store data
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.todoList = new ArrayList<>();

        try {
            storage.createFile();
            storage.download();
        } catch (IOException e) {
            e.printStackTrace();
            todoList = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("/Users/hungkhoaitay/Duke.Duke/data/duke.txt");
        Ui ui = new Ui();
        duke.run(ui);
        duke.finish();
    }

    private void run(Ui ui) {
        System.out.println(ui.printWelcome());
        process(ui, true);
    }

    private void process(Ui ui, boolean isContinue) {
        if (!isContinue) {
            return;
        }
        String userInput = ui.getUserInput();
        Response response = this.getResponse(userInput);
        ui.printResponse(response);
        process(ui, response.isContinue());
    }

    /**
     * Print Welcome message and process user's command
     */
    public Response getResponse(String input) {
        UserInput userInputProcessed = Command.analyze(input);
        Response response = null;
        try {
            response = Command.process(userInputProcessed);
        } catch (DukeException e) {
            response = new Response(new ResponseMessage(e.toString()));
        } catch (Exception e) {
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
