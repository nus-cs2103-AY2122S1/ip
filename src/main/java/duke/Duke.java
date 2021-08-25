package duke;

import duke.logic.LCommandParser;
import duke.logic.LStorage;
import duke.task.TaskList;
import java.util.Scanner;

/**
 * Duke is a personal assistant that allows users to keep track of events, deadlines and things to do.
 * The main method will start the personal assistant in the console.
 */
public class Duke {
    private final TaskList taskList;
    private final LStorage lStorage;

    public Duke(String filePath, int listLimit) {
        taskList = new TaskList(listLimit);
        lStorage = new LStorage(filePath, taskList);
    }

    public void run() {
        Ui ui = new Ui();
        while (!ui.willExit()) {
            ui.checkInput(taskList, lStorage);
        }
    }

    public static void main(String[] args) {
        new Duke("./dukedata.txt", 100).run();
    }


}
