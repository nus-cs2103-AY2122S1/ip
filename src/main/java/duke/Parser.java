package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;
import duke.command.SaveCommand;


/**
 * Class responsible for parsing and understanding user input.
 */
public abstract class Parser {
    private static void checkDescription(String[] in) throws DukeException {
        if (in.length == 1) {
            throw new DukeException("Command has empty description");
        }
    }

    //needed to rebuild label in case it contained spaces and was split
    private static String generateLabel(String[] words) {
        String[] secondOnwards = Arrays.copyOfRange(words, 1, words.length);
        return String.join(" ", secondOnwards);
    }

    //valid command will have the form
    //"deadline {label} /by YYYY-mm-dd" OR "event {label} /at YYYY-mm-dd"
    //function returns String[] array [label, date]
    private static String[] processLabelAndDate(String[] words) throws DukeException {
        String originalInput = String.join(" ", words);
        String[] temp = originalInput.split("/", 2);

        for(int i = 0; i < temp.length; i++) {
            System.out.println(temp[i]);
        }

        String[] typeAndLabel = temp[0].split(" ");
        String[] prefixAndDate = temp[1].split(" ");

        checkDescription(typeAndLabel);
        checkDescription(prefixAndDate);

        String label = generateLabel(typeAndLabel);
        String date = prefixAndDate[1];

        return new String[]{label, date};
    }

    /**
     * Takes in a String representing user input, and parses it, producing the appropriate Command.
     * @param in is a String representing user input.
     * @return Command to be executed by the application.
     * @throws DukeException if the user's input is missing a description.
     * @throws IndexOutOfBoundsException if the user's command gives a index that is out of bounds.
     * @throws NumberFormatException if the user's command has an index that the application does not understand.
     * @throws DateTimeParseException if the user's command has a date that does not match "yyyy-mm-dd" format.
     */
    public static Command parse(String in) throws DukeException, IndexOutOfBoundsException,
            NumberFormatException, DateTimeParseException {

        String[] words = in.split(" ");

        //control block for single word commands
        switch(words[0]) {
        case "list":
            return new ListCommand();

        case "done":
            return new MarkDoneCommand(Integer.parseInt(words[1]) - 1);

        case "save":
            return new SaveCommand();

        default:
            //fallthrough is intentional
        }

        //control block for multi word commands
        //function call is a guard block. Command should have more than one word to reach this point.
        checkDescription(words); //I found the problem! checkDescription throws an error but the error
        //is not being printed to the user. So invalid commands like "asdfasd" wasnt caught, but
        //invalid commands like "asdfas asdfasfds" was caught.
        System.out.println("stack trace 1");
        switch(words[0]) {
        case "find":
            return new FindCommand(generateLabel(words));

        case "delete":
            return new DeleteCommand(Integer.parseInt(words[1]) - 1);

        case "todo":
            return new AddCommand("todo", generateLabel(words));

        case "deadline":
            String[] deadlineLabelAndDate = processLabelAndDate(words);
            String deadlineLabel = deadlineLabelAndDate[0];
            LocalDate deadlineDate = LocalDate.parse(deadlineLabelAndDate[1]);
            return new AddCommand("deadline", deadlineLabel, deadlineDate);

        case "event":
            String[] eventLabelAndDate = processLabelAndDate(words);
            String eventLabel = eventLabelAndDate[0];
            LocalDate eventDate = LocalDate.parse(eventLabelAndDate[1]);
            return new AddCommand("event", eventLabel, eventDate);

        default:
            return new InvalidCommand();
        }
    }
}

