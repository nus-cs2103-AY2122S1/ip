package TiTi;

import TiTi.util.SavedHistory;
import TiTi.util.TaskList;
import TiTi.util.Ui;

/**
 * TiTi is a Personal Assistant Chatbot that helps you keep track of various things
 */
public class TiTi{
    private SavedHistory savedHistory;
    private TaskList taskList;
    private Ui ui;


    public TiTi() {
        savedHistory = new SavedHistory();
        taskList = new TaskList(savedHistory.readHistory());
        ui = new Ui(savedHistory, taskList);
    }

    private void run() {
        ui.welcome();
        while (ui.isContinue()) {
            ui.UserCommand();
        }
    }

    /**
     * Main method in program TiTi.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        new TiTi().run();
    }
}
