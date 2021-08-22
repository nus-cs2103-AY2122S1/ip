package tiger.parser;

import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidInputException;
import tiger.utils.RemoveSpaces;

import java.util.Arrays;
import java.util.List;

public class DeadLineParser extends Parser {

    public String todo = "";
    public String dateLine = "";

    // TODO: ban users from entering semicolons!

    /**
     * The {@code DeadlineParser} parser class takes in an input String and
     * parses it, so that the {@code DeadlineAction} class can access the
     * class fields and understand user input.
     *
     * @param  input String to be parsed.
     * @throws TigerInvalidInputException If input is invalid.
     */

    public DeadLineParser(String input) throws TigerInvalidInputException {
        super(input);
        RemoveSpaces removeSpaces = new RemoveSpaces();
        List<String> array =
                Arrays.asList(removeSpaces.removeBackAndFrontSpaces(input).split(" "));
        boolean byFound = false;
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i).equals("/by")) {
                byFound = true;
                continue;
            }
            if (!byFound) {
                this.todo += (array.get(i) + " ");
            } else {
                this.dateLine += (array.get(i) + " ");
            }
        }
        RemoveSpaces removeLastSpaces = new RemoveSpaces();
        try {
            this.todo = removeLastSpaces.removeLastSpaces(this.todo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Deadline description");
        }
        try {
            this.dateLine = removeLastSpaces.removeLastSpaces(this.dateLine);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Deadline date");
        }
    }
}
