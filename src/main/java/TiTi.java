
public class TiTi {
    private SavedHistory savedHistory;
    private TaskList taskList;
    private Ui ui;

    public TiTi() {
        savedHistory = new SavedHistory();
        taskList = new TaskList(savedHistory.readHistory());
        ui = new Ui(savedHistory, taskList);
    }

    public void run() {
        ui.welcome();
        while (ui.isContinue()) {
            ui.userCommand();
        }
    }

    public static void main(String[] args) {
        new TiTi().run();
    }

}
