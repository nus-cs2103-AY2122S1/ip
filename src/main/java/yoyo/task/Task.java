package yoyo.task;

import yoyo.utility.Separator;

public abstract class Task {
    protected boolean isDone = false;
    protected String name;

    Task(String name) {
        this.name = name;
    }

    Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }


    public boolean containsString(String str) {
        return this.name.contains(str);
    }

    /**
     * Returns a status string indicating state of completion.
     *
     * @return A status string for the task.
     */
    public String printCompletionStatus() {
        if (isDone) {
            return "[X]";
        } else {
            return "[]";
        }
    }

    /**
     * Returns a status string indicating type of task.
     *
     * @return An indicator string for the type of task.
     */
    public abstract String printType();

    /**
     * Toggles completion status.
     */
    public void toggleDone() {
        this.isDone = true;
    }

    /**
     * Shows all statuses appended with name.
     *
     * @return A status string containing name.
     */
     public String showStatus() {
        return this.printType() + this.printCompletionStatus() + " " + this.name;
     }

    /**
     * Returns an info string for writing to file.
     *
     * @return A status string.
     */
     public String showStatusWrite() {
         return this.printType() + this.printCompletionStatus()
                + Separator.SEPARATOR + this.name;
     }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        @SuppressWarnings("unchecked")
        Task otherTask = (Task) o;
        return this.isDone == otherTask.isDone
                && this.name.equals(otherTask.name);
    }

}
