package duke.task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public String printStatusIcon() {
        return (isDone ? "1" : "0");
    }
    public String convertToString() {
        return printStatusIcon() + "|" + description;
    }

    /**
     * Checks if description of Task has given keyword.
     *
     * @param keyword Keyword to check if description of Task contains.
     * @return true if Task contains keyword, else false.
     */
    public boolean hasKeyword(String keyword) {
        String[] deconstructedDescription = description.split(" ");

        for (int i = 0; i < deconstructedDescription.length; i++) {
            if (deconstructedDescription[i].equals(keyword)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
