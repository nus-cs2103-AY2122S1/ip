package duke;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

import java.io.File;


public class Duke {
    private final Parser PARSER;
    private final Ui UI;

    public static void main(String[] args) {
        String filePath = "data" + File.separatorChar + "duke-storage.txt";
        new Duke(filePath).run();
    }

    public Duke(String filePathToStorage) {
        Storage DUKE_STORE = new Storage(filePathToStorage);
        TaskList TASKLIST = TaskList.of(DUKE_STORE);
        this.PARSER = Parser.initialize(TASKLIST);
        this.UI = new Ui();
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
                String reply = this.PARSER.parseCommand(userInput);
                Ui.printFormatted(reply);
            } catch (DukeException e) {
                Ui.printFormatted(e.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

