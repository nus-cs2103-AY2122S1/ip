import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage("/data/duke.txt");
        try {
            this.taskList = new TaskList(storage.load());
        } catch (FileNotFoundException f) {
            System.out.println("    ____________________________________________________________\n"
                    + "     " + "\uD83D\uDE41" + " OOPS!!! Unable to retrieve file. Creating new list now!\n"
                    + "    ____________________________________________________________");
            this.taskList = new TaskList();
        }
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        Parser makeSense = new Parser(input, this.taskList, this.storage);

        while (!input.equals("bye")) {
            makeSense.compute(input);

            input = sc.nextLine();
            continue;

        }
        this.ui.printBye();

    }

    public static void main(String[] args) {
        new Duke("/data/duke.txt").run();

    }
}
