package tiger.parser;

import java.util.Arrays;
import java.util.List;

import tiger.constants.Priority;
import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidArgumentException;
import tiger.exceptions.inputs.TigerInvalidInputException;
import tiger.utils.CustomDate;
import tiger.utils.DateStringConverter;
import tiger.utils.StringUtils;

/**
 * The {@code EventParser} parser takes in an input String and parses it, so that the {@code EventAction} class
 * can access the class fields and understand user input.
 */

public class EventParser extends Parser {

    private String todo = "";
    private CustomDate date;
    private String dateString = "";
    private Priority priority = Priority.MEDIUM;
    private String input;

    /**
     * Constructor for the {@code EventParser} class.
     *
     * @param  input String to be parsed.
     * @throws TigerEmptyStringException If input is invalid.
     */

    public EventParser(String input) {
        this.input = input;
    }

    @Override
    public void parse() throws TigerInvalidInputException {
        StringUtils stringUtils = new StringUtils();
        String regex = "^(event|Event|EVENT)|(/at)|(/priority)";
        List<String> array =
                Arrays.asList(stringUtils.removeBackAndFrontSpaces(this.input).split(regex));
        // assert that the array is at size at least 3
        if (array.size() <= 1) {
            throw new TigerEmptyStringException("Event description");
        }
        if (array.size() <= 2) {
            throw new TigerEmptyStringException("Event date");
        }
        if (this.input.contains("/priority")) {
            // task priority
            if (array.size() < 4) {
                throw new TigerEmptyStringException("Event priority");
            }
            String p;
            try {
                p = stringUtils.removeBackAndFrontSpaces(array.get(3));
            } catch (StringIndexOutOfBoundsException e) {
                throw new TigerEmptyStringException("Event priority");
            }
            this.priority = Priority.getPriorityFromLetter(p);
            if (this.priority.equals(Priority.INVALID)) {
                throw new TigerInvalidArgumentException(p, "Event priority");
            }
        }
        // task description
        try {
            this.todo = stringUtils.removeBackAndFrontSpaces(array.get(1));
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Event description");
        }
        this.todo = stringUtils.capitaliseFirstLetter(this.todo);

        try {
            this.dateString = stringUtils.removeBackAndFrontSpaces(array.get(2));
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Event date");
        }
        this.date = new DateStringConverter().getDateFromString(this.dateString);
    }

    /**
     * Gets the task description.
     *
     * @return the task description.
     */

    public String getTodo() {
        return this.todo;
    }

    /**
     * Gets the task date.
     *
     * @return the task date.
     */

    public CustomDate getDate() {
        return this.date;
    }

    /**
     * Gets the priority of the task.
     *
     * @return the priority of the task.
     */

    public Priority getPriority() {
        return this.priority;
    }
}
