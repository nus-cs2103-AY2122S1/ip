import java.util.Scanner;
import commands.Command;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

import exceptions.NoSuchCommandException;

public class Duke {
    private static final String GREETING_MESSAGE = 
    "Hello! I'm Duke" + "\n" + "What can I do for you?";

    private final ArrayList<Command> allTasks = new ArrayList<>();
    private final CommandConverter ComConvert;
    private final Storage storage;
    private final TasksHandler TasksHandler;
    private final tasksMarkingHandler TasksMarkingHandler;

    public Duke() {
        this.TasksHandler = new TasksHandler(allTasks);
        this.ComConvert = new CommandConverter();
        this.storage = new Storage();
        this.TasksMarkingHandler = new tasksMarkingHandler();
    }

    protected String dukeStart() {
        return GREETING_MESSAGE;
    }

    protected void runDukeBot(Scanner sc) {
        try {
            this.getStorageStoredData();
            this.getUserInput(sc);
        } catch (NoSuchCommandException | IOException e) {
            System.err.println(e);
        }
    }

    private void getUserInput(Scanner sc) throws NoSuchCommandException, IOException {
        while (sc.hasNextLine()) {
            String instructions = sc.nextLine();
            Command command_given = this.ComConvert.convertInputToCommand(instructions);
            TasksHandler.storeTasks(command_given);
            if (TasksHandler.handleTasks(command_given)) {
                break;
            } else {
                this.storage.updateDiskStorage(this.allTasks);
            }
        }
    }

    private void getStorageStoredData() throws FileNotFoundException, NoSuchCommandException {
        HashMap<String, Boolean> stringCommands = this.storage.retrieveDiskStorageReadableFormat();
        HashMap<Command, Boolean> convertedCommands = this.ComConvert.batchConvertStorageCommands(stringCommands);
        ArrayList<Command> finalStorageCommands = this.TasksMarkingHandler.markUp(convertedCommands);
        this.TasksHandler.setToAllTasksList(finalStorageCommands);
    }
}
