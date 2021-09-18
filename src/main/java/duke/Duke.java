package duke;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.parser.Parser;
import duke.ui.Ui;

/**
 * <code>Duke</code> is the Overarching class for the Duke Bot.
 * Contains Task class and subclasses To-Do, Deadline and Event
 * Code for the bot behaviour itself is separated into 4 different classes:
 * Ui, TaskList, Storage and Parser
 */

public class Duke {

    public static void main(String[] args) {
        //initialise ui, parser and task list
        Ui ui = new Ui();
        Parser parser = new Parser(ui);
        TaskList tasks;

        //Attempt to load file
        String FILEPATH = "./duke.txt";
        Storage storage = new Storage(FILEPATH);

        tasks = new TaskList(storage.load());

        //run program
        ui.showHelp();

        while (true) {
            //read input, parse command, update list if needed, save to file.
            String input = ui.readInput("");
            parser.parseCommand(input, tasks);
            storage.save(tasks.getList());
        }
    }
}