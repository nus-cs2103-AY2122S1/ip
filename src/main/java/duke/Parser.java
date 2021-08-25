package duke;

import duke.commands.Command;
import duke.commands.AddCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.UnknownCommand;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
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
                throw new InvalidInputException("   Please specify the TASK NUMBER after typing 'done'");
            }
            break;
        case "todo": case "deadline": case "event":
            c = new AddCommand(firstWord, remainingWords);
            break;
        case "delete":
            try {
                int deleteIndex = Integer.parseInt(remainingWords);
                c = new DeleteCommand(deleteIndex);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("   Please specify the TASK NUMBER after typing 'delete'");
            }
            break;
        default:
            c = new UnknownCommand();
            break;
        }
        return c;
    }

    public static String[] parseDeadline(String deadlineDescription) {
        String[] arr = deadlineDescription.split("/by", 2);
        arr[0] = arr[0].trim();
        arr[1] = parseDate(arr[1].trim());
        return arr;
    }

    public static String[] parseEvent(String eventDescription) {
        String[] arr = eventDescription.split("/at", 2);
        arr[0] = arr[0].trim();
        arr[1] = parseDate(arr[1].trim());
        return arr;
    }

    public static String parseDate(String inputDate) {
        String output;
        String[] dayTimeArr = inputDate.split(" ");
        LocalDate date = LocalDate.parse(dayTimeArr[0]);
        if (dayTimeArr.length == 2) { // if there is a time to it
            output = date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + dayTimeArr[1];
        } else { // just the date
            output = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return output;
    }

    public static String getFirstWord(String input) {
        String[] arr = input.split(" ", 2);
        return arr[0];
    }

    public static String getRestOfWords(String input) {
        String[] arr = input.split(" ", 2);
        return arr[1];
    }
}
