package duke;

import java.io.IOException;

/**
 * Represents a Duke object. It is the object used to kickstart the Duke application.
 * This application acts as a task tracker to help its users keep track of Deadlines, Events and To-dos. 
 */
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

    /**
     * Runs the application.
     */
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
