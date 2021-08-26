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
     * Produces a string containing task's status.
     *
     * @return a string containing task's status.
     */
    public String showStatus() {
        return this.printType() + this.printCompletionStatus() + " " + this.name;
    }

    /**
     * Produces a string containing task's status in write format.
     *
     * @return a string containing task's status in write format.
     */
    public String showStatusWrite() {
        return this.printType() + this.printCompletionStatus()
                + Separator.SEPARATOR + this.name;
    }

    /**
     * Defines how to compare two Task instances.
     *
     * @param o Object to be compared with.
     * @return A boolean.
     */
    @Override
    public boolean equals(Object o) {
        @SuppressWarnings("unchecked")
        Task otherTask = (Task) o;
        return this.isDone == otherTask.isDone
                && this.name.equals(otherTask.name);
    }

}
