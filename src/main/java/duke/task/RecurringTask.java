package duke.task;


public class RecurringTask extends Task {
    private String name;
    private boolean isDone;
    private String time;
    private int counter;

    /**
     * constructor for recurring task
     * @param name the name of the task
     * @param isDone whether the task is done
     * @param time the time of the task
     * @param counter the number of session the recurring class has
     */
    public RecurringTask(String name, boolean isDone, String time, int counter) {
        super();
        this.name = name;
        this.isDone = isDone;
        this.time = time;
        this.counter = counter;
    }

    /**
     * set time for recurring task
     * @param time the time of the task
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * set counter for recurring class
     * @param counter the number of the session of the task
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * set name for recurring class
     * @param name the name of the task
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get counter in recurring class
     * @return the number of session of the task
     */
    public int getCounter() {
        return counter;
    }

    /**
     * get name in recurring class
     * @return the name of the class
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * get info about whether the task is done or not
     * @return whether the class is done or not
     */
    @Override
    public boolean isDone() {
        return isDone;
    }

    /**
     * get string format of the task
     * @return the string format of the task
     */
    @Override
    public String toString() {
        return "R" + " " + name + " "
                + "/" + time + " " + "/" + counter + " " + isDone + '\n';
    }

    /**
     * get time in a certain format
     * @return the time of the task
     */
    public String getTime() {
        return this.time;
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
     * set the task as given status
     * @param done whether the class is done or not
     */
    @Override
    public void setDone(boolean done) {
        isDone = done;
    }

}

