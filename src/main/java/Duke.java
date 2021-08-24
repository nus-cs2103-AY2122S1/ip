import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import Exceptions.DukeException;
import Exceptions.EmptyDescriptionException;
import Exceptions.EmptyTimestampException;
import Exceptions.InvalidTaskNumberException;
import Exceptions.UnknownCommandException;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    public void run() {
        // TODO
        Parser parser = new Parser();
        this.ui.showWelcome();
        Command command;
        boolean isExited = false;
        do {
            try {
                command = parser.parseNext(taskList);
                command.execute(this.taskList, this.ui, this.storage);
                isExited = command.hasExited();
            } catch (DukeException e) {
                ui.printOut(e.getMessage());
            }
        }
        while (!isExited);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
