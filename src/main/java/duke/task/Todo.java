package duke.task;


/**
 * a class that represent todo task
 */
public class Todo extends Task {
    private String name;
    private boolean isDone;

    /**
     * constructor
     * @param name
     * @param isDone
     */
    public Todo(String name, boolean isDone) {
        super();
        this.name = name;
        this.isDone = isDone;
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
        return "T" + " " + name + " " + isDone + '\n';
    }

}
