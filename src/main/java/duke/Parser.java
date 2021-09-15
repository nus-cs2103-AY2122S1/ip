package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.command.CommandAddContact;
import duke.command.CommandDelContact;
import duke.command.Command;
import duke.command.CommandCheck;
import duke.command.CommandContacts;
import duke.command.CommandDeadline;
import duke.command.CommandDelete;
import duke.command.CommandDone;
import duke.command.CommandEvent;
import duke.command.CommandFind;
import duke.command.CommandHelp;
import duke.command.CommandList;
import duke.command.CommandTodo;

/**
 * Parser is the class that handles the parsing of strings.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class Parser {
    private static final String[] TASK_TYPES = {"T", "D", "E"};
    private static ArrayList<String> storedInput;

    public Parser() {
    }

    /**
     * Takes in a string and checks if it corresponds to a task type.
     * If it does, return its index number in the TASK_TYPES array.
     * If it does not, return -1.
     *
     * @param s String to be checked.
     * @return Integer representing corresponding task type.
     */
    public static int getTaskID(String s) {
        for (int i = 0; i < TASK_TYPES.length; i++) {
            if (s.equals(TASK_TYPES[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Takes the user input and returns a valid command if it has been typed.
     *
     * @param userInput String representing input typed in by user.
     * @return A command responding to the user input.
     */
    public static Command parseCommand(String userInput) {
        if (userInput == null || userInput.isEmpty() || userInput.trim().isEmpty()) {
            return new CommandHelp();
        }
        Scanner scanner = new Scanner(userInput);
        String keyword = scanner.next();
        ArrayList<String> arguments = new ArrayList<>();

        while (scanner.hasNext()) {
            arguments.add(scanner.next());
        }

        switch (keyword) {
        case (CommandList.KEYWORD):
            return new CommandList();
        case (CommandDone.KEYWORD):
            return new CommandDone(arguments);
        case (CommandTodo.KEYWORD):
            return new CommandTodo(arguments);
        case (CommandDeadline.KEYWORD):
            return new CommandDeadline(arguments);
        case (CommandEvent.KEYWORD):
            return new CommandEvent(arguments);
        case (CommandDelete.KEYWORD):
            return new CommandDelete(arguments);
        case (CommandCheck.KEYWORD):
            return new CommandCheck(arguments);
        case (CommandFind.KEYWORD):
            return new CommandFind(arguments);
        case (CommandContacts.KEYWORD):
            return new CommandContacts();
        case (CommandAddContact.KEYWORD):
            return new CommandAddContact(arguments);
        case (CommandDelContact.KEYWORD):
            return new CommandDelContact(arguments);
        default:
            return new CommandHelp();

        }

    }



}
