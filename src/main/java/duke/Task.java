package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;

    }

    Task(String description) {
        this(description, false);

    }

    /**
     * Returns true if this task contains a certain word.
     * @param w Word to be checked.
     * @return True or false
     */
    public boolean containWord(String w) {
        String[] words = description.split(" ");
        for (String word : words ) {
            if (word.equals(w)) {
                return true;
            }
        }
        return false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + getStatusIcon() + "]" + " " + description);
        return sb.toString();
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
