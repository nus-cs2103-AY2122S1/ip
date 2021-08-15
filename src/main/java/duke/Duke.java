package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import duke.command.CommandManager;
import duke.command.ListCommand;

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

        COMMAND_MANAGER.registerCommand("list", new ListCommand(LIST));

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
            COMMAND_MANAGER.processAndExecuteInput(input);
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
