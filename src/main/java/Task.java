/**
 * Class of a task to be done.
 *
 */
public abstract class Task {
    protected String name;
    protected String checkBox;
    private static String notDone = "[ ]";
    private static String done = "[X]";


    /**
     * Constructor of task.
     *
     * @param s Name of the task.
     */
    public Task(String s) {
        this.name = s;
        this.checkBox = s = notDone;
    }

    /**
     * Marks when the task is done.
     */
    public void done() {
        this.checkBox = done;
    }

    @Override
    public String toString() {
        return this.checkBox + " " + this.name;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task t = (Task) obj;
            return t.name.equals(this.name);
        }
        return false;
    }
}