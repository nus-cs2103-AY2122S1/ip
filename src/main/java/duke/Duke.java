package duke;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Represents main logic of duke.Duke.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private HistoryManager history;

    /**
     * @param filePath path to tasks storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        assert ui != null : "ui should not be null";
        assert storage != null : "storage should not be null";
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
        history = new HistoryManager(tasks);
    }

    /**
     * Handles CLI interactions with user.
     */
    public void run() {
        assert tasks != null : "tasks should not be null";
        assert ui != null : "ui should not be null";
        assert storage != null : "storage should not be null";
        assert history != null : "history should not be null";
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDivider();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage, history);
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
     * Runs commands obtained from GUI to obtain output.
     */
    public String getResponse(String fullCommand) {
        assert tasks != null : "tasks should not be null";
        assert ui != null : "ui should not be null";
        assert storage != null : "storage should not be null";
        assert history != null : "history should not be null";
        // Change stdout of UI
        PrintStream oldStdout = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage, history);
        } catch (DukeException e) {
            ui.showDukeException(e);
        } catch (Exception e) {
            ui.showException(e);
        }
        System.setOut(oldStdout);
        return output.toString();
    }

    /**
     * Returns welcome message from UI.
     */
    public String getWelcomeMessage() {
        assert ui != null : "ui should not be null";
        // Change stdout of UI
        PrintStream oldStdout = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        ui.showWelcome();
        System.setOut(oldStdout);
        return output.toString();
    }

    /**
     * Runs CLI interface for duke.Duke.
     */
    public static void main(String[] args) {
        new duke.Duke("data/tasks.txt").run();
    }
}
