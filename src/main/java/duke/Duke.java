package duke;

import duke.command.Command;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    private static String PATH;

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
        storage = new Storage(filePath);
    }


    public static void main(String[] args) {
        PATH = System.getProperty("user.dir") + "\\data\\save.txt";
        new Duke(PATH).run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            ui.showLine();
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            ui.showLine();
        }
    }
}
