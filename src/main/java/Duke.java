import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException ioException) {
            System.out.println(ioException);
            tasks = new TaskList();
        }
        this.parser = new Parser(this.storage, this.ui, this.tasks);
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("src/data/duke.txt").chat();
    }

    /**
     * Prints (to screen) Duke's response to the user input, entered from the Command Line.
     */
    private void chat() throws DukeException {
        String input;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            }

            this.parser.parse(input);

        }
    }
}
