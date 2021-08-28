package duke;

import java.io.FileNotFoundException;

/**
 * Duke class to start running the application
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke object
     * @param filePath takes in a String representing the directory of the file
     */
    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile(filePath));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * runs the program
     */
    public void run(){
        Command c = new Command();
        c.execute(tasks,ui,storage);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
