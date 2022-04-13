package duke;

import command.Command;

public class Duke {

    private final TaskList tasklist;
    private final Storage store;
    private final Ui ui;
    private boolean isRunning = true;

    /**
     * Constructs a new Duke instance.
     */
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
     * Executes the instruction given by user.
     *
     * @param input User input.
     * @return String response of the instruction.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Command.createCommand(input);
            String response = cmd.execute(this.tasklist, this.ui, this.store, this);
            return ui.addPadding(response);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
