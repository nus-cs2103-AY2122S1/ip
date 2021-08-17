public class Todo extends Task{

    Todo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toString() {
        if(this.isDone()) {
           return String.format("[T][X] %s", this.getName());
        } else {
            return String.format("[T][ ] %s", this.getName());
        }
    }
}
