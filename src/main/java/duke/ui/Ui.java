package duke.ui;

import java.util.Scanner;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;

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
            String command = p.checkForKeyword(des);
            try {
                this.printLine();
                if (command == null) {
                    throw new DukeException(des + " is not a recognised command\n"
                            + "Please refer to the available commands using the \"allCmd\" command");
                } else {
                    Command c = null;
                    if (command.equals("bye")) {
                        c = new ByeCommand();
                        c.execute(des, tList);
                        printLine();
                        break;
                    }

                    if (command.equals("help")) {
                        c = new HelpCommand();
                    }

                    if (command.equals("list")) {
                        c = new ListCommand();
                    }

                    if (command.equals("done")) {
                        c = new DoneCommand();
                    }

                    if (command.equals("deadline")) {
                        c = new DeadlineCommand();
                    }

                    if (command.equals("event")) {
                        c = new EventCommand();
                    }

                    if (command.equals("todo")) {
                        c = new ToDoCommand();
                    }

                    if (command.equals("delete")) {
                        c = new DeleteCommand();
                    }

                    c.execute(des, tList);
                    this.printLine();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                this.printLine();
            }

        }
    }

    public static void printLine() {
        System.out.println("____________________________________________________"
                + "_________________________________________________________");
    }
}
