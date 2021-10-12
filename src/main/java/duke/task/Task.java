package duke.task;

/**
 * A class that represent general task
 */
public class Task {
    private String name;
    private boolean isDone;
    private int index;


    public Task() {

    };

    /**
     * constructor for Task
     * @param name
     * @param isDone
     * @param index
     */
    public Task(String name, boolean isDone, int index) {
        this.name = name;
        this.isDone = isDone;
        this.index = index;
    }

    /**
     * search whether keyword exist
     * @param keyword
     * @return
     */
    public boolean searchKeyword(String keyword) {
        String[] s = name.split(" ");
        for (String ss : s) {
            if (ss.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * set the status of task as the given status
     * @param done
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * get the name of the task
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * get the status of the task
     * @return
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * get the index of the class
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     * get the task in string format
     * @return
     */
    @Override
    public String toString() {
        return "Task{"
                + "name='" + this.name + '\''
                + ", isDone=" + isDone
                + ", index=" + index
                + '}' + '\n';
    }
}
