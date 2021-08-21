package tiger.parser;

import tiger.exceptions.TigerEmptyStringException;
import tiger.utils.RemoveLastSpaces;

import java.util.Arrays;
import java.util.List;

public class EventParser extends Parser {

    public String todo = "";
    public String eventAt = "";

    public EventParser(String input) throws TigerEmptyStringException {
        super(input);
        List<String> array = Arrays.asList(input.split(" "));
        assert (array.contains("/at"));
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
        RemoveLastSpaces removeLastSpaces = new RemoveLastSpaces();
        try {
            this.todo = removeLastSpaces.removeLastSpaces(this.todo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Event description");
        }
        try {
            this.eventAt = removeLastSpaces.removeLastSpaces(this.eventAt);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Event date");
        }

    }
}
