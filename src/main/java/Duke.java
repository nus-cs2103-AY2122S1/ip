import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Duke {

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private UserInterface ui;
    private DateTimeFormatter dtformatter;
    private Storage storage;
    private TaskList taskList;

    public Duke() {
        ui = new UserInterface();
        dtformatter = DateTimeFormatter.ISO_DATE;
        String home = System.getProperty("user.home");
        Path dukePath = Paths.get(home, "Documents", "duke", "data.csv");
        storage = new Storage(dukePath);
        taskList = storage.load(ui);
    }

    public void run() {
        ui.displayGreeting();
        Command command;
        do {
            command = Parser.parse(ui.getResponse());
        } while(command.shouldExecuteNext());
        storage.save(ui, taskList);
    }
}
