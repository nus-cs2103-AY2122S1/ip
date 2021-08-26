package duke.task;

import duke.date.Date;
import duke.exception.BadInputFormatException;
import duke.exception.InvalidDateException;
import org.json.simple.JSONObject;

/** Represents a Deadline object.*/
public class Deadline extends DatedTask {
    /**
     * Deadline constructor.
     *
     * @param description The Deadline's description.
     * @throws BadInputFormatException If the deadline is badly formatted.
     */
    Deadline(String description) throws BadInputFormatException, InvalidDateException {
        this(parseToDescription(description), parseToDate(description));
    }

    /**
     * Deadline constructor.
     *
     * @param description The Deadline's description.
     * @param by The date to complete the task by.
     */
    Deadline(String description, Date by) {
        super(description, by);
    }

    /**
     * Deadline constructor.
     *
     * @param description The Deadline's description.
     * @param by The date to complete the task by.
     * @param isDone The done status of the Deadline.
     */
    private Deadline(String description, Date by, boolean isDone) {
        super(description, by, isDone);
    }

    /**
     * Factory Deadline method.
     *
     * @param description The user's input.
     * @return A new Deadline object.
     */
    public static Deadline of(String description) throws BadInputFormatException, InvalidDateException {
        return new Deadline(description);
    }

    /**
     * Factory Deadline method.
     *
     * @param description The Deadline's description.
     * @param by The date to complete the Task by.
     * @param isDone The done status of the Task
     * @return A new Deadline object.
     */
    public static Deadline of(String description, Date by, boolean isDone) {
        return new Deadline(description, by, isDone);
    }

    /**
     * Helper method to parse the user's input into a Date object by splitting using regex delimited by the "/by"
     * keyword.
     *
     * @param description The user's input.
     * @return A Date object.
     * @throws BadInputFormatException If the given input is not well formatted.
     * @throws InvalidDateException If the date contained in the input does not adhere to the yyyy-MM-dd format.
     */
    private static Date parseToDate(String description) throws BadInputFormatException, InvalidDateException {
        String[] tokens = description.split(" /by ");
        if (tokens.length < 2) {
            throw new BadInputFormatException();
        }
        return Date.of(tokens[1]);
    }

    /**
     * Helper method to parse the user's input into the Deadline description by splitting using regex delimited by the
     * "/by" keyword.
     *
     * @param description The user's input.
     * @return The description of the Deadline as string.
     * @throws BadInputFormatException If the given input is not well formatted.
     */
    private static String parseToDescription(String description) throws BadInputFormatException {
        String[] tokens = description.split(" /by ");
        if (tokens.length < 2) {
            throw new BadInputFormatException();
        }
        return tokens[0];
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", isDone ? "[X]" : "[ ]", description, date);
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("type", "deadline");
        obj.put("description", description);
        obj.put("isDone", isDone);
        obj.put("date", date.toJsonString());
        return obj;
    }
}
