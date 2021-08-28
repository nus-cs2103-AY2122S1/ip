package duke;

import java.util.Scanner;

public class Duke implements Runnable {
    private static final String FILE_PATH = "duke.txt";
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a Duke object. IOException is thrown if file does not
     * exist and is unable to be created.
     *
     */
    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.tasks = new TaskList(new Storage(FILE_PATH));
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

    public String getResponse(String input) {
        return "What does " + input + " mean?";
    }

    /**
     * Class entrypoint.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
