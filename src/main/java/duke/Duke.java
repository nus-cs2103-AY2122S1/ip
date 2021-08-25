package duke;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList(storage.loadFile());
    }

    public void run() {
        ui.showWelcome();
        String command = ui.readCommand();


        while (!command.equals("bye")) {
            try {
                parser.parse(command, tasks);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                command = ui.readCommand();
            }
        }
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.goodbye();
    }


    public static void main(String[] args) {

        Duke duke = new Duke("./data/duke.txt");
        duke.run();

    }


}
