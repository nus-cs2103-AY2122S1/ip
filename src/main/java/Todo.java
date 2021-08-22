public class Todo extends Task {

    private String type = "T";

    public Todo(String name, boolean done, TaskList tasklist) {
        super(name, done, tasklist);
    }


    @Override
    public String log_record() {
        int state;
        if (this.done) {
            state = 1;
        } else {
            state = 0;
        }
        return "T , " + state + " , " + name;
    }


    @Override
    public String toString() {
        return "[T][" + this.getStatus() + "] " + this.name;
    }

}
