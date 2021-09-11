package duke.task;

import java.time.LocalDate;

public class RecurringTask extends Task {
    private String name;
    private boolean isDone;
    private String time;
    private int counter;

    public void setTime(String time) {
        this.time = time;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCounter() {
        return counter;
    }

    /**
     * constructor
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

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

    @Override
    public void setDone(boolean done) {
        isDone = done;
    }

}
