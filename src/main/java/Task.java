/**
 *
 */
public class Task {
    String task;

    /**
     *
     * @param task the name of task
     */
    public Task(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return this.task;
    }
}
