package tiger.parser;

import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.utils.RemoveSpaces;

import java.util.Arrays;
import java.util.List;

public class EventParser extends Parser {

    public String todo = "";
    public String eventAt = "";

    /**
     * The {@code EventParser} parser class takes in an input String and
     * parses it, so that the {@code EventAction} class can access the
     * class fields and understand user input.
     *
     * @param  input String to be parsed.
     * @throws TigerEmptyStringException If input is invalid.
     */

    public EventParser(String input) throws TigerEmptyStringException {
        super(input);
        RemoveSpaces removeSpaces = new RemoveSpaces();
        List<String> array =
                Arrays.asList(removeSpaces.removeBackAndFrontSpaces(input).split(" "));
        boolean atFound = false;
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i).equals("/at")) {
                atFound = true;
                continue;
            }
            if (!atFound) {
                this.todo += (array.get(i) + " ");
            } else {
                this.eventAt += (array.get(i) + " ");
            }
        }
        try {
            this.todo = removeSpaces.removeLastSpaces(this.todo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Event description");
        }
        try {
            this.eventAt = removeSpaces.removeLastSpaces(this.eventAt);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Event date");
        }

    }
}
