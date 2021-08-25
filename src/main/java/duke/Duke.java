package duke;

import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public void run(){
        Command c = new Command();
        c.execute(tasks,ui,storage);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
