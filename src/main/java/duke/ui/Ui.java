package duke.ui;

import java.util.Scanner;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Ui class used as handle interactions with the user.
 * Contains methods that
 * (i) runs the Duke chat-bot.
 */
public class Ui {

    /**
     * The run method runs the Duke chat-bot.
     * By taking in user keyboard input, it checks the input for keywords
     * and responds accordingly. Invalid input is caught and the custom
     * DukeException is thrown to inform the user that the command is invalid
     * or not properly formatted.
     */
    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today?");

        TaskList tList = new TaskList();

        Storage.readFromFile(tList);
        Scanner sc = new Scanner(System.in);

        Parser p = new Parser();

        while (true) {
            String des = sc.nextLine();
            String keyword = p.checkForKeyword(des);
            try {
                this.printLine();
                Command c;
                if (keyword == null) {
                    throw new DukeException(des + " is not a recognised command\n"
                            + "Please refer to the available commands using the \"help\" command");
                }
                switch (keyword) {
                case "bye":
                    c = new ByeCommand();
                    System.out.print(c.execute(des, tList));
                    printLine();
                    System.exit(0);
                    break;
                case "help":
                    c = new HelpCommand();
                    break;
                case "list":
                    c = new ListCommand();
                    break;
                case "done":
                    c = new DoneCommand();
                    break;
                case "delete":
                    c = new DeleteCommand();
                    break;
                case "deadline":
                    c = new DeadlineCommand();
                    break;
                case "event":
                    c = new EventCommand();
                    break;
                case "todo":
                    c = new ToDoCommand();
                    break;
                case "find":
                    c = new FindCommand();
                    break;
                default:
                    throw new DukeException("Error in parser. Ouput of parser not recognised.");
                }
                System.out.print(c.execute(des, tList));
                this.printLine();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                this.printLine();
            }

        }
    }

    /**
     * Prints a line for separation purposes.
     */
    public static void printLine() {
        System.out.println("____________________________________________________"
                + "_________________________________________________________");
    }
}
