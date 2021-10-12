package Du;

public class Todo extends Task {

    private String type = "T";

    public Todo(String name, boolean done, TaskList tasklist) {
        super(name, done, tasklist);
    }

    /**
     * Records the To do in a certain format to save to the file
     * @return String which the To do is formatted in
     */
    @Override
    public String log_record() {
        int state;
        if (this.isDone) {
            state = 1;
        } else {
            state = 0;
        }
        return "T , " + state + " , " + name;
    }

    /**
     * toString method
     * @return String for printing
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatus() + "] " + this.name;
    }

}
