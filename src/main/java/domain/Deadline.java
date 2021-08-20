package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a task with a deadline.
 */
public class Deadline extends Task {
    public static final String TYPE_STRING = "D";
    private String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        // typeString = TYPE_STRING;
        this.dueDate = dueDate;
    }

    public Deadline(String name, boolean isDone, String dueDate) {
        super(name, isDone);
        // typeString = TYPE_STRING;
        this.dueDate = dueDate;
    }

    public static Task generateFromString(String[] fields) {
        int isDoneInt = Integer.parseInt(fields[1]);
        boolean isDone = isDoneInt == 1;
        String name = fields[2];
        String dueDate = fields[3];
        return new Deadline(name, isDone, dueDate);
    }

    @Override
    public ArrayList<String> storageFields() {
        ArrayList<String> fields = super.storageFields();
        fields.add(dueDate);
        return fields;
    }

    @Override
    public String toString() {
        String base = super.toString();
        String result = String.format("%s (by: %s)", base, dueDate);
        return result;
    }
}
