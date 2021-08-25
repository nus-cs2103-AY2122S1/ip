import java.io.*;

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


    public void run() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        Storage storage = new Storage(this.taskListFileName);
        TaskList taskList = storage.initialise();

        ui.init();
        boolean ended = false;

        while (!ended) {
            String input = "";
            // Enter data using BufferReader
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

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
