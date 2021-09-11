package tiger.parser;

import tiger.constants.Priority;
import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidArgumentException;
import tiger.exceptions.inputs.TigerInvalidInputException;
import tiger.utils.StringUtils;

/**
 * The {@code FindParser} parser takes in an input String and parses it, so that the {@code FindAction} class
 * can access the class fields and understand user input.
 */

public class PriorityParser extends Parser {

    private String letter = "";
    private String input;
    private Priority priority;

    /**
     * Constructor for the {@code FindParser} class.
     *
     * @param input String to be parsed.
     */

    public PriorityParser(String input) {
        this.input = input;
        this.priority = Priority.INVALID;
    }

    @Override
    public void parse() throws TigerInvalidInputException {
        StringUtils removeSpaces = new StringUtils();
        try {
            String[] array =
                    removeSpaces.removeBackAndFrontSpaces(input).split(" ");
            for (int i = 1; i < array.length; i++) {
                this.letter += (array[i] + " ");
            }
            this.letter = removeSpaces.removeBackAndFrontSpaces(this.letter);
            this.priority = Priority.getPriorityFromLetter(this.letter.toUpperCase());
            if (this.priority.equals(Priority.INVALID)) {
                throw new TigerInvalidArgumentException(this.letter, "Priority");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Priority letter");
        }
    }

    public Priority getPriorityToSearchFor() {
        return this.priority;
    }
}
