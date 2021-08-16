package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import duke.command.CommandManager;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeException;

/**
 * Main class for the bot.
 */
public class Duke {
    private static final DukeList LIST = new DukeList();
    private static final CommandManager COMMAND_MANAGER = new CommandManager();

    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings = "Hello from\n" + logo + "\nWhat can I do for you?";
        new Response(greetings).print();

        // Duke commands work with a registry so that add-ons can be developed with
        // commands simply registered like so
        COMMAND_MANAGER.registerCommands(new ListCommand(), new DoneCommand(), new ToDoCommand(), new EventCommand(),
                new DeadlineCommand(), new DeleteCommand());

        echoInput(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void echoInput(BufferedReader reader) {
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (input.equals("bye")) {
            terminate();
        } else {
            try {
                COMMAND_MANAGER.processAndExecuteInput(input);
            } catch (DukeException ex) {
                ex.getResponse().print();
            }

            echoInput(reader);
        }
    }

    private static void terminate() {
        new Response("Bye. Hope to see you again soon!").print();
    }

    /**
     * Return the static DukeList of the program.
     * 
     * @return program's list
     */
    public static DukeList getList() {
        return LIST;
    }
}
