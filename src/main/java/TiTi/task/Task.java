package TiTi.task;


public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void complete() {
        this.isDone = true;
    }

    /**
     * Determine if the task description contain the string in question.
     *
     * @param str string to search for
     * @return boolean value of whether the substring is found
     */
    public boolean checkString(String str) {
        return description.contains(str);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}