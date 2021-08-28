import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class Duke implements Runnable {
    private static final String ERR_CREATE_FILE = "Could not create empty file.";
    private static final String ERR_UNEXPECTED = "Unexpected error occurred.";
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a Duke object. IOException is thrown if file does not
     * exist and is unable to be created.
     *
     * @param filePath path of file used to read and write tasks
     * @throws IOException if file does not exist and cannot be created
     */
    public Duke(String filePath) throws IOException {
        this.ui = new Ui();
        this.parser = new Parser();
        this.tasks = new TaskList(new Storage(filePath));
    }

    /**
     * Runs Duke object and starts taking in commands from user.
     */
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

    /**
     * Class entrypoint.
     */
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
