package aisu;

import aisu.command.Command;
import aisu.task.Task;

import java.util.List;


/**
 * A tasklist chatbot, named Aisu.
 *
 * @author Liaw Xin Yan
 */
public class Aisu {
    private final Storage storage;
    private TaskList tasklist;
    private final Ui ui;

    public Aisu(String dirPath, String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(dirPath, fileName);

        try {
            List<Task> cachedData = this.storage.load();
            this.tasklist = new TaskList(cachedData);
        } catch (AisuException e) {
            this.tasklist = new TaskList();
        }
    }

    public List<Task> getTaskListData() {
        return this.tasklist.getListData();
    }

    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Starts the tasklist program and requests for user input.
     * If user types "bye", the program ends.
     */
    public void run() {
        ui.showWelcomeMessage();

        boolean isExit = false;

        while (!isExit) {
            try {
                Command command = Parser.parse(ui.getInput());
                ui.showDivider();
                command.execute(this.tasklist, this.storage, this.ui);
                isExit = command.isExit();
            } catch (AisuException e) {
                ui.showDivider();
                ui.showError(e.getMessage());
            } finally {
                ui.showDivider();
            }
        }
    }

    /**
     * Gets a response from the user.
     * @param input Input from the user.
     * @return Reponse from Aisu.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(this.tasklist, this.storage, this.ui);
            System.out.println(command.showUiText());
            return command.showUiText();
        } catch (AisuException e) {
            return e.getMessage();
        }
    }

    /**
     * Calls the run() function.
     * Serves as an entrypoint of the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Aisu("data", "test1.txt").run();
    }
}
