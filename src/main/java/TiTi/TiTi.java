package titi;

import titi.util.SavedHistory;
import titi.util.TaskList;
import titi.util.Ui;

/**
 * TiTi is a Personal Assistant Chatbot that helps you keep track of various things.
 * Represents launcher for text interface version of program.
 */
public class TiTi {
    private SavedHistory savedHistory;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises a TiTi instance.
     */
    public TiTi() {
        savedHistory = new SavedHistory();
        taskList = new TaskList(savedHistory.readHistory());
        ui = new Ui(savedHistory, taskList);
    }

    private void run() {
        ui.welcome();
        while (ui.isContinue()) {
            ui.cueUserCommand();
        }
    }

    /**
     * Represents the main method in program TiTi.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        new TiTi().run();
    }
}
