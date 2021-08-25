import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.parser.Parser;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filepath) {
        tasks = new TaskList();
        ui = new Ui(tasks);
        storage = new Storage(filepath, tasks);
        parser = new Parser(tasks, storage, ui);
        storage.load();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greeting();
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            try {
                parser.parse(cmd);
                storage.save();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            cmd = sc.nextLine();
        }
        sc.close();
        ui.farewellMsg();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
