import duke.*;
//included package
import java.io.File;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private File
            file;
    private Parser p;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(filePath);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(filePath);
        }
        ui = new Ui(tasks, storage);
        p = new Parser();
        this.file = new File(filePath);
        tasks.readFromFile();
    }

    /**
     * Method that runs the Duke by asking for user input
     *
     * @return void
     */
    public void run() {
        System.out.println("Hello! This is Duke, your very own chat bot.");
        System.out.println("What can I help you with ?");
        while (true) {
            String fullCommand = ui.input();
            if (!fullCommand.equals("bye")) {
                try {
                    p.parse(fullCommand, tasks, storage, file);
                } catch (DukeException e) {
                    System.out.println(e.getMsg());
                }
            } else {
                System.out.println("It's sad to see you go :(");
                System.out.println("Goodbye, hope to you another day!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}

