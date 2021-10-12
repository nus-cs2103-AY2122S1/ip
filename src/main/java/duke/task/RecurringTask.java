package duke.task;


public class RecurringTask extends Task {
    private String name;
    private boolean isDone;
    private String time;
    private int counter;

    /**
     * constructor for recurring task
     * @param name
     * @param isDone
     * @param time
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
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * set counter for recurring class
     * @param counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * set name for recurring class
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get counter in recurring class
     * @return
     */
    public int getCounter() {
        return counter;
    }

    /**
     * get name in recurring class
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * get info about whether the task is done or not
     * @return
     */
    @Override
    public boolean isDone() {
        return isDone;
    }

    /**
     * get string format of the task
     * @return
     */
    @Override
    public String toString() {
        return "R" + " " + name + " "
                + "/" + time + " " + "/" + counter + " " + isDone + '\n';
    }

    /**
     * get time in a certain format
     * @return
     */
    public String getTime() {
        return this.time;
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
     * set the task as given status
     * @param done
     */
    @Override
    public void setDone(boolean done) {
        isDone = done;
    }

}

