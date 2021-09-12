package katheryne;

import java.util.Locale;

import katheryne.command.Command;
import katheryne.command.DeadlineCommand;
import katheryne.command.DeleteCommand;
import katheryne.command.DoneCommand;
import katheryne.command.EventCommand;
import katheryne.command.ExitCommand;
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
        String[] inputArr = input.split(" ", 2);
        String commandWord = inputArr[0].toUpperCase(Locale.ROOT);
        String[] processedRemainingText = Parser.parseRemainingText(commandWord, input);
        return Command.initialiseCommand(commandWord, processedRemainingText);
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
        String[] processedRemainingText = input.split(" ", 2);
        try {
            // Process as needed per command, throw errors if command format is wrong
            switch (commandWord) {
            case ExitCommand.COMMAND:
            case ListCommand.COMMAND:
                return new String[0];
                // No break, as returning early will break the switch statement
            case DoneCommand.COMMAND:
            case DeleteCommand.COMMAND:
                processedRemainingText = new String[]{input.split(" ", 2)[1]};
                if (processedRemainingText[0].split(" ").length > 1) {
                    throw new KatheryneException("Just tell me the command and what index, nothing else.");
                }
                break;
            case TodoCommand.COMMAND:
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
                processedRemainingText = input.split(" ", 2)[1].split("/by");
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
