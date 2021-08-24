package duke;


import duke.command.Command;
import duke.command.CommandBye;
import duke.command.CommandCheck;
import duke.command.CommandDeadline;
import duke.command.CommandDelete;
import duke.command.CommandDone;
import duke.command.CommandEvent;
import duke.command.CommandHelp;
import duke.command.CommandList;
import duke.command.CommandTodo;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private static final String[] TASK_TYPES = {"T", "D", "E"};
    private static ArrayList<String> storedInput;

    public Parser() {
    }

    public static int getTaskID(String s) {
        for (int i = 0; i < TASK_TYPES.length; i++) {
            if (s.equals(TASK_TYPES[i])) {
                return i;
            }
        }
        return -1;
    }

    public static Command parseCommand(String userInput) {
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
        case (CommandBye.KEYWORD):
            return new CommandBye();
        default:
            return new CommandHelp();

        }

    }



}
