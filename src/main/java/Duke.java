import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import duke.Ui;
import duke.Parser;
import duke.Storage;
import duke.TaskList;

public class Duke implements Runnable {
    private static final String ERR_CREATE_FILE = "Could not create empty file.";
    private static final String ERR_UNEXPECTED = "Unexpected error occured.";
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        this.ui = new Ui();
        this.parser = new Parser();
        this.tasks = new TaskList(new Storage(filePath));
    }

    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        String input;
        while (!(input = sc.nextLine().trim()).equals("bye")) {
            ui.showMessage(parser.execute(input, tasks));
        }
        sc.close();
        ui.showFarewell();
    }

    public static void main(String[] args) {
        try {
            new Duke("duke.txt").run();
        } catch (FileNotFoundException e) {
            System.out.println(ERR_CREATE_FILE);
        } catch (IOException e) {
            System.out.println(ERR_UNEXPECTED);
        }
    }
}
