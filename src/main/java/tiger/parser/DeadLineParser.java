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
 * The {@code DeadLineParser} parser takes in an input String and parses it, so that the {@code DeadLineAction} class
 * can access the class fields and understand user input.
 */


public class DeadLineParser extends Parser {

    private String todo = "";
    private CustomDate date;
    private String dateString = "";
    // if the user doesn't specify the priority, by default, we set it to MEDIUM.
    private Priority priority = Priority.MEDIUM;
    private String input;

    /**
     * Constructor of the {@code DeadLineParser class}
     *
     * @param input Input of the user.
     */

    public DeadLineParser(String input) {
        this.input = input;
    }

    @Override
    public void parse() throws TigerInvalidInputException {
        StringUtils stringUtils = new StringUtils();
        String regex = "^(deadline|dateline|Dateline|Deadline|DEADLINE|DATELINE)|(/by)|(/priority)";
        List<String> array =
                Arrays.asList(stringUtils.removeBackAndFrontSpaces(this.input).split(regex));
        // assert that the array is at size at least 3
        if (array.size() <= 1) {
            throw new TigerEmptyStringException("Deadline description");
        }
        if (array.size() <= 2) {
            throw new TigerEmptyStringException("Deadline date");
        }
        if (this.input.contains("/priority")) {
            // task priority
            if (array.size() < 4) {
                throw new TigerEmptyStringException("Deadline priority");
            }
            String p;
            try {
                p = stringUtils.removeBackAndFrontSpaces(array.get(3));
            } catch (StringIndexOutOfBoundsException e) {
                throw new TigerEmptyStringException("Deadline priority");
            }
            this.priority = Priority.getPriorityFromLetter(p);
            if (this.priority.equals(Priority.INVALID)) {
                throw new TigerInvalidArgumentException(p, "Deadline priority");
            }
        }
        // task description
        try {
            this.todo = stringUtils.removeBackAndFrontSpaces(array.get(1));
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Deadline description");
        }
        this.todo = stringUtils.capitaliseFirstLetter(this.todo);

        try {
            this.dateString = stringUtils.removeBackAndFrontSpaces(array.get(2));
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Deadline date");
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
