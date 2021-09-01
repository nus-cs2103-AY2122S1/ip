package duke;

import command.Command;

public class Duke {

    private final TaskList tasklist;
    private final Storage store;
    private final Ui ui;
    private boolean isRunning = true;

    public Duke() {
        store = new Storage("./data/duke.txt");
        tasklist = new TaskList();
        this.store.retrieveTasks(tasklist);
        this.ui = new Ui();
    }

    private void run() {
        while (isRunning) {
            String input = ui.getInput();
            try {
                Command cmd = Command.createCommand(input);
                cmd.execute(this.tasklist, this.ui, this.store, this);
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    public void close() {
        isRunning = false;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Command.createCommand(input);
            return cmd.execute(this.tasklist, this.ui, this.store, this);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
    }

}
