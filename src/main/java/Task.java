public abstract class Task {
    public abstract String showTask();
    public abstract String showType();

    private boolean isDone = false;

    public String checkDone() {
        return isDone ? "[X]" : "[ ]";
    }

    public void isDone() {
        isDone = true;
    }

    public String taskDone() {
        String taskDoneMessage = "   _____________________________________\n"
                + "     Nice! I've marked this task as done:\n"
                + "       " + this.showType() + this.checkDone() + " " + this.showTask() + "\n"
                + "   _____________________________________\n";
        return taskDoneMessage;
    }
}
