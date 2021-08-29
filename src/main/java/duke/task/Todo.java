package duke.task;

import java.time.LocalDate;

/**
 * a class that represent todo task
 */
public class Todo extends Task{
    private String name;
    private boolean isDone;

    public Todo(String name, boolean isDone) {
        super();
        this.name = name;
        this.isDone = isDone;
    }

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
    public String getName() {
        return name;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "T" + " " +  name  +" "+ isDone  + '\n';
    }

}