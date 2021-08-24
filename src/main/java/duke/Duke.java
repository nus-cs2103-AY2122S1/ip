package duke;

import duke.commands.Command;

/**
 * Encapsulates the main Duke class which contains the main function to run the chat bot
 */
public class Duke {
    private final UI ui;
    private final Storage storage;
    private final DateTimeHandler dth;
    private final TaskList taskList;
    private final Parser parser;

    public Duke() {
        ui = new UI();
        taskList = new TaskList();
        storage = new Storage();
        dth = new DateTimeHandler();
        parser = new Parser();
    }

    /**
     * Runs the duke chat bot
     */
    public void run() {
        try {
            storage.loadFile();
            storage.readFromFile(taskList);
        } catch (Exception e) {
            ui.print("The file could not be created");
        }
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            Command command = parser.parse(input);
            if (command == null) {
                ui.unrecognisedCommand();
                continue;
            }
            command.execute(taskList, storage, ui, dth);
            isExit = command.isExit();
            try {
                storage.writeToFile(taskList);
            } catch (Exception e) {
                ui.print("Write Error");
            }
        }
        ui.goodByeMessage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
