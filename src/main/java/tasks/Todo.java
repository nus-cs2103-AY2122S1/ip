package tasks;

import exceptions.EmptyDescException;

public class Todo extends Task {

    public Todo(String desc, Boolean isDone) {
        super(desc, isDone);
    }

    public Todo(String desc, Boolean isDone, String[] tags) throws EmptyDescException {
        super(desc, isDone, tags);
    }

    @Override
    public String toString() {
        String tagString = this.getTagString();
        return "[T]" + this.completionStatus() + desc
                + (tagString.equals("-") ? "" : " (Tags: " + tagString + ")");
    }
}
