package duke.task;

import java.time.LocalDate;

public class RecurringTask extends Task {
    private String name;
    private boolean isDone;
    private String time;

    /**
     * constructor
     * @param name
     * @param isDone
     * @param time
     */
    public RecurringTask(String name, boolean isDone, String time) {
        super();
        this.name = name;
        this.isDone = isDone;
        this.time = time;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDone() {
        return isDone;
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

    @Override
    public String toString() {
        return "R" + " " + name + " "
                + "/" + time + " " + isDone + '\n';
    }
}
