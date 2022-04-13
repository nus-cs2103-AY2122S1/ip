package tiger.parser;

import java.util.Arrays;
import java.util.List;

import tiger.constants.Priority;
import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidArgumentException;
import tiger.exceptions.inputs.TigerInvalidInputException;
import tiger.utils.StringUtils;


/**
 * The {@code ToDoParser} parser takes in an input String and parses it, so that the {@code ToDoAction} class
 * can access the class fields and understand user input.
 */

public class ToDoParser extends Parser {

    private String todo = "";
    private Priority priority = Priority.MEDIUM;
    private String input;

    public ToDoParser(String input) {
        this.input = input;
    }

    @Override
    public void parse() throws TigerInvalidInputException {

        StringUtils stringUtils = new StringUtils();
        String regex = "^(todo|Todo|TODO)|(/priority)";
        List<String> array =
                Arrays.asList(stringUtils.removeBackAndFrontSpaces(this.input).split(regex));
        if (array.size() <= 1) {
            throw new TigerEmptyStringException("ToDo description");
        }
        if (this.input.contains("/priority")) {
            // task priority
            if (array.size() < 3) {
                throw new TigerEmptyStringException("ToDo priority");
            }
            String p;
            try {
                p = stringUtils.removeBackAndFrontSpaces(array.get(2));
            } catch (StringIndexOutOfBoundsException e) {
                throw new TigerEmptyStringException("ToDo priority");
            }
            this.priority = Priority.getPriorityFromLetter(p);
            if (this.priority.equals(Priority.INVALID)) {
                throw new TigerInvalidArgumentException(p, "ToDo priority");
            }
        }
        // task description
        try {
            this.todo = stringUtils.removeBackAndFrontSpaces(array.get(1));
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("ToDo description");
        }
        this.todo = stringUtils.capitaliseFirstLetter(this.todo);
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
     * Gets the priority of the task.
     *
     * @return the priority of the task.
     */

    public Priority getPriority() {
        return this.priority;
    }

}
