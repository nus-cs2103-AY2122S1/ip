package duke;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;
import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class Duke {
    private final Parser PARSER;
    private final Ui UI;
    public static final String STORAGE_FILEPATH = "data" + File.separatorChar + "duke-storage.txt";

    public static void main(String[] args) {
        new Duke(Duke.STORAGE_FILEPATH).run();
    }

    public Duke(String filePathToStorage) {
        Storage dukeStore = new Storage(filePathToStorage);
        TaskList taskList = TaskList.of(dukeStore);
        PARSER = Parser.initialize(taskList);
        UI = new Ui();
    }

    public void run() {
        while (UI.isRunning()) {
            if (!UI.isPendingReply()) {
                continue;
            }

            String userInput = UI.readCommand();
            if (userInput.equals("bye")) {
                UI.close();
            }

            try {
                String reply = PARSER.parseCommand(userInput);
                Ui.printFormatted(reply);
            } catch (DukeException e) {
                Ui.printFormatted(e.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getResponse(String userInput) {
        return "Test response";
    }
}

