package duke;

import duke.command.Command;

import java.io.IOException;

public class Duke {
    private static final String PATHNAME = "data/duke.txt";

    private Ui ui;
    private Storage storage;
    private static List list = new List();

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            List temp = storage.readFile();
            list = temp == null ? new List() : new List(temp);
        } catch (IOException e) {
            ui.printFileError(e);
        }
    }


    public void run() {
        ui.printGreetings();
        //isExit implementation is referenced from classmate Wu Xiaoyun
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command c = Parser.parseCommands(input);
                c.execute(this.list, this.ui, this.storage);
                isExit = c.isExit;
            } catch (IOException e) {
                ui.printFileError(e);
            }

        }
    }

    public static void main(String[] args) {
        new Duke(PATHNAME).run();
    }
}
