package duke.task;

/**
 * A class that represent general task
 */
public class Task{
    private String name;
    private boolean isDone;
    private int index;


    public Task(){

    };
    public Task(String name, boolean isDone, int index) {
        this.name = name;
        this.isDone = isDone;
        this.index = index;
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

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + this.name + '\'' +
                ", isDone=" + isDone +
                ", index=" + index +
                '}' + '\n';
    }
}