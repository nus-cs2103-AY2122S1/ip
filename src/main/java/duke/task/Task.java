package duke.task;

/**
 * A class that represent general task
 */
public class Task {
    private String name;
    private boolean isDone;


    public Task() {

    };

    /**
     * constructor for Task
     * @param name the name of the task
     * @param isDone whether the class is done ot not
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * search whether keyword exist
     * @param keyword the keyword used to search current tasks
     * @return whether the task has the keyword
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
     * @param done whether the class is done ot not
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * get the name of the task
     * @return the name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * get the status of the task
     * @return whether the class is done ot not
     */
    public boolean isDone() {
        return isDone;
    }


    /**
     * get the task in string format
     * @return the string format of the task
     */
    @Override
    public String toString() {
        return "Task{"
                + "name='" + this.name + '\''
                + ", isDone=" + isDone
                + '}' + '\n';
    }
}
