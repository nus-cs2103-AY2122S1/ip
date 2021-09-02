package duke;

import java.util.Scanner;

/**
 * The Bhutu chatbot app
 */
public class Duke {
    TaskList taskList;
    Storage storage;

    public Duke() {
        try {
            this.taskList = new TaskList();
            this.storage = Storage.initStorage("data/", "data/duke.txt");
            storage.readFile(taskList);
        }
        catch(DukeException e) {
            Ui.showError(e.getMessage());
        }

    }

    public String getResponse(String input) {
        try {
            //Echo
            String response = Parser.parser(input, taskList);
            storage.saveToFile(taskList);
            return response;
        }
        catch (DukeException e) {
            return Ui.showError(e.getMessage());
        }
    }

}

