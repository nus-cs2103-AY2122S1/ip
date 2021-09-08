package duke;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static boolean done = false;

    /**
     * initialises Duke
     * @param filePath
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    private static String endBot() {
        assert !done;
        done = true;
        return "Bye for now!";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output;
        if (input.equals("bye")) {
            output = endBot();
        } else {
            try {
                output = tasks.action(input);
                storage.saveTasks(tasks.output());
            } catch (DukeException e) {
                output = e.toString();
            }
        }

        return output;
    }
}
