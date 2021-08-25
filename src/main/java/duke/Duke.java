package duke;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Main class for running duke.Duke
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * @param filePath path to tasks storage file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showDukeException(e);
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (Exception e) {
            ui.showException(e);
            tasks = new TaskList();
        }
    }

    /**
     * Main loop for interactions with user
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDivider();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showDukeException(e);
            } catch (Exception e) {
                ui.showException(e);
            } finally {
                ui.showDivider();
            }
        }
    }

    /**
     * Run commands obtained from GUI to obtain output
     */
    public String getResponse(String fullCommand) {
        // Change stdout of UI
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showDukeException(e);
        } catch (Exception e) {
            ui.showException(e);
        }
        return output.toString();
    }

    /**
     * Bridging function to print welcome message
     */
    public String getWelcomeMessage() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        ui.showWelcome();
        return output.toString();
    }

    /**
     * Main entry method for duke.Duke
     */
    public static void main(String[] args) {
        new duke.Duke("data/tasks.txt").run();
    }
}
