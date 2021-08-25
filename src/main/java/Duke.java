import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (Exception e) {
            System.out.println(e + "hellop");
            tasks = new TaskList();
        }
        parser = new Parser(storage, tasks);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            if (!parser.command(userInput)) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Ui().greeting();
        new Duke("dukeData.txt").run();
    }
}
