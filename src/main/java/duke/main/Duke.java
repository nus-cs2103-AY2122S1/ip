package duke.main;

import java.io.File;

import duke.exceptions.DukeException;
public class Duke {

    private final TaskList taskList;
    private final File file;

    /**
     * Constructor for Duke - initializing task list from
     * preprocessed file and initializing UI component
     */
    public Duke(String fileName) {
        this.taskList = new TaskList();
        this.file = new File(fileName);
        Storage.loadData(file, taskList);
    }

    public String reply(String input) {
        String response;
        try {
            response = Parser.parse(input, taskList).reply();
        } catch (DukeException e) {
            response = e.getMessage();
        } finally {
            Storage.saveData(file, taskList);
        }
        return response;
    }
}
