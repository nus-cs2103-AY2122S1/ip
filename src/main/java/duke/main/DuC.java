package duke.main;

import java.io.File;

import duke.exceptions.DucException;
public class DuC {

    private final TaskList taskList;
    private final File file;

    /**
     * Constructor for DuC - initializes task list from
     * preprocessed file and initializing UI component
     */
    public DuC(String fileName) {
        this.taskList = new TaskList();
        this.file = new File(fileName);
        Storage.loadData(file, taskList);
    }

    /**
     * Constructs a respond for any user input coming in
     * @param input user input on DuC
     * @return response and action being done
     */
    public String respondWith(String input) {
        String response;
        try {
            response = Parser.parse(input, taskList).reply();
        } catch (DucException e) {
            response = e.getMessage();
        } finally {
            Storage.saveData(file, taskList);
        }
        assert (response.length() > 0) : "No response has been made";
        return response;
    }
}
