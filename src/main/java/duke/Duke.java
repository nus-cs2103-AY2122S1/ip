package duke;

import java.time.format.DateTimeFormatter;

import java.nio.file.Path;
import java.nio.file.Paths;

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
            command.execute(taskList, ui);
        } while(command.shouldExecuteNext());
        storage.save(ui, taskList);
    }
}
