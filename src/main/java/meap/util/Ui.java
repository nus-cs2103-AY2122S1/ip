package meap.util;

import meap.exception.DukeException;
import meap.task.Deadline;
import meap.task.Event;
import meap.task.ToDo;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Class wrapping all User Interface related functions and details
 */
public class Ui {
    public static final String INDENT = "    ";
    public static final String LOGO =
                " ___  ___ _____  ___  ______ \n" +
                " |  \\/  ||  ___|/ _ \\ | ___ \\\n" +
                " | .  . || |__ / /_\\ \\| |_/ /\n" +
                " | |\\/| ||  __||  _  ||  __/ \n" +
                " | |  | || |___| | | || |    \n" +
                " \\_|  |_/\\____/\\_| |_/\\_|    \n";
//                "  __  __ ___   _   ___ \n" +
//                " |  \\/  | __| /_\\ | _ \\\n" +
//                " | |\\/| | _| / _ \\|  _/\n" +
//                " |_|  |_|___/_/ \\_\\_|  \n\n";
////                " ------------------------\n";


    public static final String WELCOME_STATEMENT = "Certified Personal Task Manager\n";
    public static final String CLOSING_STATEMENT = "Bye, hope to see you again :)\n" +
            INDENT + "shutting down...";
    public static final String[] USER_SUPPORTED_COMMANDS =
            {"todo", "deadline", "event", "list", "find", "done", "delete", "bye", "help"};
    public static final String LIST_COMMAND_REGEX = "list";
    public static final String BYE_COMMAND_REGEX = "bye";
    public static final String DONE_COMMAND_REGEX = "done [0-9]{1,2}"; //done <num> //limits to 99
    public static final String DELETE_COMMAND_REGEX = "delete [0-9]{1,2}"; //delete <num
    public static final String FIND_COMMAND_REGEX = "find \\w+"; //find <word>
    public static final String HELP_COMMAND_REGEX = "help"; //find <word>

    /**
     * Returns command syntax of command keyword parsed in
     * @param keyword command keyword
     * @return command syntax as a String
     */
    public static String commandSyntax(String keyword) {
        switch(keyword) {
            case "todo":
                return ToDo.COMMAND_SYNTAX;
            case "event":
                return Event.COMMAND_SYNTAX;
            case "deadline":
                return Deadline.COMMAND_SYNTAX + "\n" +
                        String.format(" (eg.)'%s'", Deadline.COMMAND_EXAMPLE_CALL);
            case "list":
                return "list";
            case "done":
                return "done <task index>";
            case "delete":
                return "delete <task index>";
            case "find":
                return "find <keyword>";
            case "bye":
                return "bye";
            case "help":
                return "help";
            default:
                throw DukeException.of(keyword, "keyword not identified");
        }
    }

    /**
     * All commands syntax of user commands
     * @return String of all UserCommandsSyntax prepended by '-'
     */
    public static String allUserCommandsSyntax() {
        return Arrays.asList(USER_SUPPORTED_COMMANDS)
                .stream()
                .map(command -> "- " + commandSyntax(command) + "\n")
                .collect(Collectors.joining());
    }
}
