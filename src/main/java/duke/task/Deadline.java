package duke.task;

import java.time.LocalDate;

/**
 * A class that represent deadline task
 */
public class Deadline extends Task {
    private String name;
    private boolean isDone;
    private String time;

    /**
     * Constructor for deadline class
     * @param name the name of the task
     * @param isDone whether the task is done
     * @param time the time of the task
     */
    public Deadline(String name, boolean isDone, String time) {
        super();
        this.name = name;
        this.isDone = isDone;
        this.time = time;
    }

    /**
     * get name in recurring class
     * @return the name of the task
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * get the status of the task
     * @return whether the task is done
     */
    @Override
    public boolean isDone() {
        return isDone;
    }


    /**
     * get time in a certain format
     * @return the time of the task
     */
    public String getTime() {
        String[] s = this.time.split(" ");

        LocalDate date = LocalDate.parse(s[1]);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s[0] + " ");
        stringBuilder.append(date.getMonth() + " ");
        stringBuilder.append(date.getDayOfMonth() + " ");
        stringBuilder.append(date.getYear() + " ");
        for (int i = 2; i < s.length; i++) {
            stringBuilder.append(s[i] + " ");
        }

        return stringBuilder.toString();
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
     * set the status of task as the given status
     * @param done whether the task is done
     */
    @Override
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * get the task in string format
     * @return a string format of the task
     */
    @Override
    public String toString() {
        return "D" + " " + name + " "
                + "/" + time + " " + isDone + '\n';
    }


}
