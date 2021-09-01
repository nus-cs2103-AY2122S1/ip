package duke.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.interfaces.PrintableMixin;
import duke.shared.StringHelpers;

/**
 * Encapsulates a generic task.
 */
public class Task implements PrintableMixin {

    public static final String TYPE_STRING = null;
    private String typeString = null;
    private TaskState state;
    private String name;


    static enum TaskState {
        NEW(" ", "0"), DONE("X", "1");

        private String representation;
        private String storedRepresentation;

        private TaskState(String representation, String storedRepresentation) {
            this.representation = representation;
            this.storedRepresentation = storedRepresentation;
        }

        public String getStoredRepresentation() {
            return this.storedRepresentation;
        }

        public String toString() {
            return StringHelpers.bracketWrap(representation);
        }
    }


    /**
     * Creates an incomplete task.
     *
     * @param name Name of task.
     */
    public Task(String name) {
        try {
            state = TaskState.NEW;
            this.setName(name);
            typeString = (String) this.getClass().getField("TYPE_STRING").get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            print("Reflection error");
        }
    }

    /**
     * Creates a task with given name and completion status.
     *
     * @param name   Name of task.
     * @param isDone Whether task is to be marked as complete upon creation.
     */
    public Task(String name, boolean isDone) {
        try {

            state = isDone ? TaskState.DONE : TaskState.NEW;
            this.setName(name);
            typeString = (String) this.getClass().getField("TYPE_STRING").get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            print("Reflection error");
        }
    }

    public String getTypeString() {
        return typeString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns whether task is completed.
     *
     * @return Whether task is completed.
     */
    public boolean isDone() {
        return state == TaskState.DONE;
    }

    /**
     * Marks task as done.
     */
    public void finish() {
        state = TaskState.DONE;
    }

    /**
     * Returns a list of string fields to be serialized and stored.
     *
     * @return A list of string fields representing the task.
     */
    public List<String> getStorageFields() {
        return new ArrayList<>(Arrays.asList(this.getTypeString(), this.state.getStoredRepresentation(), getName()));
    }


    public boolean match(String keyword) {
        return this.getName().contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s",
                getTypeString() == null ? "" : StringHelpers.bracketWrap(getTypeString()), state, getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
