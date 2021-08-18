package duke;
import duke.command.Command;
import java.time.format.DateTimeParseException;
import java.io.IOException;

public class Duke {
    private static TaskList tasklist;
    private static Ui ui;
    private static Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasklist = new TaskList(storage.load(ui));
        } catch (IOException | DukeException e) {
            ui.notifyLoadingError();
//            e.printStackTrace();
            tasklist = new TaskList();
        }
    }

    public void run() {
        ui.init();
        boolean isExit = false;
        while (!isExit) {
            try {
                String in = ui.readCommand();
                Command c = Parser.parse(in);
                c.execute(tasklist, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.notifyEmptyDescription();
//                e.printStackTrace();
            } catch (IndexOutOfBoundsException e) {
                ui.notifyIndexOutOfBounds();
//                e.printStackTrace();
            } catch (NumberFormatException e) {
                ui.notifyImproperIndex();
//                e.printStackTrace();
            } catch (DateTimeParseException e) {
                ui.notifyImproperDateTime();
//                e.printStackTrace();
            } finally {
                ui.printLine();
            }
        }
        try {
            storage.save(tasklist, ui);
        } catch (IOException e) {
            ui.notifySavingError();
//            e.printStackTrace();
        }
        ui.closing();
    }

    public static void main(String[] args) {
        new Duke("frosty.txt").run();
    }
}
