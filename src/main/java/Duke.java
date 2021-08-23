import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner myObj = new Scanner(System.in);
        boolean exit = false;
        Parser parser = new Parser(ui, tasks, storage);

        ui.welcomeMessage();

        while (!exit) {
            String userInput = myObj.nextLine();
            exit = parser.parse(userInput);
        }
    }

    public static void main(String[] args) {
        new Duke("../ip/src/main/data.txt").run();
    }
}
