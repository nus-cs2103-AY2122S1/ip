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
        System.out.println(ui.printWelcome());

        boolean isContinue = true;

        while (isContinue) {
            try {
                String userInput = ui.getUserInput();

                Response response = duke.getResponse(userInput);

                isContinue = response.isContinue();
                System.out.println(response);
            } catch (DukeException.DukeEmptyTask dukeEmptyTask) {
                dukeEmptyTask.printStackTrace();
            } catch (DukeException.DukeEmptyNote dukeEmptyNote) {
                dukeEmptyNote.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        duke.finish();
    }

    /**
     * Print Welcome message and process user's command
     */
    public Response getResponse(String input) throws DukeException.DukeEmptyTask, DukeException.DukeEmptyNote, IOException {
        UserInput userInputProcessed = Command.analyze(input);
        Response response = Command.process(userInputProcessed);

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
