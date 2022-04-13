package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke is a task manager using CLI.
 *
 * @author Samuel Lau
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private ArrayList<Task> list;
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath filePath is the path of the text file to be loaded by Duke.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            list = tasks.list;
        } catch (DukeException e) {
            ui.sayError(e);
            tasks = new TaskList();
            list = tasks.list;
        }
    }

    /**
     * Runs Duke.
     */
    public void run() {
        System.out.println(ui.sayHi());
        Scanner s = new Scanner(System.in);
        String command = s.nextLine();
        while (!command.equals("bye")) {
            try {
                System.out.println(Parser.parseResponse(command, ui, list, tasks));
                command = s.nextLine();
            } catch (DukeException e) {
                System.out.println(ui.sayError(e));
                command = s.nextLine();
            }
        }
        System.out.println(ui.sayBye());
        storage.writeAll(tasks);
    }

    public String getResponse(String input) {
        if (input.equals("bye")) {
            storage.writeAll(tasks);
            return ui.sayBye();
        } else {
            try {
                return Parser.parseResponse(input, ui, list, tasks);
            } catch (DukeException e) {
                return ui.sayError(e);
            }
        }
    }

    /**
     * Main method. Duke object is constructed with a filePath
     * and run method is called.
     *
     * @param args Arguments from the command line.
     */
    public static void main(String[] args) {
        new Duke("./Data/Duke.txt").run();
    }
}
