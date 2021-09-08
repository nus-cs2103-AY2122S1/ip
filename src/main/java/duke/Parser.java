package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.UnknownCommand;
import duke.exceptions.InvalidInputException;

/**
 * Class which deals with the parsing of user input
 */
public class Parser {

    /**
     * Parses the user input to return the different types of Command which will then be processed accordingly.
     *
     * @param fullCommand user input
     * @return the command based on the user input
     * @throws InvalidInputException if 'done' or 'delete' command is not preceded by a number
     */
    public static Command parse(String fullCommand) throws InvalidInputException {
        String firstWord;
        String remainingWords = "";
        if (fullCommand.contains(" ")) {
            firstWord = Parser.getFirstWord(fullCommand);
            remainingWords = Parser.getRestOfWords(fullCommand);
        } else {
            firstWord = fullCommand;
        }

        Command c;

        switch (firstWord) {
        case "find":
            c = new FindCommand(remainingWords);
            break;
        case "list":
            c = new ListCommand();
            break;
        case "bye":
            c = new ExitCommand();
            break;
        case "done":
            try {
                int doneIndex = Integer.parseInt(remainingWords);
                c = new DoneCommand(doneIndex);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Please specify the TASK NUMBER after typing 'done'");
            }
            break;
        case "todo": case "deadline": case "event": case "dowithin":
            c = new AddCommand(firstWord, remainingWords);
            break;
        case "delete":
            try {
                int deleteIndex = Integer.parseInt(remainingWords);
                c = new DeleteCommand(deleteIndex);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Please specify the TASK NUMBER after typing 'delete'");
            }
            break;
        default:
            c = new UnknownCommand();
            break;
        }
        return c;
    }

    /**
     * Parses the user input for 'doWithinPeriod' tasks for readability.
     *
     * @param doWithinDescription user input description and start/end date of the 'doWithinPeriod' task
     * @return array of 3 elements where 1st element = 'doWithin' description, 2nd/3rd element = start/end date
     *
     */
    public static String[] parseDoWithin(String doWithinDescription) {
        String[] descAndDates = doWithinDescription.split("/between");
        String[] startEndDate = descAndDates[1].split("and");
        String[] result = new String[]{descAndDates[0].trim(),
                parseDate(startEndDate[0].trim()), parseDate(startEndDate[1].trim())};
        return result;
    }

    /**
     * Parses the user input for 'deadline' for readability.
     *
     * @param deadlineDescription user input description and time of the 'deadline' task
     * @return array of 2 elements where 1st element = 'deadline' description, 2nd element = time
     */
    public static String[] parseDeadline(String deadlineDescription) {
        String[] arr = deadlineDescription.split("/by", 2);
        arr[0] = arr[0].trim();
        arr[1] = parseDate(arr[1].trim());
        return arr;
    }

    /**
     * Parses the user input for 'event' for readability.
     *
     * @param eventDescription user input description and time of the 'event' task
     * @return array of 2 elements where 1st element = 'event' description, 2nd element = time
     */
    public static String[] parseEvent(String eventDescription) {
        String[] arr = eventDescription.split("/at", 2);
        arr[0] = arr[0].trim();
        arr[1] = parseDate(arr[1].trim());
        return arr;
    }

    /**
     * Reads the input date in 'YYYY-MM-DD TIME' format and converts it to local date format.
     * If time is specified, it will be parsed as well. Otherwise, the output will not include the time.
     *
     * @param inputDate 'YYYY-MM-DD TIME' format i.e '2021-08-25'
     * @return String output in local date format i.e '2021-08-25' --> Aug 25 2021
     */
    public static String parseDate(String inputDate) {
        String output;
        String[] dayTimeArr = inputDate.split(" ");
        LocalDate date = LocalDate.parse(dayTimeArr[0]);

        if (dayTimeArr.length == 2) {
            output = date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + dayTimeArr[1];
        } else {
            output = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return output;
    }

    /** Helper function to get the first word of the input sentence */
    public static String getFirstWord(String input) {
        String[] arr = input.split(" ", 2);
        return arr[0];
    }

    /** Helper function to get the rest of input sentence, EXCLUDING the first word */
    public static String getRestOfWords(String input) {
        String[] arr = input.split(" ", 2);
        return arr[1];
    }
}
