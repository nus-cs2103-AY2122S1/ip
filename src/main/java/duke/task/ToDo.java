package duke.task;

import duke.exception.InvalidInputException;
import duke.util.Ui;
import org.json.simple.JSONObject;

public class ToDo extends Task {

    /**
     * Default constructor for a ToDo.
     *
     * @param description The description of the ToDo.
     */
    protected ToDo(String description) {
        super(description);
        this.description = description;
    }

    /**
     * A constructor for a ToDo which includes a boolean isCompleted.
     *
     * @param description The description of the ToDo.
     * @param isCompleted Whether the ToDo has been completed.
     */
    protected ToDo(String description, boolean isCompleted) {
        super(description);
        this.isCompleted = isCompleted;
    }

    /**
     * Default factory method for a ToDo.
     *
     * @param description The description of the ToDo.
     * @return A new ToDo with the given description.
     */
    public static ToDo of(String description) {
        return new ToDo(description);
    }

    /**
     * A factory method for a ToDo which includes a boolean isCompleted.
     *
     * @param description The description of the ToDo.
     * @param isCompleted Whether the ToDo has been completed.
     * @return A new ToDo with the given description and boolean.
     */
    public static ToDo of(String description, boolean isCompleted) {
        return new ToDo(description, isCompleted);
    }


    /**
     * Converts the ToDo into a string.
     *
     * @return A String representation of the ToDo.
     */
    @Override
    public String toString() {
        return String.format("[T]%s %s ", isCompleted ? "[X]" : "[ ]", description);
    }

    /**
     * Converts the ToDo into a JSON Object.
     *
     * @return A JSON Object representation of the ToDo.
     */
    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("type", "todo");
        obj.put("description", description);
        obj.put("isCompleted", isCompleted);
        return obj;
    }
}
