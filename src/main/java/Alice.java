import java.io.IOException;

/**
 * Class of chatbot
 */
public class Alice {

    private final Storage storage;
    private final TaskDialog taskDialog;
    private final Ui ui;

    public Alice(String fileName) throws DialogException {
        ui = new Ui();
        storage = new Storage(fileName);
        ui.importTaskList(storage.load());
        taskDialog =ui.getTaskDialog();
    }

    public void run() throws DialogException {
        ui.showCurrentList();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskDialog, storage);
                isExit = c.isExit;
            } catch (AliceException | IOException e) {
                Ui.printError(e);
            }

        }
    }

    public static void main(String[] args) throws DialogException, IOException {
        Ui.showWelcome();
        boolean isGo;
        String saveFileName;
        do {
            isGo = true;
            Ui.printSelectSaveFile();
            saveFileName = Ui.fastRead();
            if (!Storage.contains(saveFileName)) {
                Ui.showConfirmCreateNewFile(saveFileName);
                isGo = Parser.yesNoToBoolean(Ui.fastRead());
            }
        } while (!isGo);
        new Alice(saveFileName).run();
    }
}
