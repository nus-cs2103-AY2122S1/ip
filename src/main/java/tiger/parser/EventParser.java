package tiger.parser;

import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.utils.CustomDate;
import tiger.utils.DateStringConverter;
import tiger.utils.StringUtils;

import java.util.Arrays;
import java.util.List;

public class EventParser extends Parser {

    private String todo = "";
    private CustomDate date;
    private String dateString = "";

    /**
     * The {@code EventParser} parser class takes in an input String and
     * parses it, so that the {@code EventAction} class can access the
     * class fields and understand user input.
     *
     * @param  input String to be parsed.
     * @throws TigerEmptyStringException If input is invalid.
     */

    public EventParser(String input) {
        super(input);
    }

    public void parse() throws TigerEmptyStringException {
        StringUtils removeSpaces = new StringUtils();
        List<String> array =
                Arrays.asList(removeSpaces.removeBackAndFrontSpaces(this.input).split(" "));
        boolean atFound = false;
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i).equals("/at")) {
                atFound = true;
                continue;
            }
            if (!atFound) {
                this.todo += (array.get(i) + " ");
            } else {
                this.dateString += (array.get(i) + " ");
            }
        }
        try {
            this.todo = removeSpaces.removeLastSpaces(this.todo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Event description");
        }
        try {
            this.dateString = removeSpaces.removeLastSpaces(this.dateString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Event date");
        }
        this.date = new DateStringConverter().getDateFromString(dateString);
    }

    public String getTodo() {
        return this.todo;
    }

    public CustomDate getDate() {
        return this.date;
    }
}