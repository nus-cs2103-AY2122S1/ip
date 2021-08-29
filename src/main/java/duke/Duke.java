package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Duke {
    private String taskListFileName;

    public Duke(String taskListFileName) {
        this.taskListFileName = taskListFileName;
    }

    public static class DukeException extends Exception {
        public DukeException(String errorMessage) {
            super(errorMessage);
        }
    }

    // Normal Method Invocation
    public void run() {
        this.run(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out));
    }

    // Manually call for testing
    public void run(BufferedReader in, PrintWriter out) {
        Ui ui = new Ui(in, out);
        Parser parser = new Parser();
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
