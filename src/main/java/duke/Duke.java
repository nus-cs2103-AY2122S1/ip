package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duke {

    /** Stores a list of tasks */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /** Enum type to differentiate and parse user inputs */
    public enum Command {
        BYE       ("Usage: bye"),
        LIST      ("Usage: list"),
        DONE      ("Usage: done [task number]"),
        DELETE    ("Usage: delete [task number]"),
        TODO      ("Usage: todo [description]"),
        DEADLINE  ("Usage: deadline [description] /by [date in YYYY-MM-DD format]"),
        EVENT     ("Usage: event [description] /at [date in YYYY-MM-DD format]");

        public static Command[] taskCommands = {TODO, DEADLINE, EVENT};

        /** Help text for the command */
        private final String help_text;

        Command(String str) {
            this.help_text = str;
        }
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = TaskList.emptyTaskList();
        }
    }

    /**
     *
     * @param input String that is the user's input directly from scanner
     * @return Command that is activated by valid user input
     * @throws DukeException when user input is invalid
     */
    private Command validate(String input) throws DukeException {
        // Separate user input to get command
        String[] input_split = input.split(" ", 2);

        // Throw error when there user keys in nothing
        int key_phrases = input_split.length;
        if (key_phrases < 1) {
            throw new DukeException("You better type sumthin or else..");
        }

        // Check for command to activate in user input's first word
        switch (input_split[0]) {
            case "bye": {
                // Check if there is a key phrase after, if true throw error to double check with user
                if (key_phrases > 1) {
                    throw new DukeException("Bye or nah?\n\t" + Command.BYE.help_text);
                }
                return Command.BYE;
            }
            case "list": {
                // Check if there is a key phrase after, if true throw error to double check with user
                if (key_phrases > 1) {
                    throw new DukeException("You want your list or nah?\n\t" + Command.LIST.help_text);
                }
                return Command.LIST;
            }
            case "done": {
                // Make sure there is key phrase after to determine task to mark as done
                if (key_phrases == 1) {
                    throw new DukeException("Done what brah??\n\t" + Command.DONE.help_text);
                }
                // Makes sure that key phrase is a valid int and within range of task list
                try {
                    int index = Integer.parseInt(input_split[1]) - 1;
                    tasks.getTask(index);
                    return Command.DONE;
                }
                catch (NumberFormatException e) {
                    throw new DukeException("Not a number!\n\t" + Command.DONE.help_text);
                }
                catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Task number does not exist!\n\t" + Command.DONE.help_text);
                }
            }
            case "delete": {
                // Make sure there is key phrase after to determine task to mark as done
                if (key_phrases == 1) {
                    throw new DukeException("Delete what brah??\n\t" + Command.DELETE.help_text);
                }
                // Makes sure that key phrase is a valid int and within range of task list
                try {
                    int index = Integer.parseInt(input_split[1]) - 1;
                    tasks.getTask(index);
                    return Command.DELETE;
                }
                catch (NumberFormatException e) {
                    throw new DukeException("Not a number!\n\t" + Command.DELETE.help_text);
                }
                catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Task number does not exist!\n\t" + Command.DELETE.help_text);
                }
            }
            case "todo": {
                // Make sure there is key phrase after to record as task
                if (key_phrases == 1) {
                    throw new DukeException("Todo what brah??\n\t" + Command.TODO.help_text);
                }
                return Command.TODO;
            }
            case "deadline": {
                // Make sure there is key phrase after to record as task
                if (key_phrases == 1) {
                    throw new DukeException("Deadline for what brah??\n\t" + Command.DEADLINE.help_text);
                }
                // Makes sure key phrase after contains /by descriptor to record datetime
                String[] desc_date = input_split[1].split(" /by ", 2);
                if (desc_date.length != 2) {
                    throw new DukeException("Deadline when brah??\n\t" + Command.DEADLINE.help_text);
                }
                // Make sure date format is correct
                try {
                    LocalDate.parse(desc_date[1]);
                }
                catch (DateTimeParseException e) {
                    throw new DukeException("Follow the gahdam format!!\n\t" + Command.DEADLINE.help_text);
                }
                return Command.DEADLINE;
            }
            case "event": {
                // Make sure there is key phrase after to record as task
                if (key_phrases == 1) {
                    throw new DukeException("What event brah??\n\t" + Command.EVENT.help_text);
                }
                // Makes sure key phrase after contains /at descriptor to record datetime
                String[] desc_date = input_split[1].split(" /at ", 2);
                if (desc_date.length != 2) {
                    throw new DukeException("Event when brah??\n\t" + Command.EVENT.help_text);
                }
                // Make sure date format is correct
                try {
                    LocalDate.parse(desc_date[1]);
                }
                catch (DateTimeParseException e) {
                    throw new DukeException("Follow the gahdam format!!\n\t" + Command.EVENT.help_text);
                }
                return Command.EVENT;
            }
            default: {
                // If invalid command, show all help text to user
                String all_help_text = "";
                for (Command c : Command.values()) {
                    all_help_text = all_help_text + "\t" + c.help_text + "\n";
                }
                throw new DukeException("What you sayin brah! You better start makin sense or else...\n" +
                        "*Speak in words Pepper Jack can understand*\n" + all_help_text);
            }
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                duke.Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showLoadingError(e.getMessage());
            }
        }
        try {
            storage.save(tasks);
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
