package duke.domain;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import duke.shared.StringHelpers;

/**
 * Encapsulates a generic task.
 */
public class Task {
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

    public static final String TYPE_STRING = null;
    public String typeString = null;
    private TaskState state;
    public String name;

    /**
     * Creates an incomplete task.
     * @param name Name of task.
     */
    public Task(String name) {
        try {
            state = TaskState.NEW;
            this.name = name;
            typeString = (String) this.getClass().getField("TYPE_STRING").get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Reflection error");
        }
    }

    /**
     * Creates a task with given name and completion status.
     * @param name Name of task.
     * @param isDone Whether task is to be marked as complete upon creation.
     */
    public Task(String name, boolean isDone) {
        try {

            state = isDone ? TaskState.DONE : TaskState.NEW;
            this.name = name;
            typeString = (String) this.getClass().getField("TYPE_STRING").get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Reflection error");
        }
    }

    /**
     * Returns whether task is completed.
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
     * @return A list of string fields representing the task.
     */
    public List<String> storageFields() {
        return new ArrayList<>(Arrays.asList(this.typeString, this.state.getStoredRepresentation(), name));
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", typeString == null ? "" : StringHelpers.bracketWrap(typeString), state, name);
    }
}
