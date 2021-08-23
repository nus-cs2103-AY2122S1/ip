import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Parser;
import duke.Storage;
import duke.Ui;
import tasktypes.Task;
import tasktypes.TaskList;
import commands.Command;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public void run() {
        ui.showIntro();

        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            try {
                String beforeEdit = input.nextLine();
                String nextInput = beforeEdit.toLowerCase();
                ui.endOfCommand();
                Command c = Parser.parse(nextInput);
                c.execute(taskList, ui, storage);
                storage.updateHardDisk(taskList);
            } catch (Exception e) {
                ui.showError(e);
            } finally {
                ui.endOfResponse();
            }
        }
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        // start running the chatbot
        new Duke("data/dory.txt").run();
    }

}

