package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Duke {
    private String taskListFileName;

    /**
     * Configures a Duke runtime to use a taskList storage file at specified location.
     * @param taskListFileName path to taskList storage file.
     */
    public Duke(String taskListFileName) {
        this.taskListFileName = taskListFileName;
    }

    public static class DukeException extends Exception {
        public DukeException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Default way to run the Duke programme.
     */
    public void run() {
        this.run(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out));
    }

    /**
     * Runs the Duke programme with provided inputs and outputs. Normally invoked by run() unless dependencies need
     * to be injected by test.
     * @param in BufferedReader taking in user input.
     * @param out PrintWriter to write responses to user input.
     */
    public void run(BufferedReader in, PrintWriter out) {
        Ui ui = new Ui(in, out);
        Parser parser = new Parser(ui);
        Storage storage = new Storage(this.taskListFileName);
        TaskList taskList = storage.initialise();

        ui.init();
        boolean ended = false;

        while (!ended) {
            String input = "";

            // Reading data using readLine
            try {
                input = ui.getNextCommand();
            } catch (IOException e) {
                System.out.println("Cannot get next command, terminating");
                e.printStackTrace();
                break;
            }

            ended = parser.parse(input, taskList);
        }

        storage.store(taskList);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
