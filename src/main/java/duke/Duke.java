package duke;

import duke.command.Command;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage("./Duke.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null;
            return c.execute(taskList, storage, ui);
        } catch (DukeException e) {
            return ui.getError(e.getMessage());
        }
    }
}
