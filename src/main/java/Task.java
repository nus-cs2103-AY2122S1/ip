abstract public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskSymbol;
    protected final static String divider = ",";
    protected final static String doneStatus = "[X]";
    protected final static String notDoneStatus = "[ ]";

    public Task(String description, String taskSymbol) {
        this.description = description;
        this.isDone = false;
        this.taskSymbol = taskSymbol;
    }

    public static Task createTaskFromText(String text) throws IllegalArgumentException {
        String[] taskInformation = text.split(divider);
        String taskType = taskInformation[0].trim();
        String taskStatus = taskInformation[1].trim();

        switch(taskType) {
            case "[T]":
                Task todo = new Todo(taskInformation[2]);
                changeStatusFromText(todo, taskStatus);
                return todo;
            case "[D]":
                Task deadline = new Deadline(taskInformation[2], taskInformation[3]);
                changeStatusFromText(deadline, taskStatus);
                return deadline;
            case "[E]":
                Task event = new Event(taskInformation[2], taskInformation[3]);
                changeStatusFromText(event, taskStatus);
                return event;
            default:
                throw new IllegalArgumentException("Task symbol from text is not recognised.");
        }
    }

    public static Task changeStatusFromText(Task task, String symbol) {
        if(symbol.equals(doneStatus)) {
            task.markAsDone();
        }
        return task;
    }

    public String getTaskSymbol() {
        return taskSymbol;
    }

    public String getDivider() {
        return divider;
    }

    public String convertToText() {
        StringBuilder sb = new StringBuilder();
        return sb
            .append(getTaskSymbol())
            .append(divider)
            .append(getStatusIcon())
            .append(divider)
            .append(description)
            .toString();
    }

    /**
     * produces a graphical way of whether a task is done or not.
     *
     * @return a graphical way of whether a task is done or not
     */
    public String getStatusIcon() {
        return (isDone ? doneStatus : notDoneStatus);
    }

    /**
     * Mark a task as done and leaves a marked task unchanged.
     *
     * @return true if the task is initially not done and marked as done.
     */
    public boolean markAsDone() {
        boolean alreadyDone = isDone;
        isDone = true;
        return !alreadyDone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStatusIcon()).append(" ").append(this.description);
        return sb.toString();
    }
}
