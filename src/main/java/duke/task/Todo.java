package duke.task;


/**
 * a class that represent todo task
 */
public class Todo extends Task {
    private String name;
    private boolean isDone;

    /**
     * constructor for todo event
     * @param name the name of the task
     * @param isDone whether the class is done ot not
     */
    public Todo(String name, boolean isDone) {
        super();
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * search whether keyword exist
     * @param keyword the keyword used to search current tasks
     * @return whether the tasks contain the keyword
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
     * get task name
     * @return the name of the task
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * get the status of the task
     * @return whether the class is done ot not
     */
    @Override
    public boolean isDone() {
        return isDone;
    }

    /**
     * set the status of task as the given status
     * @param done whether the class is done ot not
     */
    @Override
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * get the task in string format
     * @return the string format of the task
     */
    @Override
    public String toString() {
        return "T" + " " + name + " " + isDone + '\n';
    }

}
