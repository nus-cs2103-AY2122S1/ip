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
    private final DukeList list;
    private final CommandManager commandManager;

    private Duke() {
        this.list = new DukeList();
        this.commandManager = new CommandManager();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Return the static DukeList of the program.
     * 
     * @return program's list
     */
    public DukeList getList() {
        return this.list;
    }

    private void run() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings = "Hello from\n" + logo + "\nWhat can I do for you?";
        new Response(greetings).print();

        // Duke commands work with a registry so that add-ons can be developed with
        // commands simply registered like so
        this.commandManager.registerCommands(new ListCommand(this), new DoneCommand(this), new ToDoCommand(this),
                new EventCommand(this), new DeadlineCommand(this), new DeleteCommand(this));

        echoInput(new BufferedReader(new InputStreamReader(System.in)));
    }

    private void echoInput(BufferedReader reader) {
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
                this.commandManager.processAndExecuteInput(input);
            } catch (DukeException ex) {
                ex.getResponse().print();
            }

            echoInput(reader);
        }
    }

    private void terminate() {
        new Response("Bye. Hope to see you again soon!").print();
    }

}
