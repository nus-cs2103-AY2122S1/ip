import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke() {
        storage = new Storage();
        ArrayList readList = storage.fileReader();
        ui = new Ui();
        taskList = new TaskList(readList, ui);

        if (readList != null) {
            ui.setItems(taskList.size());
        } else {
            taskList = new TaskList(new ArrayList<Task>(), ui);
        }
        parser = new Parser(ui, taskList, storage);
    }

    public void run() throws InputError {
        boolean byeMessage = false;
        ui.startMessage();

        while(!byeMessage) {
            String userInput = ui.startInput();
            int caseNum = parser.checkCase(userInput);

            if (caseNum == 1) {
                byeMessage = true;
            } else {
                parser.handle(caseNum, userInput, taskList);
            }
        }
        ui.byeMessage();
    }

    public static void main(String[] args) throws InputError {

        new Duke().run();

    }
}
