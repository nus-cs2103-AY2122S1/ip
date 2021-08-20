package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Encapsulates a task taking place over a specified period of time.
 */
public class Event extends Task {
    public static final String TYPE_STRING = "E";
    private String dateRange;

    public Event(String name, String dateRange) {
        super(name);
        // typeString = TYPE_STRING;
        this.dateRange = dateRange;
    }

    public Event(String name, boolean isDone, String dateRange) {
        super(name, isDone);
        this.dateRange = dateRange;
    }

    public static Task generateFromString(String[] fields) {
        int isDoneInt = Integer.parseInt(fields[1]);
        boolean isDone = isDoneInt == 1;
        String name = fields[2];
        String dateRange = fields[3];
        return new Event(name, isDone, dateRange);
    }

    @Override
    public ArrayList<String> storageFields() {
        ArrayList<String> fields = super.storageFields();
        fields.add(dateRange);

        return fields;
    }

    @Override
    public String toString() {
        String base = super.toString();
        String result = String.format("%s (at: %s)", base, dateRange);
        return result;
    }
}
