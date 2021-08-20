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

    public Task(String name) {
        try {
            state = TaskState.NEW;
            this.name = name;
            typeString = (String) this.getClass().getField("TYPE_STRING").get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Reflection error");
        }
    }

    public Task(String name, boolean isDone) {
        try {

            state = isDone ? TaskState.DONE : TaskState.NEW;
            this.name = name;
            typeString = (String) this.getClass().getField("TYPE_STRING").get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Reflection error");
        }
    }

    public void finish() {
        state = TaskState.DONE;
    }

    public List<String> storageFields() {
        return new ArrayList<>(Arrays.asList(this.typeString, this.state.getStoredRepresentation(), name));
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", typeString == null ? "" : StringHelpers.bracketWrap(typeString), state, name);
    }
}
