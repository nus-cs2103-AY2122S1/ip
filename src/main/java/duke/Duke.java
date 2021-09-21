package duke;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;

import duke.commands.Command;

import duke.tasks.TaskList;

import duke.utils.Parser;
import duke.utils.Storage;

public class Duke {
    private final String FILE_PATH = "./data/duke.txt";

    private Storage storage;
    private TaskList taskList;

    public Duke() {
        this.storage = new Storage(FILE_PATH);
        this.taskList = new TaskList(this.storage.load());
    }

    /**
     * Return appropriate response as a String according to given input
     *
     * @param input user input
     * @return the appropriate response
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String message = command.execute(this.taskList, this.storage);
            this.storage.save(this.taskList.getFormattedData());
            return message;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
