package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.DukeDoneIncorrectArgument;
import duke.exception.DukeFindIncorrectArgument;
import duke.exception.DukeIncorrectCommandWord;
import duke.exception.DukeIncorrectInputs;

import java.io.IOException;

/**
 * Represents the Parser that parses all commands from user.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Parser {

    /**
     * Represents the action command keyed in by the user.
     */
    public enum Action {
        LIST("list"),
        BYE("bye"),
        DONE("done"),
        FIND("find"),
        TASK("task"),
        DELETE("delete"),
        SORT("sort"),
        NOT_FOUND("not found");

        private final String actionCommand;

        private Action(String actionCommmand) {
            this.actionCommand = actionCommmand;
        }

        /**
         * Returns the Action type ENUM for each string
         * @param s action word typed in by user
         * @return Action that corresponds to the user's entry
         */
        public static Action getAction(String s) {
            for (Action a : values()) {
                if (a.actionCommand.equals(s)) {
                    return a;
                }
            }
            return NOT_FOUND;
        }
    }

    /**
     * Interprets the full command entered in by the user and
     * routes the command accordingly.
     * @param userInput full command inputted by the user.
     * @param d Duke chatbot that is in use.
     * @return Command action that is interpreted from the user input.
     * @throws IOException if there is an error in reading the file.
     * @throws DukeException if there is an error from Duke's internal system.
     */
    public static Command parse(String userInput, Duke d) throws DukeException {
        Action actionCommand = Parser.determineActionType(userInput);
        Command c = getCommand(actionCommand, d, userInput);

        return c;
    }

    private static String getFirstWord(String s) {
        String[] arrString = s.split(" ", 2);
        return arrString[0];
    }

    private static int getSecondNum(String s) throws DukeIncorrectInputs {
        String[] arrString = s.split(" ", 2);
        try {
            String second = arrString[1];
            Integer.parseInt(second);
        } catch (IllegalArgumentException e) {
            throw new DukeDoneIncorrectArgument();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeDoneIncorrectArgument();
        }
        return Integer.parseInt(arrString[1]);
    }
    
    private static String getKeyword(String s) throws DukeIncorrectInputs {
        String[] arrString = s.split(" ", 2);
        try {
            String second = arrString[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeFindIncorrectArgument();
        }
        return arrString[1];
    }

    private static Action determineActionType(String input) {
        String firstWord = Parser.getFirstWord(input);
        String type = firstWord.equals("todo") || firstWord.equals("event") || firstWord.equals("deadline")
                ? "task" : firstWord;
        Action actionCommand = Action.getAction(type);

        return actionCommand;
    }

    private static Command getCommand(Action actionCommand, Duke d, String userInput) {
        Command c;
        switch (actionCommand) {
        case BYE:
            c = new ByeCommand(d);
            break;
        case LIST:
            c = new ListCommand(d);
            break;
        case DONE:
            c = new DoneCommand(d, Parser.getSecondNum(userInput));
            break;
        case DELETE:
            c = new DeleteCommand(d, Parser.getSecondNum(userInput));
            break;
        case FIND:
            c = new FindCommand(d, Parser.getKeyword(userInput));
            break;
        case TASK:
            c = new TaskCommand(d, userInput);
            break;
        case SORT:
            c = new SortCommand(d);
            break;
        default:
            assert false: "Command not found";
            throw new DukeIncorrectCommandWord(new IllegalArgumentException());
        }

        return c;
    }
}
