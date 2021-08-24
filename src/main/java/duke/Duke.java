package duke;
import duke.exceptions.DukeException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    //private Ui ui;

    public Duke(File file) {
        //this.ui = new Ui();
        this.storage = new Storage(file);
        try {
            this.taskList = new TaskList();
        } catch (Exception e) {

        }
    }

    public void run() throws DukeException {
        try {
            File dukeFile = new File("data/duke.txt");
            PrintWriter writer  = this.storage.load(dukeFile);

            Ui.showWelcomeMessage();

            Parser.parseCommand(taskList, dukeFile, writer);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        Files.createDirectories(Paths.get("data/"));
        File dukeFile = new File("data/duke.txt");
        new Duke(dukeFile).run();

    }
}

