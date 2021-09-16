package katheryne;

import java.util.Locale;

import katheryne.command.Command;
import katheryne.command.DeadlineCommand;
import katheryne.command.DeleteCommand;
import katheryne.command.DoneCommand;
import katheryne.command.EventCommand;
import katheryne.command.ExitCommand;
import katheryne.command.FindCommand;
import katheryne.command.ListCommand;
import katheryne.command.TodoCommand;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Main functionality: parses user input and returns an appropriate command which can be executed.
     *
     * @param input User input given.
     * @return A command based on the input given.
     * @throws KatheryneException An exception thrown due to wrong user input.
     */
    public static Command parse(String input) throws KatheryneException {
        String[] commandWordSplitOut = splitCommandWordOut(input);
        String commandWord = commandWordSplitOut[0];
        String[] processedRemainingText = Parser.parseRemainingText(commandWord, input);
        return Command.initialiseCommand(commandWord, processedRemainingText);
    }

    /**
     * Returns a string array where the first item is the command word
     * 
     * @param input
     * @return
     */
    private static String[] splitCommandWordOut(String input) {
        String[] inputArr = input.split(" ", 2);
        inputArr[0] = inputArr[0].toUpperCase(Locale.ROOT);
        return inputArr;
    }

    /**
     * Processes the text after the commandWord according to what the command word is.
     *
     * @param commandWord
     * @param input remaining text
     * @return A string array, with the number of elements depending on the command word requirements
     * @throws KatheryneException if it is missing parameters for certain command words, or if it does not
     * match the correct commands
     */
    private static String[] parseRemainingText(String commandWord, String input) throws KatheryneException {
        // Separate out the command word, and initialise processedRemainingText
        String[] processedRemainingText = splitCommandWordOut(input);
        try {
            // Process as needed per command, throw errors if command format is wrong
            switch (commandWord) {
            case ExitCommand.COMMAND: case ListCommand.COMMAND:
                return new String[0];
            case DoneCommand.COMMAND: case DeleteCommand.COMMAND:
                if (processedRemainingText.length == 1 || processedRemainingText[1].split(" ").length > 1) {
                    throw new KatheryneException("Just tell me the command and what index, nothing else.");
                }
                processedRemainingText = new String[]{input.split(" ", 2)[1]};
                break;
            case TodoCommand.COMMAND: case FindCommand.COMMAND:
                processedRemainingText = new String[]{ processedRemainingText[1] };
                break;
            case EventCommand.COMMAND:
                processedRemainingText = processedRemainingText[1].split("/at");
                if (processedRemainingText.length != 2) {
                    throw new KatheryneException(
                            "An event needs a description and a single /at time when it occurs in the format "
                                    + "2007-12-03");
                }
                break;
            case DeadlineCommand.COMMAND:
                processedRemainingText = processedRemainingText[1].split("/by");
                if (processedRemainingText.length != 2) {
                    throw new KatheryneException(
                            "A deadline needs a description and a /by time in the format 2007-12-03.");
                }
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // When there is no second parameter, but there was expected to be one.
            throw new UnknownCommandException();
        }

        return cleanParameters(processedRemainingText);
    }

    /**
     * Cleans a string array by removing trailing and beginning white spaces, and
     * throws an error if empty strings are found within the array
     *
     * @param arr an array of parameters in string format which may have white spaces
     * @return an array of parameters in string format
     * @throws KatheryneException if one of the parameters is empty after cleaning
     */
    public static String[] cleanParameters(String[] arr) throws KatheryneException {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
            if (arr[i].isBlank()) {
                throw new KatheryneException(
                        "I can't do that, I'm missing some information. Try saying that again?");
            }
        }
        return arr;
    }
}
