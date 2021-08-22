public class Todo extends Task{

    public Todo(String task) {
        super(task);
    }

    @Override
    public String getDescription() {
        return "[T] " + super.getDescription();
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public int compareTo(Task o) {
        int val = super.compareTo(o);
        if (val == 0) {
            if (o instanceof Deadline || o instanceof Event) {
                return 1;
            } else {
                return this.getDescription().compareTo(o.getDescription());
            }
        } else {
            return val;
        }
    }
}
